package udp群聊;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPChatTest {
		/**
		 * 案例一：通过udp实现群聊程序
		 * 
		 *  思路：
		 *  	这个程序既有收又有发，需要同时执行，需要多线程技术
		 *  	一个线程复制发，一个线程负责收。需要两个任务
		 * @throws IOException 
		 */
	
	public static void main(String[] args) throws IOException {
		
		//发送端的socket 接收端的socket		
		DatagramSocket sendDs = new DatagramSocket();
		DatagramSocket ReceDs = new DatagramSocket(10003);
		
		Send send = new Send(sendDs);
		Receive rece = new Receive(ReceDs);
		
		Thread t1 = new Thread(send);
		Thread t2 = new Thread(rece);
		
		t1.start();
		t2.start();
	}
}

//发送任务
class Send implements Runnable{
	
	private DatagramSocket ds;
	
	public Send(DatagramSocket ds) {
		super();
		this.ds = ds;
	}

	@Override
	public void run() {

		System.out.println("udp  发送端     run");

		try {
		//将数据封装到数据包中。数据包对象是DatagramPacket。数据来自于键盘录入
		BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));
		String line = null;
		
		while ((line = bufr.readLine()) != null) 
		{						
			byte[] buf = line.getBytes(); //将数据转成字节数组
			//将字节数组封装到数据包中
			//如果将IP改为192.168.241.255，即将主机位改为255，网络位不用变，就是广播技术
			DatagramPacket dp = new DatagramPacket(buf, buf.length, InetAddress.getByName("192.168.241.1"), 10003);			
			ds.send(dp);
			
			if("886".equals(line))
				break;
		}	
		
		ds.close();
		}catch(IOException e) {
			
		}
	}
	
}

//接收任务
class Receive implements Runnable{
	
	private DatagramSocket ds;

	public Receive(DatagramSocket ds) {
		super();
		this.ds = ds;
	}

	
	@Override
	public void run() {

		System.out.println("udp  接收端     run");
		
		 while(true)
		 {
			try {
					 byte[] buf = new byte[1024];
					 DatagramPacket dp = new DatagramPacket(buf, buf.length);
					 ds.receive(dp);	
					 
					 String ip = dp.getAddress().getHostAddress();
					 int port = dp.getPort();
					 String text = new String(dp.getData(),0,dp.getLength()); 
					 
					 System.out.println(ip+":"+port+":    "+text);
					 if(text.equals("886"))
					 {
						 System.out.println(ip+"...离开聊天室");
					 }
				} catch (Exception e) {
				
			}
		 
		 }
	}	
}