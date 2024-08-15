package com.co.iuvity.hulk.store.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.co.iuvity.hulk.store.models.entities.Inventory;

public interface InventoryRepository extends CrudRepository<Inventory, Long> {

	@Query(value = "SELECT * FROM hulkstore.inventario WHERE id_producto=?1", nativeQuery = true)
	Inventory getInventoryByID(Long idProduct);
	
}
