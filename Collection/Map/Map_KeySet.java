import java.util.*;

public class Map_KeySet {

    //获取所有的键的集合 
    public static void main(String[] args) {
    
        Map<String, String> map = new HashMap<String, String>();

        map.put("星期一", "Monday");
        map.put("星期日", "Sunday");

        System.out.println(map.get("星期一"));
        //如何获取所有的键呢？
        //既然是所有的键，应该是一个集合，而且是一个单列集合
        //使用的是set，因为map集合中的键需要保证唯一性
        //使用方法 Set<K> keySet();获取集合中的键的set集合
        Set<String> keySet = map.keySet();
        for (String string : keySet) {
            System.out.println(string);
        }
    }
}