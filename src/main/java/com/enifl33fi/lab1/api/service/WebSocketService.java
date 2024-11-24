package com.enifl33fi.lab1.api.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WebSocketService {
  private final SimpMessagingTemplate messagingTemplate;

  public void notifyEntitiesChanged(String entityType, List<?> entities) {
    messagingTemplate.convertAndSend("/topic/" + entityType, entities);
  }
}
