DROP TABLE IF EXISTS `users`;  
CREATE TABLE `users` (  
  `username` varchar(50) NOT NULL,  
  `password` varchar(50) NOT NULL,  
  `enabled` tinyint(1) NOT NULL,  
  PRIMARY KEY (`username`)  
) ENGINE=InnoDB DEFAULT CHARSET=utf8;  
  

DROP TABLE IF EXISTS `authorities`;  
CREATE TABLE `authorities` (  
  `username` varchar(50) NOT NULL,  
  `authority` varchar(50) NOT NULL,  
  KEY `fk_authorities_users` (`username`),  
  CONSTRAINT `fk_authorities_users` FOREIGN KEY (`username`) REFERENCES `users` (`username`)  
) ENGINE=InnoDB DEFAULT CHARSET=utf8;  