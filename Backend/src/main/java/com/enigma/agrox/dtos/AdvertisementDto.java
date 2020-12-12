package com.enigma.agrox.dtos;

public class AdvertisementDto {
	private int adId;
	private FarmerDto farmer;// ad Dto eka athule farmer Dto eka dala thiyenne
	private String adDate;
	private String adTitle;
	private String adQuantity;
	private float adPrice;
	private String adType;
	private boolean adReviewed;
	
	public int getAdId() {
		return adId;
	}
	public void setAdId(int adId) {
		this.adId = adId;
	}
	public FarmerDto getFarmer() {
		return farmer;
	}
	public void setFarmer(FarmerDto farmer) {
		this.farmer = farmer;
	}
	public String getAdDate() {
		return adDate;
	}
	public void setAdDate(String adDate) {
		this.adDate = adDate;
	}
	public String getAdTitle() {
		return adTitle;
	}
	public void setAdTitle(String adTitle) {
		this.adTitle = adTitle;
	}
	public String getAdQuantity() {
		return adQuantity;
	}
	public void setAdQuantity(String adQuantity) {
		this.adQuantity = adQuantity;
	}
	public float getAdPrice() {
		return adPrice;
	}
	public void setAdPrice(float adPrice) {
		this.adPrice = adPrice;
	}
	public String getAdType() {
		return adType;
	}
	public void setAdType(String adType) {
		this.adType = adType;
	}
	public boolean isAdReviewed() {
		return adReviewed;
	}
	public void setAdReviewed(boolean adReviewed) {
		this.adReviewed = adReviewed;
	}
	
	

}
