package split_merge;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
	
public class Split 
{
	
		private static final int BUFFER_SIZE = 1048576;		//1024*1024=1048576

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
			
			File srcFile = new File("D:\\forio\\失眠飞行.mp3");			//待切割的文件
			File partsDir = new File("D:\\forio\\PartFile");			//碎片存放的文件夹
			splitFile(srcFile,partsDir);
		}


		//用于切割文件
		public static void splitFile(File srcFile, File partsDir) throws IOException 
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
			
			fos.close();		
			fis.close();
		}

}
