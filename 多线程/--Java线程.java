创建线程有两种方式：
1、继承Thread类
	1.1、定义一个类继承Thread
	1.2、重写run方法
	1.3、创建子类对象，就是创建线程对象
	1.4、调用start方法，开启线程并让线程执行，同时还会告诉jvm去调用run方法（让我想起了画笔的paint函数）

  为什么要这样做？
  	继承Thread类： 因为Thread类描述线程事务，具备线程应该有的功能

  	那为什么不直接创建Thread类的对象呢？
  		Thread t1=new Thread();
  		t1.start();
  	这样做没有错，但是该start调用的时候Thread类中run方法没有做任何事，空白的，更重要的是这个run方法中没有定义我们让线程执行的代码

  	创建线程的目的是什么？
  		就是为了建立单独的执行路径，让多部分代码同时执行，也就是线程创建并执行需要给定的代码（线程的任务）

  	对于主线程，它的任务定义在main函数中，自定义线程需要执行的任务都定义在run方法中
  	Thread类中的run方法内部的任务并不是我们所需要的，只要重写这个run方法
  	既然Thread类已经定义了线程任务的位置，只要在位置中定义任务代码即可
class Demo extends Thread
{
	private String name;
	Demo(String name)
	{
		this.name=name;
	}
	public void run()
	{
		for(int i=1;i<=20;i++)
		{
			System.out.println("name="+name+"......"+i);
		}
	}
}

class ThreadDemo
{
	public static void main(String[] args) 
	{
		--创建了两个线程对象
		Demo d1=new Demo("小强");
		Demo d2=new Demo("旺财");

		--还是要按顺序，因为d1.run();之后才能开启d2这个线程
		d1.run();		--由主线程负责
		d2.start();		--将d2这个线程开启	//d2.run();	

		--可以这样改，以便看到实验预期结果
		d2.start();		--将d2这个线程开启
		d1.run();		--由主线程负责

		/*
			线程对象调用run方法和调用start方法的区别？
			调用run方法不开启线程，仅是对象调用方法
			调用start方法开启线程，并让jvm调用run方法在开启线程中执行
		*/
	}
}


多线程执行时，在栈内存中，其实每一个执行线程都有一片自己所属于的栈内存空间
进行方法的压栈和弹栈

当执行线程的任务结束了，线程自动在栈内存中释放了
当所有的执行线程都结束了，进程就结束了



获取线程名称
Thread.currentThread()获取当前线程对象    getName()获取名称
主线程的名称：main
自定义的线程：Thread-0 ，多个时,数字顺延

System.out.println(Thread.currentThread().getName());








2、创建线程的第二种方法，实现Runnable接口
	2.1、定义类实现Runnable接口
	2.2、覆盖接口中的run方法，将线程任务代码定义到run方法中
	2.3、创建Thread类的对象 ：只有创建Thread类的对象才可以创建线程
	2.4、将Runnable接口的子类对象作为参数传递给Thread类的构造函数
		线程任务已被封装到Runnable接口的run方法中，而这个run方法所属于的Runnable接口的子类对象
		所以将这个子类的对象作为参数传递给Thread的构造函数，这样，线程对象创建时就可以明确要运行的线程任务
	2.5、调用Thread类的start方法开启线程


第二张方式实现Runnable接口避免了单继承的局限性，所以较为常用
实现Runnable接口的方式，更加符合面向对象，线程分两部分，一部分线程对象，一部分线程任务
	继承Thread类：线程对象和线程任务耦合在一起。一旦创建Thread类的子类对象，既是线程对象，又有线程任务
	而实现Runnable接口，将线程任务单独分离出来封装成对象，类型就是Runnable接口类型。
	Runnable接口对线程对象和线程任务解耦

对2.4的解释:
	Thread类的部分源码

	class Thread
	{
		private Runnable target;
		Thread(Runnable target)
		{
			this.target=target;
		}
		public void run()
		{
			if(target!=null)
			{
				target.run();
			}
		}
		public void start()
		{
			run();
		}
	}





class Demo implements Runnable
{
	private String name;
	Demo(String name)
	{
		this.name=name;
	}
	--覆盖了接口Runnable接口中的run方法
	public void run()
	{
		for(int i=1;i<=20;i++)
		{
			System.out.println("name="+name+"......"+x);
		}
	}

	class ThreadDemo
	{
		public static void main(String[] args) 
		{
			--创建Runnable子类的对象,注意它并不是线程对象
			Demo d=new Demo("Demo");

			--创建Thread对象，将Runnable接口子类对象作为参数传递给Thread类的构造函数
			Thread t1 = new Thread(d);
			Thread t2 = new Thread(d);

			--将线程启动
			t1.start();
			t2.start();
		}
	}
}