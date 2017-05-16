if exists (select 1
            from  sysobjects
           where  id = object_id('T_DICTIONARY')
            and   type = 'U')
   drop table T_DICTIONARY
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('T_JOB')
            and   name  = 'IDX_UNI_NAME'
            and   indid > 0
            and   indid < 255)
   drop index T_JOB.IDX_UNI_NAME
go

if exists (select 1
            from  sysobjects
           where  id = object_id('T_JOB')
            and   type = 'U')
   drop table T_JOB
go

if exists (select 1
            from  sysobjects
           where  id = object_id('T_ORG')
            and   type = 'U')
   drop table T_ORG
go

if exists (select 1
            from  sysobjects
           where  id = object_id('T_PARAMETER')
            and   type = 'U')
   drop table T_PARAMETER
go

if exists (select 1
            from  sysobjects
           where  id = object_id('T_RESOURCE')
            and   type = 'U')
   drop table T_RESOURCE
go

if exists (select 1
            from  sysobjects
           where  id = object_id('T_ROLE')
            and   type = 'U')
   drop table T_ROLE
go

if exists (select 1
            from  sysobjects
           where  id = object_id('T_ROLE_RESOURCE')
            and   type = 'U')
   drop table T_ROLE_RESOURCE
go

if exists (select 1
            from  sysobjects
           where  id = object_id('T_ROLE_USER')
            and   type = 'U')
   drop table T_ROLE_USER
go

if exists (select 1
            from  sysobjects
           where  id = object_id('T_SYS_LOG')
            and   type = 'U')
   drop table T_SYS_LOG
go

if exists (select 1
            from  sysobjects
           where  id = object_id('T_USER')
            and   type = 'U')
   drop table T_USER
go

/*==============================================================*/
/* Table: T_DICTIONARY                                          */
/*==============================================================*/
create table T_DICTIONARY (
   ID                   bigint               identity,
   MODULE_NAME          varchar(30)          not null,
   KEY_GROUP            varchar(30)          not null,
   KEY_NAME             varchar(50)          not null,
   KEY_DESC             varchar(256)         null,
   VALUE_UNIT           varchar(20)          null,
   VALUE_DESC           varchar(256)         null,
   VALUE                varchar(256)         not null,
   VALUE_ORDER          int                  not null,
   EFFECTIVE_DATE       datetime             null,
   EXPIRATION_DATE      datetime             null,
   constraint PK_T_DICTIONARY primary key nonclustered (ID)
)
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('T_DICTIONARY')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'VALUE_UNIT')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'T_DICTIONARY', 'column', 'VALUE_UNIT'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '单位',
   'user', @CurrentUser, 'table', 'T_DICTIONARY', 'column', 'VALUE_UNIT'
go

/*==============================================================*/
/* Table: T_JOB                                                 */
/*==============================================================*/
create table T_JOB (
   ID                   bigint               identity,
   JOB_NAME             varchar(50)          not null,
   JOB_SCHEDULE         varchar(100)         null,
   JOB_PROCESSOR        varchar(100)         null,
   JOB_PARAMETER        varchar(4096)        null,
   JOB_TYPE             int                  not null,
   EXEC_TYPE            int                  not null default 1,
   STATUS               int                  null,
   REMARK               varchar(512)         null,
   CREATE_BY            bigint               null,
   CREATE_DATE          datetime             null,
   UPDATE_BY            bigint               null,
   UPDATE_DATE          datetime             null,
   constraint PK_T_JOB primary key nonclustered (ID)
)
go

if exists (select 1 from  sys.extended_properties
           where major_id = object_id('T_JOB') and minor_id = 0)
begin 
   declare @CurrentUser sysname 
select @CurrentUser = user_name() 
execute sp_dropextendedproperty 'MS_Description',  
   'user', @CurrentUser, 'table', 'T_JOB' 
 
end 


