package com.enifl33fi.lab1.api.mapper;

import com.enifl33fi.lab1.api.dto.response.ImportHistoryResponseDto;
import com.enifl33fi.lab1.api.model.file.ImportHistory;
import com.enifl33fi.lab1.api.model.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ImportHistoryMapper {

  @Mapping(source = "user", target = "user", qualifiedByName = "mapUser")
  ImportHistoryResponseDto toDto(ImportHistory entity);

  @Named("mapUser")
  static String mapUser(User user) {
    return user != null ? user.getUsername() : null;
  }
}
