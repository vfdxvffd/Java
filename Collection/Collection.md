# 集合框架
当数据多了需要存储，需要容器，而数据个数不确定，无法使用数组，这时可以使用Java的另一个容器——集合。

* 集合和数组的区别在于数组的长度是固定的，而集合的长度是可变的
* 数组中存储的是同一类型的元素，可以存储基本数据类型
* 集合存储的都是对象。而且对象的类型可以不一致。

## Iterator迭代器
迭代器可以对一个集合进行迭代遍历

### ListIterator列表迭代器
Iterator的一个子接口，允许程序员在迭代过程中对一个列表进行增删改查的操作 

## Collection接口
Collection是最顶层的集合接口，定义了集合体系最共性的功能。
它拥有两个子接口List和Set

### List接口
* 有序的（和插入顺序一样）
* 带索引的
* 允许重复的元素
List提供增(add)删(remove)改(set)查(get)动作

### List的子类
#### Vector
* 数组结构的列表，就像是咱们数据结构中的顺序表一样
* 可增长的数组结构，长度可变
* 可使用索引访问
* 同步的，线程安全，但效率低
#### ArrayList
* 数组结构的列表，就像是咱们数据结构中的顺序表一样
* 允许用索引随机访问
* 长度可变
* 不同步的，线程不安全，但效率高，单线程建议使用
#### LinkedList
* List接口的链接列表实现
* 不同步的，线程不安全 
* 可用于实现堆栈、队列 
特点：围绕头和尾展开定义。First Last
addFirst();
addLast();
getFirst();
getLast();
removeFirst();
removeLast();

### Set接口
* 不允许重复元素
* 不保证顺序（根据具体结构确定）
* 方法和Collection一致
* Set集合取出元素的方式只有一种就是迭代器
更正式地，集合不包含一对元素e1和e2 ，使得e1.equals(e2) ，并且最多一个空元素。 正如其名称所暗示的那样，这个接口模拟了数学集抽象。
### Set的子类
#### HashSet
此类实现Set接口，由哈希表支持。它不保证set的迭代顺序；特别是它不保证该顺序恒久不变。此类允许使用null元素
* 哈希表结构
* 不同步
* 保证元素唯一性的方式依赖于：hashCode()、equals()方法（hash值一样的时候才会判断equals方法）
优点：查询速度快
缺点：需要保证数据唯一性，最好重写hashCode和equals方法
##### LinkedHashSet
HashSet的子类
* 和原来加入集合的顺序一样
* 无重复元素
* 查询效率高
#### TreeSet
* 可以对set集合中的元素进行排序（依据是CompareTo方法）
* 使用的是二叉排序树结构
如何保证元素唯一性呢？使用的是对象比较方法的返回值是否为0，0则视为相同元素，不再存入

## Map接口
* 内部存储的都是键值对
* 必须保证键的唯一性
V put(K key, V value) 返回以前与key关联的值，如果没有针对key的映射关系，则返回null

### Hashtable
* 数据结构：哈希表
* 同步的
* 不能用null作为键和值
#### Properties
属性集，Hashtable的子类，键和值都是字符串，而且可以结合流进行键值的操作
### HashMap
* 数据结构：哈希表
* 不同步
* 允许null作为键和值存储
* 不保证映射顺序，特别它不保证该顺序恒久不变
#### LinkedHashMap
HashMap的子类，基于链表+哈希表，可以保证map集合中存入和取出的顺序一致
### TreeMap
* 数据结构：红黑树结构
* 不同步
* 可以对map集合中的键进行排序