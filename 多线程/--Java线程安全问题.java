1、问题：
	售票问题中会出现0票，-1票，-2票
2、问题产生的原因：
	1、线程任务中多个线程在操作共享的数据
	2、线程任务操作共享数据的代码有多个条（运算有多个）

3、解决思路：
	只要让一个线程在执行任务时将多条操作共享数据的代码执行完
	在执行过程中，不要让其他线程参与运算，就哦了

4、Java解决此问题时通过代码块来完成的
	这个代码块：同步代码块 synchronized
	格式：
		synchronized(对象，可以是任意对象，包括 Object obj=new Object())
		{
			--需要被同步的代码
		}
	obj相当于锁，火车上的卫生间，如果obj被捕获后，在同步代码块执行完之前此obj不能被其它线程捕获
	直到同步代码块执行完

5、同步代码块的弊端：
	降低了程序的性能，但是为了安全牺牲一点性能还是可以接受的

6、同步的前提：
	必须保证多个线程在同步中使用的是同一个锁
	当多线程安全问题发生时，加入了同步后，问题依旧，就要通过 这个同步的前提来判断同步是否写正确
	synchronized(new Object())
	{

	}

	这样才是同一个锁
	private Object obj=new Object();
	synchronized(obj)
	{

	}

7、同步的另一种体现形式：
	同步函数
	public synchronized void sale()
	{

	}

	while(true)
	{
		sale();
	}

	7.1、同步函数的锁对象就是调用它的对象，比如 this 也可以

8、同步函数和同步代码块的区别：
	同步函数使用的锁时固定的this
	同步代码块使用的锁可以是任意对象

	所以当一个程序中要使用两个或以上的同步时，同步函数不够用，需要同步代码块用不同的对象作为锁
	如果只需要一个同步时完全可以使用同步函数

9、static 同步函数使用的锁不是 this，而是字节码文件对象，类名.class
	synchronized(类名.class)
	{
		
	}

10、死锁现象
	当线程任务中出现了多个同步（多个锁）时，如果同步嵌套了其他的同步这是容易引发一种现象，叫死锁
	Thread-0 和 Thread-1 两个线程同时进行，thread-0 拿到obj1锁，thread-1 拿到obj2锁，它们又都需要另一个锁才能继续执行，
	可能互相和谐相处，也有可能不和谐，还有可能刚开始和谐，最后又不和谐
	//Thread-0 
	synchronized(obj1)
	{ 
		--> thread-0 obj1
		synchronized(obj2)
		{

		}
	}

	//Thread-1 
	synchronized(obj2)
	{
		-->Thread-1 obj2
		synchronized(obj1)
		{

		}
	}


------实例：售票系统
	class Ticket implements Runnable
	{
		private int tickets=100;
		private Object obj=new Object();
		boolean flag=true;

		public void run()
		{
			if(flag)
			{
				while(true)
				{
					synchronized(obj)//同步代码块 obj锁
					{
						sale();//this 锁
					}
				}
			}
			else
			{
				while(true)
				{
					this.sale();	
				}
			}
		}

		public synchronized void sale()	//同步函数，this锁
		{
			synchronized(obj)	//obj锁
			{
				if(tickets>0)
				{
					try{
						Thread.sleep(10);
					}
					catch(InstantiationException e){

					}
					System.out.println(Thread.currentThread().getName()+"...sale..."+tickets--);
				}
			}
		} 
	}

	public class ThreadDemo
	{
		public static void main(String[] args) {
			Ticket t=new Ticket();

			Thread t1=new Thread(t);
			Thread t2=new Thread(t);

			t1.start();
			try{
				Thread.sleep(10);
			}
			catch(InstantiationException e){

			}
			t.flag=false;
			t2.start();
		}
	}

------简单代码实现死锁
	class Test implements Runnable
	{
		private boolean flag;
		Test(boolean flag)
		{
			this.flag=flag;
		}

		public void run()
		{
			if(flag)
			{
				synchronized(mylock.LOCKA)
				{
					System.out.println("if..."+"...LOCKA");
					synchronized(mylock.LOCKB)
					{
						System.out.println("if..."+"...LOCKB");
					}
				}		
			}
			else
			{
				synchronized(mylock.LOCKB)
				{
					System.out.println("else..."+"...LOCKB");
					synchronized(mylock.LOCKA)
					{
						System.out.println("else..."+"...LOCKA");
					}
				}	
			}
		}
	}

	class mylock
	{
		public static final Object LOCKA=new Object();
		public static final Object LOCKB=new Object();
	}

	public class DeadLockTest
	{
		public static void main(String[] args) {
			Test t1=new Test(true);
			Test t2=new Test(false);

			Thread t11=new Thread(t1);
			Thread t22=new Thread(t2);

			t11.start();
			t22.start();
		}
	}

补充一个小点：
try
{
	//让线程在这里稍停
	Thread.sleep(1000);//单位为毫秒
}
catch(InterruptedException e)
{
	/*未写处理方式*/
}
