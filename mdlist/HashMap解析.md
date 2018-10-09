# HashMap简介  
HashMap是最常见的集合类之一，用于存储健值对（key-value），是面试中最常见的考察点。  
![HashMap](https://github.com/npvip/StudyNote/blob/master/img/HashMap.png)  
  
---
# 思路 
* *数据结构*  
JDK1.8之前HashMap的底层数据结构是 **数组+单链表**，链表的作用是为了解决哈希冲突。  
JDK1.8之后在解决哈希冲突时，当链表长度大于阀值（默认为8）时，将链表转化为红黑树，以加快查找的速度。
```
static class Node<K,V> implements Map.Entry<K,V> {
        final int hash;
        final K key;
        V value;
        Node<K,V> next;

        Node(int hash, K key, V value, Node<K,V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public final K getKey()        { return key; }
        public final V getValue()      { return value; }
        public final String toString() { return key + "=" + value; }

        public final int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        public final V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        public final boolean equals(Object o) {
            if (o == this)
                return true;
            if (o instanceof Map.Entry) {
                Map.Entry<?,?> e = (Map.Entry<?,?>)o;
                if (Objects.equals(key, e.getKey()) &&
                    Objects.equals(value, e.getValue()))
                    return true;
            }
            return false;
        }
    }
``` 
---
* *扩容*  
```
    /**
     * Initializes or doubles table size.  If null, allocates in
     * accord with initial capacity target held in field threshold.
     * Otherwise, because we are using power-of-two expansion, the
     * elements from each bin must either stay at same index, or move
     * with a power of two offset in the new table.
     * 
     * 初始化或双倍表大小
     *
     * @return the table
     */
    final Node<K,V>[] resize() {
        Node<K,V>[] oldTab = table;
        int oldCap = (oldTab == null) ? 0 : oldTab.length;
        int oldThr = threshold;
        int newCap, newThr = 0;
        if (oldCap > 0) {
            if (oldCap >= MAXIMUM_CAPACITY) {
                threshold = Integer.MAX_VALUE;
                return oldTab;
            }
            else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY &&
                     oldCap >= DEFAULT_INITIAL_CAPACITY)
                newThr = oldThr << 1; // 阀值大小也乘以2
        }
        else if (oldThr > 0) // initial capacity was placed in threshold
            newCap = oldThr;
        else {               
            // 初始化：初始值为0，数组大小使用默认值16，加载因子默认为0.75f
            newCap = DEFAULT_INITIAL_CAPACITY;
            newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
        }
        if (newThr == 0) {
            float ft = (float)newCap * loadFactor;
            newThr = (newCap < MAXIMUM_CAPACITY && ft < (float)MAXIMUM_CAPACITY ?
                      (int)ft : Integer.MAX_VALUE);
        }
        threshold = newThr;
        @SuppressWarnings({"rawtypes","unchecked"})
            Node<K,V>[] newTab = (Node<K,V>[])new Node[newCap];
        table = newTab;
        if (oldTab != null) {
            // 将原数组上的数据转移到新的数组上
            for (int j = 0; j < oldCap; ++j) {
                Node<K,V> e;
                if ((e = oldTab[j]) != null) {
                    oldTab[j] = null;
                    if (e.next == null)
                        newTab[e.hash & (newCap - 1)] = e;
                    else if (e instanceof TreeNode)
                        ((TreeNode<K,V>)e).split(this, newTab, j, oldCap);
                    else { // preserve order
                        Node<K,V> loHead = null, loTail = null;
                        Node<K,V> hiHead = null, hiTail = null;
                        Node<K,V> next;
                        do {
                            next = e.next;
                            if ((e.hash & oldCap) == 0) {
                                if (loTail == null)
                                    loHead = e;
                                else
                                    loTail.next = e;
                                loTail = e;
                            }
                            else {
                                if (hiTail == null)
                                    hiHead = e;
                                else
                                    hiTail.next = e;
                                hiTail = e;
                            }
                        } while ((e = next) != null);
                        if (loTail != null) {
                            loTail.next = null;
                            newTab[j] = loHead;
                        }
                        if (hiTail != null) {
                            hiTail.next = null;
                            newTab[j + oldCap] = hiHead;
                        }
                    }
                }
            }
        }
        return newTab;
    }
```
  
1. 初始化  
在給HashMap添加（put）内容时，如果table数组为空，则会调用resize()方法进行初始化扩容，初始数组的大小采用默认值16。  
  

``` 
Node<K,V>[] tab; Node<K,V> p; int n, i;
// table为空或长度为0时调用resize()
if ((tab = table) == null || (n = tab.length) == 0)
            n = (tab = resize()).length;

```

2. 达到阀值后扩容  
在給HashMap添加(put)内容完成后，会将数组大小和阀值进行比较，若大于阀值则会调用resize()方法进行扩容，数组大小变为原大小的2倍。  

```
// put完成后会把数组大小和阀值进行比较，若大于阀值则resize()扩容
if (++size > threshold)
            resize();
```
---
* *添加操作*  
```
// 获取键key的hash值
static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

public V put(K key, V value) {
        return putVal(hash(key), key, value, false, true);
    }
    
final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
                   boolean evict) {
        Node<K,V>[] tab; Node<K,V> p; int n, i;
        // 数组为空时，调用resize()初始化一个数组
        if ((tab = table) == null || (n = tab.length) == 0)
            n = (tab = resize()).length;
        // i = (n - 1) & hash 获取在数组中的位置，位置上值为空时    
        if ((p = tab[i = (n - 1) & hash]) == null)
            tab[i] = newNode(hash, key, value, null);
        else {
            Node<K,V> e; K k;
            if (p.hash == hash &&
                ((k = p.key) == key || (key != null && key.equals(k))))
                e = p;
            else if (p instanceof TreeNode)
                e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
            else {
                for (int binCount = 0; ; ++binCount) {
                    if ((e = p.next) == null) {
                        p.next = newNode(hash, key, value, null);
                        if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                            treeifyBin(tab, hash);
                        break;
                    }
                    if (e.hash == hash &&
                        ((k = e.key) == key || (key != null && key.equals(k))))
                        break;
                    p = e;
                }
            }
            if (e != null) { // existing mapping for key
                V oldValue = e.value;
                if (!onlyIfAbsent || oldValue == null)
                    e.value = value;
                afterNodeAccess(e);
                return oldValue;
            }
        }
        ++modCount;
        if (++size > threshold)
            resize();
        afterNodeInsertion(evict);
        return null;
    }
```
添加流程图：  

![流程图](https://github.com/npvip/StudyNote/blob/master/img/put.png) 

---
* *HashMap的数组长度为什么是2的倍数？*  
  
在HashMap进行`put`操作时，首先需要得到要插入到数组中的位置index，通过分析源码得到index计算的步骤如下：  
1. 如下HashMap中的hash函数源码，首先计算key.hashCode()得到键的hash码，然后用hash码的低16位和高16位做异或运算（这样做的目的是让二进制表示中的"1"变得更加均匀
，散列的本意就是要尽量均匀分布）。  
```
// HashMap中的hash函数
static final int hash(Object key) {
        int h;
        // 注意！键为null时会插入到数组的第一个位置0
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
```
2. 通过1中的hash函数得到h后，会进行h & (length - 1)的运算得到index。  

如果数组的长度是2的倍数，那么（length - 1）的二进制表示会形如...00001111，即末尾n位都是1，前面都是0。在做 `与&` 运算时，值会对后面的n位进行运算，结果
肯定是落在0～length-1之间。  
如果数组的长度不是2的倍数，假设数组的长度是15，那么(length - 1) = 14的二进制为00001110,在与h进行 `与&` 运算时结果的最后一位肯定时0，那么数组中index
末尾为1的（00001111，00001101，00001001等）就不会添加健值对对象，*造成内存空间浪费*。  

# 参考文章  
https://github.com/Snailclimb/JavaGuide/blob/master/Java%E7%9B%B8%E5%85%B3/HashMap.md  
https://blog.csdn.net/Maxiao1204/article/details/80729657
