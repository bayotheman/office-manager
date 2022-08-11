package com.adebayo.officeman.backend.service;

import com.adebayo.officeman.backend.entity.Project;
import com.adebayo.officeman.backend.entity.Todo;
import com.adebayo.officeman.backend.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class TodoService {
	private final TodoRepository todoRepository;

	@Autowired
	public TodoService(TodoRepository todoRepository){
		this.todoRepository = todoRepository;
	}

	public Set<Todo> findAll(){
		return todoRepository.findAll();
	}

	public void delete(Todo todo){
		todoRepository.delete(todo);
	}

	public void deleteAll(Set<Todo> todos){
		todoRepository.deleteAll(todos);
	}

	public void save(Todo todo) {
		todoRepository.save(todo);
	}

	public Todo find(Integer id){
		return todoRepository.findById(id).get();
	}

//	@Cacheable("todos")
	public Set<Todo> findByProject(Project project){
		return todoRepository.findAllByProject(project);
	}

}
