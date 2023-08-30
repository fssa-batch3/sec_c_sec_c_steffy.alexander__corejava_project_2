package com.fssa.veeblooms.model;

import java.util.List;

import com.fssa.veeblooms.Enum.HybridEnum;


public class Plant {
	private int plantId;
	private String plantName;
	private List<String> plantImagesUrl;
	private double price;
//	private int rating;
	private String plantType;
	private float plantHeight;
	private String plantingSeason;
	private HybridEnum hybrid;

	public int getPlantId() {
     	return plantId;
	}

	public void setPlantId(int plantId) {
		this.plantId = plantId;
	}

	public String getPlantName() {
		return plantName;
	}

	public void setPlantName(String plantName) {
		this.plantName = plantName;
	}

	public List<String> getPlantImagesUrl() {
		return plantImagesUrl;
	}

	public void setPlantImagesUrl(List<String> plantImagesUrl) {
		this.plantImagesUrl = plantImagesUrl;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

//	public int getRating() {
//		return rating;
//	}
//
//	public void setRating(int rating) {
//		this.rating = rating;
//	}

	public String getPlantType() {
		return plantType;
	}

	public void setPlantType(String plantType) {
		this.plantType = plantType;
	}

	public float getPlantHeight() {
		return plantHeight;
	}

	public void setPlantHeight(float plantHeight) {
		this.plantHeight = plantHeight;
	}

	public String getPlantingSeason() {
		return plantingSeason;
	}

	public void setPlantingSeason(String plantingSeason) {
		this.plantingSeason = plantingSeason;
	}

	public HybridEnum getHybrid() {
		return hybrid;
	}

	public void setHybrid(HybridEnum hybrid) {
		this.hybrid = hybrid;
	}


	public Plant(String plantName, List<String> plantImagesUrl, double price, int rating, String plantType,
			float plantHeight, String plantingSeason, HybridEnum hybrid) {
		super();
		this.plantName = plantName;
		this.plantImagesUrl = plantImagesUrl;
		this.price = price;
//		this.rating = rating;
		this.plantType = plantType;
		this.plantHeight = plantHeight;
		this.plantingSeason = plantingSeason;
		this.hybrid = hybrid;
	}

	public Plant(String plantName) {
		this.plantName = plantName;
	}
	
	

	@Override
	public String toString() {
		return "Plant [plantId=" + plantId + ", plantName=" + plantName + ", plantImagesUrl=" + plantImagesUrl
				+ ", price=" + price + ", plantType=" + plantType + ", plantHeight="
				+ plantHeight + ", plantingSeason=" + plantingSeason + ", hybrid=" + hybrid + "]";
	}

	public Plant() {

	}

}
