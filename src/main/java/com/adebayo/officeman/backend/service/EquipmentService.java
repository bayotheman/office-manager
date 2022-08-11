package com.adebayo.officeman.backend.service;

import com.adebayo.officeman.backend.entity.Equipment;
import com.adebayo.officeman.backend.repository.EquipmentRepository;
import com.adebayo.officeman.backend.utilities.ServiceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class EquipmentService {
	private EquipmentRepository equipmentRepository;

	@Autowired
	public EquipmentService(EquipmentRepository equipmentRepository) {
		this.equipmentRepository = equipmentRepository;
	}

	public EquipmentService(){
	}

	public static EquipmentService getInstance() {
		return new EquipmentService();
	}



	EquipmentService getServiceInstance(){
		return new EquipmentService(this.equipmentRepository);
	}
	public void save(Equipment equipment) {
		if (ServiceUtils.isNull(equipment)) {
			return;
		}
		equipmentRepository.save(equipment);
	}

	public Equipment findEquipmentById(Integer id){
		return equipmentRepository.findEquipmentById(id);

	}

	public long count() {
		return equipmentRepository.count();
	}

	public void delete(@NonNull Equipment equipment) {
		equipmentRepository.delete(equipment);
	}

	public void deleteAll(Set<Equipment> equipments) {
		equipmentRepository.deleteAll(equipments);
	}

	public List<Equipment> findAll(String filterText) {
		if (filterText.isEmpty() || ServiceUtils.isNull(filterText)) {
			return equipmentRepository.findAll();
		}
		return equipmentRepository.search(filterText);
	}

//	public void setLastTestedDate(Equipment equipment) {
//		for (Equipment e : equipmentRepository.findAll()) {
//			if (e.equals(equipment)) {
//				e.setLastTimeTested();
//			}
//		}
//	}

}
