package sample;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * race condition 상황과 병렬처리 예
 * @author Administrator
 *
 */
public class RaceConditionAndParallelSample {

	public static void main(String[] args) {
		//아래와 같이 병렬처리하면 race condition에 빠짐
		int[] sum = new int[1];
		Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20)
			.parallelStream()
			.forEach(i -> sum[0] = sum[0] + i); //클로저가 참조하는 외부 free variable은 변경할 수 없음으로 편법으로 배열로 처리
		System.out.println(sum[0]);		
		
		//아래와 같이 처리.
		int total = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20)
			.parallelStream()
			.reduce(0, (i1, i2) -> i1 + i2);
		System.out.println(total);
		
		//일반 처리와 병렬 처리의 차이 구현
		long start = System.currentTimeMillis();
		int total2 = Arrays.asList(1,2,3,4,5,6,7,8)
//			.stream()
			.parallelStream()
			.peek(i -> {
				try {
					TimeUnit.SECONDS.sleep(4);
				} catch (Exception e) {
					e.printStackTrace();
				}
			})
			.reduce(0, (i1, i2) -> i1 + i2);
		System.out.println("It took " + (System.currentTimeMillis() - start));
		System.out.println(total);
		
		//이미 정렬된 배열을 parallelStream으로 정렬하면 더 느림.
		//하이버네이트 같은 ORM을 쓸 경우 경우에 따라 parallelStream을 더 느림. 예) lazy loading 사용 시.
	}
}
