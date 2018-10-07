# 克隆-接口Cloneable  

## 为什么要克隆?  
需求：对象A在某一时刻已经包含一些有效值，而此时需要有个和A完全相同的新对象B，且此后对B做任何改动都不会影响A中的值，即A和B是两个独立的对象。由于简单，
的赋值语句无法满足该需求，此时就需要使用克隆。

## 浅克隆  
对于要克隆的对象：  
1. 基本数据类型的属性，克隆一份給新的对象；  
2. 引用数据类型的属性，仅复制一份引用給新的对象，即实际指向的内存是同一块；  

`实现步骤`  
1. 实现接口Cloneable  
2. 重写clone()方法 
[demo](https://github.com/npvip/StudyNote/blob/master/src/main/java/cn/np/common/CloneDemo.java)  
 

## 深克隆    
对于要克隆的对象:  
与浅克隆的区别是引用类型的属性复制创建一块新的内存地址。  

# 序列化-接口Serializable  
  
## 定义
对象的生命通常随着生产该对象的程序的终止而终止，而有时候需要把在内存中的各种对象的状态（实例变量）保存下来，在需要时再将对象恢复。  
在以下情况下需要序列化：  
* 把内存中的对象保存到一个文件中或数据库中  
* 把对象通过网络进行传输时 
 
## 实现  
[demo](https://github.com/npvip/StudyNote/blob/master/src/main/java/cn/np/common/SerializableDemo.java)  
`注意`  
* 序列化只保存对象的非静态成员变量的值。  
* `serialVersionUID`需一致才能完成反序列化操作得到对象信息，否则抛异常InvalidClassException。  
* 使用`transient`关键词修饰的成员变量不能被序列化和反序列化。  
* 一个父类实现了序列化，子类自动实现序列化。  
* 若父类不实现Serializable接口，就需要有默认的无参的构造函数。

