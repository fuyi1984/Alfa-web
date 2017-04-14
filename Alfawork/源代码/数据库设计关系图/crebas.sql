/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/4/14 12:59:31                           */
/*==============================================================*/


drop table if exists Category;

drop table if exists DicDetail;

drop table if exists DicIndex;

drop table if exists ModuleElement;

drop table if exists Modules;

drop table if exists Org;

drop table if exists Relevance;

drop table if exists Role;

drop table if exists Users;

/*==============================================================*/
/* Table: Category                                              */
/*==============================================================*/
create table Category
(
   Id                   bigint not null auto_increment,
   Name                 varchar(255) not null,
   Status               int not null,
   SortNo               int not null,
   Description          varchar(255),
   primary key (Id)
);

alter table Category comment '分类表';

/*==============================================================*/
/* Table: DicDetail                                             */
/*==============================================================*/
create table DicDetail
(
   Id                   bigint not null auto_increment,
   Val                  varchar(255) not null,
   Text                 varchar(255) not null,
   SortNo               int not null,
   Status               int not null,
   DicId                bigint not null,
   Description          varchar(255) not null,
   primary key (Id)
);

alter table DicDetail comment '数据字典详情';

/*==============================================================*/
/* Table: DicIndex                                              */
/*==============================================================*/
create table DicIndex
(
   Id                   bigint not null auto_increment,
   Name                 varchar(255) not null,
   EKeys                varchar(255) not null,
   SortNo               int not null,
   Description          varchar(255) not null,
   CategoryId           bigint,
   primary key (Id)
);

alter table DicIndex comment '数据字典';

/*==============================================================*/
/* Table: ModuleElement                                         */
/*==============================================================*/
create table ModuleElement
(
   Id                   bigint not null,
   DomId                varchar(255) not null,
   Name                 varchar(255) not null,
   Types                varchar(100) not null,
   Attr                 varchar(500) not null,
   Script               varchar(500) not null,
   Icon                 varchar(255) not null,
   Classs               varchar(255) not null,
   Remark               varchar(200) not null,
   Sort                 int not null,
   ModuleId             bigint not null,
   primary key (Id)
);

alter table ModuleElement comment '模块元素表(需要权限控制的按钮)';

/*==============================================================*/
/* Table: Modules                                               */
/*==============================================================*/
create table Modules
(
   Id                   bigint not null auto_increment,
   CascadeId            varchar(255) not null,
   Name                 varchar(255) not null,
   Url                  varchar(255) not null,
   IsLeaf               bit not null,
   IsAutoExpand         bit not null,
   IconName             varchar(255) not null,
   Status               int not null,
   ParentName           varchar(255) not null,
   Vector               varchar(255) not null,
   SortNo               int not null,
   ParentId             bigint,
   primary key (Id)
);

alter table Modules comment '功能模块表';

/*==============================================================*/
/* Table: Org                                                   */
/*==============================================================*/
create table Org
(
   Id                   bigint not null auto_increment,
   CascadeId            varchar(255) not null,
   depart_fullname      varchar(255) not null,
   depart_name          varchar(255),
   depart_name_e        varchar(255),
   depart_code          varchar(100),
   status               int not null,
   types                int not null,
   create_date          datetime not null,
   bycreater_id         bigint not null,
   ParentName           varchar(255) not null,
   ParentId             bigint,
   IsLeaf               bit not null,
   IsAutoExpand         bit not null,
   IconName             varchar(255) not null,
   sortno               int not null,
   primary key (Id)
);

alter table Org comment '机构表';

/*==============================================================*/
/* Table: Relevance                                             */
/*==============================================================*/
create table Relevance
(
   Id                   bigint not null auto_increment,
   Description          varchar(255) not null,
   MappKey              varchar(255) not null,
   Status               int not null,
   OperatorTime         datetime not null,
   OperatorId           int not null,
   FirstId              bigint not null,
   SecondId             bigint not null,
   primary key (Id)
);

alter table Relevance comment '多对多关系集中映射';

/*==============================================================*/
/* Table: Role                                                  */
/*==============================================================*/
create table Role
(
   Id                   bigint not null auto_increment,
   role_name            varchar(255) not null,
   types                int not null,
   status               int not null,
   create_date          datetime not null,
   bycreater_id         bigint not null,
   primary key (Id)
);

alter table Role comment '角色表';

/*==============================================================*/
/* Table: Users                                                 */
/*==============================================================*/
create table Users
(
   Id                   bigint not null auto_increment,
   username             varchar(255) not null,
   password             varchar(255) not null,
   phone                varchar(255) not null,
   sex                  int not null,
   status               int not null,
   types                int not null,
   address              varchar(255),
   remarks              varchar(255),
   token                varchar(255) not null,
   create_date          datetime not null,
   bycreater_id         bigint not null,
   primary key (Id)
);

alter table Users comment '用户表';

alter table DicDetail add constraint FK_RF_DICDETAI_DICINDEX foreign key (DicId)
      references DicIndex (Id) on delete restrict on update restrict;

alter table ModuleElement add constraint FK_FK_PAGEELEM_REFERENCE_MODULE foreign key (ModuleId)
      references Modules (Id) on delete restrict on update restrict;

