package com.co.iuvity.hulk.store.services;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.co.iuvity.hulk.store.models.entities.DataPurchase;

@Service
public interface PurchaseServices {

	public DataPurchase purchaseSave(DataPurchase purchases) throws DataIntegrityViolationException;

}
