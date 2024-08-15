package com.co.iuvity.hulk.store.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.co.iuvity.hulk.store.models.entities.DataSale;

public interface SaleRepository extends CrudRepository<DataSale, Long> {
	
	@Query(value = "SELECT * FROM hulkstore.ventas WHERE id_producto=?1", nativeQuery = true)
	public List<DataSale> getAllSalesByID(Long id);

}
