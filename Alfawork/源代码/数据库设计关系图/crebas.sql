/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/11/29 ������ ���� 4:20:11                    */
/*==============================================================*/


drop table if exists CommentReply;

drop table if exists LogForLogin;

drop table if exists MoneyPay;

drop table if exists Orderforone;

drop table if exists Orgs;

drop table if exists SysConfig;

drop table if exists fileinfo2;

drop table if exists messagepublish;

drop table if exists messageusers;

drop table if exists newrole;

drop table if exists newstop;

drop table if exists newtype;

drop table if exists oiladdress;

drop table if exists ordercomments;

drop table if exists ordercontrollog;

drop table if exists rolenew;

drop table if exists td_weixin_users2;

drop table if exists user_basic;

drop table if exists user_login;

drop table if exists userregisterbehavior;

drop table if exists userregisterbehavior2;

drop table if exists vfcode;

/*==============================================================*/
/* Table: CommentReply                                          */
/*==============================================================*/
create table CommentReply
(
   id                   bigint not null auto_increment,
   content              varchar(500),
   mobile               varchar(255),
   phone                varchar(255),
   commentid            bigint,
   createdBy            varchar(255),
   createdDt            datetime,
   updatedBy            varchar(255),
   updatedDt            datetime,
   version              bigint,
   primary key (id)
);

alter table CommentReply comment '���ۻظ���';

/*==============================================================*/
/* Table: LogForLogin                                           */
/*==============================================================*/
create table LogForLogin
(
   Id                   int not null auto_increment,
   userid               int,
   description          varchar(50),
   system_time          datetime,
   primary key (Id)
);

alter table LogForLogin comment '��¼��־��';

/*==============================================================*/
/* Table: MoneyPay                                              */
/*==============================================================*/
create table MoneyPay
(
   PayId                int not null auto_increment,
   accountBalance       decimal,
   description          varchar(50),
   types                tinyint,
   orderid              int,
   system_time          datetime,
   primary key (PayId)
);

alter table MoneyPay comment '֧����';

/*==============================================================*/
/* Table: Orderforone                                           */
/*==============================================================*/
create table Orderforone
(
   id                   int not null auto_increment,
   sn                   char(20),
   pail_num             int,
   real_pail_num        int,
   workerid             int,
   sellerid             int,
   pail_amount          decimal,
   delivery_amount      decimal,
   discount_amount      decimal,
   paild_amount         decimal,
   real_amount          decimal,
   status               tinyint,
   dispatch_status      tinyint,
   confirm_status       tinyint,
   delivery_status      tinyint,
   finish_status        tinyint,
   pay_status           tinyint,
   pay_time             datetime,
   system_time          datetime,
   addressid            int,
   service_amount       decimal,
   primary key (id)
);

alter table Orderforone comment '�¶�����';

/*==============================================================*/
/* Table: Orgs                                                  */
/*==============================================================*/
create table Orgs
(
   orgid                int not null auto_increment,
   orgname              varchar(50),
   orgboss              char(8),
   mobile               char(11),
   address              varchar(255),
   picture              int,
   weixin               varchar(20),
   system_time          datetime,
   latitude             double,
   longitude            double,
   primary key (orgid)
);

alter table Orgs comment '��ҵ��λ��';

/*==============================================================*/
/* Table: SysConfig                                             */
/*==============================================================*/
create table SysConfig
(
   Id                   bigint not null auto_increment,
   configName           varchar(255) not null,
   configKey            varchar(255) not null,
   configValue          varchar(255) not null,
   description          varchar(300),
   createdBy            varchar(255) not null,
   createdDt            datetime not null,
   updatedBy            varchar(255),
   updatedDt            datetime,
   version              bigint,
   primary key (Id)
);

alter table SysConfig comment 'ϵͳ���ñ�';

/*==============================================================*/
/* Table: fileinfo2                                             */
/*==============================================================*/
create table fileinfo2
(
   Id                   int not null auto_increment,
   file_name            varchar(50),
   url                  varchar(100),
   type                 char(8),
   system_time          datetime,
   primary key (Id)
);

alter table fileinfo2 comment '�ļ���Ϣ��';

/*==============================================================*/
/* Table: messagepublish                                        */
/*==============================================================*/
create table messagepublish
(
   id                   int not null auto_increment,
   title                char(30),
   content              varchar(100),
   system_time          datetime,
   primary key (id)
);

alter table messagepublish comment '��Ϣ֪ͨ��';

/*==============================================================*/
/* Table: messageusers                                          */
/*==============================================================*/
create table messageusers
(
   id                   int not null auto_increment,
   userid               int,
   messageid            int,
   isread               tinyint,
   system_time          datetime,
   primary key (id)
);

