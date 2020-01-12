import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class HashMapDemo {

    /**
     * 练习：
     * 学生对象（姓名、年龄）都有自己的归属地
     * 将学生对象和归属地存储到map集合中
     * 注意：同姓名同年龄视为同样的键(重写equals和hashCode方法)
     * hashset的底层还是hashmap，只用键的位置
     * @param args
     */
    public static void main(String[] args) {

        //1、创建hashMap对象
        Map<student,String> map = new HashMap<student,String>();//想要有序，则用LinkedHashMap

        //2、添加元素
        map.put(new student("lisi",28),"上海");
        map.put(new student("wangwu",22),"北京");
        map.put(new student("zhaoliu",24),"成都");
        map.put(new student("zhouqi",25),"广州");
        map.put(new student("wangwu",22),"南京");

        //3、取出元素 keyset   entryset
        for (student key:map.keySet()) {
            String value = map.get(key);
            System.out.println(key.toString()+"......"+value);
        }
    }
}

class student{
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof student)) return false;
        student student = (student) o;
        return age == student.age &&
                name.equals(student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}