select @CurrentUser = user_name() 
execute sp_addextendedproperty 'MS_Description',  
   '定时作业配置信息', 
   'user', @CurrentUser, 'table', 'T_JOB'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('T_JOB')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'ID')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'T_JOB', 'column', 'ID'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '作业编号',
   'user', @CurrentUser, 'table', 'T_JOB', 'column', 'ID'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('T_JOB')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'JOB_NAME')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'T_JOB', 'column', 'JOB_NAME'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '作业名称',
   'user', @CurrentUser, 'table', 'T_JOB', 'column', 'JOB_NAME'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('T_JOB')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'JOB_SCHEDULE')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'T_JOB', 'column', 'JOB_SCHEDULE'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '定时表达式，CRON表达式',
   'user', @CurrentUser, 'table', 'T_JOB', 'column', 'JOB_SCHEDULE'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('T_JOB')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'JOB_PROCESSOR')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'T_JOB', 'column', 'JOB_PROCESSOR'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '作业服务类',
   'user', @CurrentUser, 'table', 'T_JOB', 'column', 'JOB_PROCESSOR'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('T_JOB')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'JOB_PARAMETER')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'T_JOB', 'column', 'JOB_PARAMETER'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '作业参数',
   'user', @CurrentUser, 'table', 'T_JOB', 'column', 'JOB_PARAMETER'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('T_JOB')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'JOB_TYPE')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'T_JOB', 'column', 'JOB_TYPE'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '作业类型，预留字段',
   'user', @CurrentUser, 'table', 'T_JOB', 'column', 'JOB_TYPE'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('T_JOB')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'EXEC_TYPE')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'T_JOB', 'column', 'EXEC_TYPE'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '1 自动
   0 手动
   ',
   'user', @CurrentUser, 'table', 'T_JOB', 'column', 'EXEC_TYPE'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('T_JOB')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'STATUS')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'T_JOB', 'column', 'STATUS'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '1 启用
   0 未启用',
   'user', @CurrentUser, 'table', 'T_JOB', 'column', 'STATUS'
go

/*==============================================================*/
/* Index: IDX_UNI_NAME                                          */
/*==============================================================*/
create unique index IDX_UNI_NAME on T_JOB (
JOB_NAME ASC
)
go

/*==============================================================*/
/* Table: T_ORG                                                 */
/*==============================================================*/
create table T_ORG (
   ID                   bigint               identity,
   ORG_CODE             varchar(100)         null,
   ORG_NAME             varchar(100)         null,
   PARENT_ID            bigint               null,
   ORG_LEVEL            int                  null,
   ORG_PATH             varchar(200)         null,
   ORG_STATUS           int                  null,
   ORDER_NO             int                  null,
   CREATE_BY            bigint               null,
   CREATE_DATE          datetime             null,
   UPDATE_BY            bigint               null,
   UPDATE_DATE          datetime             null,
   constraint PK_T_ORG primary key nonclustered (ID)
)
go

/*==============================================================*/
/* Table: T_PARAMETER                                           */
/*==============================================================*/
create table T_PARAMETER (
   ID                   bigint               identity,
   PARAM_GROUP          varchar(50)          not null,
   PARAM_NAME           varchar(100)         not null,
   PARAM_VALUE          varchar(200)         not null,
   LAST_UPDATE          datetime             null,
   COMMENTS             varchar(200)         null,
   STATUS               int                  null,
   constraint PK_T_PARAMETER primary key nonclustered (ID)
)
go

/*==============================================================*/
/* Table: T_RESOURCE                                            */
/*==============================================================*/
create table T_RESOURCE (
   ID                   bigint               identity,
   RES_CODE             varchar(50)          null,
   RES_TYPE             varchar(20)          null,
   RES_NAME             varchar(100)         null,
   RES_DESC             varchar(200)         null,
   PARENT_ID            bigint               null,
   RES_STATUS           int                  null,
   RES_MODULE           varchar(20)          null,
   RES_URL              varchar(200)         null,
   RES_ICON             varchar(250)         null,
   ORDER_NO             int                  null,
   OPERATIONS           varchar(512)         null,
   constraint PK_T_RESOURCE primary key nonclustered (ID)
)
go

if exists (select 1 from  sys.extended_properties
           where major_id = object_id('T_RESOURCE') and minor_id = 0)
begin 
   declare @CurrentUser sysname 
