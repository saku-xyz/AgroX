package com.enigma.agrox.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "advertisement")
public class AdvertisementModel {
	@Id
	private int ad_id;
	@ManyToOne
	@JoinColumn(name = "farmer_id")
	private FarmerModel farmer_id;//representing the whole farmer model as the foreign key instead of data type
	private String ad_date;
	private String ad_title;
	private String ad_quantity;
	private float ad_price;
	private String ad_type;
	private boolean reviewed;
	
	public int getAd_id() {
		return ad_id;
	}
	public void setAd_id(int ad_id) {
		this.ad_id = ad_id;
	}
	
	public FarmerModel getFarmer_id() {//here also changed to FarmerModel
		return farmer_id;
	}
	public void setFarmer_id(FarmerModel farmer_id) {// here also changed to FarmerModel
		this.farmer_id = farmer_id;
	}
	public String getAd_date() {
		return ad_date;
	}
	public void setAd_date(String ad_date) {
		this.ad_date = ad_date;
	}
	public String getAd_title() {
		return ad_title;
	}
	public void setAd_title(String ad_title) {
		this.ad_title = ad_title;
	}
	public String getAd_quantity() {
		return ad_quantity;
	}
	public void setAd_quantity(String ad_quantity) {
		this.ad_quantity = ad_quantity;
	}
	public float getAd_price() {
		return ad_price;
	}
	public void setAd_price(float ad_price) {
		this.ad_price = ad_price;
	}
	public String getAd_type() {
		return ad_type;
	}
	public void setAd_type(String ad_type) {
		this.ad_type = ad_type;
	}
	public boolean getReviewed() {
		return reviewed;
	}
	public void setReviewed(boolean ad_reviewed) {
		this.reviewed = reviewed;
	}
	
	

}
