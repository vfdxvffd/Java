多线程中最为常见的应用案例： 生产者消费者问题（单生产，单消费）
	生产和消费同时进行，需要多线程
	但是执行的任务取不相同，处理的资源却是相同的，线程间的通信

	1、描述以下资源
	2、描述生产者，因为具备自己的任务
	3、描述消费者，因为具备自己的任务

	问题1：
	第一次运行结果：数据错误，已经被生产很早期的商品，才被消费
	出现了线程安全问题，加入同步解决。使用同步函数
	问题已解决，不会在消费到之前很早期的商品

	问题2：发现了连续生产却没有消费，同时对用一个商品进行多次消费
	希望的结果应该是生产一个商品就被消费掉，再去生产下一个商品
	搞清楚几个问题：
		生产者什么时候生产？消费者什么时候消费？
		当盘子中没有面包时，就生产，有了就不生产
		当盘子已有面包时就消费，没有就不要消费
	加入定义标记
	加入等待和唤醒功能
	生产这生产了商品后应该告诉消费者来消费，这时生产中应该处于等待
	消费者消费了商品后应该告诉生产者来生产，这时消费者应该处于等待



	//描述资源,属性：名称，编号		行为：对名称赋值，获取商品
	class resource
	{
		private String name;
		private int count=1;

		//定义标记
		private boolean flag=false;

		//提供设置的方法
		public synchronized void set(String name)
		{

			if(flag)
			{
				try{wait();} catch(InstantiationException e){}
			}
			//给成员变量赋值，并加上编号
			this.name=name+count;
			//编号自增
			count++;
			//打印生产了哪个商品
			System.out.println(Thread.currentThread().getName()+"...生产者..."+this.name);

			//将标记改为true
			flag=true;
			//唤醒消费者
			notify();
		}

		public synchronized void out()
		{
			if(!flag)
			{
				try{wait();} catch(InstantiationException e){}
			}
			System.out.println(Thread.currentThread().getName()+".....消费者....."+this.name);
			//该标记
			flag=false;
			//唤醒生产者
			notify();
		}
	}

	//描述生产者
	class producer implements Runnable
	{
		private resource r;
		//生产者一初始化就要又资源，需要将资源传递到构造函数中
		producer(resource r)
		{
			this.r=r;
		}
		public void run()
		{
			while(true)
			{
				r.set("面包");
			}
		}
	}

	//描述消费者
	class consumer implements Runnable
	{
		private resource r;
		consumer(resource r)
		{
			this.r=r;
		}
		public void run()
		{
			while(true)
			{
				r.out();
			}
		}
	}

	public class ThreadDemo
	{
		public static void main(String[] args) {
			//创建线程对象
			resource r=new resource();

			//创建线程任务
			producer pro=new producer(r);
			consumer con=new consumer(r);

			//创建线程
			Thread t1=new Thread(pro);
			Thread t2=new Thread(con);

			t1.start();
			t2.start();
		}
	}

