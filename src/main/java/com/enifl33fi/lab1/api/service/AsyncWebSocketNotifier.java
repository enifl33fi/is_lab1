package com.enifl33fi.lab1.api.service;

import com.enifl33fi.lab1.api.dto.event.AdminRequestChangedEvent;
import com.enifl33fi.lab1.api.dto.event.EntityChangedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
@RequiredArgsConstructor
public class AsyncWebSocketNotifier {
  private final WebSocketService webSocketService;

  @Async
  @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
  public void handleEntityChanged(EntityChangedEvent event) {
    webSocketService.notifyEntitiesChanged(event.getEntityType());
  }

  @Async
  @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
  public void handleAdminRequestChanged(AdminRequestChangedEvent event) {
    webSocketService.notifyAdminRequestsChanged();
  }
}
