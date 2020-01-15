package regex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NetSpider {

	public static void main(String[] args) throws IOException {
		
		/**
		 * 	网络爬虫：其实就是一个应用程序，获取网络中的指定信息（符合指定规则的信息）
		 * 
		 * 	网络中的邮件地址
		 */
		
		//File file = new File("");
		String regex = "\\w+@\\w+(\\.\\w+)+";
		String str_url = "https://zhidao.baidu.com/question/1772307510687057620.html";

		List<String> list = getMailByNet(str_url, regex);
		for (String mail : list) {
			System.out.println(mail);
		}
		
	}


	//基于网络的
	public static List<String> getMailByNet(String str_url,String regex) throws IOException {
		
		List<String> list = new ArrayList<String>();
		
		//1、将str_url封装成URL对象
		URL url = new URL(str_url);
		
		//2、打开链接
		URLConnection conn = url.openConnection();
		
		//3、获取读取流
		InputStream in = conn.getInputStream();
		BufferedReader bufIn = new BufferedReader(new InputStreamReader(in));
		
		//4、将正则表达式编译成对象
		Pattern p = Pattern.compile(regex);
		
		String line = null;
		while((line = bufIn.readLine()) != null)
		{
			Matcher m = p.matcher(line);
			
			while(m.find()) {
				list.add(m.group());
			}
		}
		
		bufIn.close();
		return list;
	}
	
	

	
	//基于本地的
	public static List<String> getMails(File file, String regex) throws IOException {

		List<String> list = new ArrayList<String>();
		//1、读取文件
		BufferedReader bufr = new BufferedReader(new FileReader(file));
		
		//将正则规则编译成对象
		Pattern p = Pattern.compile(regex);
		
		String line = null;
		while((line = bufr.readLine()) != null)
		{
			Matcher m = p.matcher(line);
			
			while(m.find()){
				list.add(m.group());
			}
		}
		
		bufr.close();
		return list;
	}
}
