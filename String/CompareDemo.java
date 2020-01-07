public class CompareDemo{
    
    //重点看compareTo方法和equals方法

}

class Person implements Comparable{
    private String name;
    private int age;

    public Person(){                        //空参构造函数
        super();
    }
    public Person(String name, int age){
        this.name = name;
        this.age = age;
    }

    public void setName(String name){
        this.name = name;
    }
    public void setAge(int age){
        this.age = age;
    }
    public String getName(){
        return this.name;
    }
    public int getAge(){
        return this.age;
    }

    @Override
    public String toString(){
        return "Person [name="+name+", age="+age+"]";
    }
    @Override
    public int compareTo(Object o){
        if(!(o instanceof Person)){
            throw new ClassCastException("type error");
        }
        Person p = (Person)o;
        return this.age - p.age;
    }
    //建立了Person对象判断是否相同的依据，只要是姓名和年龄都相同就相同的人
    @Override
    public boolean equals(Object obj){
        if(this == obj)
            return true;
        if(!(obj instanceof Person)){
            throw new ClassCastException("type error");
        }
        Person p = (Person)obj;
        return this.name.equals(p.name) && this.age == p.age;
    }
}