package com.enifl33fi.lab1.api.config;

import com.enifl33fi.lab1.api.model.utils.OwnedEntityHandler;
import com.enifl33fi.lab1.api.model.utils.OwnedEntityListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EntityListenerConfig {
  @Autowired
  public void configureListener(OwnedEntityHandler handler) {
    OwnedEntityListener.setHandler(handler);
  }
}
