package com.co.iuvity.hulk.store.models;

import java.util.ArrayList;
import java.util.List;

public class Kardex {

	private List<KardexProducts> kardex;

	public Kardex() {
		kardex = new ArrayList<KardexProducts>();
	}

	public void addKardexProducts(KardexProducts kardexProducts) {
		kardex.add(kardexProducts);
	}

	public List<KardexProducts> getKardex() {
		return kardex;
	}

	@Override
	public String toString() {
		return "Kardex [kardex=" + kardex + "]";
	}

}
