# sec_c_sec_c_steffy.alexander__corejava_project_2

## Database Setup

### Plant Table

This table holds information about different plants available in the database.

| Column       | Data Type         | Constraints        |
|--------------|-------------------|--------------------|
| plant_id     | int               | NOT NULL, AUTO_INCREMENT |
| plantName    | varchar(255)      | NOT NULL           |
| price        | double            | NOT NULL           |
| rating       | int               | NOT NULL           |
| plantType    | varchar(100)      | DEFAULT NULL       |
| plantHeight  | float             | DEFAULT NULL       |
| plantingSeason | varchar(100)     | DEFAULT NULL       |
| hybrid       | varchar(20)       | DEFAULT NULL       |

### PlantImagesUrl Table

This table stores URLs of images associated with each plant.

| Column    | Data Type         | Constraints        |
|-----------|-------------------|--------------------|
| url_id    | int               | NOT NULL, AUTO_INCREMENT |
| plant_id  | int               | NOT NULL           |
| image_url | varchar(100)      | NOT NULL           |

Primary Key: `url_id`
Foreign Key: `plant_id` references `plant` (`plant_id`)

The `plant_id` column references the `plant` table's `plant_id` column.

Example SQL:

CREATE TABLE plantimagesurl (
  `url_id` int NOT NULL AUTO_INCREMENT,
  `plant_id` int NOT NULL,
  `image_url` varchar(100) NOT NULL,
  PRIMARY KEY (`url_id`),
  KEY `plant_id` (`plant_id`),
  CONSTRAINT `plantimagesurl_ibfk_1` FOREIGN KEY (`plant_id`) REFERENCES `plant` (`plant_id`)
);


