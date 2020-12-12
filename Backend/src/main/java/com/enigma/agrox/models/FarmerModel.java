package com.enigma.agrox.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "farmer")
public class FarmerModel {
	@Id
	private int farmer_id;
	private String farmer_name;
	private String farmer_contact_number;
	private String farmer_location;
	private String farmer_username;
	private String farmer_password;
	
	
	
	public int getFarmer_id() {
		return farmer_id;
	}
	public void setFarmer_id(int farmer_id) {
		this.farmer_id = farmer_id;
	}
	public String getFarmer_name() {
		return farmer_name;
	}
	public void setFarmer_name(String farmer_name) {
		this.farmer_name = farmer_name;
	}
	
	public String getFarmer_location() {
		return farmer_location;
	}
	public void setFarmer_location(String farmer_location) {
		this.farmer_location = farmer_location;
	}
	
	public String getFarmer_password() {
		return farmer_password;
	}
	public void setFarmer_password(String farmer_password) {
		this.farmer_password = farmer_password;
	}
	public String getFarmer_contact_number() {
		return farmer_contact_number;
	}
	public void setFarmer_contact_number(String farmer_contact_number) {
		this.farmer_contact_number = farmer_contact_number;
	}
	public String getFarmer_username() {
		return farmer_username;
	}
	public void setFarmer_username(String farmer_username) {
		this.farmer_username = farmer_username;
	}
	
	
}
