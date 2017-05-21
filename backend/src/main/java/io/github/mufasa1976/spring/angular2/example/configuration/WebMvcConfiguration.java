package io.github.mufasa1976.spring.angular2.example.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@Slf4j
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {

  private final static String FRONTEND_APPLICATION = "spring-angular2-example-frontend";

  @Value("${info.build.version}")
  private String version;

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.setOrder(2);
    String frontendApplicationResource = String.format("classpath:/META-INF/resources/webjars/%s/%s/", FRONTEND_APPLICATION, version);
    log.info("Frontend-Application: {}", frontendApplicationResource);
    registry.addResourceHandler("/**").addResourceLocations(frontendApplicationResource);
    super.addResourceHandlers(registry);
  }

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.setOrder(1);
    registry.addRedirectViewController("/", "/index.html");
    registry.addViewController("/index.html").setViewName("index");
    super.addViewControllers(registry);
  }

}
