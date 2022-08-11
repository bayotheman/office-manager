package com.adebayo.officeman.backend.repository;

import com.adebayo.officeman.backend.entity.Equipment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface EquipmentRepository extends PagingAndSortingRepository<Equipment, Integer> {
	@Query("select e from Equipment e " +
			"where lower(e.manufacturer) like lower(concat('%', :searchTerm, '%')) " +
			"or lower(e.model) like lower(concat('%', :searchTerm, '%')) " +
			"or lower(e.serialNo) like lower(concat('%', :searchTerm, '%'))" +
			"or lower(e.location) like lower(concat('%', :searchTerm, '%'))" )
	List<Equipment> search(@Param("searchTerm") String filterText);

	@Query("select e from Equipment e")
	List<Equipment> findAll();

	@Query("update Equipment e " +
			"set e.lastTimeTested = :date")
	void setLastTimeTested(@Param("date") Date date);

	Equipment findEquipmentById(Integer id);

}
