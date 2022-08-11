package com.adebayo.officeman.backend.service;

import com.adebayo.officeman.backend.entity.Employee;
import com.adebayo.officeman.backend.entity.Project;
import com.adebayo.officeman.backend.entity.Todo;
import com.adebayo.officeman.backend.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ProjectService {

	private final ProjectRepository projectRepository;

	public ProjectService(ProjectRepository projectRepository){
		this.projectRepository = projectRepository;
	}

	public void createProject(Project project){
		projectRepository.save(project);
	}

	public void deleteProject(Project project){
		projectRepository.delete(project);
	}

	public void deleteAll(){
		projectRepository.deleteAll();
	}

	public void deleteAll(Set<Project> projects){
		projectRepository.deleteAll(projects);
	}

	public long count(){
		return projectRepository.count();
	}

	public Set<Project> findAll(){

		if(projectRepository.findAll()== null){
			return new HashSet<>();
		}
		return new HashSet<>(projectRepository.findAll());
	}

	public Set<Project> findAll(String filterText){
		if(filterText == null || filterText.isEmpty()){
			return findAll();
		}else{
			return search(filterText);
		}
	}

	public Set<Project> search(String filterText){
		return projectRepository.search(filterText);
	}

	public Project find(Long id){
		return projectRepository.findProjectById(id);
	}

	public Optional<Project> findById(Long id){
		return projectRepository.findById(id);
	}

	public Project save(Project project) {
		projectRepository.save(project);
		return project;
	}

	public void saveProjects(Set<Project> projects) {
		projectRepository.saveAll(projects);
	}

	public Set<Project> findAllUncompletedProjects(){
		return projectRepository.findAllUncompletedProjectsOrderedByPriorityValue();
	}

	/**
	 * checks if a project is complete by checking its associates tasks
	 * @param project
	 * @return true if project is complete and false otherwise
	 */
	public Boolean isProjectComplete(Project project){
		Project currentProject = projectRepository.findProjectById(project.getId());
		int completedTodoCount = 0;
		Set<Todo> todos = currentProject.getTodoList();
		if(todos.size() == 0){
			return false;
		}
		for (Todo t: todos){
				if(t.isDone()){
					completedTodoCount++;
				}
			}
		return completedTodoCount == todos.size();
	}

	/**
	 * updates project status to either complete or incomplete
	 * @param project - project to be searched for
	 */
	public void updateProjectStatus(Project project){
		Project currentProject = find(project.getId());
		if(currentProject == null){
			return;
		}
		currentProject.setCompleted(isProjectComplete(currentProject));
		projectRepository.save(currentProject);

	}

	public Set<Project> findProjectsBySupervisor(Employee employee) {
		if(employee == null){
			return new HashSet<>();
		}
		Set<Project> projects = projectRepository.findAllBySupervisorId(employee.getId());
		if(projects.isEmpty()){
			return new HashSet<>();
		}
		return projects;

	}
}
