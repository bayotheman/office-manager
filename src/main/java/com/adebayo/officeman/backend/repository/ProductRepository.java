package com.adebayo.officeman.backend.repository;

import com.adebayo.officeman.backend.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

	@Query("select p from Product p " +
			"where lower(p.description) like lower(concat('%', :searchTerm, '%')) " +
			"or lower(p.po) like lower(concat('%', :searchTerm, '%')) ")
	Set<Product> search(@Param("searchTerm") String searchTerm);

	@Query("select p from Product p order by current_date ")
	Set<Product> orderByDate();

	@Query("select p from Product p ")
	Set<Product> findAll();
}
