package IO;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;


public class FileDemo 
{
	private static final String LINE_SEPARATOR = System.getProperty("line.separator");
	public static void main(String[] args) throws IOException
	{
		/**
		 * 需求1：通过流写一篇文章，里面有中文。“你好”
		 * 需求2：一篇文章中出现了多少个好字。读取数据。判断好字并计数
		 * 
		 * 	思路：读取一个文本，获取内容判断好字
		 * */
		writeCNText();
		//解决需求2问题： 使用FileReader
		readCNTxtByReader();
		
	}

	public static void readCNTxtByReader() throws IOException 
	{
		//创建一个读取字      （着重）符（着重）   （注意是字符，不是字节  ） 		 文件的读取流对象,FileReader
		//这个流的底层使用的是FileInputStream
		//Reader: 读取字符流的抽象超类， read()：读取单个字符并返回 ， read(char[]):将数据存储到字符数组中，并返回个数
		FileReader fr = new FileReader("D:\\forio\\demo.txt");
		int ch = 0 , count = 0;
		while((ch = fr.read())!= -1)
		{
			if(ch == '好')
				count++;
		}
		System.out.println(count);
		fr.close();
	}

	public static void writeCNText() throws IOException 
	{
		FileOutputStream fos = new FileOutputStream("D:\\forio\\demo.txt");
		fos.write("hao好你好你好".getBytes());
		fos.close();
	}
}
