package com.adebayo.officeman.backend.repository;

import com.adebayo.officeman.backend.entity.Remark;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface RemarksRepository extends PagingAndSortingRepository<Remark, Integer> {

	@Query("select q from Remark q " +
//			"where q.replied = :searchTerm " +
			" where lower(q.content) like lower(concat('%', :searchTerm, '%')) " +
			" or lower(q.reply) like lower(concat('%', :searchTerm, '%')) " +
			" order by q.date"
//			"order by q.date "
	)
	Set<Remark> search(@Param("searchTerm") String searchTerm);

	@Query("select q from Remark q")
	Set<Remark> findAll();
}
