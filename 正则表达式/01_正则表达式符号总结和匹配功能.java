1、了解一下正则符号
	1.1	中括号：[判断字符位上内容]
	1.2	预定义字符：都带着反斜线
		.  : 任意字符
		\\d: 数字[0-9]
		\\D: 非数字[^0-9]
		\\w: 单词字符[a-zA-Z]
	1.3 边界字符：
		^ : 行开头
		$ : 行结尾
		\b: 单词边界
	1.4 数量词：必须结合内容
	   x? : x内容出现0次或1次
	   x* : x内容出现0次或多次
	   x+ : x内容出现1次或多次
	 x{n} :	x内容出现n次
	x{n,} :	x内容出现至少n次
   x{n,m} :	x内容出现n到m次


2、常见操作字符串的功能，字符串String类
	1、匹配：
		其实用的就是String类中的matches方法
	2、切割：
		用的是String类中的 split(regex);
	3、替换
		用的是replaceAll(regex,String);
			  replaceFirst(regex,String);


package regex;

public class MatchesDemo {

	public static void main(String[] args) {
		
		//正则的匹配的功能

		//校验手机号码
		String number = "18591516783";

		//规则
		//1标识第一位只能是1，[358]表示第二位是3、5、8其中一个，\\d{9}表示后面是9个任意数字
		String number_regex = "1[358]\\d{9}";	
		
		System.out.println(number+":"+number.matches(number_regex));
	}
}
