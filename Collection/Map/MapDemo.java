import java.util.*;

public class MapDemo {

    /**
     * 存储中英文星期的对应
     * 使用map集合
     * @param args
     */
    public static void main(String[] args) {
        
        Map<String, String> map = new HashMap<String, String>();

        //添加元素
        //put方法，如果键相同，值覆盖，并将旧值返回
        System.out.println(map.put("星期一", "Monday"));    //输出null
        System.out.println(map.put("星期一", "Mon"));       //输出Monday

        //通过给定的键获取值
        String value1 = map.get("星期一");
        String value2 = map.get("星期二");//如果键不存在，则返回null
        System.out.println(value1);
        System.out.println(value2);
        
        //删除元素，根据键删除
        String s = map.remove("星期一");    //获取给定键对应的值，并将该键值对从map中删除
        System.out.println(s);

        System.out.println(map);
    }
}