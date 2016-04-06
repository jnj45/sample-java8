package sample;

import java.util.concurrent.TimeUnit;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * @author Administrator
 *
 */
public class ParallelPerformanceExcamples1 {

	public static void main(String[] args) {
//		final long n = 10_000_000;
		final long n = 1000;
//		System.out.println((1+n)*(n/2)); //가우스 수식
		
		final long start1 = System.currentTimeMillis();
		System.out.println("iterativeSum:" + iterativeSum(n));
		System.out.println("elapsed " + (System.currentTimeMillis()-start1) + " ms.\n");
		
		final long start2 = System.currentTimeMillis();
		System.out.println("sequentialSum:" + sequentialSum(n));
		System.out.println("elapsed " + (System.currentTimeMillis()-start2) + " ms.\n");
		
		final long start3 = System.currentTimeMillis();
		System.out.println("parallelSum:" + parallelSum(n));
		System.out.println("elapsed " + (System.currentTimeMillis()-start3) + " ms.\n");
		
		final long start4 = System.currentTimeMillis();
		System.out.println("rangedSum:" + rangedSum(n));
		System.out.println("elapsed " + (System.currentTimeMillis()-start4) + " ms.\n");
		
		final long start5 = System.currentTimeMillis();
		System.out.println("parallelRangedSum:" + parallelRangedSum(n));
		System.out.println("elapsed " + (System.currentTimeMillis()-start5) + " ms.\n");
		
		
	}
	
	public static long iterativeSum(long n){
		long result = 0;
		for(long i = 0; i <= n; i++){
			result += i; 
			slowDown();
		}
		return result;
	}
	public static long sequentialSum(long n){
		return Stream.iterate(1L, i -> i + 1).limit(n).peek(i->slowDown()).reduce(Long::sum).get();
	}
	
	public static long parallelSum(long n){
		//단순계산은 parallel처리 시 더 오래 걸림.
		//reduce는 초기값이 필요한데 병렬처리 시 초기값을 알 수 있을 때가지 기다려야 하므로 더 느려짐.
		return Stream.iterate(1L, i -> i + 1).limit(n).parallel().peek(i->slowDown()).reduce(Long::sum).get();
	}
	
	public static long rangedSum(long n){
		return LongStream.rangeClosed(1, n).peek(i->slowDown()).reduce(Long::sum).getAsLong();
	}
	
	public static long parallelRangedSum(long n){
		return LongStream.rangeClosed(1, n).parallel().peek(i->slowDown()).reduce(Long::sum).getAsLong();
	}

	public static void slowDown(){
		try {
			TimeUnit.MILLISECONDS.sleep(10L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
