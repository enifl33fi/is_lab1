package com.enifl33fi.lab1.api.service;

import com.enifl33fi.lab1.api.dto.request.file.ProductImportRequestDto;
import com.enifl33fi.lab1.api.exception.ImportFileException;
import com.enifl33fi.lab1.api.exception.XAResourceIsNotReadyException;
import com.enifl33fi.lab1.api.model.file.ImportHistory;
import com.enifl33fi.lab1.api.model.file.ImportStatus;
import com.enifl33fi.lab1.api.model.user.Role;
import com.enifl33fi.lab1.api.model.user.User;
import com.enifl33fi.lab1.api.repository.ImportHistoryRepository;
import com.enifl33fi.lab1.api.service.entity.ProductService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.minio.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Log4j2
public class ImportService {
  private final ProductService productService;
  private final ObjectMapper objectMapper;
  private final ImportHistoryRepository importHistoryRepository;
  private final MinioClient minioClient;
  private final PlatformTransactionManager transactionManager;

  @Value("${minio.bucket}")
  private String bucketName;

  public void importProducts(MultipartFile file, User user) {
    ImportHistory importHistory = new ImportHistory();
    importHistory.setUser(user);
    importHistory.setStatus(ImportStatus.FAILED);

    try {
      List<ProductImportRequestDto> productDtos =
          objectMapper.readValue(file.getInputStream(), new TypeReference<>() {});

      String fileUrl = commit(productDtos, file, user.getUsername());

      importHistory.setFileUrl(fileUrl);
      importHistory.setImportedCount(productDtos.size());
      importHistory.setStatus(ImportStatus.SUCCESS);
      importHistoryRepository.saveAndFlush(importHistory);

    } catch (IOException e) {
      importHistoryRepository.saveAndFlush(importHistory);
      throw new ImportFileException();
    } catch (Exception e) {
      e.printStackTrace();
      importHistoryRepository.saveAndFlush(importHistory);
    }
  }

  public String commit(List<ProductImportRequestDto> products, MultipartFile file, String username)
      throws Exception {
    DefaultTransactionDefinition def = new DefaultTransactionDefinition();
    def.setName("fileImportTransaction");
    def.setIsolationLevel(DefaultTransactionDefinition.ISOLATION_SERIALIZABLE);
    def.setPropagationBehavior(DefaultTransactionDefinition.PROPAGATION_REQUIRED);

    TransactionStatus importTransaction = transactionManager.getTransaction(def);
    String fileName = String.format("%s_%d.json", username, System.currentTimeMillis());

    boolean isDbReady = true;
    boolean isMinioReady = true;

    try {
      isDbReady = checkDatabaseReady();
      isMinioReady = checkMinioReady();

      String notReadyResource = getNotReadyResource(isDbReady, isMinioReady);
      if (notReadyResource != null) {
        throw new XAResourceIsNotReadyException(notReadyResource);
      }

      InputStream fileInputStream = file.getInputStream();
      long fileSize = file.getSize();

      try {
        minioClient.putObject(
            PutObjectArgs.builder().bucket(bucketName).object(fileName).stream(
                    fileInputStream, fileSize, -1)
                .build());
      } catch (ConnectException e) {
        throw new XAResourceIsNotReadyException("S3");
      }

      try {
        productService.saveAll(products);
      } catch (JpaSystemException | DataAccessResourceFailureException e) {
        throw new XAResourceIsNotReadyException("DB");
      }

      transactionManager.commit(importTransaction);

      return minioClient.getPresignedObjectUrl(
          GetPresignedObjectUrlArgs.builder()
              .method(io.minio.http.Method.GET)
              .bucket(bucketName)
              .object(fileName)
              .build());
    } catch (XAResourceIsNotReadyException e) {
      switch (e.resource) {
        case "S3":
          rollbackDb(importTransaction);
          break;
        case "Both":
          break;
        case "DB":
        default:
          rollbackDb(importTransaction);
          rollbackMinio(fileName);
          break;
      }

      throw e;
    } catch (Exception e) {
      rollbackDb(importTransaction);
      rollbackMinio(fileName);

      throw e;
    }
  }

  public boolean checkDatabaseReady() {
    try {
      return productService.getAllEditableEntitiesIds() != null;
    } catch (Exception e) {
      log.error("DB ready check failed", e);
      return false;
    }
  }

  private boolean checkMinioReady() {
    try {
      minioClient.statObject(StatObjectArgs.builder().bucket(bucketName).object("test").build());
      return true;
    } catch (Exception e) {
      log.error("MinIO ready check failed", e);
      return false;
    }
  }

  public List<ImportHistory> getImportHistory(User user) {
    if (user.getRole() == Role.ADMIN) {
      return importHistoryRepository.findAll();
    } else {
      return importHistoryRepository.findAllByUser(user);
    }
  }

  private String getNotReadyResource(boolean isDbReady, boolean isMinioReady) {
    if (!isDbReady && !isMinioReady) {
      return "Both";
    } else if (!isDbReady) {
      return "DB";
    } else if (!isMinioReady) {
      return "S3";
    }

    return null;
  }

  private void rollbackMinio(String fileName) throws Exception {
    minioClient.removeObject(
        RemoveObjectArgs.builder().bucket(bucketName).object(fileName).build());
  }

  private void rollbackDb(TransactionStatus tx) {
    try {
      transactionManager.rollback(tx);
    } catch (Exception e) {
    }
  }
}
