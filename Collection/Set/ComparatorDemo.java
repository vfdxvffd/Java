import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class ComparatorDemo {

    /**
     * 分析源码得知，传入比较器的时候，根据比较器排序，没有比较器则根据自然排序（即类实现的Comparable接口）
     * 比较器和Comparable接口并不是这个集合特有的，而是所有对象比较都可以使用的
     * @param args
     */
    public static void main(String[] args) {
        Set set = new TreeSet();

        set.add(new Student("lisi6",21));
        set.add(new Student("lisi8",22));
        set.add(new Student("lisi5",25));
        set.add(new Student("lisi3",23));
        set.add(new Student("lisi7",20));

        for (Iterator iter = set.iterator(); iter.hasNext();){
            System.out.println(iter.next());
        }
        System.out.println("-------------------------------");
        /**
         * 想要按照学生的姓名排序，说明学生中的自然排序不是所需要的。
         * 这时只能使用比较器
         * 可以另外创建一个类叫ComparatorByName，在里面定义比较器，TreeSet set = new TreeSet(new ComparatorByName());
         * 也可以直接使用匿名内部类
         */
        //使用泛型，可以避免向下转型为Student
        TreeSet<Student> setComparatorByName = new TreeSet<Student>(new Comparator<Student>() {
            @Override
            public int compare(Student student, Student t1) {//先比较姓名，如果姓名相同再比较年龄
                int temp = student.getName().compareTo(t1.getName());
                return temp == 0? student.getAge()-t1.getAge() : temp;
            }
        });
        setComparatorByName.addAll(set);
        for(Iterator iter = setComparatorByName.iterator(); iter.hasNext();){
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