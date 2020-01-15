package IO;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * 源和目的都是内存的流
 * 
 * 字节流：
 * 	ByteArrayInputStream	ByteArrayOutputStream
 * 字符流：
 * 	CharArrayReader		CharArrayWriter
 * 	StringReader		StringWriter
 * 原理就是通过流的read，write方法对数组以及字符串进行操作
 * 
 * 关闭这些流都是无效的，并未调用系统资源，不需要抛出IO异常
 * */

public class summary {	

	public static void main(String[] args){
		//源和目的都是内存的读写
		
		//源：内存
		//用流的思想来操作数据
		ByteArrayInputStream bis = new ByteArrayInputStream("abcde".getBytes());
		
		//目的：内存
		ByteArrayOutputStream bos = new ByteArrayOutputStream();//内部有一个可以自动增长的数组
		
		//不断的读写
		int ch = 0;
		while((ch = bis.read()) != -1)
		{
			bos.write(ch);
		}
		//因为没有调用底层资源，所以不要关闭，即使调用了close，也没有任何效果，不会抛出IOException
		
		System.out.println(bos.toString());
	}

}
