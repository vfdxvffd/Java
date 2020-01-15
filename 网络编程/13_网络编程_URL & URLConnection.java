package myserver;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class URLDemo {

	public static void main(String[] args) throws IOException {
		
		String str_url = "http://192.168.241.1:8080/myweb/1.html";
		
		//将url地址封装成对象
		URL url = new URL(str_url);
		
		System.out.println("协议:"+url.getProtocol());
		System.out.println("主机:"+url.getHost());
		System.out.println("端口:"+url.getPort());
		System.out.println("路径:"+url.getPath());
		System.out.println("文件:"+url.getFile());
		System.out.println("参数信息:"+url.getQuery());
		
		//获取指定资源的连接对象,封装了socket
		URLConnection conn = url.openConnection();
		
		InputStream in = conn.getInputStream();
		byte[] buf = new byte[1024];
		int len = in.read(buf);
		String text = new String(buf,0,len);
		System.out.println(text);		//这样打印出来的是没有应答头（只有应答体）的应答消息
	}
	
}
