@修改了文件上传重名的问题
@增加了并发上传，开启多个线程


//客户端
package tcp;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class UploadPicClient {

	public static void main(String[] args) throws IOException {
		System.out.println("上传图片客户端启动...");
		
		//1、创建socket
		Socket s = new Socket("192.168.241.1", 10018);
		
		//2、读取源图片
		File picFile = new File("D:\\forio\\download.jpg");
		BufferedInputStream fis = new BufferedInputStream(new FileInputStream(picFile));
		
		//3、目的：socket输出流，发送到服务端
		OutputStream out = s.getOutputStream();
		byte[] buf = new byte[1024];
		
		int len = 0;
		while((len = fis.read(buf)) != -1)
		{
			out.write(buf, 0, len);
		}
		
		//告诉服务端   图片发送完成，不要在等着读了
		s.shutdownOutput();
		
		//读取上传成功字样
		BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
		String info = in.readLine();
		System.out.println(info);
		
		//关闭
		fis.close();
		s.close();
	}
}



//服务端
package tcp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class UploadPicServer {

	public static void main(String[] args) throws IOException {
		System.out.println("上传图片服务端启动...");

		// 创建ServerSocket
		ServerSocket ss = new ServerSocket(10018);

		while (true) {
			// 获取客户端
			Socket s = ss.accept();

			// 实现多个客户端并发上传，服务器端必须启动做个线程来完成
			new Thread(new UploadPic(s)).start();

		}
	}
}



class UploadPic implements Runnable {

	private Socket s;

	public UploadPic(Socket s) {
		super();
		this.s = s;
	}

	@Override
	public void run() {

		try {

			String ip = s.getInetAddress().getHostAddress();
			System.out.println(ip + "connected...");

			// 写图片数据到文件的输出流
			File dir = new File("D:\\forio\\photo");
			// 为了避免重名覆盖，通过给重名文件进行编号
			int count = 1;
			File picFile = new File(dir, ip + "(" + count + ")" + ".jpg");
			while (picFile.exists()) {
				count++;
				picFile = new File(dir, ip + "(" + count + ")" + ".jpg");
			}

			FileOutputStream fos = new FileOutputStream(picFile);

			// 读取客户端发来的数据
			InputStream in = s.getInputStream();
			byte[] buf = new byte[1024];
			int len = 0;

			while ((len = in.read(buf)) != -1) {
				fos.write(buf, 0, len);
			}

			// 给客户端一个回馈信息
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
			out.write("上传成功！"); // 写完需要刷新一下，或者关闭流

			// 关闭资源
			out.close();
			fos.close();
			s.close();

		} catch (Exception e) {

		}

	}

}
