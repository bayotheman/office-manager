package com.adebayo.officeman.backend.service;

import com.adebayo.officeman.backend.entity.Product;
import com.adebayo.officeman.backend.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ProductService {
	private final ProductRepository productRepository;

	public ProductService(ProductRepository productRepository){
		this.productRepository = productRepository;
	}

	public void save(Product product){
		if(product != null) {
			productRepository.save(product);
		}
	}

	public void delete(Product product){
		productRepository.delete(product);
	}

	public Set<Product> findAll(){
		return productRepository.findAll();
	}

	public Set<Product> search(String filter){
		if(filter.isEmpty() || filter == null){
			return findAll();
		}
		return productRepository.search(filter);
	}

	public Set<Product> findAllByCurrentDate(){
		return productRepository.orderByDate();
	}

	public void deleteAll(Set<Product> selectedItems) {
		productRepository.deleteAll(selectedItems);
	}
}
