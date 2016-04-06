package sample;

public class ClosureSample2 {
	
	private int number = 999;
	
	public static void main(String[] args) {
		new ClosureSample2().test();
	}

	public static <T> String toString(T value){
		return "static to String : " + value;
	}
	
	private void test(){
		int number = 100;
		
		// anonymous class
		testClosure(new Runnable() {
			@Override
			public void run() {
				System.out.println(number); //-->100
//				System.out.println(this.number); //-->오류
				System.out.println(ClosureSample2.this.number); //-->999
				
//				System.out.println(toString("staic")); //--> 오류. 
				//anonymous class가 가지고 있는 메소드(상속한 메소드 포함)와 이름이 동일한 외부 메소드에 접근할 경우 shadowing이 발생한다.
			}
		});
		
		// Lamda Expression
		testClosure(() -> System.out.println(number)); //-->100
		testClosure(() -> System.out.println(this.number)); //-->999. Lamda 자체는 scope 없으므로 scope이 object로 확장됨.(렉시컬 스코프)
		testClosure(() -> System.out.println(toString("static"))); //Lamda expression에서는 shadowing이 발생하지 않음
		
		testClosure(() -> {
//				int number = 77; //->Lambda expression's local variable number cannot redeclare another local variable defined in an enclosing scope. 
				System.out.println(number);
			}
		);
	}
	private static void testClosure(final Runnable runnable){
		runnable.run();		
	}

}
