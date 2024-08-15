package com.co.iuvity.hulk.store.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.co.iuvity.hulk.store.models.entities.DataPurchase;
import com.co.iuvity.hulk.store.models.entities.Inventory;
import com.co.iuvity.hulk.store.repositories.InventoryRepository;
import com.co.iuvity.hulk.store.repositories.PurchaseRepository;

@Service
public class PurchaseServicesImp implements PurchaseServices {

	@Autowired
	private PurchaseRepository purchaseseRepository;

	@Autowired
	private InventoryRepository inventoryRepository;

	@Override
	public DataPurchase purchaseSave(DataPurchase purchases) throws DataIntegrityViolationException {

		DataPurchase purchaseSaved = purchaseseRepository.save(purchases);
		Inventory inventory = inventoryRepository.getInventoryByID(purchaseSaved.getProduct().getId());

		if (inventory == null) {
			inventoryRepository.save(new Inventory(purchases.getProduct(), purchases.getAmount()));
		} else {
			int talAmount = Integer.parseInt(inventory.getAmount()) + Integer.parseInt(purchases.getAmount());
			inventory.setAmount(String.valueOf(talAmount));
			inventoryRepository.save(inventory);
		}

		return purchaseSaved;
	}

}
