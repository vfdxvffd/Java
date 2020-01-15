package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternDemo {

	/**
	 *	上面那篇文章这些方法在API说明中都提到了Pattern，这些方法的内部使用的都是Pattern完成 
	 *	Pattern是什么呢？ Pattern就是正则表达式的编译表现形式，即就是将正则表达式编译成Java语句
	 *	每一个正则表达式在编译后都是一个Pattern对象
	 *
	 *	1、将正则表达式的字符串格式先通过Pattern中的compile方法编译成Pattern正则表达式对象
	 *		原来Pattern的作用时将正则字符串封装成正则对象
	 *
	 *		Pattern p = Pattern.complie("a*b");
	 *
	 *	2、要想用这个规则对字符串进行操作，还需要通过正则对象的matcher方法和指定的字符串关联，并获取匹配器对象
	 *		真正将规则作用到字符串上的对象时匹配器Matcher
	 *
	 *		Matcher m = p.matcher("aaaaab");
	 *
	 *	3、用匹配器对象Matcher的功能(方法)对字符串进行操作了
	 *
	 *	matches():匹配整个		lookingAt():每次从头开始		find():可以匹配下一个
	 */
	
	public static void main(String[] args) {
		
		//演示正则表达式的获取功能
		
		//需求：获取字符串中由三个字母组成的单词		
		String str = "da jia zhu yi le , ming tian bu fang jia , over !";
		
		//定义规则
		String regex = "\\b[a-z]{3}\\b";	//    \\b是单词边界
		
		//1、将正则字符串编译成正则对象
		Pattern p = Pattern.compile(regex);
		
		//2、将正则对象和字符串相关联，获取匹配器
		Matcher m = p.matcher(str);
		
		//3、使用find方法
		
		//System.out.println(m.find());
		//System.out.println(str.subSequence(m.start(), m.end()));
		//System.out.println(m.group());
		
		
//		if(m.find())
//			System.out.println(m.group());
		
		while(m.find())
			System.out.println(m.group());
		
	}
}