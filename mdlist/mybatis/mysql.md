# MySQL语句

## 用户 
### 创建用户名为test，密码为test_1234的本地用户：   
```sql
CREATE USER 'test'@'localhost' IDENTIFIED BY 'test_1234';
```
### 授权语句：  
```sql
// 创建用户并授权
GRANT 权限 ON 权限范围 TO 用户 IDENTIFIED BY '密码';
// 对已有用户授权
GRANT 权限 ON 权限范围 TO 用户;
```
* 权限：INSERT,DELETE,UPDATE,CREATE,DROP等；数据库已定义好的权限，ALL、PRIVILEGES、REPLICATION、SLAVE。  
* 权限范围：
  *  全库级别：*.*  
  *  单库级别：mytest.*  
  *  单表级别：mytest.student  
* 用户：'test'@'localhost'（本地），'test'@'192.168.34.149'等。  

## 表  
#### 修改表结构  
1. 修改字段名称  
```sql
ALTER TABLE [表名] CHANGE COLUMN [原列名] [新列名] [类型约束];
```
2. 修改字段数据类型  
```sql
ALTER TABLE [表名] MODIFY [列名] [数据类型];
``` 
  
  
#### 表结构  
1. 查看表结构、字段类型 
```sql
-- 显示字段，字段类型，是否为空，是否为主键，默认属性等
DESC tabl_name;
``` 
2. 查询表中列的注释信息  
```sql
SELECT * FROM information_schema.columns
WHERE table_schema = 'db' #表所在数据库
AND table_name = 'tablename' ; #你要查的表
--只查询列名和注释
SELECT column_name, column_comment FROM information_schema.columns
WHERE table_schema = 'db' #表所在数据库
AND table_name = 'tablename' ; #你要查的表

```
3. 查看表生成的DDL  
```sql
SHOW CREATE TABLE table_name;
```

## 脚本  
1. 执行  
```sql
--在命令行操作时
SOURCE 文件绝对路径
```
  