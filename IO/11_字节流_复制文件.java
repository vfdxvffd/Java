package IO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileDemo 
{
	public static void main(String[] args) throws IOException
	{
		/*
		 * 需求：复制文件
		 * 原理:读取一个已有文件，并将这些读到的数据写入到另一个文件中
		 */
		
		//明确源和目的
		File srcFile = new File("D:\\forio\\demo.txt");
		File destFile = new File("D:\\forio\\book.txt");
		/*换成MP3也行啊
		File srcFile = new File("D:\\forio\\needy.mp3");
		File destFile = new File("D:\\A妹的歌2.mp3");
		*/
		
		//明确字节流输入流和源相关联,输出流和目的相关联
		FileOutputStream fos = new FileOutputStream(destFile);
		FileInputStream fis = new FileInputStream(srcFile);
		
		//使用输入流的读取方法读取字节,并将字节写入到目的中
		
		//第一种方法，一个字节一个字节的赋复制
			int len = 0;
			while((len = fis.read()) != -1)
			{
				fos.write(len);
			}
		
		//第二种方法，用字节流数组，定义一个缓冲区
			byte[] buf = new byte[3072];
			int len = 0;		//表示一次读到的字节数
			while((len = fis.read(buf)) != -1)
			{
				fos.write(buf,0,len);	//查API，FileOutputStream里有三个write的重载方法，这是第二种write(byte[] b,int off,int len)，将指定byte数组中从偏移量off开始的len个字节写入此输出流
			}
		
		
		//关闭资源
		fis.close();
		fos.close();
	}
}
