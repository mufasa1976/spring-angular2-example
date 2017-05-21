package io.github.mufasa1976.spring.angular2.example.controller.impl;

import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.github.mufasa1976.spring.angular2.example.controller.HelloWorldController;
import io.github.mufasa1976.spring.angular2.example.resource.HelloWorldResource;
import io.github.mufasa1976.spring.angular2.example.service.HelloWorldService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class HelloWorldControllerImpl implements HelloWorldController {

  private final HelloWorldService helloWorldService;

  @Override
  @Secured({ "ROLE_GROUP1", "ROLE_GROUP2", "ROLE_GROUP3" })
  public PagedResources<HelloWorldResource> findAll(Pageable pageable) {
    return helloWorldService.readAll(pageable, Optional.empty());
  }

  @Override
  @Secured({ "ROLE_GROUP1", "ROLE_GROUP2", "ROLE_GROUP3" })
  public ResponseEntity<HelloWorldResource> read(@PathVariable Long id) {
    return helloWorldService.read(id)
        .map(ResponseEntity::ok)
        .orElseGet(ResponseEntity.notFound()::build);
  }

  @Override
  @Secured("ROLE_GROUP1")
  public ResponseEntity<HelloWorldResource> update(@PathVariable Long id, @RequestBody HelloWorldResource resource) {
    return helloWorldService.update(id, resource)
        .map(ResponseEntity::ok)
        .orElseGet(ResponseEntity.notFound()::build);
  }

  @Override
  @Secured("ROLE_GROUP3")
  public HelloWorldResource create(@RequestBody HelloWorldResource resource) {
    return helloWorldService.create(resource);
  }

  @Override
  @Secured("ROLE_GROUP2")
  public void delete(@PathVariable Long id) {
    helloWorldService.delete(id);
  }

}
