import java.util.*;

public class Map_values {

    /**
     * 获取所有值
     * Collection<V> values() : 返回此映射中包含的值的Collection视图
     * 因为map中的值不一定是唯一的，所以不能用set来保存，选用Collection
     * @param args
     */
    public static void main(String[] args) {
    
        Map<String, String> map = new HashMap<String, String>();

        map.put("星期一", "Monday");
        map.put("星期日", "Sunday");

        Collection<String> values = map.values();
        for (String string : values) {
            System.out.println(string);
        }
    }
}