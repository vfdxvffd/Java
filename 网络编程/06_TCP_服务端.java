package tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.OutputStream;

//没有和客户端的交互，下面的demo有交互
public class TCPServer {

	public static void main(String[] args) throws IOException {
		System.out.println("服务端。。。");
		 /**
		  *	需求：获取客户端的数据并显示在屏幕上
		  *	
		  *	思路：
		  *		1、创建服务端的socket。需要明确端口（监听一个端口），要不然客户端无法连接
		  *		2、服务端只要获取到连接过来的客户端就可以和指定的客户端通信了
		  *		3、通过获取客户端的读取流对象读取客户端发来的数据
		  *		33、并显示在屏幕上
		  *		5、关闭资源
		  */ 
		
		//1、创建服务端的socket。需要明确端口（监听一个端口），要不然客户端无法连接
		
		ServerSocket ss = new ServerSocket(10003);
		
		//2、服务端只要获取到连接过来的客户端就可以和指定的客户端通信了
		
		Socket s = ss.accept();
		String ip = s.getInetAddress().getHostAddress();
		System.out.println(ip+"...connected");
		//3、通过获取客户端的读取流对象读取客户端发来的数据
		InputStream in = s.getInputStream();

		//33、并显示在屏幕上
		byte[] buf = new byte[1024];
		int len = 0;
		while((len = in.read(buf)) != -1)
		{
			System.out.println(new String(buf, 0, len));
		}
		
		//5、关闭资源
		s.close();
		//ss.close();	这一般不关
	}
}






//和客户端有交互的demo
public class TCPServer2 {

	public static void main(String[] args) throws IOException {
		
		System.out.println("服务端2   run...");
		/**
		 *	案例二：实现客户端和服务端的收发过程 
		 *	服务器端
		 */
		
		
		//创建tcp服务端socket明确端口
		ServerSocket ss = new ServerSocket(10009);
		while(true)
		{
			//获取客户端对象
			Socket s = ss.accept();
			System.out.println(s.getInetAddress().getHostAddress()+"...connected");
			
			//读取客户端发送过来的数据
			InputStream in = s.getInputStream();
			byte[] buf = new byte[1024];
			int len = in.read(buf);
			System.out.println(new String(buf,0,len));
//			int len = 0;
//			while((len = in.read(buf)) != -1)
//			{
//				System.out.println(new String(buf,0,len));
//			}
//			
//			System.out.println("阻塞了吗？");
			//给客户端回馈数据
			OutputStream out = s.getOutputStream();
			out.write("客户端。我已收到。哦耶！".getBytes());
			
			//关闭客户端
			s.close();
		}
		
		
		//关闭服务端
		//ss.close();	如果是不断的获取客户端，就不用关闭服务器
	}
}
