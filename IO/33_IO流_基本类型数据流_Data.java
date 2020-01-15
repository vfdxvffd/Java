package IO;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class summary {	

	public static void main(String[] args) throws IOException {
		
		/**
		 * 另一个需求：保证数据的原样性不变
		 * 		例如：写一个整数，源是四个字节，希望目的也是四个字节
		 * 	需要一个可以操作基本数值的对象
		 * DataOutputStream			DataInputStream
		 * */
		
		writeData();
		readData();
	}

	public static void writeData() throws IOException 
	{
		FileOutputStream fos = new FileOutputStream("D:\\forio\\demo.txt");
		//需要额外功能吗？	需要， 可以写一个基本数值的原字节不变
		DataOutputStream dos = new DataOutputStream(fos);
		
		dos.writeInt(97);
		dos.close();
	}

	
	public static void readData() throws IOException 
	{
		FileInputStream fis = new FileInputStream("D:\\forio\\demo.txt");
		//需要额外功能，读取一个整数
		DataInputStream dis = new DataInputStream(fis);
		int x = dis.readInt();
		System.out.println("x="+x);
		dis.close();
	}
}
