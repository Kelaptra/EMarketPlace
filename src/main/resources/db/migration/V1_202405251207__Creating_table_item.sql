CREATE TABLE `item` (
                        `id` int unsigned NOT NULL AUTO_INCREMENT,
                        `name` varchar(45) CHARACTER SET utf16 COLLATE utf16_bin NOT NULL,
                        `price` double unsigned NOT NULL,
                        `description` varchar(45) NOT NULL,
                        `submission_time` timestamp NOT NULL,
                        `photo_url` varchar(45) CHARACTER SET utf16 COLLATE utf16_bin DEFAULT NULL,
                        PRIMARY KEY (`id`),
                        UNIQUE KEY `photo_url_UNIQUE` (`photo_url`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci