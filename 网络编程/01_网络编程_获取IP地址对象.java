package net;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IPDemo {
		/**
		 * ip地址对象。		InetAddress
		 * @throws UnknownHostException 
		 * */
	public static void main(String[] args) throws UnknownHostException {
				
		//获取本地主机地址对象
		InetAddress ip = InetAddress.getLocalHost();
		
		//获取其他主机的地址对象
		//InetAddress ip = InetAddress.getByName("www.baidu.com.cn");
		System.out.println(ip.getHostAddress()+":"+ip.getHostName());
	}
}
