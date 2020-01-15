package IO;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileDemo 
{
	public static void main(String[] args) throws IOException
	{
		//需求：读取文件中的数据，显示再屏幕上
		
		File file = new File("D:\\forio\\demo.txt");
		
		//创建一个字节输入流对象,必须明确数据源,其实就是创建字节读取流和数据源相关联
		FileInputStream fis = new FileInputStream(file);


		
		//第一种方法：读取数据,先使用read();一次读一个字节，然后指向下一个字节
		int ch1 = 0;	
		while((ch1 = fis.read()) != -1)
		{				
			//read()返回的是0-255的字节数
			System.out.println("ch1="+(char)ch1);
		}
		


		//第二种方法：read(typr[]);
		byte[] buf = new byte[1024];	//数组大小可以设置为1024的整数倍
		int len = 0;					//len表示一次读取的字节数
		while((len = fis.read(buf)) != -1)
		{
			System.out.println(new String(buf,0,len));		//new String(buf,0,len)是String的一种构造方法，具体可以查API
		}
		
		fis.close();
								
		/*
		//创建一个字节数组
		int x = file.available();		//先算出文件的字节数
		byte[] buf = new byte[x];		//将读取的存在缓冲区中,虽然定义了一个刚刚好的数组。注意：但是如果文件过大，容易溢出，所以建议缓冲区大小还是1024的整数倍
		//读取
		int len = fis.read(buf);	
		System.out.println(len+":"+new String(buf));
		//关闭并释放资源
		fis.close();
		*/

		//关闭并释放资源
		fis.close();
	}
}

