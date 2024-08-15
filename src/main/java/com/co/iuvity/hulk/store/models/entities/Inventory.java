package com.co.iuvity.hulk.store.models.entities;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "inventario")
public class Inventory {

	@Id
	@Column(name = "id_inventario")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NonNull
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_producto")
	private Product product;

	@Column(name = "cantidad_inv")
	@NotEmpty
	private String amount;

	public Inventory(Product product, String amount) {
		this.product = product;
		this.amount = amount;
	}

	public Inventory() {
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Inventory [id=" + id + ", product=" + product + ", amount=" + amount + "]";
	}

}
