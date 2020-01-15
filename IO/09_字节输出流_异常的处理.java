package IO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileDemo 
{
	public static void main(String[] args)
	{
		File file = new File("K:\\forio\\demo.txt");
		FileOutputStream fos = null;
		
		try {
			fos = new FileOutputStream(file);
			fos.write("abcde".getBytes());
		}catch (IOException e) {
			System.out.println(e.toString()+"-------");
		}finally {
			if(fos!=null) 
			{
				try 
				{
					fos.close();
				} 
				catch (IOException e) 
				{
					// TODO Auto-generated catch block
					throw new RuntimeException("");
				}
			}					
		}
		
		
		
	}
}

















