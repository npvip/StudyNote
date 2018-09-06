1、增删改的返回可以是Long.Integer,Boolean  
2、mysql支持自增主键  


**参数处理**  
1、单个参数  
2、多个参数：底层封装成一个Map,key依次为param1,...,paramn;  
    建议使用命名参数@Param()   

**参数取值#或$**  
\#:预编译，将参数设置到sql中；PreparedStatement;防止sql注入  
$:取出的值直接拼接在sql中;原生sql不支持占位符的地方可以使用$（如排序、分表等）  
   
    
**mybatis缓存**  
1、一级缓存：本地缓存，sqlSession级别的，默认是一直开启的。  
    与数据库同一次会话期间查询到的数据会放在本地缓存中  
  **失效的情况**  
  （1）sqlSession不同；  
  （2）sqlSession相同，查询条件不同；  
  （3）sqlSession相同，两次查询中间执行了任一增删改操作；  
  （4）sqlSession相同，清除缓存clearCache。  
    
2、二级缓存：全局缓存,基于namespace，一个namespace对应一个二级缓存  
    使用步骤：    
    (1) 开启全局二级缓存配置： &lt; setting name="cacheEnabled" value="true" /&gt;   
    (2)mapper.xml文件中配置使用二级缓存:&lt;cache&gt; &lt;/cache&gt;   
       eviction:缓存回收策略：  
        LRU:**默认**，最近最少使用的：移除最长时间不被使用的对象  
        FIFO:先进先出，按照对象进入缓存的顺序移除  
        SOFT:软引用，移除基于垃圾回收器状态和软引用规则的对象  
        WEAK:弱引用，更积极的移除基于垃圾回收状态的弱引用规则的对象  
       flushInterval: 缓存刷新间隔，默认是不清空  
       readOnly:是否只读，默认是false  
       size:缓存放多少元素  
       type:指定自定义缓存的全类名  
    （3）pojo需要实现序列化接口Serializable      
3、使用顺序  
（1）二级缓存  
（2）一级缓存  
（3）数据库  
