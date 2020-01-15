package IO;

import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;

import javax.management.RuntimeErrorException;

//过滤器
public class FileFilterByJava implements FileFilter {
	private String suffix;

	public FileFilterByJava(String suffix) {
		this.suffix = suffix;
	}

	@Override
	public boolean accept(File pathname) {
		return pathname.getName().endsWith(suffix);
	}

}

//主类
public class Demo {

	private static final String LINE_SEPARATOR = System.getProperty("line.separator");

	public static void main(String[] args) throws IOException {
		/**
		 * 需求:获取指定目录下的所有 .java文件（包括子目录的），并写到一个文件中
		 * 
		 * 思路： 1、一看到包含子目录，必须递归 2、写数据到文件，输出流 3、发现只要 .java，需要过滤器 4、满足过滤条件的文件可能非常多，需要先进行存储
		 */

		// 被遍历的目录
		File dir = new File("D:\\C++");

		// 明确一个过滤器
		FileFilter filter = new FileFilterByJava(".cpp");

		// 创建一个容器，用来存储符合条件的文件
		LinkedList<File> list = new LinkedList<File>();

		// 获取指定文件的清单，将其存入容器
		getFileList(filter, dir, list);

		// 创建写入的目的文件
		File file = new File("D:\\wenjian.txt");
		if (!file.exists()) {
			file.createNewFile();
		}
		// 将容器中的路径写入文件
		WriteToFile(file, list);

	}

	// 将容器中的路径写入文件
	private static void WriteToFile(File file, LinkedList<File> list) throws IOException {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
			String info;
			for (File file2 : list) {
				info = file2.getAbsolutePath().toString() + LINE_SEPARATOR;
				fos.write(info.getBytes());
			}
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					throw new RuntimeErrorException(null, "关闭失败");
				}
			}
		}

	}

	// 获取指定文件的清单，将其存入容器
	public static void getFileList(FileFilter filter, File dir, LinkedList<File> list) {

		File[] files = dir.listFiles();
		for (File file : files) {
			if (file.isDirectory()) // 如果是文件夹，递归调用
			{
				getFileList(filter, file, list);
			} else // 如果是文件
			{
				if (filter.accept(file)) // 放入过滤器过滤
				{
					list.add(file);
				}
			}

		}
	}
}


