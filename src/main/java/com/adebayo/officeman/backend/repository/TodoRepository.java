package com.adebayo.officeman.backend.repository;

import com.adebayo.officeman.backend.entity.Project;
import com.adebayo.officeman.backend.entity.Todo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Set;

public interface TodoRepository extends PagingAndSortingRepository<Todo, Integer> {

	@Query("select t from Todo t left join fetch t.project "
//			+
//			"order by t.startDate asc "
	)
	Set<Todo> findAll();

	Set<Todo> findAllByProject(Project project);

	Set<Todo> getTodosByDone(Boolean done);


}
