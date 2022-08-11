package com.adebayo.officeman.backend.service;

import com.adebayo.officeman.backend.entity.Remark;
import com.adebayo.officeman.backend.repository.RemarksRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RemarksService {

	private final RemarksRepository remarksRepository;

	public RemarksService(RemarksRepository remarksRepository){
		this.remarksRepository = remarksRepository;
	}

	public void save(Remark remark){
		remarksRepository.save(remark);
	}

	public long count(){
		return remarksRepository.count();
	}

	public void delete(Remark remark){
		remarksRepository.delete(remark);
	}

	public Set<Remark> findAll(){
		return remarksRepository.findAll();
	}

	public Set<Remark> findAll(String filter){
		if(filter.isEmpty() || filter == null){
			return remarksRepository.findAll();
		}
		return remarksRepository.search(filter);
	}

	public void deleteAll(Set<Remark> selectedItems) {
		remarksRepository.deleteAll(selectedItems);
	}
}
