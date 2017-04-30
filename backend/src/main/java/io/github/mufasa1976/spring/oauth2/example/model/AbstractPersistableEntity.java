package io.github.mufasa1976.spring.oauth2.example.model;

import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Setter(AccessLevel.NONE)
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractPersistableEntity extends AbstractPersistable<Long> implements Auditable {

  @Version
  private int version;

  @CreatedBy
  @Column(updatable = false, nullable = false)
  private String createdBy;

  @CreatedDate
  @Column(updatable = false, nullable = false)
  private LocalDateTime createdAt;

  @LastModifiedBy
  @Column(insertable = false)
  private String lastModifiedBy;

  @LastModifiedDate
  @Column(insertable = false)
  private LocalDateTime lastModifiedAt;

}
