# MyBatis学习  

## 简介  
MyBatis是一款优秀的持久层框架，它支持定制化SQL、存储过程以及高级映射。  

## XML配置  

### 类型处理器（typeHandlers）  
1. 作用  
MyBatis在预处理语句中设置参数时，以及从结果集中取值时，都会用类型处理器将获取的值转换成Java类型。  
MyBatis默认支持的类型处理器参考：[!typeHandlers](http://www.mybatis.org/mybatis-3/zh/configuration.html#typeHandlers)  
2. 实现自定义的类型处理器  
* 1)继承org.apache.ibatis.type.BaseTypeHandler或实现org.apache.ibatis.type.TypeHandler接口  
* 2)指定其映射某个JDBC类型
* 3)在mybatis配置文件中配置`<typeHandlers></typeHandlers>`  

### 插件（plugins）  
MyBatis允许在已映射语句执行过程中的某一点进行拦截调用。默认情况下，MyBatis允许使用插件来拦截的方法调用包括：  
* Executor (update, query, flushStatements, commit, rollback, getTransaction, close, isClosed)
* ParameterHandler (getParameterObject, setParameters)
* ResultSetHandler (handleResultSets, handleOutputParameters)
* StatementHandler (prepare, parameterize, batch, update, query)  

### 映射器（mappers）  
```xml

<mappers>
    <!--使用相对路径-->
    <mapper resource=""></mapper>
    <!--使用映射器接口实现类的完全限定类名-->
    <mapper class=""></mapper>
    <!-- 使用完全限定资源定位符（URL） -->
    <mapper url=""></mapper>
    <!-- 将包内的映射器接口实现全部注册为映射器 -->
    <mapper package=""></mapper>
</mappers>
```

### 应用细节  
#### insert获取自增主键的值  
1. MySQL支持自增主键
```xml
<!-- 使用useGeneratedKeys，keyProperty(对应java bean的属性)配置 -->
<insert id="addEmp" parameterType="cn.np.mybatis.bean.Employee" useGeneratedKeys="true" keyProperty="id">
        insert into employee(name, age, sex, department)
        values(#{name}, #{age}, #{sex}, #{department})
</insert>
```
2. Oracle  
Oracle不支持自增主键，一般使用序列达到自增的效果。  
先用`<selectkey>`查询id的sql；查询id值封装給java bean的属性；在允许插入时就可以取java bean属性的值。  
```xml
<!-- before -->
<insert id="addEmp" parameterType="cn.np.mybatis.bean.Employee">
  <selectkey keyProperty="id" order="BEFORE" resultType="Integer">
    <!-- EMPLOYEES_SEQ是已定义好的序列 -->
    select EMPLOYEES_SEQ.nextval from dual
  </selectkey>
   insert into employee(id, name, age, sex, department)
   values(#{id}, #{name}, #{age}, #{sex}, #{department})
</insert>

```

#### 参数处理  
1. 单个参数 
当入参只有一个参数时，`#{}`里的名称可以随意编写，但建议和形参名称一致。  
```
    // 查-单个参数
    Employee queryEmpById(Integer id);
    
    <!-- 注意这里写成idsadd也可以成功执行，建议写成id -->
    <select id="queryEmpById" resultType="cn.np.mybatis.bean.Employee">
        select * from employee where id = #{idsadd}
    </select>
``` 
2. 多个参数  
当入参是多个参数时，如果按照以下方式进行操作会**报错**：Caused by: org.apache.ibatis.binding.BindingException: Parameter 'name' not found. Available parameters are [0, 1, param1, param2]    
```
    // 以这种方式操作会报错，是错误的使用方法
    
    // 查-多个参数
    Employee queryEmpByNameAge(String name, Integer age);
    
    <select id="queryEmpByNameAge" resultType="cn.np.mybatis.bean.Employee">
        select * from employee where name = #{name} and age = #{age}
    </select>

``` 

原因是：多个参数时MyBatis会把它们封装成一个map，#{}就是从map获取指定的key值，其中key：param1...paramN。  
以上查询按照如下方式修改即可：  
```
    <select id="queryEmpByNameAge" resultType="cn.np.mybatis.bean.Employee">
        select * from employee where name = #{param1} and age = #{param2}
    </select>
```

