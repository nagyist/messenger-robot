
-- ----------------------------  
-- Records of users  
-- ----------------------------  
INSERT INTO `users` VALUES ('root', 'root', '1');  
INSERT INTO `users` VALUES ('user', 'user', '1'); 
  
-- ----------------------------  
-- Records of authorities  
-- ----------------------------  
INSERT INTO `authorities` VALUES ('root', 'ROLE_ADMIN');  
INSERT INTO `authorities` VALUES ('user', 'ROLE_USER');  
INSERT INTO `authorities` VALUES ('root', 'ROLE_USER');  