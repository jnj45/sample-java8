package sample;

import static java.util.stream.Collectors.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

public class StreamExamples2 {

	public static void main(String[] args) {
		Product productA = new Product(1L, "A", new BigDecimal("100.00"));
		Product productB = new Product(2L, "B", new BigDecimal("23.00"));
		Product productC = new Product(3L, "C", new BigDecimal("31.45"));
		Product productD = new Product(4L, "D", new BigDecimal("80.20"));
		Product productE = new Product(5L, "E", new BigDecimal("7.50"));
		
		List<Product> products = Arrays.asList(
			productA,
			productB,
			productC,
			productD,
			productE
		);
		
		BigDecimal b30 = new BigDecimal("30");
	
		System.out.println(
			products.stream()
			        .filter(product -> product.getPrice().compareTo(b30) >= 0)
			        .collect(toList())
		);
		System.out.println("======================================================================");
		System.out.println(
				products.stream()
				.filter(product -> product.getPrice().compareTo(b30) >= 0)
				.map(product -> product.toString())
				.collect(joining("\n"))
		);
		System.out.println("======================================================================");
		System.out.println(
				products.stream()
//				.filter(product -> product.getPrice().compareTo(new BigDecimal("30")) >= 0)
				.map(product -> product.getPrice())
				.reduce(BigDecimal.ZERO, (price1,  price2) -> price1.add(price2))
		);
		System.out.println("======================================================================");
		System.out.println(
				products.stream()
				.filter(product -> product.getPrice().compareTo(b30) >= 0)
				.count()
		);
	}
	
	
}

@Data
@AllArgsConstructor
class Product{
	private Long id;
	private String name;
	private BigDecimal price;
}

@ToString(callSuper=true)
class DiscountedProduct extends Product{
	public DiscountedProduct(Long id, String name, BigDecimal price) {
		super(id, name, price);
	}
}

@Data
@AllArgsConstructor
class OrderedItem{
	private Long id;
	private Product product;
	private int quantity;
	
	public BigDecimal getTotalPrice(){
		return product.getPrice().multiply(new BigDecimal(quantity));
	}
}
@Data
@AllArgsConstructor
class Order{
	private Long id;
	private String orderNumber;
	private List<OrderedItem> items;
	
	public BigDecimal totalPrice(){
		return items.stream()
				     .map(item -> item.getTotalPrice())
				     .reduce(BigDecimal.ZERO, (price1, price2) -> price1.add(price2));
	}
}

