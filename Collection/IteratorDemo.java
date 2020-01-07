import java.util.Collection;
import java.util.ArrayList;
import java.util.Iterator;

public class IteratorDemo{
    public static void main(String[] args) {
        /**
         * 集合的取出方式
         */
        //1、创建集合对象
        Collection coll = new ArrayList();
        coll.add("abc1");
        coll.add("abc2");
        coll.add("abc3");
        coll.add("abc4");
            /**
             * 细节
             * 1、集合存储的其实都是对象的地址（对象的引用）。
             * 2、集合中可以存储基本数值吗？不可以
             * 虽然可以coll.add(3);这么写，但是存储的还是对象（基本数据类型包装类对象）
             * 3、存储时都提升为了Object，所以取出来的都是Object，需要使用元素的特有方法时需要向下转型，
             * 输出调用的是toString方法，所以不用转型，但length需要
             */
            coll.add(3);    //其实在class文件中就是coll.add(Inteage.valueOf(3));  自动装箱

        //2、获取容器的迭代器对象，通过iterator()方法
        Iterator iter = (Iterator) coll.iterator();

        //3、使用具体的迭代器对象获取集合中的元素

        // while(iter.hasNext()){
        //     System.out.println(iter.next());
        // }

        for (Iterator it = coll.iterator(); iter.hasNext();) {
            System.out.println(it.next());
            String str = (String)it.next();
            System.out.println(str.length());   //细节3
        }   //for循环和while循环的区别：while循环后iter就会指向结尾空，变成一个无用的迭代器浪费空间，而for循环之后it出了作用域被回收内存
          
    }
}