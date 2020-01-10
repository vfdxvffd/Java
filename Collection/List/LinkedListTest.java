import java.util.*;

public class LinkedListTest{
    /**
     * 用LinkedList模拟一个队列数据结构
     * @param args
     */
    public static void main(String[] args) {
        Queue queue = new Queue();
        queue.Add("hahaha1");
        queue.Add("hahaha2");
        queue.Add("hahaha3");
        queue.Add("hahaha4");
        queue.Add("hahaha5");

        while(!queue.IsEmpty()){
            System.out.println(queue.Get());
        }
    }
}

class Queue{
    private LinkedList link;
    Queue(){
        link = new LinkedList();
    }

    public void Add(Object obj){
        //内部使用的还是链表的方法
        link.addFirst(obj);
    }

    public Object Get(){
        return link.removeLast();
    }

    public boolean IsEmpty(){
        return link.isEmpty();
    }
}