package io.github.mufasa1976.spring.angular2.example.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.hateoas.Identifiable;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

@Data
@EqualsAndHashCode(callSuper = false)
@Setter(AccessLevel.NONE)
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractPersistableEntity extends AbstractPersistable<Long> implements Auditable, Identifiable<Long> {

  @Version
  @Setter(AccessLevel.PUBLIC)
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
