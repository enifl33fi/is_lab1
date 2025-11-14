package com.enifl33fi.lab1.api.model.file;

import com.enifl33fi.lab1.api.model.utils.OwnedEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "import_history")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ImportHistory extends OwnedEntity {
  @Column(nullable = false)
  @Enumerated(value = EnumType.STRING)
  private ImportStatus status;

  @Column(name = "imported_count")
  private Integer importedCount;
}
