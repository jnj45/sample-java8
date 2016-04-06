package sample;

import static java.util.stream.Collectors.toList;

import java.math.BigDecimal;
import java.util.Arrays;

public class MethodReferenceExamples {

	public static void main(String[] args) {
		Arrays.asList(1,2,3,4,5)
//		      .forEach(i -> System.out.println(i));
			  .forEach(System.out::println); // method reference
		
		System.out.println(
		Arrays.asList(new BigDecimal("10.0"), new BigDecimal("23"), new BigDecimal("5"))
		      .stream()
//		      .sorted()
//		      .sorted((b1,b2) -> b1.compareTo(b2))
//		      .sorted(BigDecimalUtil::compare) //static method refer
		      .sorted(BigDecimal::compareTo) //instace method refer. 자동으로 b1 의 compareTo 메소드에 b2를 파라미터로 사용
		      .collect(toList())
		);
		
		String targetString = "c";
		System.out.println(
			Arrays.asList("a","b","c","d")
			   	  .stream()
//			   	  .anyMatch(x -> x.equals("c"))
//			   	  .anyMatch(targetString::equals)
			   	  .anyMatch("c"::equals)
			   	  
		);
	}
}

class BigDecimalUtil {
	public static int compare(BigDecimal b1, BigDecimal b2){
		return b1.compareTo(b2);
	}
}