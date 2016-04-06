package sample;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FunctionalInterfaceExamples {

	public static void main(String[] args) {
		//Functional Interface : abstract method가 하나만 존재하는 interface
		//anonymous class를 생성할 필요없이 lamda expression 을 사용할 수 있음.
		
		//anoymous class 방식 코드
		final Function<String, Integer> toInt = new Function<String, Integer>() {
			@Override
			public Integer apply(String t) {
				return Integer.parseInt(t);
			}
		};
		System.out.println(toInt.apply("100"));
		
		//람다 표현식
		final Function<String, Integer> toInt2 = t -> Integer.parseInt(t);
		System.out.println(toInt2.apply("100"));
		
//		final Function<Integer, Integer> identity = Function.identity();
		final Function<Integer, Integer> identity = t -> t;
		System.out.println("identity.apply:"+identity.apply(999));
		
		final Consumer<String> print = s -> System.out.println("Hello " + s);
		print.accept("lee");
		
		final Predicate<Integer> isPostive = i -> i > 0;
		System.out.println(isPostive.test(1));
		
		List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
		
		Predicate<Integer> g3 = i -> i > 3;
		Predicate<Integer> l7 = i -> i < 7;
		
//		List<Integer> nums1 = filter(numbers, i -> i > 3);
//		List<Integer> nums1 = filter(numbers, g3);
		List<Integer> nums1 = filter(numbers, g3.and(l7));
		System.out.println(nums1);
		
		//====================================================
		//Supplier, lazy evaluation
		//====================================================
		final Supplier<String> helloSupplier = () -> "Hello! ";
		System.out.println(helloSupplier.get());
		
		long start = System.currentTimeMillis();
//		printIfValidataion(1, getVeryExpensiveValue());
//		printIfValidataion(0, getVeryExpensiveValue());
//		printIfValidataion(-1, getVeryExpensiveValue());
		printIfValidataion(1, () -> getVeryExpensiveValue());
		printIfValidataion(0, () -> getVeryExpensiveValue());
		printIfValidataion(-1, () -> getVeryExpensiveValue());
		System.out.println((System.currentTimeMillis() - start)/1000 + " seconds.");
	}
	
	private static String getVeryExpensiveValue(){
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "kevin";
	}
	
//	private static void printIfValidataion(int number, String value){
	private static void printIfValidataion(int number, Supplier<String> valueSuppler){
		if (number >= 0){
//			System.out.println("value is " + value);
			System.out.println("value is " + valueSuppler.get());
		}else{
			System.out.println("Invalid");
		}
	}
	private static <T> List<T> filter(List<T> list, Predicate<T> filter){
		List<T> result = new ArrayList<T>();
		for(T input : list){
			if (filter.test(input)){
				result.add(input);
			}
		}
		return result;
	}
	
	//=======================================
	//사용자 정의 Funtional Interface
	//=======================================
	public static void main2(String[] args) {
//		println("Area is ", 2, 3, (message, length, width) -> String.valueOf(message + (length * width)) );
//		println(1L, "kevin", "aaa@test.com", (id, name, email) -> "User info: ID=" + id + ", email:" + email + ", name:" + name);
		
		BigDecimalToCurrency bigDecimalToCurrency = bd -> "$" + bd.toString();
		System.out.println(bigDecimalToCurrency.toCurrency(new BigDecimal("120.00")));
		
		final InvalidFuntionalInterface anonymousinvalidFuntionalInterface = new InvalidFuntionalInterface() {
			@Override
			public <T> String makeString(T value) {
				return value.toString();
			}
		};
//		final InvalidFuntionalInterface invalidFuntionalInterface = value -> value.toString();
//		System.out.println(invalidFuntionalInterface.makeString(123));
	}
	
	private static <T1, T2, T3> void println(T1 t1, T2 t2, T3 t3, Funtion3<T1, T2, T3, String> function){
		System.out.println(function.apply(t1, t2, t3));
	}
}

@FunctionalInterface
interface Funtion3<T1, T2, T3, R>{
	R apply(T1 t1, T2 t2, T3 t3);
//	R accept(T1 t1, T2 t2, T3 t3); abstract 메소드는 단 하나만 존재해야함.
}
	
@FunctionalInterface
interface BigDecimalToCurrency{
	String toCurrency(BigDecimal value);
}

//람다 표현식 사용 못함.
@FunctionalInterface
interface InvalidFuntionalInterface{
	<T> String makeString(T value);
}