生产者消费者问题（多生产，多消费）

	问题1：生产了商品没有被消费，同一个商品被消费多次
		生产2499
		生产2500
		消费2500
		被唤醒的线程没有判断标记，以为在wait之前已经判断过一次了，然后进入等待，被唤醒后接着上次等待的地方执行，即不用判断直接生产或者消费
		解决方法：只要让被唤醒的线程必须判断标记就可以了
			将 if(flag) 改为 while(flag)
		记住:多生产多消费，必须是while判断条件

	问题2：发现while判断后，死锁了
		原因：生产方唤醒了线程池中的生产方的线程。本方唤醒了本方
		解决：希望本方要唤醒对方
		将notify()改为notifyAll()


	//描述资源,属性：名称，编号		行为：对名称赋值，获取商品
	class resource
	{
		private String name;
		private int count=1;

		//定义标记
		private boolean flag=false;

		//提供设置的方法
		public synchronized void set(String name)	--t1 t2
		{
			while(flag)	//	if(flag)
			{
				try{wait();} catch(InstantiationException e){}
			}
			//给成员变量赋值，并加上编号
			this.name=name+count;
			//编号自增
			count++;
			//打印生产了哪个商品
			System.out.println(Thread.currentThread().getName()+"...生产者..."+this.name);

			//将标记改为true
			flag=true;
			//唤醒消费者
			notifyAll();	//notify();会造成死锁
		}

		public synchronized void out()	--t3 t4
		{
				while(!flag)	//if(!flag)
				{
					try{wait();} catch(InstantiationException e){}
				}
				System.out.println(Thread.currentThread().getName()+".....消费者....."+this.name);
				//该标记
				flag=false;
				//唤醒生产者
				notifyAll();	//notify();会造成死锁
						
		}
	}

	//描述生产者
	class producer implements Runnable
	{
		private resource r;
		//生产者一初始化就要又资源，需要将资源传递到构造函数中
		producer(resource r)
		{
			this.r=r;
		}
		public void run()
		{
			while(true)
			{
				r.set("面包");
			}
		}
	}

	//描述消费者
	class consumer implements Runnable
	{
		private resource r;
		consumer(resource r)
		{
			this.r=r;
		}
		public void run()
		{
			while(true)
			{
				r.out();
			}
		}
	}

	public class ThreadDemo
	{
		public static void main(String[] args) {
			//创建线程对象
			resource r=new resource();

			//创建线程任务
			producer pro=new producer(r);
			consumer con=new consumer(r);

			//创建线程
			Thread t1=new Thread(pro);
			Thread t2=new Thread(pro);
			Thread t3=new Thread(con);
			Thread t4=new Thread(con);

			t1.start();
			t2.start();
			t3.start();
			t4.start();
		}
	}



	其实还有一些遗憾：效率低了

	jdk 1.5 以后提供了多生产多消费的解决方案
		在java.util.concurrent.locks 软件包中提供相应的解决方案
		lock接口：比同步更厉害，有更多操作		lock()：获取锁	unlock()：释放锁
			提供了一个更加面向对象的锁，在该锁中提供了更多的显式的锁操作
			替代同步
	升级到jdk 1.5	，先把同步改成lock()

	已经将旧锁换成新锁，那么监视器上的方法（wait，noyify，notifyAll）也应该替换成新锁的监视器方法
	而jdk 1.5中将这些原有的监视器方法封装到了一个Condition对象中
	想要获得监视器，需要先获取Condition对象


	Condition对象的出现，替代了Object中监视器方法
	await();
	signal();
	signalAll();

	将所有监视器方法替换成了Condition
	功能和上面一样，仅仅是用新的对象，改了写法而已
	但是问题依旧，效率问题还是没有解决

	希望本方可以唤醒对方中的一个
	老程序中可以通过两个锁嵌套完成，但是容易引发死锁
	新程序中就可以解决这个问题，只用一个锁 
	可以在一个锁上加上多个监视器对象

	import java.util.concurrent.locks.*;
	//描述资源,属性：名称，编号		行为：对名称赋值，获取商品
	class resource
	{
		private String name;
		private int count=1;

		//定义一个锁对象
		private Lock lock = new ReentranLock();
		//获取锁上的Condition对象,为了解决本方唤醒对方的问题。可以一个锁创建两个监视器的对象
		private Condition consume= lock.newCondition();
		private Condition produce= lock.newCondition();
		//定义标记
		private boolean flag=false;

		//提供设置的方法
		public void set(String name)	--t1 t2
		{
			//获取锁
			lock.lock();
		try{
			while(flag)	//	if(flag)
			{
				try{consume.await();} catch(InstantiationException e){}
			}
			//给成员变量赋值，并加上编号
			this.name=name+count;
			//编号自增
			count++;
			//打印生产了哪个商品
			System.out.println(Thread.currentThread().getName()+"...生产者..."+this.name);

			//将标记改为true
			flag=true;
			//唤醒一个消费者
			produce.signal(); 
		}
		finally{

				//释放锁
				lock.unlock();
			}
			
		}

		public void out()	--t3 t4
		{
			lock.lock();
		try{
				while(!flag)	//if(!flag)
				{
					try{produce.await();} catch(InstantiationException e){}
				}
				System.out.println(Thread.currentThread().getName()+".....消费者....."+this.name);
				//该标记
				flag=false;
				//唤醒一个生产者
				consume.signal();
			}
			finally{
				lock.unlock();
			}			
		}
	}

	//描述生产者
	class producer implements Runnable
	{
		private resource r;
		//生产者一初始化就要又资源，需要将资源传递到构造函数中
		producer(resource r)
		{
			this.r=r;
		}
		public void run()
		{
			while(true)
			{
				r.set("面包");
			}
		}
	}

	//描述消费者
	class consumer implements Runnable
	{
		private resource r;
		consumer(resource r)
		{
			this.r=r;
		}
		public void run()
		{
			while(true)
			{
				r.out();
			}
		}
	}

	public class ThreadDemo
	{
		public static void main(String[] args) {
			//创建线程对象
			resource r=new resource();

			//创建线程任务
			producer pro=new producer(r);
			consumer con=new consumer(r);

			//创建线程
			Thread t1=new Thread(pro);
			Thread t2=new Thread(pro);
			Thread t3=new Thread(con);
			Thread t4=new Thread(con);

			t1.start();
			t2.start();
			t3.start();
			t4.start();
		}
	}










