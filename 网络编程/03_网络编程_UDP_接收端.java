package net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

//每次接收显示一条消息
public class UDPRecieve {

	public static void main(String[] args) throws IOException {
		
		System.out.println("udp  接收端     run");
		/**
		 * 定义一个udp的接收端，接收发送过来的数据。并显示在屏幕上
		 * 
		 * 思路：
		 *     1、先有udp  socket服务，就是先有插座嘛。 而且记住：接收端一定要明确端口，否则，收不到数据
		 *     2、接受数据。之前应该先将数据存储到数据包中。因为数据还有解析
		 *     3、先定义数据包
		 *     4、通过数据包对象获取数据包的内容，发送端的ip、发送端的端口、发送过来的数据
		 *     5、关闭资源
		 * */
		
		
		
		//1、先有udp  socket服务，就是先有插座嘛。而且记住：接收端一定要明确端口，否则，收不到数据
		 DatagramSocket ds = new DatagramSocket(10000);
		 
		//2、接受数据。之前应该先将数据存储到数据包中。因为数据还有解析
		//3、先定义数据包
		 byte[] buf = new byte[1024];
		 DatagramPacket dp = new DatagramPacket(buf, buf.length);
		 ds.receive(dp);	//阻塞
		 
		//4、通过数据包对象获取数据包的内容，发送端的ip、发送端的端口、发送过来的数据
		 String ip = dp.getAddress().getHostAddress();
		 int port = dp.getPort();
		 String text = new String(dp.getData(),0,dp.getLength()); 
		 
		 System.out.println(ip+"---:---"+port+"---:---"+text);
		//5、关闭资源
		 ds.close();
	}
	
}

//连续接收多条消息并显示
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
