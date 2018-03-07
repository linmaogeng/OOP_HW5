import java.util.Random;


public class findPi {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long before = System.currentTimeMillis();
		final long total = 4000000000L;
		long dotIn = 0;
		double radius = 1000,x,y;
		
		Random generator = new Random();
		for(long i=0;i<total;i++) {
			x = generator.nextDouble()*radius;
			y = generator.nextDouble()*radius;
			if(Math.hypot(x, y) <= radius){
				dotIn++;
			}
		}
		System.out.println((double)dotIn/total*4);
		System.out.println(System.currentTimeMillis()-before);
	}
}

