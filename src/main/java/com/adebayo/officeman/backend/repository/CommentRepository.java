package com.adebayo.officeman.backend.repository;

import com.adebayo.officeman.backend.entity.Comment;
import com.adebayo.officeman.backend.entity.Project;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface CommentRepository extends PagingAndSortingRepository<Comment, Integer> {
//	@Query("select c from Comment c " +
//			"where lower(c.date) like lower(concat('%', :searchTerm, '%')) " +
//			"or lower(c.model) like lower(concat('%', :searchTerm, '%')) " +
//			"or lower(e.serialNo) like lower(concat('%', :searchTerm, '%'))" +
//			"or lower(e.location) like lower(concat('%', :searchTerm, '%'))" )
//	List<Equipment> search(@Param("searchTerm") String filterText);
//}
	Set<Comment> findCommentsByProject(Project project);
//	Comment findDistinctTopByProject(Project project);

	@Query("select c from Comment c " +
			"left join fetch c.project " +
			"where c.project.id =:projectId ")
	Set<Comment> search(@Param("projectId") Long id);

	@Query("select c from Comment c " +
			"left join c.project " +
			"where c.id = :id ")
	Comment findCommentById(@Param("id") Integer id);

	Comment findCommentByProject(Project project);
}
