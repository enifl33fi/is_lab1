package com.enifl33fi.lab1.api.dto.event;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EntityChangedEvent {
  private final String entityType;
}
