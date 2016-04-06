package sample;

import java.math.BigDecimal;
import java.util.function.Function;

import lombok.AllArgsConstructor;
import lombok.Data;

public class MethodReferenceExamples2Constructor {

	public static void main(String[] args) {
		//=====================================
		//파라미터가 하나인 생성자의 method reference 예
		//=====================================
		Section s1 = new Section(10);
		
		Function<Integer, Section> sectionFactoryWithLamdaExp = number -> new Section(number);
		Section s2 = sectionFactoryWithLamdaExp.apply(10);
		
		Function<Integer, Section> sectionFactoryWithMethodReference = Section::new;
		Section s3 = sectionFactoryWithMethodReference.apply(10);
		
		//=====================================
		//파라미터가 여러 개인 생성자의 method reference 예. 별도의 FunctionalInterface를 만들어 사용
		//=====================================
		Item p1 = new Item(1L, "A", new BigDecimal("10.0"));
		
		ItemCreator icmr = Item::new;
		Item p2 = icmr.create(1L, "A", new BigDecimal("10.0"));
				
		//=====================================
		//generic type을 이용한 동적 type constructor 활용. compile time error check enable
		//=====================================
		ItemA ia = createItem(1L, "A", new BigDecimal("10.0"), ItemA::new);
		ItemB ib = createItem(1L, "B", new BigDecimal("10.0"), ItemB::new);
		
	}
	
	private static <T extends Item> T createItem(Long id, String name, BigDecimal price, ItemCreator<T> ic){
		return ic.create(id, name, price);
	}

}

@FunctionalInterface
interface ItemCreator<T extends Item>{
	T create(long id, String name, BigDecimal price);
}

@AllArgsConstructor
@Data
class Section{
	int i;
}

@AllArgsConstructor
@Data
class Item{
	long id;
	String name;
	BigDecimal price;
}

class ItemA extends Item{
	public ItemA(Long id, String name, BigDecimal price){
		super(id, name, price);
	}

	@Override
	public String toString() {
		return "A=" + super.toString();
	}
}

class ItemB extends Item{
	public ItemB(Long id, String name, BigDecimal price){
		super(id, name, price);
	}

	@Override
	public String toString() {
		return "B=" + super.toString();
	}
}
