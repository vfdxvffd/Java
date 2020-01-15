package tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;


//没有和服务端的交互，下面的demo有交互
public class TCPClient {

	public static void main(String[] args) throws IOException{
		
		System.out.println("客户端运行。。。");
		/**
		 *	需求：通过tcp传输数据发送给服务器
		 *	思路：
		 *		1、建立tcp客户端socket.明确服务端的地址和端口
		 *		2、如果通道建立成功就会出现socket io流
		 *			客户端需要做的就是获取socket流中输出流将数据发送目的地服务端
		 *		3、通过socket输出流将数据发送
		 *		33、关闭
		 */
		
		
		//1、建立tcp客户端socket.明确服务端的地址和端口
		
		Socket s = new Socket("192.168.241.1", 10003);
		
		//2、如果通道建立成功就会出现socket io流		客户端需要做的就是获取socket流中输出流将数据发送目的地服务端
		//3、通过socket输出流将数据发送
		
		OutputStream out = s.getOutputStream();
		out.write("hello,TCP来了".getBytes());
		//33、关闭
		s.close();
	}
}



//和服务端有交互的demo
public class TCPClient2 {

	public static void main(String[] args) throws IOException{
		System.out.println("客户端2   run...");
		/**
		 *	案例二：实现客户端和服务端的收发过程 
		 */
		
		//创建客户端socket对象，明确服务端地址和端口
		Socket s = new Socket("192.168.241.1",10009);
		
		//发送数据通过socket输出流完成
		OutputStream out = s.getOutputStream();
		out.write("服务端，我来了".getBytes());
		
		//读取服务器端返回的数据
		InputStream in = s.getInputStream();
		byte[] buf = new byte[1024];
		int len= in.read(buf);
		System.out.println(new String(buf,0,len));
//		int len = 0;
//		while((len = in.read(buf)) != -1)
//		{
//			System.out.println(new String(buf,0,len));
//		}
		
		//关闭资源
		s.close();
		
	}
}
