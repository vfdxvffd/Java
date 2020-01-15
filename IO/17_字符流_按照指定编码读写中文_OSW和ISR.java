package IO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FileDemo 
{
	public static void main(String[] args) throws IOException
	{
		//需求：既然识别中文的码表有两个，GBK  UTF-8
		//能不能将中文数据按照utf-8的方式进行文件的存储呢？
		//这样就不能使用FileWriter了，因为FileWriter中默认的是GBK(在中国)
		//通过FileWriter的API的描述，要指定编码表这些值，需要使用OutputStreamWriter
		//OutputStreamWriter 是字符流通向字节流的桥梁： 可使用指定的charset将要写入流的字符编码成字节
		//它的作用就是，将字符串按照指定的编码表转成字节，再使用字节流将这些字节写出去
		//https://zhidao.baidu.com/question/535910341.html可以看看这个回答
		
		WriteIn();
		ReadOut();
		
	}

	//InputStreamWriter   按照指定编码读中文
	private static void ReadOut() throws IOException
	{
		InputStreamReader isr = new InputStreamReader(new FileInputStream("D:\\forio\\demo.txt"),"utf-8");
		
		char[] ch = new char[1024];
		int len=isr.read(ch);
		
		System.out.println(new String(ch,0,len));
		isr.close();
	}

	//OutputStreamWriter   按照指定编码写中文
	public static void WriteIn() throws IOException {
		//创建了一个字符通向字节的流对象，并明确了字节流的目的地是一个文件，并明确编码表是utf-8
		OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("D:\\forio\\demo.txt"),"UTF-8");
		
		osw.write("你好");
		osw.close();
	}
}



	OutputStreamWriter和InputStreamReader分别是FileWriter和FileReader的父类

	父类和子类的功能有什么区别呢？
	OutputStreamWriter和InputStreamReader是字符和字节的桥梁：也可以称之为字符转换流
	字符转换流原理：字节流+编码表

	FileWriter和FileReader：作为子类，仅作为可以操作字符文件的便捷类存在
	当操作的字符文件，使用的是默认编码表时可以不用父类，而直接使用子类就完成操作了，简化了代码

	InputStreamReader isr = new InputStreamReader(new FileInputStream("a.txt"));		//默认字符集
	InputStreamReader isr = new InputStreamReader(new FileInputStream("a.txt"),"GBK");	//指定GBk字符集
	FileReader fr = new FileReader("a.txt");
	注意：这三句代码功能是一样的，其中第三句最为便捷
		 一旦要指定其他编码时，绝对不能使用子类，必须使用字符转换流
		 什么时候用子类呢？
		 	条件：1、操作的是文件
				  2、使用默认编码
	转换流：
	字节--->字符： 看不懂的--->看得懂的 	需要读    输入流	 	InputStreamReader
	字符--->字节： 看的懂的--->看不懂的 	需要写    输出流	 	OutputStreamWriter