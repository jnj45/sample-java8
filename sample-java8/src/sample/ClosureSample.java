package sample;

import java.util.Comparator;

public class ClosureSample {

	public static void main(String[] args) {
		
		int factor = 10; //final 생략해도 됨
		Comparator<Integer> comparator = (i1, i2) -> i1 > factor ? i1 : i1.compareTo(i2);
		// factor = 1; --> 컴파일 오류 발생(Local variable factor defined in an enclosing scope must be final or effectively final)
		//                 나중에 실행되거나, 전혀 다른 쓰레드에서 실행될 수 있기 때문???
		
		//함수 외부에 있는 변수(nonlocal variable, free variable)을 참조하도록 영역?을 넘어 덮어버리기(close) 때문에 closure라고 부름.
		//java8부터 final 생략가능하지만, 변경하면 안 됨 --> effectively final.
		//변경하는 코드가 들어가면 컴파일 오류 발생(Local variable factor defined in an enclosing scope must be final or effectively final)
		//나중에 실행되거나, 전혀 다른 쓰레드에서 실행될 수 있기 때문???
		int number = 100; 
		testClosure(new Runnable() {
			@Override
			public void run() {
//				number = 101; //--> 컴파일오류발생
				System.out.println(number);
			}
		});
		
		testClosure(() -> System.out.println(number));
		
//		number = 101; //--> 컴파일오류발생
		
	}
	
	private static void testClosure(final Runnable runnable){
		runnable.run();		
	}

}
