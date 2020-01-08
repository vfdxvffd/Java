import java.util.*;

public class CollectionTest{
    public static void main(String[] args) {
        /**
         * 往集合中存储自定义对象
         * 集合重复的判断依据：都是根据equals方法来判断的
         */
        //1、创建集合对象
        Collection coll = new ArrayList();

        //2、添加具体的学生元素
        coll.add(new Student("zhangsan", 7));
        coll.add(new Student("lisi", 8));
        coll.add(new Student("wangwu", 10));
        coll.add(new Student("wangwu", 10));    //不重复,但是在自己重写equals方法后，就会变成重复了
        Student stu = new Student("haha", 21);
        coll.add(stu);
        coll.add(stu);  //重复元素
        /**
         * 集合判断元素（对象）重复或者说相同的依据其实就是equals
         */

        //3、取出
        for(Iterator iter = coll.iterator(); iter.hasNext();){
            //System.out.println(iter.next());
            Student s = (Student)iter.next();
            System.out.println(s.getName());
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