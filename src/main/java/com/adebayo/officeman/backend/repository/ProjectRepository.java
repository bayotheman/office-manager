package com.adebayo.officeman.backend.repository;

import com.adebayo.officeman.backend.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;


public interface ProjectRepository extends JpaRepository<Project, Long> {
	@Query("select p from Project p left join fetch p.supervisor " +
			"left join fetch p.todoList " +
			"left join fetch p.comment " +
			"where lower(p.title) like lower(concat('%', :searchTerm, '%')) " +
			" or lower(p.supervisor.firstName) like lower(concat('%', :searchTerm, '%')) " +
			" or lower(p.supervisor.lastName) like lower(concat('%', :searchTerm, '%')) " +
			" order by p.dueDate"
//			" or p.priorityValue = :searchTerm "
	)
	Set<Project> search(@Param("searchTerm") String filterText);

	@Query("select p from Project p left join fetch p.supervisor " +
			"left join fetch p.todoList " +
			"left join fetch p.comment " +
			"order by p.startDate asc " )
	List<Project> findAll();

	@Query("select p from Project p " +
			"left join fetch p.todoList " +
			"left join fetch p.comment " +
			"where p.id = :projectId")
	Project findProjectById(@Param("projectId") Long id);

	@Query("select p from Project p " +
			"left join fetch p.supervisor " +
			"where p.supervisor.id = :supervisorId ")
	Set<Project> findAllBySupervisorId(@Param("supervisorId") Integer id);

	Set<Project> findAllByCompletedFalse();


//	Set<Project> findProjectByAnticipatedCompletionDateIsBetween(LocalDate anticipatedCompletionDate, LocalDate anticipatedCompletionDate2);
//

//	Set<Project> findAllByCompletedFalseAndPriorityValueEquals(Integer priorityValue);
//
//	Set<Project> findProjectByAnticipatedCompletionDateNear(LocalDate anticipatedCompletionDate);
//
	@Query(
			"select p from Project p left join p.supervisor " +
					"left join p.todoList " +
					"where p.completed = false " +
					"order by p.priorityValue "
	)
	Set<Project> findAllUncompletedProjectsOrderedByPriorityValue();
//
//	Set<Project> findProjectByCompletedFalseOrderByAnticipatedCompletionDate();





}
