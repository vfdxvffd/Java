import java.util.*;

public class TreeSetDemo{

    public static void main(String[] args) {

        /**
         * TreeSet可以对set内对象进行排序
         * TreeSet的add方法内部最终实现：
         * 需要将元素转成Comparable类型，因为这个类型具备了排序的能力
         * 这个类型中专门提供了一个比较方法compareTo
         * 如果要让学生具备比较排序的功能，需要让学生扩展功能，实现Comparable接口
         */
        Set set = new TreeSet();
        // set.add("hahaha");
        // set.add("heihei");
        // set.add("xixixi");
        // set.add("nba");
        // set.add("abc");
        set.add(new Student("lisi1", 21));
        set.add(new Student("lisi2", 22));
        set.add(new Student("lisi3", 21));
        set.add(new Student("lisi4", 20));
        set.add(new Student("lisi5", 19));

        for(Iterator iter = set.iterator(); iter.hasNext();){
            System.out.println(iter.next());
        }
    }
}

class Student implements Comparable{

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

    //重写CompareTo方法，建立Student的自然排序（对象的默认排序方式）
    //排序分主次，首要比年龄，其次比姓名，如果两个都相同则返回0，表示两个是相同的对象
    //若是相同的对象，则树集只存储一个，这就是TreeSet内部的数据结构
    @Override
    public int compareTo(Object o) {
        if(!(o instanceof Student)){
            throw new ClassCastException();
        }
        Student stu = (Student)o;
        if(this.age == stu.age){    //年龄相同，则比较名字
            if(this.name == stu.name){  //名字相同则返回0表示相同元素
                return 0;
            }else{  //名字不同则说明不是同一个对象，应该比较名字
                //字符串是对象，本身具有比较方法
                return this.name.compareTo(stu.name);
            }
        }
        return this.age - stu.age;
    }
}