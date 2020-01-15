频繁的客户端和服务端的通信

需求：客户端通过键盘录入发送数据到服务端，服务端将接收到的数据显示到屏幕上的同时，将这些数据转成大写发回给客户端。		当客户端录入的是over时，大写转换结束

客户端：
	思路: 1、创建socket，明确地址和端口
		  2、源：键盘录入，获取需要转换的数据
		  3、目的：网络，socket输出流。将录入的数据发送到服务端
		  4、源：socket，socket读取流，读取服务端发送回来的大写数据
		  5、目的：客户端显示器，将大写数据显示出来
		  6、频繁的读写操作
		  7、关闭资源

服务端:
	思路：
		 1、创建服务端socket   明确端口
		 2、源:socket输入流。读取客户端的发过来的数据
		 3、目的：socket输出流。将转成大写的数据发送给客户端
		 4、频繁的读写操作
		 5、关闭客户端

//客户端
package tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class TransClient {

	public static void main(String[] args) throws IOException {
		
		System.out.println("客户端运行。。。");
		//客户端
		//步骤：
		
		//1、创建socket，明确地址和端口
		Socket s = new Socket("192.168.241.1",10003);
				
		//2、源：键盘录入，获取需要转换的数据
		BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));
		
		//3、目的：网络，socket输出流。将录入的数据发送到服务端
			//OutputStream out = s.getOutputStream();
			//既然都是字符数据。可以使用额外功能，转换，同时提高效率
			//BufferedWriter BufOut = new BufferedWriter(new OutputStreamWriter(out));	也可以直接使用打印流
		PrintWriter out = new PrintWriter(s.getOutputStream()/*,true*/);
		
		//4、源：socket，socket读取流，读取服务端发送回来的大写数据
		InputStream in = s.getInputStream();
		BufferedReader bufIn = new BufferedReader(new InputStreamReader(in));
		
		//5、目的：客户端显示器，将大写数据显示出来，直接使用输出语句
		//6、频繁的读写操作
		String line = null;
		while((line = bufr.readLine()) != null)
		{
		
			//将读取键盘的数据发送到服务端
			//因为服务端的ReadLine方法需要读到一个行终止符，所以需要写入一个换行符
			out.print(line+"\r\n");	//两种方法添加换行符， 1、 "\r\n" 2、 println
			out.flush();	//也可以在上面创建流的时候添加参数true，即可自动刷新
			
			
			if("over".equals(line))
				break;
			//读取服务端返回的数据
			String upperText = bufIn.readLine();
			System.out.println(upperText);
		
		}
		
		//7、关闭资源
		s.close();
	}
}


//服务端
package tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TransServer {

	public static void main(String[] args) throws IOException {
		System.out.println("服务端运行。。。");
		
		//服务端
		//思路：
		
		 //1、创建服务端socket   明确端口
			ServerSocket ss = new ServerSocket(10003);
		//获取客户端对象
			while(true)
			{
				Socket s = ss.accept();
			 //2、源:socket输入流。读取客户端的发过来的数据
				BufferedReader bufIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
				
			 //3、目的：socket输出流。将转成大写的数据发送给客户端
				
				PrintWriter out = new PrintWriter(s.getOutputStream());
				
			 //4、频繁的读写操作
				String line = null;
				while((line = bufIn.readLine()) != null)
				{
					if("over".equals(line))
						break;
					System.out.println(line);
					//将读取到的字母数据转成大写，发回客户端
					out.print(line.toUpperCase()+"\r\n");
					out.flush();
				}
				
			 //5、关闭客户端
				s.close();
			}
			//服务端不关闭
	}
}
