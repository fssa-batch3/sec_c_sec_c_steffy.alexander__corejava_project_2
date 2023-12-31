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
alter table plant drop column rating;

UPDATE plant SET plantName = 'rose plant', price = 200.0, rating = 5, plantType = 'Flower', plantHeight = 5.2, plantingSeason = 'Autumn', hybrid = 'YES' WHERE plant_id = 5;

CREATE TABLE `plantimagesurl` (
  `url_id` int NOT NULL AUTO_INCREMENT,
  `plant_id` int NOT NULL,
  `image_url` varchar(100) NOT NULL,
  PRIMARY KEY (`url_id`),
  foreign KEY (`plant_id`) references plant (`plant_id`) on delete cascade
  );
select * from plantimagesurl;

DELIMITER && 
CREATE PROCEDURE DeletePlants(IN plant_ids INT)
BEGIN
    
  
    DELETE FROM plantimagesurl WHERE plant_id = plant_ids;
    DELETE FROM plant WHERE plant_id = plant_ids;
    
      --   DELETE FROM plant WHERE plant_id = plant_ids;
END &&
DELIMITER ;



