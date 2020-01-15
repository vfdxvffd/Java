package IO;

import java.io.File;
import java.io.IOException;

import javax.naming.directory.DirContext;

public class FileDemo 
{
	public static void main(String[] args) throws IOException 
	{
		/*
		 * File类中的方法2
		 * 
		   *   对文件或者文件夹进行操作
		 * */
		
		
		//对文件操作
				File file = new File("D:\\forio\\3.txt");
				/*
				 * 创建文件，如果文件不存在，创建，返回true
				 * 如果存在，不创建，返回false
				 * 如果路径错误，IOException
				 * */
				boolean b1 = file.createNewFile();
				System.out.println("b1="+b1);
				
				
				//删除，
				boolean b2 =  file.delete();	//注意：删掉不去回收站，慎用
				//返回false的两种情况：1、不存在，2、正在使用
				System.out.println("b2="+b2);
				
				
				//判断是否存在
				boolean b3 = file.exists();
				System.out.println("b3="+b3);
		
		
		//对目录操作，创建，删除，判断
				File dir = new File("D:\\demo");
				File dirs = new File("D:\\haha\\hehe\\xixi");
				
				
				boolean b4 = dir.mkdir();	//创建单个目录
				System.out.println("b4="+b4);
				
				boolean b5 = dirs.mkdirs();	//创建多级目录
				System.out.println("b5="+b5);
				
				
				
				boolean b6 = dirs.delete();
				//删除目录时，如果目录中有内容，无法直接删除。只有将目录中的内容都删除后，保证该目录为空，这时这个目录才可以删除
				System.out.println("b6="+b6);
		
		
		
		System.out.println("----------判断文件，目录----------");
		File f = new File("D:\\java.txt");
		//要判断是否为文件还是目录，必须先判断存在 
		//f.createNewFile();
		f.mkdir();			//创建了一个名字叫做“java.txt”的目录
		//也可以创建“haha”文件，没有扩展名
		System.out.println(f.isFile());
		System.out.println(f.isDirectory());
	}
}