3. 命名参数  
多个参数的情况下推荐使用命名参数，命名参数是只封装map时明确指定key的名称，使用`@Param`注解完成。  
```
    // 查-多个参数
    Employee queryEmpByNameAge(@Param("name") String name, @Param("age") Integer age);
    
    <select id="queryEmpByNameAge" resultType="cn.np.mybatis.bean.Employee">
        select * from employee where name = #{name} and age = #{age}
    </select>
```
4. POJO  
当多个参数是业务逻辑的数据模型，就可以直接传入POJO，使用`#{属性名}`取出传入POJO的属性值。  
否则，可以直接传入Map，使用#{key}取出map中对应的值。  

#### \#{}与\${}的区别  
${}:取出的值直接拼接在sql语句中，会有安全问题，应用场景有分表拼接表名等；  
\#{}:以预编译的形式，将参数设置到sql语句中，能防止sql注入； 

\#{}更丰富的用法：javaType,jdbcType,mode(存储过程) 

#### resultType  
1. 返回结果为List时，resultType等于java bean；  
2. 返回结果为Map时，resultType="map";  
3. 返回结果为Map且指定某属性为key，java bean为value时，在Mapper接口方法上使用注解`@MapKey`,如下：  
```
// 使用查询出的结果id为key
@MapKey("id")
Map<Integer,Blog> queryMapByTitle(@Param("title")String title);
```
#### resultMap  
1. 关联查询-级联属性  
```
public class Employee {
    private int id;
    private String name;
    private int age;
    private String sex;
    private Department department;
    
    //setter(),getter()省略
}

public class Department {
    private int id;
    private String deptName;
    // 使用collection
    private List<Employee> emps;
    //setter(),getter()省略
}

```
查询出员工的信息以及部门信息：  
```
public interface EmployeeMapper {
    // 关联查询-级联属性结果
    Employee queryEmpAndDept(@Param("id") Integer id);
}
```
```
<!-- 使用级联属性 -->
<resultMap id="myEmp" type="cn.np.mybatis.bean.Employee">
    <id property="id" column="id"></id>
    <result property="name" column="name"></result>
    <result property="age" column="age"></result>
    <result property="sex" column="sex"></result>
    <!-- department属性 -->
    <result property="department.id" column="did"></result>
    <result property="department.deptName" column="dept_name"></result>
</resultMap>

<select id="queryEmpAndDept" resultMap="myEmp">
   select e.id id,e.name name,e.age age,e.sex sex,e.salary salary,d.id did,d.dept_name dept_name
   from employee e, tbl_dept d
   where e.dept_id = d.id and e.id = #{id}
</select>
```
  
2. association  
使用association可以指定联合的java bean对象。  
```
<resultMap id="empDeptMap" type="cn.np.mybatis.bean.Employee">
    <id property="id" column="id"></id>
    <result property="name" column="name"></result>
    <result property="age" column="age"></result>
    <result property="sex" column="sex"></result>
    <!--  property指定外层java bean属性名称 -->
    <association property="department" javaType="cn.np.mybatis.bean.Department">
        <id property="id" column="did"></id>
        <result property="deptName" column="dept_name"></result>
    </association>
</resultMap>

<select id="queryEmpAndDept" resultMap="empDeptMap">
   select e.id id,e.name name,e.age age,e.sex sex,e.salary salary,d.id did,d.dept_name dept_name
   from employee e, tbl_dept d
   where e.dept_id = d.id and e.id = #{id}
</select>

```  
使用association还可以用分步查询得到上面的结果。具体实现：[具体实现](https://github.com/npvip/StudyNote/blob/master/src/main/java/cn/np/mybatis/mapper/BlogMapper.xml)    
  
3. collection  
collection的作用与association相似。注意`ofType`属性，这个属性用来区分 JavaBean(或字段)属性类型和集合包含的类型。  
```
public interface DepartmentMapper {

    Department queryDepartEmp(String name);
}
```
```
<resultMap id="departEmpMap" type="cn.np.mybatis.bean.Department">
     <id property="id" column="did"></id>
     <result property="deptName" column="dept_name"></result>
   <collection property="emps" ofType="cn.np.mybatis.bean.Employee">
     <result property="name" column="ename"></result>
     <result property="age" column="age"></result>
     <result property="sex" column="sex"></result>
   </collection>
</resultMap>

<select id="queryDepartEmp" resultMap="departEmpMap">
    select d.id did, d.dept_name dept_name, e.name ename, e.age age, e.sex sex
    from tbl_dept d left join employee e on d.id = e.dept_id
    where d.dept_name = #{name}
</select>
```


#### 动态SQL  
  


## 参考  
* mybatis参考文档：http://www.mybatis.org/mybatis-3/zh/index.html  