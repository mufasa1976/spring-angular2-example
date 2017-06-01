package io.github.mufasa1976.spring.angular2.example.assembler;

import org.springframework.stereotype.Component;

import io.github.mufasa1976.spring.angular2.example.controller.HeroController;
import io.github.mufasa1976.spring.angular2.example.model.HeroEntity;
import io.github.mufasa1976.spring.angular2.example.resource.HeroResource;

@Component
public class HeroResourceAssembler extends AbstractReferableResourceAssemblerSupport<HeroEntity, HeroResource> {

  public HeroResourceAssembler() {
    super(HeroController.class, HeroResource.class);
  }

  @Override
  HeroResource toResourceWithoutSelfLink(HeroEntity entity) {
    return HeroResource.builder()
        .reference(entity.getReference())
        .version(entity.getVersion())
        .createdBy(entity.getCreatedBy())
        .createdAt(entity.getCreatedAt())
        .lastModifiedBy(entity.getLastModifiedBy())
        .lastModifiedAt(entity.getLastModifiedAt())
        .name(entity.getName())
        .build();
  }
}
