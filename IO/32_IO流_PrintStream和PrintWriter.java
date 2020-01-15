package IO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;

/**	【笔记总结】
 * 字节流和字符流已经掌握了很多对象
 * 
 * 1、IO流的基础流对象。直接调用底层资源，操作数据的对象	File开头的对象
 * 2、根据IO流的学习规律，后面出现的流对象无非是增加一些额外功能
 * 		转换---转换流  InputStreamReader  OutputStreamWriter		
 *		高效---缓冲区对象   BufferedXXX		
 *		有多个源（字节流）---序列流   sequenceInputStream
 * 		对象需要序列化---ObjectInputStream ，ObjectOutputStream
 * 		需要操作基本类型数据保持字节原样性---DataOutputStream	，DataInputStream
 *
 *
 * 3、继续提供一些功能
 * 		需求：写一个数据（整数）到文件中，到目的地整数的表现形式不变
 * 			可以通过将整数转成字符串，变成字节数组写入目的地
 * 
 * 			简化方式，之前学习输出语句发现要输出的内容都原样不变的体现出来
 * 			输出语句对应的对象PrintStream
 * 		对象提供了很多打印的方法，打印方法的好处在于保证数据值的表现形式不变
 * 	PrintWriter	字符打印流
 * */



public class summary {	

	public static void main(String[] args) throws IOException {
		
		需求：写一个数据（整数）到文件中，到目的地整数的表现形式不变
		FileOutputStream fos = new FileOutputStream("D:\\forio\\demo.txt");
		
		//字节流的write方法只将一个整数的最低字节写入到目的地  00000000  00000000  00000000 01100001，
		//这是97在内存中对应的字节（int占四个字节），write会舍弃前面三个，只读取最后一个，到记事本中解码就会变成“a”
		fos.write(97);	
		fos.write(String.valueOf(97));
		fos.close();
		
		需要额外功能，保证数据值的表示形式。
		FileOutputStream fos = new FileOutputStream("D:\\forio\\demo.txt");
		PrintStream ps = new PrintStream(fos);
		//ps.write(97);	只能写入最低字节，调用的还是底层流的write方法
		ps.print(97);	//将数据转成字符串再写入。保证数据值的表现形式
		ps.close();
		
		
		PrintStream ps = new PrintStream("D:\\forio\\demo.txt");
		ps.print(97);
		ps.close();
		
		
		
		//需求：读取键盘录入。将录入的数据转成大写保存到文件中
		BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));
		
		//PrintWriter pw = new PrintWriter("D:\\forio\\demo.txt");	不能自动刷新，需要调用flush方法
	
		PrintWriter pw = new PrintWriter(System.out, true);	//自动刷新，对println有效
		
		//想要将数据打印到文件中，并使用自动刷新
		//也可以向里面传入其他的输出流，BufferedWriter,OutputStreamWriter 也都可以	
		//PrintWriter pw = new PrintWriter(new FileWriter("D:\\forio\\demo.txt"), true);
		
		String line = null;
		while((line = bufr.readLine()) != null)
		{
			if("over".equals(line))
				break;
			pw.println(line.toUpperCase());
		//	pw.flush();
		}
		
		pw.close();
		
	}
}
