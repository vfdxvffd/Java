package split_merge;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Properties;

public class merge 
{
		public static void main(String[] args) throws IOException 
		{
			File partDir = new File("D:\\forio\\PartFile");
			mergerFile(partDir);
		}

		
		
		public static void mergerFile(File partDir) throws IOException 
		{
			/**
			 * 虽然合并成功，但问题如下：
			 * 		1、如何明确碎片个数来确定循环次数，已明确要有多少个输入流对象
			 * 		2、如何知道合并的文件的类型
			 * 解决方法：
			 * 		应该先读取配置文件
			 * */
			
			//1、获取配置文件
			File configFile = getConfigFile(partDir);
			
			//2、获取配置文件信息容器,获取配置信息的属性集		
			Properties prop = getProperties(configFile);
			
			//3、将属性集对象传递到合并方法中
			merger(partDir,prop);
			
		}
		
		
		

		//根据配置文件获取配置信息属性集
		public static Properties getProperties(File configFile) throws IOException 
		{
			
			FileInputStream fis = null;
			Properties prop = new Properties();
			
			try {
				//读取流和配置文件相关联
				fis = new FileInputStream(configFile);		
				//将流中的数据加载到集合中
				prop.load(fis);
			}finally {
				if(fis != null)
				{
					fis.close();
				}
			}
			
			return prop;	
		}

		
		
		
		//根据碎片目录获取配置文件对象
		public static File getConfigFile(File partDir) 
		{
			//健壮性判断
			if(!(partDir.exists() && partDir.isDirectory()))
			{
				throw new RuntimeException(partDir.toString()+"不是有效目录！");
			}
			
			
			//1、判断碎片文件目录中是否存在properties文件
			File[] files = partDir.listFiles(new FileFilter() {
				
				@Override
				public boolean accept(File pathname) 
				{				
					return pathname.getName().endsWith(".properties");
				}
			});
			
			if(files.length != 1)
			{
				throw new RuntimeException(partDir.toString()+"properties扩展名的文件不存在或不唯一！");
			}
			
			
			File configFile = files[0];
			return configFile;
		}

		
		
		
		//合并文件
		private static void merger(File partDir,Properties prop) throws IOException 
		{
			
			//获取属性集中的信息
			String filename = prop.getProperty("filname");
			int partCount = Integer.parseInt(prop.getProperty("partsCount"));
			
			//准备使用IO包中的SequenceInputStream，对碎片进行合并，将多个读取流合并成一个读取流
			ArrayList<FileInputStream> list = new ArrayList<FileInputStream>();
			for(int i = 1; i < partCount; i++)
			{
				list.add(new FileInputStream(new File(partDir,i+".part")));
			}
			
			
			//怎么获取枚举对象？List自身时无法获取枚举Enumeration对象的，考虑到Collections中去找
			Enumeration<FileInputStream> en = Collections.enumeration(list);
			
			//源，读取流
			SequenceInputStream sis = new SequenceInputStream(en);
			
			//目的
			FileOutputStream fos = new FileOutputStream(new File(partDir, filename));
			
			//不断的读写
			byte[] buf = new byte[4096];
			int len = 0;
			while((len = sis.read(buf)) != -1)
			{
				fos.write(buf,0,len);
			}
			
			sis.close();
			fos.close();
		}


}

