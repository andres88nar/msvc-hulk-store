package com.co.iuvity.hulk.store.repositories;

import org.springframework.data.repository.CrudRepository;

import com.co.iuvity.hulk.store.models.entities.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
