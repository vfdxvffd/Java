package IO;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

public class Demo 
{

	public static void main(String[] args) throws IOException 
	{
		//Properties集合的使用
		methodDemo_1();
		methodDemo_2();
		methodDemo_3();
	}

	//1、基本使用，存和取
	public static void methodDemo_1() {
		//创建一个集合
		Properties prop = new Properties();
		
		//添加数据
		prop.setProperty("zhangsan", "39");
		prop.setProperty("lisi", "29");
		
		//获取数据
		//String value = prop.getProperty("lisi");
		//System.out.println("value="+value);
		
		//全部取出
		Set<String> set = prop.stringPropertyNames();
		
		for (String name : set) {
			String value = prop.getProperty(name);
			System.out.println(name+":"+value);
		}
		
		//方便调试
		//prop.list(System.out);
	}
	
	
	//2、演示从流中加载
	public static void methodDemo_2() throws IOException 
	{
		File configFile = new File("D:\\forio\\PartFile\\8.partconfig");
		FileReader fr = new FileReader(configFile);
		
		Properties prop = new Properties();
		//使用Properties集合中的load方法，就可以将流中的数据加载集合中
		prop.load(fr);
		
		//从流中读取数据并切割，切割完之后再存到prop中去
		System.out.println(prop);
		
		fr.close();
	}
	
	
	
	//3、保存到流中的方法（持久化）
	public static void methodDemo_3() throws IOException
	{
		Properties prop = new Properties();
		
		//添加数据
		prop.setProperty("zhangsan", "39");
		prop.setProperty("lisi", "29");
		
		//想要把数据保存到文件中，需要输出流
		FileWriter fw = new FileWriter("D:\\forio\\info.properties");
		
		//使用store方法
		prop.store(fw, "info");		//info那个不咋重要，就是标识一下
		
		fw.close();
	}
}	







