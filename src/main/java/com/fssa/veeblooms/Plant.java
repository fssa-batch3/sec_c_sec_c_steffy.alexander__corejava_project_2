package com.fssa.veeblooms;

import java.util.List;

import com.fssa.veeblooms.Enum.HybridEnum;


public class Plant {
	
	private String plantName;
	private List<String> plantImagesUrl;
	private double price;
	private int rating;
	private String plantType;
	private float plantHeight;
	private String plantingSeason;
	private HybridEnum hybrid;

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

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

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

	public Plant(List<String> plantImagesUrl, double price, int rating, String plantType,
			float plantHeight, String plantingSeason, HybridEnum hybrid) {
		super();
		
		this.plantImagesUrl = plantImagesUrl;
		this.price = price;
		this.rating = rating;
		this.plantType = plantType;
		this.plantHeight = plantHeight;
		this.plantingSeason = plantingSeason;
		this.hybrid = hybrid;
	}
	public Plant(String plantName) {
		this.plantName = plantName;
	}

	public Plant() {

	}

//	public static void main(String[] args) throws CustomException, SQLException {
//
//		List<String> images = new ArrayList<String>();
//		images.add("https://www.youtube.com/watch?v=55tCJ8Odjvw");
//		images.add("https://learn.facecampus.org/fn/fop-and-dsa-training/#curriculum");
//		images.add("https://app.facecampus.org/calendar/");
//		images.add("https://chat.openai.com/");
//
//		Plant plant = new Plant();
//		plant.setPlantName("rose plant");
//		plant.setPlantImagesUrl(images);
//		plant.setPrice(250);
//		plant.setRating(5);
//		plant.setPlantType("Flower");
//		plant.setPlantHeight(5.2f);
//		plant.setPlantingSeason("Autumn");
//		plant.setHybrid(HybridEnum.YES);
//
//		PlantService plantService = new PlantService(new PlantValidator(), new PlantDAO());

//		plantService.addPlant(plant); 
		
		
//		int deleteId = 4;
//
//		PlantDAO.deletePlantById(15);
		


				
//				plantService.updatePlant(plant,17);
				//plantService.deletePlant(12);
//		plantService.deletePlant(12);

	}
//}
