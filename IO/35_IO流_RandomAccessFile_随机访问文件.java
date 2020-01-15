package IO;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileDemo{
	//需求：对文件进行读或者写的操作，想从哪里读就从哪里读，想从哪里写就从哪里写
	public static void main(String[] args) throws IOException {		
		
		/**	RandomAccessFile
		 * 	1、随机访问
		 * 	2、操作文件		也只能操作文件，局限性
		 * 	3、既可以读也可以写
		 * 	4、内部维护了用于存储数据的byte数组
		 * 	5、提供了一个对数组操作的文件指针
		 * 	6、文件指针可以通过getFilePoint 方法读取，并通过seek方法设置
		 * 
		 * 	注意：随机读写，数据需要（最好）有规律，用RandomAccessFile类需要明确要操作数据的位置
		 * */
	
		writeFile();
		
		readFile();
	}


	public static void writeFile() throws IOException {
		
		RandomAccessFile raf = new RandomAccessFile("D:\\forio\\demo.txt", "rw");
	
		//写一些字符信息，姓名+年龄
//		raf.write("张三".getBytes());
//		raf.writeInt(97);	//保证字节的原样性
//		raf.write("李四".getBytes());
//		raf.writeInt(99);	//保证字节的原样性
		
		raf.seek(8*10);	//一个人的数据占8个字节
		raf.write("王武".getBytes());
		raf.writeInt(102);	//保证字节的原样性
		
		System.out.println(raf.getFilePointer());//获取的是指针指向数组的位置下标
		raf.close();
	}
	
	
	
	public static void readFile() throws IOException {
		
		RandomAccessFile raf = new RandomAccessFile("D:\\forio\\demo.txt", "r");
		
		//可以改变指针位置,想读谁就读谁
		raf.seek(8*10);
		
		byte[] buf = new byte[4];
		raf.read(buf);
		String name = new String(buf);
		System.out.println("name = "+name);
		
		
		int age = raf.readInt();
		System.out.println("age = "+age);
		
		raf.close();
	}
}