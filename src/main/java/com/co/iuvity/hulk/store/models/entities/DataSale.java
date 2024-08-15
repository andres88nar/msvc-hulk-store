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
@Table(name = "ventas")
public class DataSale {

	@Id
	@Column(name = "id_venta")
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

	@Column(name = "precio_venta_und")
	@NotEmpty
	private String salePrice;

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

	public String getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(String salePrice) {
		this.salePrice = salePrice;
	}

	@Override
	public String toString() {
		return "DataSale [id=" + id + ", date=" + date + ", product=" + product + ", amount=" + amount + ", salePrice="
				+ salePrice + "]";
	}

}
