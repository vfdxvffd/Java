package IO;

public class FileDemo 
{
	
	/**
	 * 	练习：
	 * 		1、对字符串按照字节数截取，“abc你好cd谢谢”，有五个字符，七个字节
	 * 	按照3个字节截取abc， 按照4个字节截取abc和“你”字的一半，如果出现中文一半舍弃
	 * 	请定义一个功能解决这个问题
	 * */
	public static void main(String[] args) 
	{
		String str = "abc你好cd谢谢";
		byte[] buf = str.getBytes();
		//测试数据
		for(int len = 1;len <= buf.length; len++)
		{
			String s = cutString(str,len);
			System.out.println(s);
		}
		
		
	}

	public static String cutString(String str, int len) 
	{
		int count = 0;
		byte[] buf = str.getBytes();		//得到字节数组
		/**
		 * GBK编码的英文为一个字节，一个字节都是正的，中文为两个字节，中文的两个字节编码都是负的，但也有一些是先负后正，这种中文暂时称为“王非”
		 *  	从要截取的位置开始向前遍历，如果截取位置是正数，则直接返回从开始到这个位置的字符串即可，因为是正的说明两种情况：1、截取的这个位置是个英文，不存在中文一半编码的问题		
		 *  2、截取的位置是“王非”，这样的话说明是这个中文的第二个字节，也不用担心中文一半的问题
		 *  	如果截取的位置是负数，则说明有可能造成中文一半的问题，从这个截取位置往前遍历，一直到遇到正数，统计负数的个数，如果为奇数，则说明应该舍弃最后一个字节，以保证最后的这个一半的中文被舍弃
		 *  如果是偶数，则说明截取的位置是中文的第二个字节，也不用担心中文一半的问题，即直接返回从开始到这个位置的字符串即可
		 * */
		for(int i = len-1; i >= 0; i--)			//往前遍历
		{
			if(buf[i] < 0)
			{
				count++;
			}
			else {
				break;			//往前遍历时碰到正数，不计数，跳出循环，开始判断
			}
		}
		
		if(count % 2 == 0)
		{
			return new String(buf,0,len);
		}
		else {
			return new String(buf,0,len-1);
		}
	}
}