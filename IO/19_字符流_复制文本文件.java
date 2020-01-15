package IO;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileDemo 
{
	public static void main(String[] args) throws IOException
	{		
		/**
		 * 练习： 复制文本文件
		 * 思路：
		 * 	  1、既然是文本，就涉及编码表，需要用字符流
		 * 	  2、操作的是文件，涉及硬盘
		 *    3、有指定码表吗？ 没有，默认即可
		 *    操作的是文件，使用默认码表。直接使用字符操作文件的便捷类。FileReader   FileWriter
		 * */
		
		copyText();
	}

	public static void copyText() throws IOException 
	{
		//明确源和目的文件
		FileReader fr = new FileReader("D:\\forio\\book.txt");
		FileWriter fw = new FileWriter("D:\\forio\\copy.txt");
		
		//循环读写操作

		/*   一个字符一个字符的操作,效率不高
		int len=0;
		while((len = fr.read()) != -1)
		{
			fw.write(len);
		}*/
		
		//整一个缓冲区,提高效率
		char[] buf = new char[1024];
		int len = 0;
		while((len = fr.read(buf)) != -1)
		{
			fw.write(new String(buf,0,len));
			fw.flush();
		}

		fr.close();
		fw.close();
	}
}