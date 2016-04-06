package lamda.works;

public class ImplFunctions {
	
	//두 스트링 연결
	TestFunctionInterface<String> stringAdder = (String s1, String s2) -> s1 + s2;
	
	public void concatnateStrings(String s1, String s2){
		System.out.println("concatnateStrings result:" + stringAdder.doSomething(s1, s2));
	}
	
	public static void main(String[] args) {
		ImplFunctions f = new ImplFunctions();
		f.concatnateStrings("a", "b");
	}

}
