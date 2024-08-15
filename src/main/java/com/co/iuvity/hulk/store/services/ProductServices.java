package com.co.iuvity.hulk.store.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.co.iuvity.hulk.store.models.entities.Product;

@Service
public interface ProductServices {

	public List<Product> listProducts();
	
	public Optional<Product> getProduct(long id);

	public Product updateProdut(Product produc);

	public Product addProduct(Product product);
	
	public void delete (Product product);

}
