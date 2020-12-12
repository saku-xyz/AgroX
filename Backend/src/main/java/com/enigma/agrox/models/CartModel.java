package com.enigma.agrox.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cart")
public class CartModel {
	@Id
	private int cart_id;
	private int ad_id;
	private int seller_id;
	private int cart_quantity;
	private String cart_unit;
	
	public int getCart_id() {
		return cart_id;
	}
	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
	}
	public int getAd_id() {
		return ad_id;
	}
	public void setAd_id(int ad_id) {
		this.ad_id = ad_id;
	}
	public int getSeller_id() {
		return seller_id;
	}
	public void setSeller_id(int seller_id) {
		this.seller_id = seller_id;
	}
	public int getCart_quantity() {
		return cart_quantity;
	}
	public void setCart_quantity(int cart_quantity) {
		this.cart_quantity = cart_quantity;
	}
	public String getCart_unit() {
		return cart_unit;
	}
	public void setCart_unit(String cart_unit) {
		this.cart_unit = cart_unit;
	}
	
	
	
}
