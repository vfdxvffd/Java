//过滤器需要实现接口
1、根据文件后缀名过滤，比如只要.txt文件
package IO;

import java.io.File;
import java.io.FilenameFilter;
//过滤文件名的过滤器
public class FilenameFilterByTxt implements FilenameFilter {

	private String suffix;
	
	//构造函数
	public FilenameFilterByTxt(String s) {
		// TODO Auto-generated constructor stub
		suffix=s;
	}
	
	//重写accept函数
	@Override
	public boolean accept(File dir, String name) {
		// TODO Auto-generated method stub
		return name.endsWith(suffix);	//也可以直接endwith(".txt");	但这样可以减少耦合性，更灵活的选择以哪种结束
	}
}



package IO;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

public class FileDemo 
{
	public static void main(String[] args) 
	{
		//需求2：获取目录中的文件，只要.txt文件
		
		File dir = new File("D:\\forio");
	
		File[] files = dir.listFiles(new FilenameFilterByTxt(".txt"));		//传入过滤器，如果懵逼了可以查看listFiles的API
		
		/*  listFiles方法的源码：
		 * 
		 * public File[] listFiles(FilenameFilter filter) 
		 * {
		        String ss[] = list();		//调用File类中的list方法，先获取所有的名称
		        if (ss == null) return null;	//判断，如果数组为空，返回
		        ArrayList<File> files = new ArrayList<>();	//创建了一个集合，元素是File类型
		        for (String s : ss)				//遍历数组名称
		        
		        //调用accept方法，对遍历的名称进行过滤器判断，将当前目录this，遍历到名称s传递给accept方法，如果过滤器为空，即没有过滤条件。则全部为真
		            if ((filter == null) || filter.accept(this, s))	
		                files.add(new File(s, this));	//将满足过滤条件的添加到集合中，将名称和当前目录封装成File对象。new File(dir,name);
		        return files.toArray(new File[files.size()]);	//将集合转成数组返回，因为不需要增删操作
    		}
		 * */
		
		
		for (File file : files) {
				System.out.println(file);
		}
	}
}






2、根据文件夹或者文件过滤
package IO;

import java.io.File;
import java.io.FileFilter;
//过滤文件夹的过滤器
public class FileFilterByDir implements FileFilter {

	@Override
	public boolean accept(File pathname) {
		// TODO Auto-generated method stub
		return pathname.isDirectory();
	}
}



package IO;

import java.io.File;
import java.io.FileFilter;

public class Demo 
{
	public static void main(String[] args) 
	{
		File dir = new File("D:\\forio");
		
		File[] files = dir.listFiles(new FileFilterByDir());			//传入过滤器，如果懵逼了可以查看listFiles的API
		for (File file : files) {
			System.out.println(file);
		}
	}
}