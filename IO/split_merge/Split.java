package split_merge;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
	
public class Split 
{
	
		private static final int BUFFER_SIZE = 1048576;		//1024*1024=1048576

		public static void main(String[] args) throws IOException 
		{
			/**
			 * ��ϰ����һ��ý���ļ��и�ɶ����Ƭ
			 * 
			 * 	˼·��
			 * 		1����ȡԴ�ļ�����Դ�ļ������ݷֱ��Ƶ�����ļ���
			 * 		2���иʽ�����֣�Ҫô������Ƭ�����ң�Ҫô������Ƭ��С��
			 * 		3��һ����������Ӧ��������
			 * 		4��ÿһ����Ƭ����Ҫ��ţ�˳��Ҫ��
			 * */
			
			File srcFile = new File("D:\\forio\\ʧ�߷���.mp3");			//���и���ļ�
			File partsDir = new File("D:\\forio\\PartFile");			//��Ƭ��ŵ��ļ���
			splitFile(srcFile,partsDir);
		}


		//�����и��ļ�
		public static void splitFile(File srcFile, File partsDir) throws IOException 
		{
			//��׳���ж�
			if(!(srcFile.exists() && srcFile.isFile()))
				throw new RuntimeException("Դ�ļ������ڻ�����ȷ���ļ�");
			if(!partsDir.exists())
				partsDir.mkdirs();
				
			//ʹ���ֽڶ�ȡ����Դ�ļ�����
			FileInputStream fis = new FileInputStream(srcFile);
			
			//��ȷĿ�ģ�Ŀ��������ж����ֻ��������
			FileOutputStream fos = null;
			
			//���建����.	1M
			byte[] buf = new byte[BUFFER_SIZE];
			
			//Ƶ����д����
			int NO = 1;		//��Ƭ�ļ��ı��
			int len = 0;
			while((len = fis.read(buf)) != -1)
			{
				//�������������ֻҪ�����˻�������С����Ƭ����ȷ����ֱ������Ƭ�ļ���д����
				//��Ƭ�ļ��洢��partsDir�У�����Ϊ���+��չ��
				fos = new FileOutputStream(new File(partsDir,(NO++)+".part"));
				//���������е�����д�뵽��Ƭ�ļ���
				fos.write(buf,0,len);
				//�ر������
				fos.close();		
			}
			
			/**
			 * ��Դ�ļ��Լ��и��һЩ��ϢҲ��������������Ƭ�ļ�һ����
			 * ��Ϣ:
			 * 		1��Դ�ļ������ƣ��ļ����ͣ�
			 * 		2���и���Ƭ�ĸ���
			 * ����Щ��Ϣ������װ��һ���ļ��С���Ҫһ���������ɴ˶���
			 * */
			
			String filename = srcFile.getName();
			int partCount = NO;
			
			//����һ�������
			fos = new FileOutputStream(new File(partsDir,NO+".properties"));
			
			//����һ�����Լ�
			Properties prop = new Properties();
			prop.setProperty("filname", filename);
			prop.setProperty("partsCount", Integer.toString(partCount));
			
			//��������Ϣ�洢�����Լ���
			prop.store(fos, "part file info");
			
			fos.close();		
			fis.close();
		}

}
