FileReader = 字节读取流 + 默认编码表
字符流：为了便于操作数据中的字符数据。       原理：字节流+编码表
字符流的两个基类：
Reader：read() 									读取字符
	    read(char[] cbuf)						将数据存储到字符数组中，并返回个数
	    read(char[] cbuf , int off, int len)	将字符读入数组的某一部分
	    read(CharBuffer target)					试图将字符读入指定的字符缓冲区

Writer：write() 
	write(char[] buf) 						写入字符数组
	write(char[] buf, int off, int len)		写入字符数组的某一部分
	write(int c)							写入单个字符
	write(String str) 						写入字符串
	write(String str , int off, int len)	写入字符串的某一部分

fw.write(char[] buf);	调用完之后，这些文字都要先编码，都写入到了流的缓冲区中，需要调用fw.flush();即可将缓冲区中的内容刷新入文件，但流还可以再使用，另外，fw.close();	close中在关闭前会先刷新一次再关，流不再能使用。如果写入文件多，最好一边写一边刷

字节流操作的是字节数组，字符流操作的是字符数组


package IO;

import java.io.FileWriter;
import java.io.IOException;


public class FileDemo 
{
	public static void main(String[] args) throws IOException
	{
		FileWriter fw = new FileWriter("D:\\forio\\book.txt");
		fw.write("你好再见谢谢");
		fw.close();
	}
}