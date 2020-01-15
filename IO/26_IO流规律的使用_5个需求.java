package IO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;


public class FileDemo 
{
	public static void main(String[] args) throws IOException 
	{
	需求0：将字符串写入到文件中
		/**
	 	明确一：有源吗？有目的吗？
			源：字符串	String	不需要使用IO，直接定义String字符串就可以了
			目的：文件	File  	使用IO，输出流OutputStream  Writer
			
		明确二：
			是文本数据吗？	是
			目的：Writer
			
		明确三：具体设备是？
			目的设备是：
				硬盘：File开头的流对象Writer体系中的
				
		明确四：需要额外功能吗？
			需要高效：缓冲区对象 BufferedWriter
				
		明确出来具体要使用的流对象是FileWriter	 BufferedWriter
		**/
		FileWriter fw = new FileWriter("D:\\forio\\demo.txt");
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write("需求0");
		bw.newLine();
		bw.flush();
		bw.close();
		
		

	需求1：复制一个文本文件，有可能对复制过程中的文本进行过滤
		/**
	 	明确一：有源，有目的
			源   ：		InputStream	Reader
			目的：	OutputStream Writer
		     强调：如果只做复制动作不需要考虑是字节还是文本，直接使用字节流	
		     但是如果在复制过程中，需要对文本中的字符数据进行操作，必须使用字符流
		FileInputStream fis = new FileInputStream("D:\\forio\\demo.txt");
		FileOutputStream fos = new FileOutputStream("D:\\forio\\copy.txt");
		
		明确二：是纯文本数据
			源：	Reader
			目的：Writer
			
		明确三：具体设备
			源设备：
				硬盘：File开头的对象FileReader				
			目的设备：
				硬盘：File开头的对象FileWriter
		**/
		FileReader fr = new FileReader("D:\\forio\\demo.txt");
		FileWriter fw = new FileWriter("D:\\forio\\copy.txt");
		
		明确四：需要额外功能
			高效
		
		BufferedReader bufr = new BufferedReader(new FileReader("D:\\forio\\demo.txt"));
		BufferedWriter bufw = new BufferedWriter(new FileWriter("D:\\forio\\copy.txt"));
		String line = bufr.readLine();
		line = line.replace("nba", "美国职业篮球联赛");
		bufw.write(line);
		bufr.close();
		bufw.close();
		
		
		
		需求2：读取键盘录入将数据存储到文件中
		/**
	 	明确一：有源，有目的
			源   ：		InputStream	Reader
			目的：	OutputStream Writer

		明确二：是纯文本数据
			源：	Reader
			目的：Writer
			
		明确三：具体设备
			源设备：    键盘	System.in
			目的设备：硬盘	FileWriter		
		**/
		InputStream in = System.in;
		FileWriter fw = new FileWriter("D:\\forio\\demo.txt");
		
		byte[] buf = new byte[1024];
		int len = in.read(buf);
		String str = new String(buf,0,len);
		fw.write(str);
		//像上面这样，最后还有判断最后一个字节是不是中文的一半，如果是还要截取，虽然可以做，但是很麻烦
		
		/*
		明确四：需要额外功能吗？
			需要啊： 转换，因为明确源的体系是Reader。可是具体设备Systen.in这是字节流，需要字符流
				 	需要转换功能，将字节流转换为字符流		字节--->字符	InputStreamReader
				 	*/
		InputStreamReader isr = new InputStreamReader(System.in);
		FileWriter fw = new FileWriter("D:\\forio\\demo.txt");
			
		//还需要额外功能	高效
		BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufw = new BufferedWriter(new FileWriter("D:\\forio\\demo.txt"));
		
		


		需求3：读取文本文件打印到屏幕上
		
		/**
	 	明确一：有源，有目的
			源   ：		InputStream	Reader
			目的：	OutputStream Writer

		明确二：是纯文本数据
			源：	Reader
			目的：Writer
			
		明确三：具体设备
			源设备：    硬盘	FileReader
			目的设备：屏幕	System.out
		 **/
		FileReader fr = new FileReader("D:\\forio\\demo.txt");
		OutputStream out = System.out;
		//明确四：额外功能		高效
		BufferedReader bufr = new BufferedReader(new FileReader("D:\\forio\\demo.txt"));
		BufferedWriter bufw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		



		需求四：读取文件中的文本数据，将按照utf-8的方式存储到文件中
		/**
	 	明确一：有源，有目的
			源   ：		InputStream	Reader
			目的：	OutputStream Writer

		明确二：是纯文本数据
			源：	Reader
			目的：Writer
			
		明确三：具体设备
			源设备：    硬盘	FileReader
			目的设备：硬盘	System.out
		 **/
		
		FileReader fr = new FileReader("D:\\forio\\demo.txt");
		FileWriter fw = new FileWriter("D:\\forio\\copy.txt");
		/*但是，不符合题中的要求，对于目的要求必须是utf-8编码
		所以必须使用额外功能*/
		
		
		明确四：需要额外功能    		转换
		FileReader fr = new FileReader("D:\\forio\\demo.txt");//默认编码
		OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("D:\\forio\\copy.txt"),"utf-8");
		
		还需要其他额外功能  	缓冲区
		BufferedReader bufr = new BufferedReader(new FileReader("D:\\forio\\demo.txt"));
		BufferedWriter bufw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D:\\forio\\copy.txt"),"utf-8"));
		
		
	}
}
