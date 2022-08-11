package com.adebayo.officeman.backend.controllers;

import com.adebayo.officeman.backend.controllers.assemblers.ProjectAssembler;
import com.adebayo.officeman.backend.controllers.exceptions.ProjectNotFoundException;
import com.adebayo.officeman.backend.entity.Project;
import com.adebayo.officeman.backend.service.ProjectService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class ProjectController {
	private final ProjectService projectService;
	private final ProjectAssembler projectAssembler;

	public ProjectController(ProjectService projectService, ProjectAssembler projectAssembler) {
		this.projectService = projectService;
		this.projectAssembler = projectAssembler;
	}

	@GetMapping("/projects")
	public CollectionModel<EntityModel<Project>> all(){
		Set<EntityModel<Project>> projects = projectService.findAll().stream()
				.map(projectAssembler::toModel)
				.collect(Collectors.toSet());
		return CollectionModel.of(projects,
				linkTo(methodOn(ProjectController.class).all()).withSelfRel());
	}

	@GetMapping("/projects/{id}")
	public EntityModel<Project> one(@PathVariable Long id){
		Project project = projectService.findById(id)
				.orElseThrow(() -> new ProjectNotFoundException(id));

		return projectAssembler.toModel(project);
	}

	@PostMapping("/projects")
	ResponseEntity<?> createProject(@RequestBody Project project){
		EntityModel<Project> entityModel = projectAssembler.toModel(projectService.save(project));
		return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
				.body(entityModel);
	}
}
