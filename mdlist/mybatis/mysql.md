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