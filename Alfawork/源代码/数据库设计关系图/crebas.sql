/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/12/1 星期五 上午 11:30:50                    */
/*==============================================================*/


drop table if exists EMenuInfos;

drop table if exists menurole;

drop table if exists org;

drop table if exists role;

drop table if exists user_basic;

drop table if exists user_login;

/*==============================================================*/
/* Table: EMenuInfos                                            */
/*==============================================================*/
create table EMenuInfos
(
   id                   int not null auto_increment,
   menuname             char(30),
   icon                 char(10),
   url                  varchar(100),
   parentid             int,
   cascadeid            int,
   system_time          datetime,
   primary key (id)
);

alter table EMenuInfos comment '菜单表';

/*==============================================================*/
/* Table: menurole                                              */
/*==============================================================*/
create table menurole
(
   id                   int not null,
   menuid               int,
   roleid               int,
   system_time          datetime,
   primary key (id)
);

alter table menurole comment '菜单角色关系表';

/*==============================================================*/
/* Table: org                                                   */
/*==============================================================*/
create table org
(
   id                   int not null auto_increment,
   orgname              varchar(30),
   country              varchar(6),
   province             varchar(6),
   city                 varchar(6),
   districts            varchar(6),
   street               varchar(30),
   address_all          varchar(60),
   person               varchar(5),
   check_state          tinyint,
   check_error          varchar(60),
   status               tinyint,
   license_imageid      int,
   groupid              int,
   priceid              int,
   facade_imageid       int,
   legal_person_phone   char(11),
   accountid            int,
   system_time          datetime,
   primary key (id)
);

alter table org comment '单位表';

/*==============================================================*/
/* Table: role                                                  */
/*==============================================================*/
create table role
(
   id                   int not null auto_increment,
   rolename             char(20),
   description          varchar(50),
   status               tinyint,
   system_time          datetime,
   primary key (id)
);

alter table role comment '角色表';

/*==============================================================*/
/* Table: user_basic                                            */
/*==============================================================*/
create table user_basic
(
   id                   int not null auto_increment,
   name                 char(10),
   mobile               char(11),
   orgid                int,
   roleid               int,
   remark               varchar(50),
   system_time          datetime,
   primary key (id)
);

alter table user_basic comment '用户基本信息表';

/*==============================================================*/
/* Table: user_login                                            */
/*==============================================================*/
create table user_login
(
   id                   int not null,
   userbasicid          int,
   password             varchar(30),
   status               tinyint,
   token                varchar(64),
   wxtoken              varchar(64),
   wxxtoken             varchar(64),
   system_time          datetime,
   ischeck              tinyint,
   isfullinfo           tinyint,
   primary key (id)
);

alter table user_login comment '用户登录信息表';

