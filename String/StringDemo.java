public class StringDemo{
    /**
     * @param args
     * String类
     * 1、字符串都是对象
     * 2、一旦初始化就不可被更改。因为是常量final
     * 3、通过String类的构造函数可以知道。将字节数组或字符数组转化为字符串
     */
    public static void main(String[] args) {

        //多种不同的方式创建字符串
        String str = "abc";
        String str1 = new String("abc");
        // char data[] = {'a','b','c'};
        // String str1 = new String(data);      //可以传入字符数组或者字节数组
        System.out.println(str==str1);          //false
        System.out.println(str.equals(str1));   //true,因为String复写了equals方法，建立了字符串自己的判断相同的依据，是通过字符串对象中的内容来判断的
        /**
         * str和str1有什么不同？
         * str创建，在内存中只有一个对象
         * str1创建，在内存中有两个对象，先在堆中new一个对象，调用构造函数，把常量池中abc的地址传给构造函数
         */


        //字符串对象不可变
        String str2 = "abc";
        str2 = "hahha";      //只不过是str指向了另一个对象
        System.out.println("str2="+str2);       //str2=hahha

        //字符串对象可共享（都在常量池中）
        String s1 = "test";
        String s2 = "test";
        System.out.println(s1==s2); //==比较的是地址，输出true，说明引用s1和s2指向同一个对象
    }
}