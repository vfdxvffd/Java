package IO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileDemo 
{
	
	
	
	private static final String LINE_SEPARATOR = System.getProperty("line.separator");

	public static void main(String[] args) throws IOException
	{
		//需求1：将数据写入到文件中
		//File file = new File("D:\\forio\\demo.txt");		下面这样写更严谨


		//创建临时目录
		File dir = new File("D:\\forio");
		if(!dir.exists())
		{
			dir.mkdir();
		}
		//创建存储数据的文件
		File file = new File(dir,"demo.txt");
		
		//创建一个用于操作文件的字节输出流对象。已创建就必须明确数据存储的目的地
		//输出流目的时文件，会自动创建，如果文件已存在，则覆盖
		FileOutputStream fos = new FileOutputStream(file);
		
		//调用父类中的write方法
		byte[] b = "sahdj".getBytes();
		fos.write(b);
		
		//关闭流资源
		fos.close();
				
		
		
		
		
		//需求2：将数据续写到已有文件中,就是追加
		//FileOutputStream(File file,boolean append)  续写
		
		File file = new File("D:\\forio\\demo.txt");
		
		FileOutputStream fos = new FileOutputStream(file, true);
		
		fos.write("hahaha".getBytes());
		
		fos.close();
		
		
		
		
		//需求3：在下一行写入新的数据
		File file = new File("D:\\forio\\demo.txt");
		
		FileOutputStream fos = new FileOutputStream(file, true);
		
		String str = LINE_SEPARATOR+"java";	//LINE_SEPARATOR是一个常量值，在上面定义了，为了兼容所有平台的换行，windows平台换行是 \r\n
		fos.write(str.getBytes());
		
		fos.close();
		
	}
}
