package Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 序列化接口的作用：没有方法，不需要覆盖，是一个标记接口，为了启动一个序列化功能
 * 唯一作用，给每一个需要序列化的类都分配一个序列版本号
 * 这个版本号和该类相关联
 * 这个版本号有什么用呢？
 * 在序列化时，会将这个序列号也一同保存到文件中
 * 在反序列化时，会读取这个序列化进行匹配，如果不匹配会抛出异常。java.io.InvalidClassException
 * 原来是用于验证的
 * 
 * 那我们需要在定义时显式声明吗？
 * 因为各个编译器计算出同一个类的序列版本号可能不一样，所以强烈建议显式声明
 * */



public class Person implements Serializable//标记接口，用于启动类的序列化功能
{

	private static final long serialVersionUID = 3L;//自己定义序列版本号
	private static String name;			//静态数据是不会被序列化的，即不会被写入
	
	//对于一个非静态的数据也不想序列化怎么办
	private transient/*瞬态*/ int age;		//用transient来修饰
	
	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}
	
	String Getname()
	{
		return name;
	}
}



public class wrObject {

		public static void main(String[] args) throws IOException, ClassNotFoundException {
			//需求：想要将封装了数据的对象进行持久化。当写入的对象很多时会按照顺序排列，也称之为对象的序列化
			
			//1、应该现有对象。Person  name  age
			//2、往硬盘上写数据，进行持久化，需要IO技术，输出流  FileOutputStream
			//3、在字节输出流中按照名称规律在API中找到一个子类，ObjectOutputStream
			//4、在基础流对象上需要似乎用额外功能
		
			writeObj();
			
			//需求：读取已有的对象文件，并获取对象中的数据
			//通过阅读ObjectOutputStream对象的文档，发现有一个对应的对象ObjectInputStream可以用于读取存储对象的文件
			//对象的反序列化
			readObj();
		}


		public static void writeObj() throws IOException 
		{
			Person p = new Person("lisi", 20);
			FileOutputStream fos = new FileOutputStream("D:\\forio\\Obj.object");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(p);
			oos.close();
		}
		
		
		public static void readObj() throws IOException, ClassNotFoundException
		{
			
			FileInputStream fis = new FileInputStream("D:\\forio\\Obj.object");
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			Person p = (Person) ois.readObject();
			
			//Object obj = ois.readObject();
			
			System.out.println(p.Getname());
		}
	}