select @CurrentUser = user_name() 
execute sp_dropextendedproperty 'MS_Description',  
   'user', @CurrentUser, 'table', 'T_RESOURCE' 
 
end 


select @CurrentUser = user_name() 
execute sp_addextendedproperty 'MS_Description',  
   '功能菜单按钮等资源', 
   'user', @CurrentUser, 'table', 'T_RESOURCE'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('T_RESOURCE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'RES_TYPE')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'T_RESOURCE', 'column', 'RES_TYPE'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'MENU, BUTTON, ',
   'user', @CurrentUser, 'table', 'T_RESOURCE', 'column', 'RES_TYPE'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('T_RESOURCE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'OPERATIONS')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'T_RESOURCE', 'column', 'OPERATIONS'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '逗号分隔操作代码和描述，分号分隔操作',
   'user', @CurrentUser, 'table', 'T_RESOURCE', 'column', 'OPERATIONS'
go

/*==============================================================*/
/* Table: T_ROLE                                                */
/*==============================================================*/
create table T_ROLE (
   ID                   bigint               identity,
   ROLE_CODE            varchar(50)          null,
   ROLE_NAME            varchar(100)         null,
   ROLE_DESC            varchar(200)         null,
   ROLE_STATUS          int                  null,
   WELCOME_URL          varchar(100)         null,
   ORDER_NO             int                  null,
   CREATE_BY            bigint               null,
   CREATE_DATE          datetime             null,
   UPDATE_BY            bigint               null,
   UPDATE_DATE          datetime             null,
   constraint PK_T_ROLE primary key nonclustered (ID)
)
go

/*==============================================================*/
/* Table: T_ROLE_RESOURCE                                       */
/*==============================================================*/
create table T_ROLE_RESOURCE (
   ID                   bigint               identity,
   ROLE_ID              bigint               not null,
   RES_ID               bigint               not null,
   OPERATIONS           varchar(512)         null,
   constraint PK_T_ROLE_RESOURCE primary key nonclustered (ID)
)
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('T_ROLE_RESOURCE')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'OPERATIONS')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'T_ROLE_RESOURCE', 'column', 'OPERATIONS'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '逗号分隔操作代码和描述，分号分隔操作',
   'user', @CurrentUser, 'table', 'T_ROLE_RESOURCE', 'column', 'OPERATIONS'
go

/*==============================================================*/
/* Table: T_ROLE_USER                                           */
/*==============================================================*/
create table T_ROLE_USER (
   ID                   bigint               identity,
   ROLE_ID              bigint               not null,
   USER_ID              bigint               not null,
   constraint PK_T_ROLE_USER primary key nonclustered (ID)
)
go

/*==============================================================*/
/* Table: T_SYS_LOG                                             */
/*==============================================================*/
create table T_SYS_LOG (
   ID                   bigint               identity,
   LOG_TYPE             varchar(10)          null,
   USER_ID              bigint               null,
   USER_NAME            varchar(100)         null,
   LOG_TIME             datetime             null,
   IP_ADDRESS           varchar(50)          null,
   OPERATION            varchar(50)          null,
   LOG_DESC             varchar(2000)        null,
   constraint PK_T_SYS_LOG primary key nonclustered (ID)
)
go

/*==============================================================*/
/* Table: T_USER                                                */
/*==============================================================*/
create table T_USER (
   ID                   bigint               identity,
   USER_NAME            varchar(100)         null,
   USER_PASSWORD        varchar(50)          null,
   USER_FULL_NAME       varchar(200)         null,
   GENDER               varchar(5)           null,
   EMAIL                varchar(50)          null,
   MOBILE               varchar(50)          null,
   TELEPHONE            varchar(50)          null,
   ORG_ID               bigint               not null,
   USER_STATUS          int                  null,
   ORDER_NO             int                  null,
   REMARK               varchar(200)         null,
   CREATE_BY            bigint               null,
   CREATE_DATE          datetime             null,
   UPDATE_BY            bigint               null,
   UPDATE_DATE          datetime             null,
   constraint PK_T_USER primary key nonclustered (ID)
)
go
