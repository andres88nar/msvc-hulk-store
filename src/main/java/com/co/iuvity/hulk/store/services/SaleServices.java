package com.co.iuvity.hulk.store.services;

import org.springframework.stereotype.Service;

import com.co.iuvity.hulk.store.exception.BusinessException;
import com.co.iuvity.hulk.store.models.entities.DataSale;

@Service
public interface SaleServices {

	public DataSale saleSave(DataSale sale) throws BusinessException;

}
