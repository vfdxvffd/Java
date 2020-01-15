package myserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MyBrowser {
	
		/**
		 *	 模拟一个浏览器。发送之前IE发送的http消息
		 *	客户端：自定义的浏览器
		 *	服务端：Tomcat
		 * @throws IOException 
		 * @throws UnknownHostException 
		 * */
	public static void main(String[] args) throws UnknownHostException, IOException {
		
		Socket s = new Socket("192.168.241.1",8080);
	
		//把IE的信息发给服务端
		PrintWriter out = new PrintWriter(s.getOutputStream(),true);
		out.println("GET /myweb/1.html HTTP/1.1");
		out.println("Accept: */*");
		out.println("Host: 192.168.241.1:8080");
		out.println("Connection: close");
		out.println();	//空行
		
		//读取服务端的数据
		InputStream in = s.getInputStream();
		byte[] buf = new byte[1024];
		int len = in.read(buf);
		String text = new String(buf,0,len);
		System.out.println(text);
		
		s.close();
		
	}
	
	/**
	 * 
	 * 	发送和IE相同的HTTP协议的消息，收到了Tomcat服务器返回的数据
	 * 
	 * 	HTTP的应答消息
	 * 
	 * 	HTTP/1.1 200 			//应答行	http协议版本	应答状态码
		Accept-Ranges: bytes
		ETag: W/"49-1566303345728"
		Last-Modified: Tue, 20 Aug 2019 12:15:45 GMT
		Content-Type: text/html
		Content-Length: 49
		Date: Tue, 20 Aug 2019 12:21:32 GMT
		Connection: close
					//空行，下面是应答体，就是所需要的数据
		锘?<font color='red' size='7'>娆㈣繋鍏変复</font>
	 * 
	 */
	
	
}
