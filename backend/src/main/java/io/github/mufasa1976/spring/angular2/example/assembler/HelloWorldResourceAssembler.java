package io.github.mufasa1976.spring.angular2.example.assembler;

import org.springframework.stereotype.Component;

import io.github.mufasa1976.spring.angular2.example.controller.HelloWorldController;
import io.github.mufasa1976.spring.angular2.example.model.HelloWorldEntity;
import io.github.mufasa1976.spring.angular2.example.resource.HelloWorldResource;

@Component
public class HelloWorldResourceAssembler extends AbstractReferableResourceAssemblerSupport<HelloWorldEntity, HelloWorldResource> {

  public HelloWorldResourceAssembler() {
    super(HelloWorldController.class, HelloWorldResource.class);
  }

  @Override
  HelloWorldResource toResourceWithoutSelfLink(HelloWorldEntity entity) {
    return HelloWorldResource.builder()
        .reference(entity.getReference())
        .version(entity.getVersion())
        .createdBy(entity.getCreatedBy())
        .createdAt(entity.getCreatedAt())
        .lastModifiedBy(entity.getLastModifiedBy())
        .lastModifiedAt(entity.getLastModifiedAt())
        .value(entity.getValue())
        .build();
  }
}
