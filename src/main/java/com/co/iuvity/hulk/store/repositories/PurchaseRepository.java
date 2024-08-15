package com.co.iuvity.hulk.store.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.co.iuvity.hulk.store.models.entities.DataPurchase;

public interface PurchaseRepository extends CrudRepository<DataPurchase, Long> {

	@Query(value = "SELECT * FROM hulkstore.compras WHERE id_producto=?1", nativeQuery = true)
	public List<DataPurchase> getAllPurchasesByID(Long id);

}
