package com.enifl33fi.lab1.api.repository;

import com.enifl33fi.lab1.api.model.file.ImportHistory;
import com.enifl33fi.lab1.api.model.user.User;
import com.enifl33fi.lab1.api.repository.entity.OwnedEntityRepository;
import java.util.List;

public interface ImportHistoryRepository extends OwnedEntityRepository<ImportHistory> {
  List<ImportHistory> findAllByUser(User user);
}
