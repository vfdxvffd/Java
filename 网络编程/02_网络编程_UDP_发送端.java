package net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

//每次发送一条消息
public class UDPSend {

	
	public static void main(String[] args) throws IOException {
		
		System.out.println("udp  发送端     run");
		
		/**
		 * 通过查阅文档，了解到用于UDP传输协议的对象是DatagramSocket
		 * 
		 * 通过UDP协议发送一段文本数据
		 * 思路：
		 *     1、需要先建立UDP的socket。它具备发送和接收功能
		 *     2、将数据封装到数据包中。数据包对象是DatagramPacket
		 *     3、使用socket对象的send方法将数据包发送出去
		 *     4、关闭资源
		 * */
		
		
		//1、需要先建立UDP的socket。它具备发送和接收功能
		DatagramSocket ds = new DatagramSocket(8888);
		
		//2、将数据封装到数据包中。数据包对象是DatagramPacket
		String text = "hello udp来了！";
		byte[] buf = text.getBytes();	//将数据转成字节数组
		//将字节数组封装到数据包中
		DatagramPacket dp = new DatagramPacket(buf, buf.length, InetAddress.getByName("192.168.241.1"), 10000);
		
		//3、使用socket对象的send方法将数据包发送出去
		ds.send(dp);
		
		//4、关闭资源
		ds.close();
	}
}


//发送多条消息
public class UDPsend2 {

	public static void main(String[] args) throws IOException {

		System.out.println("udp  发送端     run");

		/**
		 * 通过查阅文档，了解到用于UDP传输协议的对象是DatagramSocket
		 * 
		 * 通过UDP协议发送一段文本数据 思路： 1、需要先建立UDP的socket。它具备发送和接收功能
		 * 2、将数据封装到数据包中。数据包对象是DatagramPacket 3、使用socket对象的send方法将数据包发送出去 4、关闭资源
		 */

		// 1、需要先建立UDP的socket。它具备发送和接收功能
		DatagramSocket ds = new DatagramSocket(9999);

		// 2、将数据封装到数据包中。数据包对象是DatagramPacket。数据来自于键盘录入
		BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));
		String line = null;
		while ((line = bufr.readLine()) != null) 
		{
			if("over".equals(line))
				break;
			byte[] buf = line.getBytes(); // 将数据转成字节数组
			// 将字节数组封装到数据包中
			DatagramPacket dp = new DatagramPacket(buf, buf.length, InetAddress.getByName("192.168.241.1"), 10000);
			
			ds.send(dp);
		}

		// 4、关闭资源
		ds.close();
	}

}
