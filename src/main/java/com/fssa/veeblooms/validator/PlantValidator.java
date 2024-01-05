package com.fssa.veeblooms.validator;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fssa.veeblooms.enumclass.HybridEnum;
import com.fssa.veeblooms.enumclass.PlantTypeEnum;
import com.fssa.veeblooms.exception.*;
import com.fssa.veeblooms.model.*;

public class PlantValidator {

    // Method to validate a Plant object
	
    public static boolean validatePlant(Plant plant) throws CustomException {
        // Check if the provided plant object is null
    	
        if (plant == null) {
            throw new CustomException(ErrorMessages.INVALID_PLANT_NULL);
        }
        
        // Validate various attributes of the plant
        validatePlantName(plant.getPlantName());
        validatePlantImagesUrl(plant.getPlantImagesUrl());
        validatePrice(plant.getPrice());
        validatePlantType(plant.getPlantType());
        validatePlantHeight(plant.getPlantHeight());
        validatePlantingSeason(plant.getPlantingSeason());
        validateHybrid(plant.getHybrid()); 
        return true; 
    } 

    // Method to validate plant name
    
    public static boolean validatePlantName(String plantName) throws CustomException {
        // Check if the plant name is null or empty
        if (plantName == null || plantName.trim().isEmpty() ) {
            throw new CustomException(ErrorMessages.INVALID_PLANT_NAME);
        }
        // Check if the plant name length is less than 3 characters
        if (plantName.length() < 3) {
            throw new CustomException(ErrorMessages.INVALID_PLANT_NAME_LENGTH);
        }
        // Use regex pattern to validate plant name format
        String regexPattern = "";
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(plantName);
        boolean isMatch = matcher.matches();

        // If the name format doesn't match the pattern, throw an exception
        if (!isMatch) {
            throw new CustomException(ErrorMessages.INVALID_PLANT_NAME_PATTERN);
        }
        return true;
    }

    // Method to validate plant images URLs
    
    public static boolean validatePlantImagesUrl(List<String> plantImagesUrl) throws CustomException {
        // Check if the list of image URLs is null or empty
        if (plantImagesUrl == null || plantImagesUrl.isEmpty()) {
            throw new CustomException(ErrorMessages.INVALID_PLANT_PLANTIMAGESURL);
        }

        // Validate each individual image URL in the list
        for (String imageUrl : plantImagesUrl) {
            if (imageUrl == null || imageUrl.trim().isEmpty()) {
                throw new CustomException(ErrorMessages.INVALID_PLANT_INVALIDPLANTIMAGESURL);
            }
        }

        return true;
    }

    // Method to validate price
    
    public static boolean validatePrice(double price) throws CustomException {
        // Check if the price is non-positive
        if (price <= 0) {
            throw new CustomException(ErrorMessages.INVALID_PLANT_PRICE);
        }
        return true;
    }

    // Method to validate plant type
    public static boolean validatePlantType(PlantTypeEnum plantType) throws CustomException {
        // Check if the plant type is null
        if (plantType == null) {
            throw new CustomException(ErrorMessages.INVALID_PLANT_TYPE);
        }
        return true;
    }

    // Method to validate plant height
    
    public static boolean validatePlantHeight(float plantHeight) throws CustomException {
        // Check if the plant height is non-positive
        if (plantHeight <= 0) {
            throw new CustomException(ErrorMessages.INVALID_PLANT_HEIGHT);
        }
        return true;
    }

    // Method to validate planting season
    
    public static boolean validatePlantingSeason(String plantingSeason) throws CustomException {
        // Check if the planting season is null or empty
        if (plantingSeason == null || plantingSeason.trim().isEmpty()) {
            throw new CustomException(ErrorMessages.INVALID_PLANTING_SEASON);
        }
        // Check if the planting season length is less than 3 characters
        if (plantingSeason.length() < 3) {
            throw new CustomException(ErrorMessages.INVALID_PLANT_NAME_LENGTH);
        }
        // Using regex pattern to validate planting season format
        
        String regexPattern = "^[^0-9]*$";
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(plantingSeason);
        boolean isMatch = matcher.matches();

        // If the planting season doesn't match the pattern, throw an exception
        if (!isMatch) {
            throw new CustomException(ErrorMessages.INVALID_PLANTING_SEASON);
        }
        return true;
    }

    // Method to validate hybrid
    
    public static boolean validateHybrid(HybridEnum hybrid) throws CustomException {
        // Check if the hybrid enum is null
        if (hybrid == null) {
            throw new CustomException(ErrorMessages.INVALID_HYBRID);
        }
        return true;
    }
}
