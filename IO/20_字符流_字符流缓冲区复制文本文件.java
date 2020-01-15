package IO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class FileDemo 
{
	public static void main(String[] args) throws IOException
	{
		/**
		 * 	字符流中是否有提供缓冲区
		 * 	注意：其实自定义数组就可以解决问题缓冲区问题并提高效率
		 * 	为什么还要去使用流中的缓冲区对象？因为缓冲区对象中封装了出数组以外，还提供了更多的操作缓冲区数据的方法
		 * 	BufferedReader 		BufferedWriter
		 *  
		 * 	讲解字符流缓冲区的中特有方法
		 * 	操作字符数据时，有一个文本特有的表现形式，叫行Line
		 * 	操作的方法
		 * 		BufferedReader：	readLine():一行一行的读取
		 * 
		 * */
		
			writeText();
			readText();			
			copyTextByBuffer();
		
	}

	public static void writeText() throws IOException 
	{
		//
		BufferedWriter bufw = new BufferedWriter(new FileWriter("D:\\forio\\demo.txt"));
		
		//循环写入数据
		for(int x = 1; x < 6; x++)
		{
			bufw.write(x+"Java");
			bufw.newLine();			//写入一个换行符，兼容各个平台
			bufw.flush();			//刷新缓冲区
		}
		bufw.close();
	}

	
	
	public static void readText() throws IOException 
	{
		BufferedReader bufr = new BufferedReader(new FileReader("D:\\forio\\book.java"));
		
		String line = null;			//中转站
		while((line = bufr.readLine()) != null)		//bufr.readLine()返回值是String类型
		{
			System.out.println(line);
		}
		
		//readLine不返回换行符
//		while((line = bufr.readLine()) != null)
//		{
//			System.out.print(line);
//		}	
		bufr.close();
	}

	
	
	public static void copyTextByBuffer() throws IOException 
	{
		BufferedReader bufr = new BufferedReader(new FileReader("D:\\forio\\book.java"));
		BufferedWriter bufw = new BufferedWriter(new FileWriter("D:\\forio\\copy.txt"));
		
		//循环写一行数据
		String line = null;			//类似于一个中转站
		while((line = bufr.readLine()) != null)
		{
			bufw.write(line);
			bufw.newLine();
			bufw.flush();
		}
		
		bufr.close();
		bufw.close();
	}
}