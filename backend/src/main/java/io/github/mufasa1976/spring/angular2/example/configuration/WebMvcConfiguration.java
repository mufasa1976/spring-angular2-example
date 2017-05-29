package io.github.mufasa1976.spring.angular2.example.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebMvc
@Slf4j
class WebMvcConfiguration extends WebMvcConfigurerAdapter {

  private final static String FRONTEND_APPLICATION = "spring-angular2-example-frontend";

  private final static Map<String, String> RESOURCE_MAPPINGS;

  static {
    RESOURCE_MAPPINGS = new HashMap<>();
    RESOURCE_MAPPINGS.put("/*.bundle.js", "/");
    RESOURCE_MAPPINGS.put("/*.bundle.js.map", "/");
    RESOURCE_MAPPINGS.put("/assets/**", "/assets/");
  }

  @Value("${info.build.version}")
  private String version;

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.setOrder(0);
    RESOURCE_MAPPINGS.forEach((path, suffix) ->
            registry.addResourceHandler(path).addResourceLocations(prefixWithFrontendClasspath(suffix)));
  }

  private String prefixWithFrontendClasspath(String suffix) {
    return String.format("classpath:/META-INF/resources/webjars/%s/%s%s", FRONTEND_APPLICATION, version, suffix);
  }

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/**").setViewName("app");
  }

}