alter table messageusers comment '��Ϣ�û���';

/*==============================================================*/
/* Table: newrole                                               */
/*==============================================================*/
create table newrole
(
   id                   int not null auto_increment,
   newid                int,
   roleid               int,
   system_time          datetime,
   primary key (id)
);

alter table newrole comment '���Ž�ɫ��ϵ��';

/*==============================================================*/
/* Table: newstop                                               */
/*==============================================================*/
create table newstop
(
   id                   int not null auto_increment,
   title                char(30),
   content              text,
   publishDt            datetime,
   typeid               int,
   system_time          datetime,
   primary key (id)
);

alter table newstop comment '����ͷ��';

/*==============================================================*/
/* Table: newtype                                               */
/*==============================================================*/
create table newtype
(
   typeid               int not null auto_increment,
   typename             char(10),
   system_time          datetime,
   primary key (typeid)
);

alter table newtype comment '��������';

/*==============================================================*/
/* Table: oiladdress                                            */
/*==============================================================*/
create table oiladdress
(
   id                   int not null auto_increment,
   userid               int,
   province             char(10),
   city                 char(15),
   area                 char(20),
   townandstreets       varchar(50),
   ischeck              int,
   system_time          datetime,
   primary key (id)
);

alter table oiladdress comment '���͵�ַ��';

/*==============================================================*/
/* Table: ordercomments                                         */
/*==============================================================*/
create table ordercomments
(
   replycontent         varchar(100),
   id                   int not null,
   content              varchar(100),
   service_attitude     int,
   receiving_speed      int,
   oil_prices           int,
   system_time          datetime,
   average              int,
   workerid             int,
   sellerid             int,
   primary key (id)
);

alter table ordercomments comment '�������ۻظ���';

/*==============================================================*/
/* Table: ordercontrollog                                       */
/*==============================================================*/
create table ordercontrollog
(
   id                   int not null auto_increment,
   orderid              int,
   status               tinyint,
   description          varchar(50),
   system_time          datetime,
   primary key (id)
);

alter table ordercontrollog comment '����������־��';

/*==============================================================*/
/* Table: rolenew                                               */
/*==============================================================*/
create table rolenew
(
   id                   int not null,
   rolename             char(20),
   description          varchar(50),
   status               tinyint,
   system_time          datetime,
   primary key (id)
);

alter table rolenew comment '��ɫ��(��)';

/*==============================================================*/
/* Table: td_weixin_users2                                      */
/*==============================================================*/
create table td_weixin_users2
(
   Id                   int not null auto_increment,
   openid               varchar(100),
   headimgurl           varchar(255),
   status               tinyint,
   userid               int,
   system_time          datetime,
   primary key (Id)
);

alter table td_weixin_users2 comment '΢��Openid��';

/*==============================================================*/
/* Table: user_basic                                            */
/*==============================================================*/
create table user_basic
(
   id                   int not null,
   name                 char(10),
   mobile               char(11),
   orgid                int,
   roleid               int,
   remark               varchar(50),
   system_time          datetime,
   primary key (id)
);

alter table user_basic comment '�û�������Ϣ��';

/*==============================================================*/
/* Table: user_login                                            */
/*==============================================================*/
create table user_login
(
   id                   int not null,
   userid               int,
   password             varchar(30),
   status               tinyint,
   token                varchar(64),
   mobiletoken          varchar(64),
   system_time          datetime,
   ischeck              tinyint,
   primary key (id)
);

alter table user_login comment '�û���¼��Ϣ��';

/*==============================================================*/
/* Table: userregisterbehavior                                  */
/*==============================================================*/
create table userregisterbehavior
(
   id                   bigint not null auto_increment,
   userid               bigint,
   registerid           bigint,
   createdBy            varchar(255),
   createdDt            datetime,
   updatedBy            varchar(255),
   updatedDt            datetime,
   version              bigint,
   primary key (id)
);

alter table userregisterbehavior comment '�û�ע����Ϊ��';

/*==============================================================*/
/* Table: userregisterbehavior2                                 */
/*==============================================================*/
create table userregisterbehavior2
(
   id                   int not null auto_increment,
   userid               int,
   registerid           int,
   system_time          datetime,
   primary key (id)
);

alter table userregisterbehavior2 comment '�û�ע����Ϊ��(ҵ����Ա�������ϵ�λ)';

/*==============================================================*/
/* Table: vfcode                                                */
/*==============================================================*/
create table vfcode
(
   verifySid            int not null auto_increment,
   code                 char(6),
   type                 tinyint,
   boundAccount         char(11),
   system_time          datetime,
   primary key (verifySid)
);

alter table vfcode comment '��֤��';

