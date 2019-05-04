/* 初始化管理员SQL*/
INSERT ignore INTO `sk_privilege` (`pid`, `create_user`,`update_user`, `privilege_name`) VALUES (1,  1, 1, 'create');
INSERT ignore INTO `sk_privilege` (`pid`, `create_user`,`update_user`, `privilege_name`) VALUES (2,  1, 1, 'update');
INSERT ignore INTO `sk_privilege` (`pid`, `create_user`,`update_user`, `privilege_name`) VALUES (3,  1, 1, 'delete');
INSERT ignore INTO `sk_privilege` (`pid`, `create_user`,`update_user`, `privilege_name`) VALUES (4,  1, 1, 'select');

INSERT ignore INTO `sk_role` (`rid`, `create_user`,`update_user`, `role_name`) VALUES (1,  1, 1, 'admin');

INSERT ignore INTO `sk_user` (`uid`, `create_user`, `update_user`, `enabled`, `expired`, `locked`, `password`, `username`) VALUES (1, 1, 1, true, false, false, "$2a$10$/sfJiCwx0Hd2rbdTKKyzKOfx/Pi7JhguIS/Yq8cCFEc7T2eh9yORe", "system");
INSERT ignore INTO `sk_user` (`uid`, `create_user`, `update_user`, `enabled`, `expired`, `locked`, `password`, `username`) VALUES (2, 1, 1, true, false, false, "$2a$10$Bc514O6KlhsI3II3c7Uw/.pfIl4AL3TgAAZrLjmkQoLE75gkIntgC", "admin");

insert ignore into `user_role`(`uid`,`rid`) values (2,1);

insert ignore into `role_privilege`(`rid`,`pid`) values (1,1);
insert ignore into `role_privilege`(`rid`,`pid`) values (1,2);
insert ignore into `role_privilege`(`rid`,`pid`) values (1,3);
insert ignore into `role_privilege`(`rid`,`pid`) values (1,4);
