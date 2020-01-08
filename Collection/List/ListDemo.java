import java.util.*;

public class ListDemo{
    public static void main(String[] args) {
        //使用List解决插入元素的问题，因为add方法是追加到结尾
        //list接口的特有方法，全都是围绕索引来定义的
        //list获取元素的方法有两种，一种是迭代器依次访问，一种用索引随机访问
        //list接口支持对元素进行增删改查动作
        List list = new ArrayList();

        //1、添加元素
        list.add(new Student("wangcai1", 21));
        list.add(new Student("wangcai2", 22));
        list.add(new Student("wangcai3", 23));

        //2、随机位置插入元素
        list.add(1, new Student("xiaoqiang",25));

        //3、随机删除
        list.remove(2);
        //list.remove(999);   //索引越界java.lang.IndexOutOfBoundsException: Index: 999, Size: 3

        //4、修改元素set方法
        list.set(1, new Student("xiaoming",12));

        //5、用索引获取,get函数
        System.out.println(list.get(1));
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}

class Student{

    private String name;
    private int age;

    public Student(String name, int age){
        this.name = name;
        this.age = age;
    }

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setAge(int age){
        this.age = age;
    }
    public int getAge(){
        return this.age;
    }

    @Override
    public String toString(){
        return "Student [name="+name+", age="+age+"]";
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj)
            return true;
        if(!(obj instanceof Student)){
            throw new ClassCastException("typeError!");
        }
        Student stu = (Student) obj;
        return this.name.equals(stu.name) && this.age == stu.age;
    }
}