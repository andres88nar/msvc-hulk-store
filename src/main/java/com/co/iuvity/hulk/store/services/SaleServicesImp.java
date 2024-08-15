package com.co.iuvity.hulk.store.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.iuvity.hulk.store.exception.BusinessException;
import com.co.iuvity.hulk.store.models.entities.DataSale;
import com.co.iuvity.hulk.store.models.entities.Inventory;
import com.co.iuvity.hulk.store.repositories.InventoryRepository;
import com.co.iuvity.hulk.store.repositories.SaleRepository;

import static com.co.iuvity.hulk.store.constant.MessagesConstans.MSN_INSUFFICIENT_AMOUNT;
import static com.co.iuvity.hulk.store.constant.MessagesConstans.REPLACE;

@Service
public class SaleServicesImp implements SaleServices {

	@Autowired
	private SaleRepository saleRepository;

	@Autowired
	private InventoryRepository inventoryRepository;

	@Override
	public DataSale saleSave(DataSale sale) throws BusinessException {
		DataSale saleSaved = saleRepository.save(sale);
		Inventory inventory = inventoryRepository.getInventoryByID(saleSaved.getProduct().getId());
		
		if (inventory == null || Integer.parseInt(sale.getAmount()) > Integer.parseInt(inventory.getAmount())) {
			throw new BusinessException(MSN_INSUFFICIENT_AMOUNT.replace(REPLACE, String.valueOf(sale.getProduct().getId())));
		}
		
		int talAmount = Integer.parseInt(inventory.getAmount()) - Integer.parseInt(sale.getAmount());
		inventory.setAmount(String.valueOf(talAmount));
		inventoryRepository.save(inventory);		
		
		return saleRepository.save(saleSaved);
	}

}
