package com.enifl33fi.lab1.api.service;

import com.enifl33fi.lab1.api.dto.request.file.ProductImportRequestDto;
import com.enifl33fi.lab1.api.exception.ImportFileException;
import com.enifl33fi.lab1.api.model.file.ImportHistory;
import com.enifl33fi.lab1.api.model.file.ImportStatus;
import com.enifl33fi.lab1.api.model.user.Role;
import com.enifl33fi.lab1.api.model.user.User;
import com.enifl33fi.lab1.api.repository.ImportHistoryRepository;
import com.enifl33fi.lab1.api.service.entity.ProductService;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImportService {
  private final ProductService productService;
  private final ObjectMapper objectMapper;
  private final ImportHistoryRepository importHistoryRepository;

  public void importProducts(InputStream fileInputStream, User user) {
    ImportHistory importHistory = new ImportHistory();
    importHistory.setUser(user);
    importHistory.setStatus(ImportStatus.FAILED);

    try {
      List<ProductImportRequestDto> productDtos =
          objectMapper.readValue(fileInputStream, new TypeReference<>() {});

      productService.saveAll(productDtos);

      importHistory.setImportedCount(productDtos.size());
      importHistory.setStatus(ImportStatus.SUCCESS);
      importHistoryRepository.saveAndFlush(importHistory);

    } catch (StreamReadException e) {
      importHistoryRepository.saveAndFlush(importHistory);
      throw new ImportFileException();
    } catch (DatabindException e) {
      importHistoryRepository.saveAndFlush(importHistory);
      throw new ImportFileException();
    } catch (IOException e) {
      importHistoryRepository.saveAndFlush(importHistory);
      throw new ImportFileException();
    } catch (Exception e) {
      e.printStackTrace();
      importHistoryRepository.saveAndFlush(importHistory);
      throw e;
    }
  }

  public List<ImportHistory> getImportHistory(User user) {
    if (user.getRole() == Role.ADMIN) {
      return importHistoryRepository.findAll();
    } else {
      return importHistoryRepository.findAllByUser(user);
    }
  }
}
