package Demo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class resource
{
	String name;
	//锁
	private final Lock lock=new ReentrantLock();
	//Condition监视器
	private final Condition consume=lock.newCondition();
	private final Condition produce=lock.newCondition();
	//存储商品的容器
	String [] items=new String[200];
	//角标和容器中的数量
	int consumeptr=0,producestr=0,accout=0;
	int jishu=1;
	
	//生产者函数
	public void put(String x)
	{
		lock.lock();
		try {
			//先判断是否需要生产，或者等待
			while(accout==items.length)
			{
				try {
					produce.await();
				} catch (InterruptedException e) {
					//e.printStackTrace();
				}
			}
			//将商品放入容器
			items[producestr]=x+jishu;
			System.out.println(Thread.currentThread().getName()+"...生产者..."+items[producestr]);
			//判断如果脚标到最后一个，则从0开始
			if(++producestr==items.length)
			{
				producestr=0;
			}
			accout++;
			jishu++;
			//唤醒消费者
			consume.signal();
		}finally {
			lock.unlock();
		}
	}
	
	//消费者函数
	public void take()
	{
		lock.lock();
		try {
			while(accout==0)
			{
				try {
					consume.await();
				} catch (InterruptedException e) {
					//e.printStackTrace();
				}
			}
			//将商品从容器取出
			String x=items[consumeptr];
			System.out.println(Thread.currentThread().getName()+"...消费者..."+x);
			//判断脚标到最后一个，则从0开始
			if(++consumeptr==items.length)
			{
				consumeptr=0;
			}
			accout--;
			produce.signal();
		}finally {
			lock.unlock();
		}
	}
}

class producer implements Runnable
{
	private resource r;
	public producer(resource r)
	{
		this.r=r;
	}
	public void run()
	{
		while(true)
		{
			r.put("面包");
		}
	}
}

class consumer implements Runnable
{
	private resource r;
	public consumer(resource r)
	{
		this.r=r;
	}
	public void run() 
	{
		while(true)
		{
			r.take();
		}
	}
	
}

public class ConsumeAndProduce {
	public static void main(String[] args) {
		//创建线程对象
		resource r=new resource();
		
		//创建Runnable子类的对象，不是线程对象，是线程任务
		producer pro=new producer(r);
		consumer con=new consumer(r);
		//创建线程 ，创建Thread对象，并将Runnable子类对象传递进去
		Thread t1=new Thread(pro);
		Thread t2=new Thread(con);
		//开启线程
		t1.start();
		t2.start();
	}
}











