import java.util.Collection;
import java.util.ArrayList;

public class CollectionDemo{

    /**
     * 当数据多了需要存储，需要容器，而数据个数不确定，无法使用数组，这时可以使用Java的另一个容器——集合
     * 
     * 集合和数组的区别在于数组的长度是固定的，而集合的长度是可变的
     * 数组中存储的是同一类型的元素，可以存储基本数据类型
     * 集合存储的都是对象。而且对象的类型可以不一致。
     * 
     * Collection是最顶层的集合接口，定义了集合体系最共性的功能
     * @param args
     */
    public static void main(String[] args) {
        Collection coll = new ArrayList();//目前演示Collection方法，不关心子类对象的类型是什么
        collectionDemo(coll);
    }

    public static void collectionDemo(Collection coll) {
        
        //1、往集合中添加对象元素。add(Object)
        coll.add("abc1");
        coll.add("abc2");
        coll.add("abc3");
        coll.addAll(coll);  //将一个集合中的元素全放入另一个集合

        System.out.println(coll);   //[abc1, abc2, abc3]

        //2、删除
        coll.remove("abc2");
        coll.removeAll(coll);   //删除这个集合中的所有也包含在那个集合中的元素，次方法调用后，这个集合中将不再包含与那个集合中的相同元素
        System.out.println(coll);
        c1.retainAll(c2);       //将c1中与c2中相同的元素保留，刚好与removeAll相反

        //3、contains判断包含
        System.out.println(coll.contains("abc1"));
        System.out.println(coll.contains(coll));    //是否包含这个集合
        System.out.println(coll.containsAll(coll)); //是否包含这个集合中的所有元素

        //4、元素数size
        System.out.println(coll.size());

        //5、清除
        coll.clear();

        //还有很多，这里不做演示，可以查看API
    }
}