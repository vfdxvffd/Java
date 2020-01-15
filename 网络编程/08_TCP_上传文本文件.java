//客户端
package tcp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class UplodeClient {


	public static void main(String[] args) throws IOException {
		
		System.out.println("上传文件客户端运行。。。");
		//客户端
		//步骤：
		
		//1、创建socket，明确地址和端口
		Socket s = new Socket("192.168.241.1",10009);
				
		//2、源：读取文本文件，获取需要转换的数据
		BufferedReader bufr = new BufferedReader(new FileReader("D:\\forio\\client.txt"));
		
		//3、目的：网络，socket输出流。将录入的数据发送到服务端
		PrintWriter out = new PrintWriter(s.getOutputStream(),true);
		
		//4、频繁的读写操作
		String line = null;
		while((line = bufr.readLine()) != null)
		{
		
			//将读取键盘的数据发送到服务端
			//因为服务端的ReadLine方法需要读到一个行终止符，所以需要写入一个换行符
			out.println(line);	//两种方法添加换行符， 1、 "\r\n" 2、 println
	
		}
		//给服务端发送一个结束标记，这个标记时约定标记，可以更简单，使用socket对象的shutdownOutput();
		//out.println("over");		
		s.shutdownOutput();//向服务端发送了结束标记。可以让服务端结束读取的动作
		
		//5、源：socket，socket读取流，读取服务端发送回来的上传成功信息
		InputStream in = s.getInputStream();
		BufferedReader bufIn = new BufferedReader(new InputStreamReader(in));
		
		String info = bufIn.readLine();
		System.out.println(info);
			
		//7、关闭资源
		bufr.close();
		s.close();
	}

}




//服务端
package tcp;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class UplodeServer {

	
	public static void main(String[] args) throws IOException {
		System.out.println("服务端运行。。。");
		
		//服务端
		//思路：
		
			//1、创建服务端socket   明确端口
			ServerSocket ss = new ServerSocket(10009);
		
			while(true)
			{
				//获取客户端对象
				Socket s = ss.accept();
				System.out.println(s.getInetAddress().getHostAddress()+"connected...");
				
				//2、源:socket输入流。读取客户端的发过来的数据
				BufferedReader bufIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
				
				//3、目的：写入一个文本文件
				
				PrintWriter pw = new PrintWriter(new FileWriter("D:\\forio\\server.txt"),true);
				//4、频繁的读写操作
				String line = null;
				while((line = bufIn.readLine()) != null)
				{
					
					//if("over".equals(line))
						//break;
					//将读取到的数据写入到文件中
					pw.println(line);
					
				}
				
				//5、发回给客户端上传成功
				PrintWriter out = new PrintWriter(s.getOutputStream(),true);
				out.println("上传成功！");
				//6、关闭客户端
				s.close();
			}
			
	}

}
