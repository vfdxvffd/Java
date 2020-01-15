package regex;

import java.util.Arrays;

public class RegexTest {

	public static void main(String[] args) {

		/**
		 * 案例一：我我我我....我我我....我我我我要要.....要要要要要.......要要学学学....学学.......学学软件工程
		 * 
		 * 实现：我要学软件工程 思路: 1、使用正则表达式 2、先把所有的.去掉。替换 3、替换叠词。替换
		 */
		test_1();

		/**
		 * 案例二 ： "23.12.10.5 192.168.100.223 3.3.3.3 10.10.10.10" 要求将这些ip按照顺序进行排序
		 *
		 * 思路： 1、将ip通过空格切割 2、对ip进行排序(调用sort方法)，通过字符串字典顺序排序，这个顺序是错误的
		 * 原因：每个ip有四段，每一段最多三位。应该按照位数比较才对 所以应该将每一段补足三位，不满足的用0填充，这样比较字典顺序才是对的 3、怎么补0呢？
		 * 每一段的位数都不同，补0的个数也不一样 技巧：干脆按照所需的最多的0个数来补。每一段都补两个0 有的地址段多了，取每一段的最后三位
		 */
		test_2();

		/**
		 * 案例三:校验电子邮件地址E-mail
		 */
		test_3();

	}

	public static void test_1() {

		String str = "我我我我....我我我....我我我我要要.....要要要要要.......要要学学学....学学.......学学软件工程";

		str = str.replaceAll("\\.+", "");
		System.out.println(str);

		str = str.replaceAll("(.)\\1+", "$1");
		System.out.println(str);
	}

	
	
	public static void test_2() {

		String ip_str = "23.12.10.5       192.168.100.223     3.3.3.3     10.10.10.10";

		ip_str = ip_str.replaceAll("(\\d+)", "00$1"); // 干脆按照所需的最多的0个数来补。每一段都补两个0

		// 有的地址段多了，取每一段的最后三位，下面两种方法都可以不过第二种好貌似好一点
		// ip_str = ip_str.replaceAll("\\d{1,2}(\\d)(\\d)(\\d)", "$1$2$3");
		ip_str = ip_str.replaceAll("0*(\\d{3})", "$1");

		String[] ips = ip_str.split(" +");
		Arrays.sort(ips);
		for (String ip : ips) {
			System.out.println(ip.replaceAll("0*(\\d+)", "$1")); // 把加的0又给去掉，哈哈哈
		}
	}

	
	
	public static void test_3() {

		String mail = "abc12@sina.com";
		
		//邮箱：前面是字母数字或者下划线(一次或多次)，然后是@，再然后是域名(字母数字一次或多次)，最后是1-3次的.com.cn啊这些的
		String regex = "[a-zA-Z_0-9]+@[a-zA-Z0-9]+(\\.[a-zA-Z]{2,3}){1,3}";
		System.out.println(mail+":"+mail.matches(regex));
	
	}



}