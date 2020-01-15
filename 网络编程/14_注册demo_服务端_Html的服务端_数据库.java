package myserver;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class ForHtml {

	private static final String DB_URL = "jdbc:mysql://localhost:3306/unkonwn?useSSL=false&&serverTimezone=UTC";
	private static final String NAME = "root";
	private static final String PASSWORD = "123456";
	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
		System.out.println("服务端    run...");

		// 服务端
		ServerSocket ss = new ServerSocket(10003);

		while(true)
		{
			// 接收客户端
			Socket s = ss.accept();
			System.out.println(s.getInetAddress().getHostAddress()); // 打印连接的客户端主机

			try {

				// 接收浏览器表单界面发送回来的注册信息，最后将注册信息保存在info里
				String info = getInfo(s);

				// 切割字符串info，得到注册信息键值对
				String[] arr = info.split("&");
				
				// 判断两次输入的密码是否一样，如果不一样，直接注册失败
				if (!arr[1].split("=")[1].equals(arr[2].split("=")[1])) {
					System.out.println("注册失败！");
					PrintWriter out = new PrintWriter(s.getOutputStream());
					out.println("<font size='7' color='red'>注册失败！两次输入的密码不一致！</font>");
					out.flush();
					return;
				}

				// 调用函数向数据库中加入新用户，如果成功，则在浏览器上显示注册成功，否则显示注册失败
				if (mysqlcon(arr)) {
					System.out.println("新增加一位用户！");
					PrintWriter out = new PrintWriter(s.getOutputStream());
					out.println("<font size='7' color='red'>注册成功！</font>");
					out.flush();
				} else {
					System.out.println("注册失败！");
					PrintWriter out = new PrintWriter(s.getOutputStream());
					out.println("<font size='7' color='red'>注册失败！</font>");
					out.flush();
				}
			} finally {
				// 关闭客户端和服务端
				s.close();
				//ss.close();
			}
		}
		

	}

	
	// 接收浏览器表单界面发送回来的注册信息，最后将注册信息保存在info里并返回
	public static String getInfo(Socket s) throws IOException
	{
		//先将从客户端读取到的信息存到一个文件中
		InputStream in = s.getInputStream();
		byte[] buf = new byte[1024];
		int len = in.read(buf);
		FileOutputStream fos = new FileOutputStream("D://forio//demo.txt");
		fos.write(buf, 0, len);
		
		//再从文件中读取到最后一行就是所求的info信息，不要问我为什么搞得这么麻烦，因为其他方法都会抛出异常，只有这种没问题
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("D://forio//demo.txt")));
		String info = null;
		while ((info = br.readLine()) != null) {
			if (br.readLine() == null) {
				break;
			}
		}
		fos.close();
		br.close();
		
		return info;
	}
	
	
	
	
	// 加入新用户函数，成功返回true，失败返回false，因为异常知识有限，无法判断失败原因
	public static boolean mysqlcon(String[] arr) throws ClassNotFoundException, SQLException {
		Connection conn = null;

		// 注册JDBC驱动
		Class.forName(JDBC_DRIVER);

		// 打开链接
		// System.out.println("连接数据库 。。。");
		conn = DriverManager.getConnection(DB_URL, NAME, PASSWORD);

		// 预执行sql语句
		String sql = "insert into user(username,password,country,sex,technology_one,technology_two,technology_thr,technology_fou) values(?,?,?,?,?,?,?,?)";
		PreparedStatement presta = conn.prepareStatement(sql);

		// 设置sql语句中的values值
		presta.setString(1, arr[0].split("=")[1]);
		presta.setString(2, arr[1].split("=")[1]);
		presta.setString(3, arr[4].split("=")[1]);
		presta.setString(4, arr[3].split("=")[1]);
		
		//因为技术这一选项是不定项选择，所以这样才能保证把所选技术正确加入数据库
		//从第五个字段开始时技术
		int i = 5;

		//arr的长度标数据的数量，这些应该填入注册时候的信息
		for(i = 5; i <= arr.length - 1; i++)
		{
			presta.setString(i, arr[i].split("=")[1]);
		}
		
		
		//从数据的数量+1开始就应该是没有的技术了，填入null
		for( ;i <= 8; i++)
		{
			presta.setString(i, null);
		}

		
		// 执行sql语句
		try {
			presta.execute();
		} catch (SQLIntegrityConstraintViolationException e) {
			return false;
		}

		return true;

	}

}
