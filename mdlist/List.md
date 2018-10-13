# 数组和集合  

## 数组  
代表一个简单的线性序列，它使得元素的访问速度非常快，但却要为这种速度付出代价：创建数组时，它的大小时固定的。
初始化  
```
// 基本数据类型
int[] a1 = { 1, 2, 3, 4, 5};
// 必须要指定数组大小
Integer[] a2 = new Integer[5];
// 或直接指定对象或值
Integer a3 = new Integer[]{ 1, 2, 3};
```

## 集合  
Collection:一组单独的元素，通常应用了某种规则。  
一个List（列表）必须按特定的顺序容纳元素，一个Set（集）不可包含任何重复的元素。 

 

## 集合和数组  
* 长度：数组长度是固定的，集合长度可变；
* 存放内容：数组可以存放基本类型和引用类型，集合只能存放引用类型；  
* 元素内容：数组只能存放同一类的类型，集合可以存放不同类型；

## 源码学习  
### ArrayList  
1. 数据结构  
ArrayList底层数据结构是**数组**，可以进行动态扩容。  
```
private static final Object[] EMPTY_ELEMENTDATA = {};

private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

transient Object[] elementData;

```

2. 初始化  
* 使用无参构造器初始化`ArrayList`，在使用add操作往`ArrayList`里添加元素时会先扩容一个默认大小为10的数组。  
* 使用有参构造器传入`int initialCapacity`会构造一个大小为initialCapacity的数组。  

```
    /**
     * Constructs an empty list with an initial capacity of ten.
     * 初始化会构造一个空数组，在使用add操作时会扩容大小为10的数组 
     */
    public ArrayList() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }
    
    /**
     * 构造一个大小initialCapacity的数组
     *
     */
    public ArrayList(int initialCapacity) {
            if (initialCapacity > 0) {
                this.elementData = new Object[initialCapacity];
            } else if (initialCapacity == 0) {
                this.elementData = EMPTY_ELEMENTDATA;
            } else {
                throw new IllegalArgumentException("Illegal Capacity: "+
                                                   initialCapacity);
            }
        }
```
3. 扩容  
通过`add`添加操作查看扩容的流程：  
```
    /**
     * The size of the ArrayList (the number of elements it contains)
     * ArrayList包含的元素数量
     */
    private int size;
    
    /**
     * Default initial capacity.
     * 默认数组的大小
     */
    private static final int DEFAULT_CAPACITY = 10;

    public boolean add(E e) {
        ensureCapacityInternal(size + 1);  // Increments modCount!!
        elementData[size++] = e;
        return true;
    }
    
    private void ensureCapacityInternal(int minCapacity) {
            ensureExplicitCapacity(calculateCapacity(elementData, minCapacity));
    }
    
    private static int calculateCapacity(Object[] elementData, int minCapacity) {
            // 默认为空数组时
            if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
                return Math.max(DEFAULT_CAPACITY, minCapacity);
            }
            return minCapacity;
    }
    
    private void ensureExplicitCapacity(int minCapacity) {
            modCount++;
    
            // overflow-conscious code
            if (minCapacity - elementData.length > 0)
                grow(minCapacity);
    }
    // 扩容
    private void grow(int minCapacity) {
            // overflow-conscious code
            int oldCapacity = elementData.length;
            // 大小扩大至原数组的1.5倍
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            if (newCapacity - minCapacity < 0)
                newCapacity = minCapacity;
            if (newCapacity - MAX_ARRAY_SIZE > 0)
                newCapacity = hugeCapacity(minCapacity);
            // minCapacity is usually close to size, so this is a win:
            // 扩容操作 把原数组整个复制到新数组中，整个操作代价高
            elementData = Arrays.copyOf(elementData, newCapacity);
    }
    
```

4. 删除  
调用`System.arraycopy`将index + 1后面的元素复制到index，该操作的时间复杂度是`O(n)` ，因此ArrayList删除元素的代价高，效率较低。   
```
public E remove(int index) {
        rangeCheck(index);

        modCount++;
        E oldValue = elementData(index);

        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(elementData, index+1, elementData, index,
                             numMoved);
        elementData[--size] = null; // clear to let GC do its work

        return oldValue;
    }
```
5. 序列化  
ArrayList是基于数组实现，具有动态扩容的特性。保存元素的数组不一定全部被使用，因此在进行序列化时只会序列化有元素填充的部分。 
注意在ArrayList中存储元素的数组使用`transient`修饰：   
```
transient Object[] elementData;
```

***

### Vector
1. 数据结构  
实现的数据结构根`ArrayList`一样，基于数组实现，具有动态扩容的特性。  
不同的是`Vector`是线程安全的（同步），方法上加了`synchronized`关键字。  
```
public synchronized boolean add(E e) {
        modCount++;
        ensureCapacityHelper(elementCount + 1);
        elementData[elementCount++] = e;
        return true;
    }
```

2. 与`ArrayList`区别  
 * Vecotr是线程安全的，由于加锁开销会比较大，效率相对ArrayList较低。  
 * Vector每次扩容是`2`倍，ArrayList是`1.5`倍。  
 * 一般不要使用Vector，可以使用`Collections.synchronizedList()`得到一个线程安全的ArrayList。 
 
***
 
###  LinkedList
1. 数据结构  
LinkedList底层数据结构是双向链表。  
```
 // 双向链表
 private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
```

2. 与ArrayList比较  
 * ArrayList是基于动态数组实现，LinkedList是基于双向链表实现。  
 * ArrayList支持随机访问，LinkedList不支持。  
 * LinkedList在任意位置添加或删除元素更快。  