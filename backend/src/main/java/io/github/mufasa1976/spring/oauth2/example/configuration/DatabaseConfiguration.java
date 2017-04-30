package io.github.mufasa1976.spring.oauth2.example.configuration;

import io.github.mufasa1976.spring.oauth2.example.model.AbstractPersistableEntity;
import io.github.mufasa1976.spring.oauth2.example.model.HelloWorldEntity;
import io.github.mufasa1976.spring.oauth2.example.repository.BaseRepository;
import io.github.mufasa1976.spring.oauth2.example.repository.HelloWorldRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
@EnableJpaRepositories(basePackageClasses = BaseRepository.class)
@EntityScan(basePackageClasses = AbstractPersistableEntity.class)
@EnableJpaAuditing(auditorAwareRef = DatabaseConfiguration.AUDITOR_AWARE_BEAN, modifyOnCreate = false)
@EnableTransactionManagement
public class DatabaseConfiguration {

  final static String AUDITOR_AWARE_BEAN = "auditorAware";

  private final static String SYSTEM_USER = "system";

  @Bean(AUDITOR_AWARE_BEAN)
  public AuditorAware<String> auditorAware() {
    return this::getCurrentUser;
  }

  private String getCurrentUser() {
    SecurityContext securityContext = SecurityContextHolder.getContext();
    if (securityContext == null) {
      return SYSTEM_USER;
    }

    Authentication authentication = securityContext.getAuthentication();
    if (authentication == null) {
      return SYSTEM_USER;
    }

    return StringUtils.isNotBlank(authentication.getName()) ? authentication.getName() : "system";
  }
}
