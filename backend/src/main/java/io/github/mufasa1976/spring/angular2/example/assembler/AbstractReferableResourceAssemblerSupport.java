package io.github.mufasa1976.spring.angular2.example.assembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import io.github.mufasa1976.spring.angular2.example.model.ReferableEntity;
import org.springframework.hateoas.mvc.IdentifiableResourceAssemblerSupport;

import io.github.mufasa1976.spring.angular2.example.model.AbstractPersistableEntity;
import io.github.mufasa1976.spring.angular2.example.resource.AbstractResource;

abstract class AbstractReferableResourceAssemblerSupport<ENTITY extends AbstractPersistableEntity, RESOURCE extends AbstractResource> extends IdentifiableResourceAssemblerSupport<ENTITY, RESOURCE> {

  private final Class<?> controllerClass;

  AbstractReferableResourceAssemblerSupport(final Class<?> controllerClass, final Class<RESOURCE> resourceClass) {
    super(controllerClass, resourceClass);
    this.controllerClass = controllerClass;
  }

  @Override
  public final RESOURCE toResource(ENTITY entity) {
    return addSelfRel(toResourceWithoutSelfLink(entity), entity);
  }

  private RESOURCE addSelfRel(RESOURCE resource, ReferableEntity<String> referableEntity) {
    resource.add(linkTo(controllerClass, referableEntity.getReference()).slash(referableEntity.getReference()).withSelfRel());
    return resource;
  }

  abstract RESOURCE toResourceWithoutSelfLink(ENTITY entity);
}
