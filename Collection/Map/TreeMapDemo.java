import java.util.*;

public class TreeMapDemo {

    /**
     * 练习：
     * 按照学生的年龄从小到大排序
     * 学生对象（姓名、年龄）都有自己的归属地
     * 将学生对象和归属地存储到map集合中
     * 注意：同姓名同年龄视为同样的键(重写equals和hashCode方法)
     * @param args
     */
    public static void main(String[] args) {

        //1、创建hashMap对象,为了排序，对象实现Comparable接口，或直接以内部类的形式传入比较器
        Map<student,String> map = new TreeMap<student,String>(new Comparator<student>() {
            @Override
            public int compare(student student, student t1) {   //以姓名排序
                return student.getName().compareTo(t1.getName());
            }
        });

        //2、添加元素
        map.put(new student("lisi",28),"上海");
        map.put(new student("wangwu",22),"北京");
        map.put(new student("zhaoliu",24),"成都");
        map.put(new student("zhouqi",25),"广州");

        //3、取出元素 entryset
        for (Map.Entry<student,String> me: map.entrySet()) {
            student key = me.getKey();
            String value = me.getValue();
            System.out.println(key+"......"+value);
        }
    }
}

class student implements Comparable<student>{
    private String name;
    private int age;

    public student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public int compareTo(student student) { //年龄为主，名字为辅
        int temp = this.age - student.age;
        return temp == 0? this.name.compareTo(student.name) : temp;
    }
}
