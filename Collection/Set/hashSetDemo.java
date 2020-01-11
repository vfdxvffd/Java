import java.util.*;

public class hashSetDemo{
    public static void main(String[] args) {
        
        /**
         * 为什么Student对象没有保证唯一性呢？
         * 通过对哈希表的分析
         * 存储元素时，先调用元素对象的hashCode()方法，而每个对象都是新建的对象（hashcode的计算方法是
         *    根据地址进行计算的，而每new一个对象，就会新分配一个地址）
         * 所以hashCode指都不相同，也就不需要判断equals方法了，只有hashCode计算出hash值相同时才会调用equals方法
         * 想要按照同姓名同年龄来保证Student对象的唯一性咋办？
         * 不能使用Object中hashCode方法，需要重写hashCode的算法内容
         */
        Set set = new HashSet();
        // set.add("hahaha");
        // set.add("nba");
        // set.add("java");
        // set.add("hahaha");
        // set.add("nba");
        // set.add("java");
        set.add(new Student("zhangsan",12));
        set.add(new Student("lisi",22));
        set.add(new Student("wangwu",13));
        set.add(new Student("zhangsan",12));
        set.add(new Student("lisi",22));
        set.add(new Student("wangwu",13));

        for(Iterator iter = set.iterator();iter.hasNext();){
            System.out.println(iter.next());
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

    //重写hashCode方法，建立Student对象hash值算法内容
    @Override
    public int hashCode(){
        return 1;
    }
}