package IO;

import java.io.File;
import java.util.LinkedList;

public class FileDemo 
{
	static int acount=0;
	public static void main(String[] args) 
	{
		/*
		 * 遍历文件夹，且不使用递归：
		 * 思路：
		 * 	1、遍历test目录，将子目录存储到容器内
		 * 	2、遍历容器。从容器中取出子目录，进行遍历
		 * 	3、子目录中还有目录，继续存储，让遍历过的容器中的目录出队，释放容器空间
		 * 	4、容器大小不确定，所以用集合
		 * 	5、目录名有可能重复，所以用队列
		 */
		
		
		File dir = new File("D:\\C++");
		
		Queue<File> queue = new Queue<File>();
		//1、对dir进行当前目录的遍历
		File[]files = dir.listFiles();
		for (File file : files) 
		{
			//2、如果有子目录，存储到队列中
			if(file.isDirectory())
			{
				acount++;
				queue.myAdd(file);
			}
			else 
			{
				acount++;
				System.out.println(file.getName());
			}
		}
		//3、遍历队列容器。因为子目录都在队列中
		while(!queue.IsEmpty())
		{
			File[] files2 = queue.myRemove().listFiles();
			for (File file : files2) {
				if(file.isDirectory())
				{
					acount++;
					queue.myAdd(file);
				}
				else {
					System.out.println(file.getName());
					acount++;
				}
			}
		}
		System.out.println("一共有"+acount+"个文件");
	}
}


class Queue<E>
{
	private LinkedList<E> link;
	public Queue()
	{
		link = new LinkedList<E>();
	}
	public void myAdd(E obj)
	{
		link.addFirst(obj);
	}
	public E myRemove()
	{
		return 	link.removeLast();	
	}
	public boolean IsEmpty()
	{
		return link.isEmpty();
	}
}















