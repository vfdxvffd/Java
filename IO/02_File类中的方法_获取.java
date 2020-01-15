package IO;
import java.io.File;
import java.text.DateFormat;
import java.util.Date;
public class FileDemo 
{
	public static void main(String[] args) 
	{
		/*
			File类的方法演示
			获取文件的信息，名称，大小，时间
		*/	
		File file = new File("3.txt");

		String absPath = file.getAbsolutePath();	//绝对路径
		String pathString= file.getPath();	//File中封装的路径是什么获取的就是什么
		String filename = file.getName();
		long size = file.length();			//字节数
		long time = file.lastModified();	//毫秒值
		
		System.out.println("absPath="+absPath);
		System.out.println("Path="+pathString);
		System.out.println("filename="+filename);
		System.out.println("size="+size);
		System.out.println("time="+time);
		
		//毫秒值--Date--格式化--字符串文本
		String str_date = DateFormat.getDateTimeInstance(DateFormat.LONG,DateFormat.LONG).format(new Date(time));
		System.out.println(str_date);
	}
}
