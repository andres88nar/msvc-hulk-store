package com.co.iuvity.hulk.store.controller;

import static com.co.iuvity.hulk.store.constant.CommonsConstans.FIELD_AMOUND;
import static com.co.iuvity.hulk.store.constant.CommonsConstans.FIELD_DATE;
import static com.co.iuvity.hulk.store.constant.CommonsConstans.FIELD_ID_PRODUCT;
import static com.co.iuvity.hulk.store.constant.CommonsConstans.FIELD_NAME;
import static com.co.iuvity.hulk.store.constant.CommonsConstans.FIELD_PRODUCT;
import static com.co.iuvity.hulk.store.constant.CommonsConstans.FIELD_PURCHASE_PRICE;
import static com.co.iuvity.hulk.store.constant.CommonsConstans.FIELD_SALE_PRICE;

import static com.co.iuvity.hulk.store.constant.CommonsConstans.REGEX_POSITIVE_NUMBER;
import static com.co.iuvity.hulk.store.constant.CommonsConstans.REGEX_DATE;

import static com.co.iuvity.hulk.store.constant.MessagesConstans.ERROR_FORMAT_DATE;
import static com.co.iuvity.hulk.store.constant.MessagesConstans.ERROR_FORMAT_NUMBER;
import static com.co.iuvity.hulk.store.constant.MessagesConstans.MSN_INVALID_ID_PRODUCT;
import static com.co.iuvity.hulk.store.constant.MessagesConstans.NOT_FOUND_PRODUCT;
import static com.co.iuvity.hulk.store.constant.MessagesConstans.NOT_FOUND_PRODUCTS;
import static com.co.iuvity.hulk.store.constant.MessagesConstans.REPLACE;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co.iuvity.hulk.store.commons.services.StandarResponse;
import com.co.iuvity.hulk.store.commons.services.ValidateField;
import com.co.iuvity.hulk.store.exception.BusinessException;
import com.co.iuvity.hulk.store.exception.InputValidationException;
import com.co.iuvity.hulk.store.models.entities.Product;
import com.co.iuvity.hulk.store.models.Kardex;
import com.co.iuvity.hulk.store.models.Purchase;
import com.co.iuvity.hulk.store.models.Sale;
import com.co.iuvity.hulk.store.models.entities.DataPurchase;
import com.co.iuvity.hulk.store.models.entities.DataSale;
import com.co.iuvity.hulk.store.services.KardexServices;
import com.co.iuvity.hulk.store.services.ProductServices;
import com.co.iuvity.hulk.store.services.PurchaseServices;
import com.co.iuvity.hulk.store.services.SaleServices;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RestController
@RequestMapping("hullstore/")
public class HulkStoreController {

	@Autowired
	private ProductServices productServices;

	@Autowired
	private PurchaseServices purchaseServices;

	@Autowired
	private SaleServices saleServices;

	@Autowired
	private StandarResponse standResponse;

	@Autowired
	private ValidateField validateField;
	
	@Autowired
	private KardexServices kardexServices;
	
	private Gson Gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();

