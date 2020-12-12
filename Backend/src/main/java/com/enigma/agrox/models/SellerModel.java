package com.enigma.agrox.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "seller")
public class SellerModel {
	@Id
	private int seller_id;
	private String seller_username;
	private String seller_password;
	
	
	public int getSeller_id() {
		return seller_id;
	}
	public void setSeller_id(int seller_id) {
		this.seller_id = seller_id;
	}
	
	public String getSeller_username() {
		return seller_username;
	}
	public void setSeller_username(String seller_username) {
		this.seller_username = seller_username;
	}
	public String getSeller_password() {
		return seller_password;
	}
	public void setSeller_password(String seller_password) {
		this.seller_password = seller_password;
	}

}
