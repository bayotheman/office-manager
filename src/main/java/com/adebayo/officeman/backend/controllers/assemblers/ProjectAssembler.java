package com.adebayo.officeman.backend.controllers.assemblers;

import com.adebayo.officeman.backend.controllers.ProjectController;
import com.adebayo.officeman.backend.entity.Project;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProjectAssembler implements RepresentationModelAssembler<Project, EntityModel<Project>> {
	@Override
	@NonNull
	public EntityModel<Project> toModel(@NonNull Project project) {
		return EntityModel.of(project,
				linkTo(methodOn(ProjectController.class).one(project.getId())).withSelfRel(),
				linkTo(methodOn(ProjectController.class).all()).withRel("projects"));
	}

}
