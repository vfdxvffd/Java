package IO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

//需求：将键盘录入的数据存储到文件中
/**
 *思路:
 * 1、将数据存储到文件，没有问题
 * 2、怎么获取数据来源呢？键盘录入怎么弄呢？ 
 * 键盘录入是输入,系统中应该具备的一部分
 * 在System类中找到了标准输入流属性in
 * System.in对应的类型是InputStream		字节输入流
 */

public class FileDemo 
{
	public static void main(String[] args) throws IOException 
	{
		InputStream in = System.in;		//先写一个输入流
		//通过桥梁，将字节输入流转换为字符输入流
		InputStreamReader isr = new InputStreamReader(in);
		//对字符进行效率提高，而且使用缓冲区对象的特有方法readLine();
		BufferedReader bufr = new BufferedReader(isr);
		
		//以上三句太繁琐可以写成一句
		//BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));
		//再为了方便起见，在Eclipse中设置了快捷引用，写cin回车就ok了


		String line = null;
		while((line = bufr.readLine()) != null)
		{
			if("over".equals(line))
				break;
			System.out.println(line);
		}

	}
}
