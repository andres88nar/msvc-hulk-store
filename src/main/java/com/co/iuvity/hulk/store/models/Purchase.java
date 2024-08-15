package com.co.iuvity.hulk.store.models;

import com.co.iuvity.hulk.store.models.entities.DataPurchase;

public class Purchase {

	private DataPurchase purchase;

	public Purchase(DataPurchase purchases) {
		this.purchase = purchases;
	}

	public DataPurchase getPurchase() {
		return purchase;
	}

	public void setPurchase(DataPurchase purchases) {
		this.purchase = purchases;
	}

}
