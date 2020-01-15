package IO;

import java.io.File;

public class FileDemo 
{
	static int acount=0;
	public static void main(String[] args) 
	{
		/***
		 * File类的listFiles()列出当前目录下的文件以及文件夹内容
		 * 
		 * 需求：能不能列出当前目录下的子目录中的所有内容
		 * 思路：
		 * 	1、在遍历当前目录时，会获取到当前的所有文件及文件夹
		 * 	2、要遍历子目录需要对获取的当前的file对象进行判断，只有是目录时才可以作为子目录进行继续遍历
		 */
		
		File dirFile = new File("D:\\VS 2019");
		ListAll(dirFile);
		System.out.println("一共有"+acount+"个文件和文件夹");
	}
	
	public static void ListAll(File dir) 	//dir用来接收待遍历的目录
	{
		File[] files = dir.listFiles();
		
		for (File file2 : files) {		
			if(file2.isDirectory())		//如果遍历到当前的file对象是个目录，继续遍历，递归调用
			{
				ListAll(file2);
			}
			acount++;
			System.out.println(file2.getName());
		}
	}
}
