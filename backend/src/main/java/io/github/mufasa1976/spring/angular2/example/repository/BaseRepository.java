package io.github.mufasa1976.spring.angular2.example.repository;

import io.github.mufasa1976.spring.angular2.example.model.AbstractPersistableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<T extends AbstractPersistableEntity> extends JpaRepository<T, Long>, QueryDslPredicateExecutor<T> {}
