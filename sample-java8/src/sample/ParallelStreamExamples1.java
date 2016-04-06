package sample;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class ParallelStreamExamples1 {
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		
		//병렬처리 시 jvm이 사용가능한 코어 개수 설정
		//0: 코어개수 1개
		//1: 코어개수 2개
		//3: 코어개수 4개
		//7: 코어개수 8개
		System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "7");
		
		Arrays.asList(1,2,3,4,5,6,7,8)
//		      .stream() //cpu 코어를 1개만 사용하므로 8초 걸림
		      .parallelStream() //멀티코어 사용으로 2초걸림.(기본옵션 2코어)
		      .map(i -> {
		    	  try {
		    		  TimeUnit.SECONDS.sleep(1);
		    	  } catch (Exception e) {
		    		  e.printStackTrace();
		    	  }
		    	  return i;
		      })
		      .forEach(i -> System.out.println(i));
		System.out.println("elsaped " + (System.currentTimeMillis() - start) / 1000 + " seconds.");
	}
}
