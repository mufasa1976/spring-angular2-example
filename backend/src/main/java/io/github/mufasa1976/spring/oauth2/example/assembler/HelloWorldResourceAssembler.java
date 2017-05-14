package io.github.mufasa1976.spring.oauth2.example.assembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.springframework.hateoas.mvc.IdentifiableResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import io.github.mufasa1976.spring.oauth2.example.controller.impl.HelloWorldControllerImpl;
import io.github.mufasa1976.spring.oauth2.example.model.HelloWorldEntity;
import io.github.mufasa1976.spring.oauth2.example.resource.HelloWorldResource;

@Component
public class HelloWorldResourceAssembler extends IdentifiableResourceAssemblerSupport<HelloWorldEntity, HelloWorldResource> {

  public HelloWorldResourceAssembler() {
    super(HelloWorldControllerImpl.class, HelloWorldResource.class);
  }

  @Override
  public HelloWorldResource toResource(HelloWorldEntity entity) {
    HelloWorldResource resource = HelloWorldResource.builder()
        .version(entity.getVersion())
        .createdBy(entity.getCreatedBy())
        .createdAt(entity.getCreatedAt())
        .lastModifiedBy(entity.getLastModifiedBy())
        .lastModifiedAt(entity.getLastModifiedAt())
        .value(entity.getValue())
        .build();
    resource.add(linkTo(HelloWorldControllerImpl.class, entity.getId()).slash(entity.getId()).withSelfRel());
    return resource;
  }
}
