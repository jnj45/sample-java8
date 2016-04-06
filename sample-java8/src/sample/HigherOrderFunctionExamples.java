package sample;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class HigherOrderFunctionExamples {

	public static void main(String[] args) {
		Function<Function<Integer, String>, String> f1 = g -> g.apply(10);
		System.out.println(
				f1.apply(i -> "#" + i) // "#10"
		); 
		
		Function<Integer, Function<Integer, Integer>> f2 = i -> (i2 -> i + i2);
		System.out.println(
				f2.apply(1).apply(9) // 10
		);
		
		List<Integer> list = Arrays.asList(1,2,3,4,5);
		List<String> mappedList = map(list, i -> "#"+i);
		System.out.println(mappedList); //[#1, #2, #3, #4, #5]
		
		System.out.println(
			list.stream()
				.map(i -> "#"+i)
				.collect(toList())
		);
		
		Function<Integer, Function<Integer, Function<Integer, Integer>>> f3 = i1 -> i2 -> i3 -> i1 + i2 + i3;
		System.out.println(f3.apply(1).apply(2).apply(3)); //6
		
	}
	
	private static <T, R> List<R> map(List<T> list, Function<T, R> mapper){
		List<R> result = new ArrayList<R>();
		for(T t : list){
			result.add(mapper.apply(t));
		}
		return result;
	}

}
