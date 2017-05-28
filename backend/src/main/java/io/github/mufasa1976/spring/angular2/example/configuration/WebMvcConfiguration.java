package io.github.mufasa1976.spring.angular2.example.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
class WebMvcConfiguration extends WebMvcConfigurerAdapter {

  private final static String FRONTEND_APPLICATION = "spring-angular2-example-frontend";

  @Value("${info.build.version}")
  private String version;

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.setOrder(2);
    String frontendApplicationResource = prefixWithFrontendClasspath("");
    log.info("Frontend-Application: {}", frontendApplicationResource);
    registry.addResourceHandler("/**").addResourceLocations(frontendApplicationResource);
    super.addResourceHandlers(registry);
  }

  private String prefixWithFrontendClasspath(String file) {
    return String.format("classpath:/META-INF/resources/webjars/%s/%s/%s", FRONTEND_APPLICATION, version, file);
  }

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.setOrder(1);
    addAngularRoutes(registry);
    super.addViewControllers(registry);
  }

  private void addAngularRoutes(ViewControllerRegistry registry) {
    addAngularRoute("/", registry);
    addAngularRoute("/heroes", registry);
    addAngularRoute("/heroes/**", registry);
    addAngularRoute("/dashboard", registry);
  }

  private void addAngularRoute(String route, ViewControllerRegistry registry) {
    registry.addViewController(route).setViewName("app");
  }

}
