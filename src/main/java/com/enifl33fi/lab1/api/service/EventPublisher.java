package com.enifl33fi.lab1.api.service;

import com.enifl33fi.lab1.api.dto.event.AdminRequestChangedEvent;
import com.enifl33fi.lab1.api.dto.event.EntityChangedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventPublisher {
  private final ApplicationEventPublisher eventPublisher;

  public void publishEntityChangedEvent(String entityType) {
    eventPublisher.publishEvent(new EntityChangedEvent(entityType));
  }

  public void publishAdminRequestChangedEvent() {
    eventPublisher.publishEvent(new AdminRequestChangedEvent());
  }
}
