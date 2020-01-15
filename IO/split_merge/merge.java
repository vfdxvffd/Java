package split_merge;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Properties;

public class merge 
{
		public static void main(String[] args) throws IOException 
		{
			File partDir = new File("D:\\forio\\PartFile");
			mergerFile(partDir);
		}

		
		
		public static void mergerFile(File partDir) throws IOException 
		{
			/**
			 * ��Ȼ�ϲ��ɹ������������£�
			 * 		1�������ȷ��Ƭ������ȷ��ѭ������������ȷҪ�ж��ٸ�����������
			 * 		2�����֪���ϲ����ļ�������
			 * ���������
			 * 		Ӧ���ȶ�ȡ�����ļ�
			 * */
			
			//1����ȡ�����ļ�
			File configFile = getConfigFile(partDir);
			
			//2����ȡ�����ļ���Ϣ����,��ȡ������Ϣ�����Լ�		
			Properties prop = getProperties(configFile);
			
			//3�������Լ����󴫵ݵ��ϲ�������
			merger(partDir,prop);
			
		}
		
		
		

		//���������ļ���ȡ������Ϣ���Լ�
		public static Properties getProperties(File configFile) throws IOException 
		{
			
			FileInputStream fis = null;
			Properties prop = new Properties();
			
			try {
				//��ȡ���������ļ������
				fis = new FileInputStream(configFile);		
				//�����е����ݼ��ص�������
				prop.load(fis);
			}finally {
				if(fis != null)
				{
					fis.close();
				}
			}
			
			return prop;	
		}

		
		
		
		//������ƬĿ¼��ȡ�����ļ�����
		public static File getConfigFile(File partDir) 
		{
			//��׳���ж�
			if(!(partDir.exists() && partDir.isDirectory()))
			{
				throw new RuntimeException(partDir.toString()+"������ЧĿ¼��");
			}
			
			
			//1���ж���Ƭ�ļ�Ŀ¼���Ƿ����properties�ļ�
			File[] files = partDir.listFiles(new FileFilter() {
				
				@Override
				public boolean accept(File pathname) 
				{				
					return pathname.getName().endsWith(".properties");
				}
			});
			
			if(files.length != 1)
			{
				throw new RuntimeException(partDir.toString()+"properties��չ�����ļ������ڻ�Ψһ��");
			}
			
			
			File configFile = files[0];
			return configFile;
		}

		
		
		
		//�ϲ��ļ�
		private static void merger(File partDir,Properties prop) throws IOException 
		{
			
			//��ȡ���Լ��е���Ϣ
			String filename = prop.getProperty("filname");
			int partCount = Integer.parseInt(prop.getProperty("partsCount"));
			
			//׼��ʹ��IO���е�SequenceInputStream������Ƭ���кϲ����������ȡ���ϲ���һ����ȡ��
			ArrayList<FileInputStream> list = new ArrayList<FileInputStream>();
			for(int i = 1; i < partCount; i++)
			{
				list.add(new FileInputStream(new File(partDir,i+".part")));
			}
			
			
			//��ô��ȡö�ٶ���List����ʱ�޷���ȡö��Enumeration����ģ����ǵ�Collections��ȥ��
			Enumeration<FileInputStream> en = Collections.enumeration(list);
			
			//Դ����ȡ��
			SequenceInputStream sis = new SequenceInputStream(en);
			
			//Ŀ��
			FileOutputStream fos = new FileOutputStream(new File(partDir, filename));
			
			//���ϵĶ�д
			byte[] buf = new byte[4096];
			int len = 0;
			while((len = sis.read(buf)) != -1)
			{
				fos.write(buf,0,len);
			}
			
			sis.close();
			fos.close();
		}


}

