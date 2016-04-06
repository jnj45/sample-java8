package sample;

import static java.util.stream.Collectors.*;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.IntUnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamExamples1 {

	public static void main(String[] args) {
//		IntStream.rangeClosed(0, 10).forEach(i -> System.out.print(i + " "));
//		IntStream.iterate(1, i -> i + 1)
//				 .forEach(i -> System.out.println(i + " "));
//		IntUnaryOperator.identity();
		
//		Stream.iterate(BigInteger.ONE, i -> i.add(BigInteger.ONE))
//		      .forEach(i -> System.out.println(i));
		
//		Stream.of(1,2,3,4,5,6)
//			  .forEach(i -> System.out.println(i));
		
		final List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
		System.out.println(
			numbers.stream()
			       .filter(i -> i > 3 && i < 9)
			       .map(i -> i * 2)
			       .filter(i -> i > 10) // Optional[12]
//			       .filter(i -> i > 20) // Optional.empty
//			       .findFirst()
			       .collect(toList())
		);
		
		numbers.stream().collect(toSet());
		
		Integer i1 = 1;
		if (1 == i1){
			System.out.println("a:"+true);
		}
		
		Integer i127 = 127;
		if (Integer.valueOf(127) == i127){
			System.out.println("b:"+true);
		}
		
		Integer i128 = 128;
		if (Integer.valueOf(128) == i128){
			System.out.println("c:"+true);
		}
		if (Integer.valueOf(128).equals(i128)){
			System.out.println("d:"+true);
		}
	}

}
