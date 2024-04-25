package com.bookStore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.sql.Date;

@Entity
@Table(name="MyBooks")
public class MyBookList {
	
	@Id
	private int product_id;
	private String name;
	private int quantity;
	private float price;
	private Date expiryDate;

	public MyBookList() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MyBookList(int product_id, String name, int quantity, float price, Date expiryDate) {
		super();
		this.product_id = product_id;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.expiryDate = expiryDate;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}


}
