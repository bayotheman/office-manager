package com.adebayo.officeman.backend.controllers.exceptions;

public class ProjectNotFoundException extends RuntimeException {
	public ProjectNotFoundException(Long id){
		super("Could not find employee " + id);
	}
}
