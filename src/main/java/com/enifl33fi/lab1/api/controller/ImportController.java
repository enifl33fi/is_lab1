package com.enifl33fi.lab1.api.controller;

import com.enifl33fi.lab1.api.dto.response.ImportHistoryResponseDto;
import com.enifl33fi.lab1.api.mapper.ImportHistoryMapper;
import com.enifl33fi.lab1.api.model.file.ImportHistory;
import com.enifl33fi.lab1.api.model.user.User;
import com.enifl33fi.lab1.api.service.ImportService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/import")
@RequiredArgsConstructor
public class ImportController {
  private final ImportService importService;
  private final ImportHistoryMapper importHistoryMapper;

  @GetMapping
  @ResponseBody
  public ResponseEntity<List<ImportHistoryResponseDto>> getImportHistory(
      @AuthenticationPrincipal User user) {
    return ResponseEntity.ok(
        importService.getImportHistory(user).stream().map(importHistoryMapper::toDto).toList());
  }

  @PostMapping(consumes = "multipart/form-data")
  @ResponseBody
  public ResponseEntity<ImportHistory> getImportHistory(
      @RequestPart("file") MultipartFile file, @AuthenticationPrincipal User user) {
    importService.importProducts(file, user);
    return ResponseEntity.ok().build();
  }
}
