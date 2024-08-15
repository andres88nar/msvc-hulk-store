package com.co.iuvity.hulk.store.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.co.iuvity.hulk.store.models.entities.Product;
import com.co.iuvity.hulk.store.repositories.ProductRepository;

@Service
public class ProductServicesImp implements ProductServices {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Product> listProducts() {
		return (List<Product>) productRepository.findAll();
	}

	@Override
	public Optional<Product> getProduct(long id) {
		return productRepository.findById(id);
	}

	// @Override
	public Product updateProdut(Product product) {		
		return productRepository.save(product);
	}

	@Override
	public Product addProduct(Product product) {
		return productRepository.save(product);
	}
	
	public void delete (Product product) {
		productRepository.delete(product);
	}
	

}
