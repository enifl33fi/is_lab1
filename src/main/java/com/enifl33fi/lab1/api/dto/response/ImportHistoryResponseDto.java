package com.enifl33fi.lab1.api.dto.response;

import com.enifl33fi.lab1.api.model.file.ImportStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImportHistoryResponseDto {
  Integer id;
  ImportStatus status;
  Integer importedCount;
  String user;
  String fileUrl;
}
