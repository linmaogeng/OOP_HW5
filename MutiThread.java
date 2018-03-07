import java.util.concurrent.ThreadLocalRandom;

public class MutiThread extends Thread { //https://docs.oracle.com/javase/8/docs/api/java/lang/Thread.html
		
	private long loop,dotOut;
	
	MutiThread(long loop){
		this.loop = loop;
		dotOut = 0;
	}

	public long getDot(){
		return dotOut;
	}
	
    public void run() {
    	//http://thoughtfuljava.blogspot.com/2012/09/prefer-threadlocalrandom-over-random.html
    	//https://stackoverflow.com/questions/23396033/random-over-threadlocalrandom
    	//By experiment, ThreadLocalRandom generate double about 8 times faster than Math.Random
		double x,y;
		ThreadLocalRandom rand = ThreadLocalRandom.current();
		
		for(long count=0;count<loop;count++) {// https://en.m.wikipedia.org/wiki/Monte_Carlo_integration
			x = rand.nextDouble();
			y = rand.nextDouble();
			dotOut += x*x+y*y;  //Java somehow can auto cast double to long or int when += was used
		}
    }

	public static void main(String[] args) {
		final long before = System.currentTimeMillis();
 		long total = 4000000000L;
		final int THREADS = 4;
		long loop = total/THREADS;
		MutiThread[] all = new MutiThread[THREADS];
		
		for(int i=0;i<all.length;i++) {
			all[i]= new MutiThread(loop);
			all[i].start();
		}
		
		try {
			for(int i=0;i<all.length;i++) {
				all[i].join();
				total -= all[i].getDot();
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}

		System.out.println((double)total/1000000000);
		System.out.println("Excuting Time: " + (System.currentTimeMillis()-before));
	}
	
}

