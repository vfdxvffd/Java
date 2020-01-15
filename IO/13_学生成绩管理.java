package IO;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.TreeSet;

import javax.management.RuntimeErrorException;

/*先是一个学生信息类，里面实现了Comparable接口，便于TreeSet直接排序，还写了toString方法
还重写了hashcode和equals方法，和众多get和set方法，有点繁杂了*/
public class Student implements Comparable<Student>{
	
	private String name;
	private int cn, en, ma;
	private int sum;

	public Student(String name, int cn, int en, int ma) {
		super();
		this.name = name;
		this.cn = cn;
		this.en = en;
		this.ma = ma;
		this.sum = cn + en + ma;
	}	
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cn;
		result = prime * result + sum;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (cn != other.cn)
			return false;
		if (sum != other.sum)
			return false;
		return true;
	}
	
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCn() {
		return cn;
	}

	public void setCn(int cn) {
		this.cn = cn;
	}

	public int getEn() {
		return en;
	}

	public void setEn(int en) {
		this.en = en;
	}

	public int getMa() {
		return ma;
	}

	public void setMa(int ma) {
		this.ma = ma;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", sum=" + sum + "]";
	}
	@Override
	public int compareTo(Student o) {
		// TODO Auto-generated method stub
		int temp = this.sum-o.sum;		
		return temp==0?this.name.compareTo(o.name):temp;
	}
}


//这里是主类
public class FileDemo 
{
	private static final String LINE_SEPARATOR = System.getProperty("line.separator");	//写入文件的换行符

	//学生信息存储			就是管理系统那些操作
	public static void main(String[] args) throws IOException
	{
		/**
		 * 将学生对象（姓名，语文成绩，数学成绩，英语成绩，总分）按照从高到低排序， 并将姓名和总分从高到低写入文件中
		 * 
		 * 思路：
		 * 1、描述学生
		 * 2、既然要按照总分从高到低排序，学生多要存储集合，TreeSet可以排序
		 * 3、将具体信息写入文件
		 * 		操作文件，持久化存储，设计IO技术，而且是将数据存储到文件中，所以是写入，输出流
		 * */
		
		TreeSet<Student> set = new TreeSet<Student>(Collections.reverseOrder());		//实现了comparable接口的Student类传入后可以自动排序
		set.add(new Student("李四",20,20,20));
		set.add(new Student("旺财",10,10,20));
		set.add(new Student("小明",60,30,70));
		set.add(new Student("小红",80,90,80));
		set.add(new Student("小强",20,70,20));
		
		File file = new File("D:\\grades.txt");		
		if(!file.exists())
		{
			file.createNewFile();		//创建存放成绩表的文件
		}
		writeGrades(set,file);			//调用函数写入文件
		for (Student stu : set) {
			System.out.println(stu);	//打印一下写入的数据
		}
		
	}
	
	//函数本身不多，只是异常处理比较复杂，因为考虑到后期出现的一系列问题，所以没有简单的抛出
	public static void writeGrades(TreeSet<Student> set,File file) throws IOException 
	{
		//创建输出流对象和目的文件关联。并创建目的文件，outputstream操作文件Fileoutputstream
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
			//遍历集合中的对象，将数据写入到指定的文件中
			for (Student stu : set) 
			{
				String info = stu.getName()+"\t"+stu.getSum()+LINE_SEPARATOR;
				fos.write(info.getBytes());
			}
		}finally {						
			if(fos!=null)
			{
				//关闭资源
				try {
					fos.close();
				} catch (IOException e) {
					throw new RuntimeErrorException(null, "系统资源关闭失败");
				}
			}		
		}
		
		
	}
	
}
