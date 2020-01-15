package IO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;


public class FileDemo 
{
	private static final int BUFFER_SIZE = 1048576;		//1024*1024=1048576
	private static final String LINE_SEPARATOR = System.getProperty("line.separator");

	public static void main(String[] args) throws IOException 
	{
		/**
		 * 练习：将一个媒体文件切割成多个碎片
		 * 
		 * 	思路：
		 * 		1、读取源文件，将源文件的数据分别复制到多个文件中
		 * 		2、切割方式有两种：要么按照碎片个数且，要么按照碎片大小切
		 * 		3、一个输入流对应多个输出流
		 * 		4、每一个碎片都需要编号，顺序不要错
		 * */
		
		File srcFile = new File("D:\\forio\\needy.mp3");
		File partsDir = new File("D:\\forio\\PartFile");
		int no = splitFile(srcFile,partsDir);
		
		File configFile = new File(partsDir, Integer.toString(no)+".partconfig");
		readConfig(configFile);
	}


	//用于切割文件
	public static int splitFile(File srcFile, File partsDir) throws IOException 
	{
		//健壮性判断
		if(!(srcFile.exists() && srcFile.isFile()))
			throw new RuntimeException("源文件不存在或不是正确的文件");
		if(!partsDir.exists())
			partsDir.mkdirs();
			
		//使用字节读取流和源文件关联
		FileInputStream fis = new FileInputStream(srcFile);
		
		//明确目的，目的输出流有多个，只创建引用
		FileOutputStream fos = null;
		
		//定义缓冲区.	1M
		byte[] buf = new byte[BUFFER_SIZE];
		
		//频繁读写操作
		int NO = 1;		//碎片文件的编号
		int len = 0;
		while((len = fis.read(buf)) != -1)
		{
			//创建输出流对象。只要满足了缓冲区大小，碎片数据确定，直接往碎片文件中写数据
			//碎片文件存储到partsDir中，名称为编号+扩展名
			fos = new FileOutputStream(new File(partsDir,(NO++)+".part"));
			//将缓冲区中的数据写入到碎片文件中
			fos.write(buf,0,len);
			//关闭输出流
			fos.close();		
		}
		
		/**
		 * 将源文件以及切割的一些信息也保存起来随着碎片文件一起发送
		 * 信息:
		 * 		1、源文件的名称（文件类型）
		 * 		2、切割碎片的个数
		 * 将这些信息单独封装到一个文件中。还要一个输出流完成此动作
		 * */
		
		String filename = srcFile.getName();
		int partCount = NO;
		
		//创建一个输出流
		fos = new FileOutputStream(new File(partsDir,NO+".properties"));
		
		//创建一个属性集
		Properties prop = new Properties();
		prop.setProperty("filname", filename);
		prop.setProperty("partsCount", Integer.toString(partCount));
		
		//将配置信息存储到属性集中
		prop.store(fos, "part file info");
		
//		fos.write(("filename="+filename+LINE_SEPARATOR).getBytes());		
//		fos.write(("partsCount="+(Integer.toString(partCount))).getBytes());

		
		
		fos.close();		
		fis.close();
		
		return NO;
	}

	
	//读取配置文件
	public static void readConfig(File configFile) throws IOException 
	{
		BufferedReader bufr = new BufferedReader(new FileReader(configFile));

		/*
		 * 发现配置文件很多，需要进行存储
		 * 用哪个容器？个数不确定，就使用集合
		 * 有对应关系，使用双列集合		Map集合
		 * 发现配置文件中的信息都是字符串，这些信息不在内存中，而是在硬盘上
		 * map中和IO技术结合的集合对象	：Properties。   它里面存储的键值都是字符串，通常这个集合就用于配置文件的操作
		 * 
		 * */
		
		Properties prop = new Properties();
		//使用Properties集合中的load方法，就可以将流中的数据加载集合中
		prop.load(bufr);	
		//从流中读取数据并切割，切割完之后再存到prop中去
		System.out.println(prop);
		
		
		bufr.close();
	}

}