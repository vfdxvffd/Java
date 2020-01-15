package IO;

import java.io.File;
import java.io.IOException;

public class FileDemo 
{
	public static void main(String[] args) throws IOException 
	{
		/**
		 * 需求：删除一个带有内容的目录：
		 * 思路：
		 *1、必须从里往外删
		 *2、到底有没有多级目录不确定，递归
		 * */
		
		
		File dir = new File("D:\\新建文件夹");
		removeDir(dir);
	}
	
	public static void removeDir(File dir)
	{
		File[] files = dir.listFiles();
		if(files != null)		//如果不判断，可能会抛空指针异常
		{
			for (File file : files) 
			{
				if(file.isDirectory())	//如果是文件夹，递归调用
				{
					removeDir(file);
				}
				else 			//如果不是文件夹，即是文件，删除
				{
					System.out.println(file+"---------------"+file.delete());
				}
			}
		}		
		System.out.println(dir+"---------------"+dir.delete());		//最后删除文件夹
	}
}
