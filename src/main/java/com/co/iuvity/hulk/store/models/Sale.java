package com.co.iuvity.hulk.store.models;

import com.co.iuvity.hulk.store.models.entities.DataSale;

public class Sale {

	private DataSale sale;

	public Sale(DataSale sale) {
		this.sale = sale;
	}

	public DataSale getSale() {
		return sale;
	}

	public void setSale(DataSale sale) {
		this.sale = sale;
	}

	@Override
	public String toString() {
		return "Sale [sale=" + sale + "]";
	}

}
