package Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

public class AppCountTest {

	/**
	 * 练习:定义功能记录程序运行次数，满足次数后，给出提示：试用次数已到，请注册
	 * 
	 * 思路：
	 *		1、需要计数器。这个软件使用一次计数一次。每使用一次，就进行次数累计
	 *		2、计数器是程序的一个变量，程序启动计数器计数，可是程序结束这个计数器就消失了
	 *	下次启动会重新进行计数，原来计数值没有保留下来。
	 *		3、让这个计数器持久化。存储到文件中，为了标识数据的可读性，给数据起个名字
	 *	出现键值对。而且还是一个持久化的键值对，Properties集合正好符合这个要求
	 * @throws IOException 
	 * */
	public static void main(String[] args) throws IOException {
		
		if(isStop())
		{
			System.out.println("使用次数已到，请注册");
			return;
		}
		
		runcode();
		
	}
	
	private static boolean isStop() throws IOException 
	{
		
		//1、配置文件
		File configFile = new File("D:\\forio\\app.properties");
		if(!configFile.exists())		//如果配置文件不存在，创建
			configFile.createNewFile();
		
		//2、创建属性集
		Properties prop = new Properties();		
		
		//3、定义读取流和配置文件关联
		FileInputStream fis = new FileInputStream(configFile);
		
		//4、将流关联的数据读到属性集中
		prop.load(fis);
		
		//5、通过属性集的指定键count，获取次数
		String value = prop.getProperty("count");
		int count = 0;
		
		//6、对value值判断，如果存在就自增
		if(value != null)
		{
			count = Integer.parseInt(value);
			
			if(count >= 5)
			{
				fis.close();
				return true;
			}	
		}
		count++;		//对其值进行自增
		//7、将自增后的值和指定的键重新存储到属性集中，键相同，值覆盖
		prop.setProperty("count", Integer.toString(count));
		
		//8、将属性集存储到配置文件中，对配置文件中的信息进行更新
		FileOutputStream fos = new FileOutputStream(configFile);	//输出流每次会将源文件覆盖，所以不能放在上面
		prop.store(fos, "App run count");
		
		//9、关闭资源
		fos.close();
		fis.close();
		
		return false;
	}

	//程序主体
	public static void runcode() {
		System.out.println("程序运行中......play");
	}
}
