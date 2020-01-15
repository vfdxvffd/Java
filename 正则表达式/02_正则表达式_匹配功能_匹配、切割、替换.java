package regex;

public class RegexFunctionDemo {

	public static void main(String[] args) {

		// 正则的匹配的功能

		// 演示匹配
		MatchesDemo();

		// 演示切割
		splitDemo();

		// 演示替换
		replaceAllDemo();
		
		//演示获取
		//看下一个文件，写在一起我怕查起来不方便
		
	}



	public static void MatchesDemo() {

		// 校验手机号码
		String number = "18591516783";
		// 规则
		String number_regex = "1[358]\\d{9}";

		System.out.println(number + ":" + number.matches(number_regex));
	}

	
	
	public static void splitDemo() {

		String str = "zhangsan,lisi,wangwu";
		String str_regex = ",";

		String str = "zhangsan.lisi.wangwu";
		String str_regex = "\\."; // .在正则符号里是一个特殊符号，表示任意符号，\.转义一下变成普通符号，放在双引号里就变成了\\.

		String str = "zhangsan lisi wangwu";
		String str_regex = " +"; //一个或多个空格

		// 按照叠词切割。叠词就是后者和前者一致，前者还是任意，必须后者在复用前者的内容
		// 必须在正则表达式中进行复用：将需要复用的内容进行封装，然后调用这个封装即可
		// 正则的复用用的是()来封装的，虽然没有名字，但是正则在使用()进行封装时，
		// 自动的给这些()进行了编号，从1开始，称之为正则表达式中的组。通过组的编号就可以调用指定的组，进行复用
		String str = "zhangsan##############lisi@@@@@@wangwu"; // 切割依据变成叠词
		String str_regex = "(.)\\1+"; // (.)将一个任意字符封装起来，表示某一个字符，\\1 转义1表示编号1的组，+表示多个

		// (A)(B)(C)(D)\\1\\3\\2\\4
		// ((A)(B(C))) 组嵌套，技巧：从左起数有几个左括号就是几组
		// \\1((A)(B(C))) \\2(A) \\3(B(C)) \\4(C) \\0始终代表整个整个组
		String[] arr = str.split(str_regex);
		for (String s : arr) {
			System.out.println(s);
		}

	}

	
	
	public static void replaceAllDemo() {

		String str = "kxj###########wsja##########nsk";		
		//将#替换成@
		str = str.replaceAll("#+","@");
		
		String str = "kxj###########wsja&&&&&&&&&&&&nsk";		
		//将叠词替换成@
		str = str.replaceAll("(.)\\1+","@");
		
		String str = "kxj###########wsja&&&&&&&&&&&&nsk";		
		//将叠词替换成其中的一个	多个###### 用#替换,多个&&&&&	   用&替换
		//当在第二个参数中要使用第一个正则参数中的组时，可以使用$加编号来完成组的调用。\\1只能使用在正则表达式中
		str = str.replaceAll("(.)\\1+","$1");	

		String str = "wqer15891516783uadshuhuqwg213231dhdakjss";		
		//将字符串中的联系方式数字(QQ号，电话等等)都替换成***
		str = str.replaceAll("\\d{5}","***");
		
		String str = "15891516783";		
		//将电话号码的中间四位变成****
		str = str.replaceAll("(\\d{3})(\\d{4})(\\d{4})","$1****$3");
		
		System.out.println(str);
	}
	
	
	
}