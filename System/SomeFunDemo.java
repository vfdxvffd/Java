import java.util.*;

public class SomeFunDemo {

    /**
     * System：
     * 1、不需要实例化，都是静态的属性和方法
     * 2、out对应标准输出流（默认显示器，PrintStream），in对应标准输入流（默认键盘）
     * 3、演示一些System类中的方法
     * @param args
     */
    public static void main(String[] args) {
        
        //currentTimeMilles方法，获取当前时间的毫秒值
        long time = System.currentTimeMillis();
        System.out.println(time);

        //演示getProperties()获取系统属性集
        Properties prop = System.getProperties();
            //获取系统属性集中的信息，遍历Properties集合。使用map的方法没问题，但是map有泛型。需要强转
            //那么寻找Properties自身获取数据的方法
        // Set<String> set = prop.stringPropertyNames();
        // for (String string : set) {
        //     System.out.println(prop.getProperty(string));
        // }
        System.out.println(prop.getProperty("os.name"));
        //还可以获取系统分隔符、路径分隔符等等很多东西
    }    
}