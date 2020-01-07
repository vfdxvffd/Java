public class StringBufferDemo{

    /**
     * StringBuilder和StringBuffer的区别在于StringBuffer是线程安全的，
     * 即它里面的方法都是保证同步的   synchronized append(); ...
     * 也正是因为线程安全，每次都要判断、拿锁保证同步，所以它的效率低于StringBuilder
     * 所以单线程建议使用StringBuilder，多线程建议使用StringBuffer
     * @param args
     */
    public static void main(String[] args) {
        /**
           * StringBuffer:
           * 1、是一个字符串缓冲区，其实就是一个容器
           * 2、长度是可变的，任意类型都都行。但注意是将任意数据都转成字符串进行添加
           * 3、容器对象提供了很多对容器中数据的操作功能，比如增、删、改、查
           * 4、必须所有的数据最终变成一个字符串
           * 和数组最大的不同就是：数组存储完可以单独操作每一个元素，每一个元素都是独立的
           * 字符串缓冲区，所有存储的元素都被转成了字符串，而且最后拼成了一个大的字符串
           */
        
        //1、创建一个字符串缓冲区对象。用于存储数据
        StringBuffer sb = new StringBuffer();

        //2、添加数据.不断添加数据后，要对缓冲区的最后数据进行操作，必须转换成字符串才可以
        sb.append("abc");
        sb.append(true);
        sb.append(3).append(33);    //append方法返回的还是一个字符缓冲区
        String str = sb.toString(); //toString可以将其转换成String
        System.out.println(sb);
        System.out.println(str);
        
        sb.insert(2, "it");         //随机位置插入
        System.out.println(sb);

        //3、删除
        sb.delete(2, 3);
        sb.deleteCharAt(0);
        System.out.println(sb);

        //4、replace替换
        sb.replace(7, 10, "666");
        System.out.println(sb);

        //5、split,对String的操作
        String[] arrs = str.split("用正则表达式");

        //6、reverse
        sb.reverse();
        System.out.println(sb);
        
    }
}