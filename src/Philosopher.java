import java.util.Random;

public class Philosopher implements Runnable{
	
	private int id;
	private Chopstick leftChopstick;
	private Chopstick rightChopstick;
	
	private Random random;
	
	private int eatingCounter;
	
	private volatile boolean isFull = false;
	
	public Philosopher(int id, Chopstick leftChopstick , Chopstick righChopstick) {
		this.id = id;
		this.leftChopstick = leftChopstick;
		this.rightChopstick = righChopstick;
		this.random = new Random();
	}

	@Override
	public void run() {
		try {
			while(!isFull) {
				think();
				
				if(leftChopstick.pickUp(this, State.LEFT)) {
					if(rightChopstick.pickUp(this, State.RIGHT)) {
						eat();
						rightChopstick.putDown(this, State.RIGHT);
					}
					leftChopstick.putDown(this, State.LEFT);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public int getCounter() {
		return this.eatingCounter;
	}
	
	public void setFull(boolean isFull) {
		this.isFull = isFull;
	}
	
	private void think() throws InterruptedException{
		System.out.println(this +" is thinking...");
		Thread.sleep(random.nextInt(100));
	}
	
	private void eat() throws InterruptedException{
		System.out.println(this +" is eating...");
		this.eatingCounter++;
		Thread.sleep(random.nextInt(100));
	}
	
	
	@Override
	public String toString() {
		return "Philosopher " + id ;
	}
	

}
