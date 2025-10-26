package com.enifl33fi.lab1.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WebSocketService {
  private final SimpMessagingTemplate messagingTemplate;

  public void notifyEntitiesChanged(String entityType) {
    messagingTemplate.convertAndSend(
        "/topic/" + entityType, "An update about entity: " + entityType);
  }

  public void notifyAdminRequestsChanged() {
    messagingTemplate.convertAndSend("/topic/adminRequests", "An update admin about requests");
  }
}
