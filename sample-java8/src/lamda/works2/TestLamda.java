package lamda.works2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TestLamda {

	public static void main(String[] args) {
		
		List<MyClass> thisListofMyClass = new ArrayList<MyClass>();
		thisListofMyClass.add(new MyClass(2));
		thisListofMyClass.add(new MyClass(1));
		thisListofMyClass.add(new MyClass(3));
		
		for(MyClass cls : thisListofMyClass){
			System.out.println(cls.getValue());
		}
		
//		Collections.sort(thisListofMyClass, new Comparator<MyClass>() {
//			public int compare(MyClass a, MyClass b){
//				return a.getValue() - b.getValue(); //실제 필요한 코드 한줄인테, 클래스를 만들어서 넘겨야 됨.
//			}
//		});
		
		thisListofMyClass.sort(new Comparator<MyClass>() {
			public int compare(MyClass a, MyClass b){
				return a.getValue() - b.getValue(); //실제 필요한 코드 한줄인테, 클래스를 만들어서 넘겨야 됨.
			}
		});
		
		//람다 표현식으로
		//thisListofMyClass.sort((MyClass a, MyClass b) -> {return a.getValue() - b.getValue()});
		//보다 약식으로
		//thisListofMyClass.sort((a, b) -> {a.getValue() - b.getValue()});
		
		for(MyClass cls : thisListofMyClass){
			System.out.println(cls.getValue());
		}
		
//		java.util.function.
	}

}
