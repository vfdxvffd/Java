import java.io,File;

public class FileDemo
{
	public static void main(String[] args) 
	{
		/*
			File类的构造函数
			如何创建文件对象
		*/

		String pathname = "D:\\forio";
		String pathname2 = "D:\\forio\\3.txt";	//封装的是3.txt这个文件
		File f1 = new File(pathname);	//将forio文件封装成对象，注意也可以封装不存在的文件或者文件夹，变成对象，可以再创建嘛
		System.out.println(f1);


		File f2 = new File("D:\\forio","3.txt");//分开之后可以将后面的变成一个变量
		System.out.println(f2);

		File dir = new File("D:\\forio");
		File f3 = new File(dir,"3.txt");
		System.out.println(f3);

		//也可以向下面这样，File.separator表示系统分隔符，可以适应所有系统，不止Windows
		File f4 = new File("D:"+File.separator+"forio"+File.separator+"3.txt");
		System.out.println(f4);
	}
}