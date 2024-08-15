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
@Table(name = "compras")
public class DataPurchase {

	@Id
	@Column(name = "id_compra")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NonNull
	private Long id;

	@Column(name = "fecha")
	@NotEmpty
	private String date;

	@ManyToOne
	@JoinColumn(name = "id_producto")
	private Product product;

	@Column(name = "cantidad")
	@NotEmpty
	private String amount;

	@Column(name = "precio_compra_und")
	@NotEmpty
	private String purchasePrice;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(String purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "DataPurchase [id=" + id + ", date=" + date + ", product=" + product + ", amount=" + amount
				+ ", purchasePrice=" + purchasePrice + "]";
	}

}
