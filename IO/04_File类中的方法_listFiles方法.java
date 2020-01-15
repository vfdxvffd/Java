package IO;

import java.io.File;

public class FileDemo 
{
	public static void main(String[] args) 
	{
		//需求：对给定的目录获取内部的内容
		File dir = new File("D:\\forio");
		//判断：1、必须是存在的	2、必须是目录，否则容易引发空指针异常NullPointerException
		
		String[] names = dir.list();	//获取的是目录下的当前的文件以及文件夹名称
		for (String name : names) {
		//	System.out.println(name);
		}			
		
		
		File[]files = dir.listFiles();	//获取目录下的当前文件以及文件夹对象
		for (File file : files) {
			System.out.println(file.getName());
		}
	}
}
