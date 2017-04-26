/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/4/26 17:02:02                           */
/*==============================================================*/


drop table if exists SysAccount;

drop table if exists SysCategory;

drop table if exists SysConfig;

drop table if exists SysDicDetail;

drop table if exists SysDicIndex;

drop table if exists SysModuleElement;

drop table if exists SysModules;

drop table if exists SysOrg;

drop table if exists SysRelevance;

drop table if exists SysRole;

drop table if exists SysUsers;

/*==============================================================*/
/* Table: SysAccount                                            */
/*==============================================================*/
create table SysAccount
(
   Id                   bigint not null,
   Account_name         varchar(255) not null,
   Account_type         varchar(100) not null,
   Account_typename     varchar(255) not null,
   UsableCredit         bigint not null,
   accountBalance       varchar(255) not null,
   accountdeposit       varchar(255) not null,
   version              bigint not null,
   status               varchar(100) not null,
   statusName           varchar(255) not null,
   description          varchar(255),
   createdBy            varchar(255) not null,
   createdDt            datetime not null,
   updatedBy            varchar(255),
   updatedDt            datetime,
   userId               bigint not null,
   primary key (Id)
);

alter table SysAccount comment '账户表';

/*==============================================================*/
/* Table: SysCategory                                           */
/*==============================================================*/
create table SysCategory
(
   Id                   bigint not null auto_increment,
   Name                 varchar(255) not null,
   Status               int not null,
   SortNo               int not null,
   Description          varchar(255),
   primary key (Id)
);

alter table SysCategory comment '分类表';

/*==============================================================*/
/* Table: SysConfig                                             */
/*==============================================================*/
create table SysConfig
(
   Id                   bigint not null,
   configName           varchar(255) not null,
   configKey            varchar(255) not null,
   configValue          varchar(255) not null,
   description          varchar(300),
   createdBy            varchar(255) not null,
   createdDt            datetime not null,
   updatedBy            varchar(255),
   updatedDt            datetime,
   version              bigint not null,
   primary key (Id)
);

alter table SysConfig comment '系统配置表';

/*==============================================================*/
/* Table: SysDicDetail                                          */
/*==============================================================*/
create table SysDicDetail
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

alter table SysDicDetail comment '数据字典详情';

/*==============================================================*/
/* Table: SysDicIndex                                           */
/*==============================================================*/
create table SysDicIndex
(
   Id                   bigint not null auto_increment,
   Name                 varchar(255) not null,
   EKeys                varchar(255) not null,
   SortNo               int not null,
   Description          varchar(255) not null,
   CategoryId           bigint,
   primary key (Id)
);

alter table SysDicIndex comment '数据字典';

/*==============================================================*/
/* Table: SysModuleElement                                      */
/*==============================================================*/
create table SysModuleElement
(
   Id                   bigint not null,
   DomId                varchar(255) not null,
   Name                 varchar(255) not null,
   Types                varchar(100) not null,
   Attr                 varchar(500) not null,
   Script               varchar(500) not null,
   Icon                 varchar(255) not null,
   Classs               varchar(255) not null,
   Remark               varchar(200),
   SortNo               int not null,
   ModuleId             bigint not null,
   primary key (Id)
);

alter table SysModuleElement comment '模块元素表(需要权限控制的按钮)';

/*==============================================================*/
/* Table: SysModules                                            */
/*==============================================================*/
create table SysModules
(
   Id                   bigint not null auto_increment,
   CascadeId            varchar(255) not null,
   Name                 varchar(255) not null,
   Url                  varchar(255) not null,
   IsLeaf               varchar(100) not null,
   IsAutoExpand         varchar(100) not null,
   IconName             varchar(255) not null,
   Status               varchar(100) not null,
   ParentName           varchar(255) not null,
   Vector               varchar(255) not null,
   SortNo               int not null,
   ParentId             bigint,
   primary key (Id)
);

alter table SysModules comment '功能模块表';

/*==============================================================*/
/* Table: SysOrg                                                */
/*==============================================================*/
create table SysOrg
(
   CascadeId            varchar(255) not null,
   depart_fullname      varchar(255) not null,
   Id                   bigint not null auto_increment,
   depart_name          varchar(255),
   depart_name_e        varchar(255),
   depart_code          varchar(100),
   status               varchar(100) not null,
   types                varchar(100) not null,
   createdDt            datetime not null,
   createdBy            varchar(255) not null,
   ParentName           varchar(255) not null,
   ParentId             bigint,
   IsLeaf               varchar(100) not null,
   IsAutoExpand         varchar(100) not null,
   IconName             varchar(255) not null,
   sortno               int not null,
   version              bigint not null,
   updatedBy            varchar(255),
   updatedDt            datetime,
   statusname           varchar(255) not null,
   typesname            varchar(255) not null,
   primary key (Id)
);

alter table SysOrg comment '机构表';

/*==============================================================*/
/* Table: SysRelevance                                          */
/*==============================================================*/
create table SysRelevance
(
   Id                   bigint not null auto_increment,
   Description          varchar(255) not null,
   MappKey              varchar(255) not null,
   Status               varchar(100) not null,
   OperatorTime         datetime not null,
   OperatorId           varchar(255) not null,
   FirstId              bigint not null,
   SecondId             bigint not null,
   Statusname           varchar(255) not null,
   primary key (Id)
);

alter table SysRelevance comment '多对多关系集中映射';

/*==============================================================*/
/* Table: SysRole                                               */
/*==============================================================*/
create table SysRole
(
   Id                   bigint not null auto_increment,
   role_name            varchar(255) not null,
   types                varchar(100) not null,
   status               varchar(100) not null,
   createdDt            datetime not null,
   createdBy            varchar(255) not null,
   version              bigint not null,
   updatedBy            varchar(255),
   updatedDt            datetime,
   typesname            varchar(255) not null,
   statusname           varchar(255) not null,
   roleDesc             varchar(255),
   primary key (Id)
);

alter table SysRole comment '角色表';

/*==============================================================*/
/* Table: SysUsers                                              */
/*==============================================================*/
create table SysUsers
(
   updatedBy            varchar(255),
   Id                   bigint not null auto_increment,
   username             varchar(255) not null,
   password             varchar(255) not null,
   phone                varchar(255) not null,
   sexname              varchar(255) not null,
   sex                  varchar(100) not null,
   statusname           varchar(255) not null,
   status               varchar(100) not null,
   typesname            varchar(255) not null,
   types                varchar(100) not null,
   address              varchar(255),
   remarks              varchar(255),
   token                varchar(255) not null,
   createdDt            datetime not null,
   createdBy            varchar(255) not null,
   version              bigint not null,
   updatedDt            datetime,
   errorCount           int,
   LoginIp              varchar(255) not null,
   primary key (Id)
);

alter table SysUsers comment '用户表';

alter table SysDicDetail add constraint FK_RF_DICDETAI_DICINDEX foreign key (DicId)
      references SysDicIndex (Id) on delete restrict on update restrict;

alter table SysModuleElement add constraint FK_FK_PAGEELEM_REFERENCE_MODULE foreign key (ModuleId)
      references SysModules (Id) on delete restrict on update restrict;

