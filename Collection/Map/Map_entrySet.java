import java.util.*;

public class Map_entrySet {

    /**
     * Set<Map.Entry<K,V>> entrySet(): 将map集合中的映射关系存储到Set集合
     * 映射关系：其实就是指键和值的对应关系。类似于夫妻的结婚证
     * 映射关系是什么类型的呢？ Map.Entry
     * Map.entry又是一个内部接口，它拥有以下方法（具体查阅文档）
     * boolean equals(Object obj) : 比较对象与此项的相等性
     * K getKey() : 返回与此项对应的键
     * V getValue() : 返回与此项对应的值
     * int hashCode() : 返回此映射项的哈希码值
     * V setValue(V value) : 用指定的值替换与此项对应的值
     * @param args
     */
    public static void main(String[] args) {
    
        Map<String, String> map = new HashMap<String, String>();

        map.put("星期一", "Monday");
        map.put("星期日", "Sunday");

        Set<Map.Entry<String, String>> entreset = map.entrySet();
        Iterator<Map.Entry<String, String>> iter = entreset.iterator();
        
        //遍历Set集合中的映射关系
        while(iter.hasNext()){
            Map.Entry<String, String> me = iter.next();//取到了映射关系对象
            //获取键
            String key = me.getKey();
            //获取值
            String val = me.getValue();
            System.out.println(key+"------"+val);
        }
        /**
         * map集合没有迭代器，取出元素的方式：
         * 将map集合转成单列集合，再使用单列结合的迭代器就可以了
         * map集合也不能直接被foreach循环遍历，因为foreach需要迭代器
         * 但是可以用foreach遍历映射关系的set集合
         */
    }
}

/**
 * MyMap
 */
public interface MyMap {

    public static interface MyEntry{}   //内部接口
}