USE veeblooms;


CREATE TABLE `plant` (
  `plant_id` int NOT NULL AUTO_INCREMENT,
  `plantName` varchar(255) NOT NULL,
  `price` double NOT NULL,
  `rating` int NOT NULL,
  `plantType` varchar(100) DEFAULT NULL,
  `plantHeight` float DEFAULT NULL,
  `plantingSeason` varchar(100) DEFAULT NULL,
  `hybrid` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`plant_id`)
);

select * from  plant;

UPDATE plant SET plantName = 'rose plant', price = 200.0, rating = 5, plantType = 'Flower', plantHeight = 5.2, plantingSeason = 'Autumn', hybrid = 'YES' WHERE plant_id = 5;

CREATE TABLE `plantimagesurl` (
  `url_id` int NOT NULL AUTO_INCREMENT,
  `plant_id` int NOT NULL,
  `image_url` varchar(100) NOT NULL,
  PRIMARY KEY (`url_id`),
  KEY `plant_id` (`plant_id`),
  CONSTRAINT `plantimagesurl_ibfk_1` FOREIGN KEY (`plant_id`) REFERENCES `plant` (`plant_id`)
);

select * from plantimagesurl;

DELIMITER && 
CREATE PROCEDURE DeletePlants(IN plant_ids INT)
BEGIN
    -- Delete the record from the Ground table
    
    -- Delete existing GroundImages and SportsAvailable records for the given groundId
    DELETE FROM plantimagesurl WHERE plant_id = plant_ids;
    DELETE FROM plant WHERE plant_id = plant_ids;
    
      --   DELETE FROM plant WHERE plant_id = plant_ids;
END &&
DELIMITER ;