	@GetMapping(value = "allproducts", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> listProducts() {
		List<Product> listProducts = productServices.listProducts();
		if (listProducts.size() > 0) {
			return ResponseEntity.ok(listProducts);
		}
		return standResponse.createResponseBuilderNoFound(NOT_FOUND_PRODUCTS);
	}

	@GetMapping(value = "product/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getProduct(@PathVariable Long id) {
		Optional<Product> produc = productServices.getProduct(id);
		if (produc.isPresent()) {
			return ResponseEntity.ok(produc);
		}
		return standResponse.createResponseBuilderNoFound(NOT_FOUND_PRODUCT.replace(REPLACE, String.valueOf(id)));
	}

	@PostMapping(value = "newproduct", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> addProduct(@RequestBody Product product) {
		try {
			validateField.validateFieldNull(FIELD_NAME, Optional.ofNullable(product.getName()));
			Product productAdd = productServices.addProduct(product);
			return standResponse.createResponseBuilderCreate(productAdd);
		} catch (InputValidationException e) {
			return standResponse.createResponseBuilderBadRequest(e.getMessage());
		}
	}

	@PutMapping(value = "updateproduct/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateProduct(@PathVariable Long id, @RequestBody Product product) {
		try {
			Optional<Product> produc = productServices.getProduct(id);
			if (produc.isPresent()) {
				validateField.validateFieldNull(FIELD_NAME, Optional.ofNullable(product.getName()));
				product.setId(id);
				Product productupdate = productServices.updateProdut(product);
				return ResponseEntity.ok(productupdate);
			}
			return standResponse.createResponseBuilderNoFound(NOT_FOUND_PRODUCT.replace(REPLACE, String.valueOf(id)));

		} catch (InputValidationException e) {
			return standResponse.createResponseBuilderNoFound(e.getMessage());
		}

	}

	@DeleteMapping(value = "deleteproduct/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> deleteProduct(@PathVariable Long id) {
		Optional<Product> product = productServices.getProduct(id);
		if (product.isPresent()) {
			productServices.delete(product.get());
			return standResponse.createResponseBuilderDeleted(id);
		}
		return standResponse.createResponseBuilderNoFound(NOT_FOUND_PRODUCT.replace(REPLACE, String.valueOf(id)));
	}

	@PostMapping(value = "newpurchase", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> addpurchase(@RequestBody String body) {		
		try {
			DataPurchase purchase = Gson.fromJson(body, DataPurchase.class);
			validateFieldsPurchase(purchase);
			DataPurchase purchaseAdded = purchaseServices.purchaseSave(purchase);
			Optional<Product> produc = productServices.getProduct(purchaseAdded.getProduct().getId());
			purchaseAdded.setProduct(produc.get());
			return ResponseEntity.ok(new Purchase(purchaseAdded));
		} catch (InputValidationException e) {
			return standResponse.createResponseBuilderBadRequest(e.getMessage());
		} catch (DataIntegrityViolationException e) {
			return standResponse.createResponseBuilderConflict(MSN_INVALID_ID_PRODUCT);
		}
	}

	@PostMapping(value = "newsale", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> addSale(@RequestBody String body) {
		try {
			DataSale sale = Gson.fromJson(body, DataSale.class);
			validateFieldSale(sale);
			DataSale saleAdded = saleServices.saleSave(sale);
			Optional<Product> produc = productServices.getProduct(saleAdded.getProduct().getId());
			saleAdded.setProduct(produc.get());
			return ResponseEntity.ok(new Sale(saleAdded));
		} catch (InputValidationException e) {
			return standResponse.createResponseBuilderBadRequest(e.getMessage());
		}catch (BusinessException e) {
			return standResponse.createResponseBuilderConflict(e.getMessage()); 
		}catch (DataIntegrityViolationException e) {
			return standResponse.createResponseBuilderConflict(MSN_INVALID_ID_PRODUCT);
		}
	}
	
	
	@GetMapping(value = "kardex", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getKardex (){		
		Kardex kardex = kardexServices.getKardex();		
		return ResponseEntity.ok(kardex);		
	}

	private void validateFieldsPurchase(DataPurchase purchase) throws InputValidationException {

		validateField.validateFieldNull(FIELD_DATE, Optional.ofNullable(purchase.getDate()));
		validateField.validateRegEx(purchase.getDate(), REGEX_DATE, ERROR_FORMAT_DATE.replace(REPLACE, FIELD_DATE));

		validateField.validateFieldNull(FIELD_AMOUND, Optional.ofNullable(purchase.getAmount()));
		validateField.validateRegEx(purchase.getAmount(), REGEX_POSITIVE_NUMBER, ERROR_FORMAT_NUMBER.replace(REPLACE, FIELD_AMOUND));

		validateField.validateFieldNull(FIELD_PURCHASE_PRICE, Optional.ofNullable(purchase.getPurchasePrice()));
		validateField.validateRegEx(purchase.getPurchasePrice(), REGEX_POSITIVE_NUMBER,	ERROR_FORMAT_NUMBER.replace(REPLACE, FIELD_PURCHASE_PRICE));

		validateField.validateObjectNull(FIELD_PRODUCT, purchase.getProduct());
		validateField.validateObjectNull(FIELD_ID_PRODUCT, purchase.getProduct().getId());

	}

	private void validateFieldSale(DataSale sale) throws InputValidationException {

		validateField.validateFieldNull(FIELD_DATE, Optional.ofNullable(sale.getDate()));
		validateField.validateRegEx(sale.getDate(), REGEX_DATE, ERROR_FORMAT_DATE.replace(REPLACE, FIELD_DATE));

		validateField.validateFieldNull(FIELD_AMOUND, Optional.ofNullable(sale.getAmount()));
		validateField.validateRegEx(sale.getAmount(), REGEX_POSITIVE_NUMBER, ERROR_FORMAT_NUMBER.replace(REPLACE, FIELD_AMOUND));

		validateField.validateFieldNull(FIELD_SALE_PRICE, Optional.ofNullable(sale.getSalePrice()));
		validateField.validateRegEx(sale.getSalePrice(), REGEX_POSITIVE_NUMBER, ERROR_FORMAT_NUMBER.replace(REPLACE, FIELD_SALE_PRICE));

		validateField.validateObjectNull(FIELD_PRODUCT, sale.getProduct());
		validateField.validateObjectNull(FIELD_ID_PRODUCT, sale.getProduct().getId());
	}
	
	
	
	

}
