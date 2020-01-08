import java.util.*;

public class ListIterator{
    /**
     * 列表迭代器ListIterator
     * 允许Programmer按任一方向遍历列表，迭代期间修改列表，并获得迭代器在列表中的当前位置
     * ListIterator没有当前元素，它的光标位置始终位于调用previous()所返回的元素和调用next()所返回
     * 的元素之间。长度为n的列表的列表迭代器有n+1个可能的指针位置
     * 
     * 通过List接口中的listIterator()就可获取
     * 该列表迭代器只有List接口有。而且这个迭代器可以完成在迭代过程中的增删改查动作
     * @param args
     */
    public static void main(String[] args) {
        
        List list = new ArrayList();

        list.add("abc1");
        list.add("abc2");
        list.add("abc3");
        list.add("abc4");
        list.add("abc5");

        //获取集合中的元素,如果集合中有元素abc1，那就插入一个新元素Java
//      Iterator iter = list.iterator();
        ListIterator iter = list.listIterator();
        while(iter.hasNext()){
            Object obj = iter.next();   //java.util.ConcurrentModificationException
            //在迭代过程中，使用了集合的方法对元素进行操作。导致迭代器并不知道集合中的变化，容易引发数据的不确定性
            //解决：在迭代时候，不要使用集合的方法操作元素，而是使用迭代器的方法操作
            //Iterator有一个子接口ListIterator，叫做列表迭代器
            if("abc1".equals(obj)){
                // list.add("Java");
                iter.add("java");
            }
        }
        System.out.println(list);
    }
}