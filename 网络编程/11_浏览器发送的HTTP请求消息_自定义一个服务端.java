package myserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Myserver {

	public static void main(String[] args) throws IOException {
		System.out.println("服务端启动...");
		/**
		 * 自定义server服务端
		 * 获取浏览器信息。并反馈信息
		 *	最好用IE浏览器。谷歌可能哪里没设置好，会出问题
		 */
		
		ServerSocket ss = new ServerSocket(10003);
		
		Socket s = ss.accept();
		System.out.println(s.getInetAddress().getHostAddress()+"connected...");
		
		//读取客户端的数据
		InputStream in = s.getInputStream();
		byte[] buf = new byte[1024];
		int len = in.read(buf);
		String text = new String(buf,0,len);
		System.out.println(text);
		
		//给客户端回馈数据
		PrintWriter out = new PrintWriter(s.getOutputStream(),true);
		out.println("<font color='red' size='7'>欢迎光临</font>");
		out.flush();
		
		s.close();
		ss.close();
	}
	
//看到了如下信息
//请求头:
		GET / HTTP/1.1
		Accept: text/html, application/xhtml+xml, image/jxr, */*
		Accept-Language: zh-CN
		User-Agent: Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko
		Accept-Encoding: gzip, deflate
		Host: 192.168.241.1:10003
		Connection: Keep-Alive
//空行
//请求体:
	
	
}
