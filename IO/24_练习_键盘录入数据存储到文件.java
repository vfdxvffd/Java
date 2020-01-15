package IO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

//需求：将键盘录入的数据存储到文件中
/**
 * 1、键盘录入
 * 2、目的是文件
 * 3、这个示例既要用到输入流，也要用到输出流
 * 而且操作的数据都是文本数据，也可以使用字符流
 * 而且目的是文件可以使用操作文件的字符输出流
 */

public class FileDemo 
{
	public static void main(String[] args) throws IOException 
	{
		//键盘录入
		BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));
		
		//目的是文件
		FileWriter fw = new FileWriter("D:\\forio\\demo.txt");
		BufferedWriter bfw = new BufferedWriter(fw);		//效率
		
		String line = null;
		while((line = bufr.readLine()) != null)
		{
			if("over".equals(line))
				break;
			bfw.write(line);
			bfw.newLine();
			bfw.flush();
		}
		bfw.close();
	}
}
