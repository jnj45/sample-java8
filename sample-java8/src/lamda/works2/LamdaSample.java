package lamda.works2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * 기존 코드에서 람다식으로 변환하는 과정에 대한 예
 * @author Administrator
 *
 */
public class LamdaSample {

	public static void main(String[] args) {
		/*
		 * 1-10 의 배열에서 2보다 큰수, 7보다 작은 수들만 추출하는 것이 목적
		 * 
		 */
		//===========================================================
		// 전통적 코딩방식 예
		//===========================================================
		final List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
		List<Integer> result1 = new ArrayList<>();
		for(Integer number : list){
			if (number > 2){
				result1.add(number);
			}
		}
		System.out.println(result1);
		
		List<Integer> result2 = new ArrayList<>();
		for(Integer number : list){
			if (number < 7){
				result2.add(number);
			}
		}
		System.out.println(result2);
		
		//===========================================================
		// 익명 클래스 방법 코딩 예
		//===========================================================
		List<Integer> result3 = filter(list, new Predicate<Integer>() {
			@Override
			public boolean test(Integer integer) {
				return integer > 2;
			}
		});
		System.out.println(result3);
		
		List<Integer> result4 = filter(list, new Predicate<Integer>() {
			@Override
			public boolean test(Integer integer) {
				return integer < 7;
			}
		});
		System.out.println(result4);
		
		//===========================================================
		// 람다식 코딩 예
		//===========================================================
		List<Integer> result5 = filter(list, n -> n > 2);
		System.out.println(result5);
		
		List<Integer> result6 = filter(list, n -> n < 7);
		System.out.println(result6);
		
		//===========================================================
		// 람다식 응용. 함수조합
		//===========================================================
		final Predicate<Integer> greaterThen2 = n -> n > 2;
		final Predicate<Integer> lessThen7 = n -> n < 7;
		
		List<Integer> result7 = filter(list, greaterThen2.and(lessThen7));
		System.out.println(result7);
	}
	
	private static <T> List<T> filter(List<T> list, Predicate<T> predicate){
		List<T> result = new ArrayList<>();
		for(T value : list){
			if (predicate.test(value)){
				result.add(value);
			}
		}
		return result;
	}

}
