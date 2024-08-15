package com.co.iuvity.hulk.store.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.iuvity.hulk.store.models.Kardex;
import com.co.iuvity.hulk.store.models.KardexProducts;
import com.co.iuvity.hulk.store.models.entities.Inventory;
import com.co.iuvity.hulk.store.models.entities.Product;
import com.co.iuvity.hulk.store.repositories.InventoryRepository;
import com.co.iuvity.hulk.store.repositories.ProductRepository;
import com.co.iuvity.hulk.store.repositories.PurchaseRepository;
import com.co.iuvity.hulk.store.repositories.SaleRepository;

@Service
public class KardexServicesImp implements KardexServices {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private PurchaseRepository purchaseseRepository;

	@Autowired
	private SaleRepository saleRepository;

	@Autowired
	private InventoryRepository inventoryRepository;

	private Kardex kardex;

	@Override
	public Kardex getKardex() {
		kardex = new Kardex();
		List<Product> allProducts = (List<Product>) productRepository.findAll();

		allProducts.forEach(product -> {

			KardexProducts kardexProducts = new KardexProducts();
			Inventory inventory = inventoryRepository.getInventoryByID(product.getId());

			kardexProducts.setId(product.getId());
			kardexProducts.setName(product.getName());
			kardexProducts.setInventario((inventory == null ? 0 : Integer.parseInt(inventory.getAmount())));
			kardexProducts.setIngresos(purchaseseRepository.getAllPurchasesByID(product.getId()));
			kardexProducts.setEgresos(saleRepository.getAllSalesByID(product.getId()));

			kardex.addKardexProducts(kardexProducts);

		});

		return kardex;

	}

}
