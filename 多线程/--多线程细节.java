多线程细节：
	1、sleep方法和wait方法异同点是什么？
		相同点：可以让线程处于冻结状态
		不同点：
				（1）sleep必须指定时间
					 wait可以指定时间，也可以不指定时间
				（2)sleep时间到，线程处于临时阻塞或运行
					wait如果没有指定时间，必须要通过notify或者notifyAll唤醒
				（3）sleep不一定非要定义在同步中
					wait必须定义在同步中
				（4）都定义在同步中，线程执行到sleep中不会释放锁，线程执行到wait会释放锁(wait如果不释放就没人notify它)
							synchronized(obj)
							{
								sleep(5000);
								wait();
							}
	


	2、线程如何停止？
		stop方法过时了，看其描述发现，有其他解决方案
		线程结束：就是让线程任务代码执行完，run方法结束
		run方法咋结束？
		run方法中通常都要定义循环（否则多线程没有意义），只要控制循环就ok了
		示例：
			class Demo implements Runnable
			{
				private boolean flag=true;
				public void run()
				{
					while(flag)
					{
						System.out.println(Thread.currentThread().getName()+".......");
					}
				}
				//对标记的修改方法
				public void changeflag()
				{
					flag=false;
				}
			}

			class StopThreadDemo
			{
				public static void main(String[] args) {
					Demo d=new Demo();

					Thread t1=new Thread(d);
					Thread t2=new Thread(d);

					t1.start();
					t2.start();

					int x=0;
					while(true)
					{
						if(++x==50)//条件满足
						{
							d.changeflag();//改变线程任务代码的标记，让其他线程也结束
							break;//跳出循环，主线程可以结束
						}
						System.out.println("main......"+x);
					}
					System.out.println("over");
				}
			}

	注意：万一线程在任务中处于了冻结状态，那么它还能取判断标记吗？不能！
		这个案例不能使用notifyAll，因为不是同一个锁
		咋办？
		查阅stop方法的描述，发现提供了一个解决方案：
			如果目标线程等待很长时间，则应使用interrupt方法来中断等待
			所谓的中断并不是停止线程
			interrupt的功能是 将线程的冻结状态清除，让线程的恢复到运行状态，即让线程重新具备CPU的执行资格
			因为是强制性的所以会有异常发生，可以在catch中捕获异常，在异常处理中，改变循环标记，让run方法结束

			class Demo implements Runnable
			{
				private boolean flag=true;
				public synchronized void run()
				{
					while(flag)
					{
						try{
							wait();//	t1 t2都在这等待了
						}
						catch(InstantiationException e)
						{
							//只要t1，或者t2被唤醒就要切换标记，不用在主线程里切换了
							changeflag();
							System.out.println(Thread.currentThread().getName()+"..."+e.toString());
						}
						System.out.println(Thread.currentThread().getName()+".......");
					}
				}
				//对标记的修改方法
				public void changeflag()
				{
					flag=false;
				}
			}

			class StopThreadDemo
			{
				public static void main(String[] args) {
					Demo d=new Demo();

					Thread t1=new Thread(d);
					Thread t2=new Thread(d);

					t1.start();
					t2.start();

					int x=0;
					while(true)
					{
						if(++x==50)//条件满足
						{
							//d.changeflag();//改变线程任务代码的标记，让其他线程也结束
							//在主线程中强制对t1线程对象进行中断状态的清除，强制让其恢复到运行状态
							t1.interrupt();
							t2.interrupt();
							break;//跳出循环，主线程可以结束
						}
						System.out.println("main......"+x);
					}
					System.out.println("over");
				}
			}






	3、守护线程：后台线程，一般创建的是前台线程
		前台后台线程运行时都是一样的，获取CPU的执行权执行
		只有结束的时候有些不同
		前台线程要通过run方法结束，线程结束
		后台线程也可以通过run方法结束，线程结束，还有另一种情况
		当线程中所有的前台线程都结束了，这时无论后台线程处于什么样的状态，都会结束，从而进程就会结束
		进程结束依赖的都是前台线程

	举个例子：
		class Demo implements Runnable
			{
				private boolean flag=true;
				public synchronized void run()
				{
					while(flag)
					{
						try{
							wait();//	t1 t2都在这等待了
						}
						catch(InstantiationException e)
						{
							//只要t1，或者t2被唤醒就要切换标记，不用在主线程里切换了
							changeflag();
							System.out.println(Thread.currentThread().getName()+"..."+e.toString());
						}
						System.out.println(Thread.currentThread().getName()+".......");
					}
				}
				//对标记的修改方法
				public void changeflag()
				{
					flag=false;
				}
			}

			class StopThreadDemo
			{
				public static void main(String[] args) {
					Demo d=new Demo();

					Thread t1=new Thread(d);
					Thread t2=new Thread(d);
					//将t2标记为后台线程，守护线程,只要主线程和t1线程结束，进程就会结束，t2也会随之结束
					t2.setDaemon(true);

					t1.start();
					t2.start();

					int x=0;
					while(true)
					{
						if(++x==50)//条件满足
						{
							//d.changeflag();//改变线程任务代码的标记，让其他线程也结束
							//在主线程中强制对t1线程对象进行中断状态的清除，强制让其恢复到运行状态
							t1.interrupt();
							//t2.interrupt(); 	注释掉t2的interrupt，t2不能在这里被强制恢复到运行状态
							break;//跳出循环，主线程可以结束
						}
						System.out.println("main......"+x);
					}
					System.out.println("over");
				}
			}





	4、线程优先级：用数字标识的，1-10
		其中默认的初始优先级是5，数字过于接近的优先级体现不出来
		最明显的三个优先级 1，5，10
		setPriority(Thread.MAX_PRIORITY);//最大优先级 10
		setPriority(Thread.NORM_PRIORITY);//缺省优先级 默认的 5
		setPriority(Thread.MIN_PRIORITY);//最小优先级 1



	5、线程组：ThreadGroup：可以通过Thread的构造函数明确新线程对象所属的线程组
		线程组的好处：可以对多个同组线程，进行统一的操作。
		默认都属于main线程组



	6、join，当前线程（一般是主线程）等到调用这个方法的线程执行完后才从冻结状态恢复
		public static void main(String[] args) {
			Demo d=new Demo();

			Thread t1=new Thread(d);
			Thread t2=new Thread(d);

			t1.start();
			//主线程执行到这里，直到t1要加入执行，主线程释放了执行权，执行资格，并处于冻结状态
			//什么时候恢复呢？ 等到t1线程执行完
			try{t1.join();} catch(InstantiationException e){}//一般用于临时加入一个运算线程，让该线程运算完，程序才会继续执行

			t2.start();
		}




	7、Thread.yield();//线程临时暂停

		public void run()
		{
			for(int i=1;i<=20;i++)
			{
				System.out.println("haha");
				Thread.yield();//线程临时暂停，将执行权释放，让其他线程有机会获取，有可能下次机会又将执行权抢回来
			}
		}





	8、匿名内部类

		class ThreadTest
		{
			public static void main(String[] args) {
				
				//第一个线程
				new Thread(){
					public void run()
					{
						for(int i=1;i<=20;i++)
						{
							System.out.println("···········X·············"+i);
						}
					}
				}.start();



				//第二个线程
				Runnable r=new Runnable(){
					public void run()
					{
						for(int i=1;i<=20;i++)
						{
							System.out.println("···········Y·············"+i);
						}
					}
				};
				new Thread(r).start();



				//主线程的循环
				for(int i=1;i<=20;i++)
				{
					System.out.println("···········X·············"+i);
				}
			}
		}


	//测试题
		new Thread(new Runnable()
		{
			public void run()
			{
				System.out.println("Runnable run");
			}
		}){
			public void run()
			{
				System.out.println("subthread run");//执行这一句，而不是上面的
			}//这个run方法是Thread子类的run方法，已经覆盖了父类
		}.start();


	//分析
		//Thread类的源码
		class Thread
		{
			private Runnable r;
			Thread(Runnable r)
			{
				this.r=r;
			}

			//这个是父类的run方法，在Thread类的匿名内部类中已经被覆盖，所以当时执行的是子类中定义的run方法
			public void run()
			{
				if(r!=null)
				{
					r.run();
				}
			}

			public void start()
			{
				run();
			}
		}