package sample;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

public class FunctionalInterfaceExam {

	public static void main(String[] args) {
		Product productA = new Product(1L, "A", new BigDecimal("10.00"));
		Product productB = new Product(2L, "B", new BigDecimal("55.50"));
		Product productC = new Product(3L, "C", new BigDecimal("17.45"));
		Product productD = new Product(4L, "D", new BigDecimal("23.00"));
		Product productE = new Product(5L, "E", new BigDecimal("110.99"));
		
		List<Product> products = Arrays.asList(
			productA,
			productB,
			productC,
			productD,
			productE
		);
		
		//===============================================
		//Predicate F.I를 이용한 메소드 활용
		//===============================================
		BigDecimal twenty = new BigDecimal("20");
//		List<Product> result = new ArrayList<>();
//		for(Product product : products){
//			if (product.getPrice().compareTo(twenty) >= 0){
//				result.add(product);
//			}
//		}
//		System.out.println(result);
		System.out.println(filter(products, product -> product.getPrice().compareTo(twenty) > 0));
		System.out.println(filter(products, product -> product.getPrice().compareTo(new BigDecimal("10")) <= 0));
		
		//===============================================
		//Function F.I를 이용한 메소드 활용
		//===============================================
		List<Product> expensiveProducts = filter(products, product -> product.getPrice().compareTo(new BigDecimal("50")) >= 0);
//		List<Product> discountedProducts = new ArrayList<Product>();
//		for(Product product : expensiveProducts){
//			discountedProducts.add(new DiscountedProduct(product.getId(), product.getName(), product.getPrice().multiply(new BigDecimal("0.5"))));
//		}
		List<DiscountedProduct> discountedProducts = map(expensiveProducts, 
				product -> new DiscountedProduct(product.getId(), product.getName(), product.getPrice().multiply(new BigDecimal("0.5"))));
		
		System.out.println("           expensiveProducts:"+expensiveProducts);
		System.out.println("          discountedProducts:"+discountedProducts);
		
		//===============================================
		//F.I를 활용시 super/sub class 문제 -> ? super T 사용
		//===============================================
		Predicate<Product> predicate = product -> product.getPrice().compareTo(new BigDecimal("30")) <=0;
		System.out.println("discountedProducts under $30:"+filter(discountedProducts, predicate ));
		System.out.println("          Products under $30:"+filter(products, predicate ));
		
		//===============================================
		//
		//===============================================
		List<BigDecimal> prices = map(products, product -> product.getPrice());
//		BigDecimal total = BigDecimal.ZERO;
//		for(BigDecimal price : prices){
//			total = total.add(price);
//		}
		BigDecimal total = total(products, product -> product.getPrice());
		System.out.println("         Total: " + total);
		BigDecimal discountedTotal = total(discountedProducts, product -> product.getPrice());
		System.out.println("discountedTotal: " + discountedTotal);
		
		Order order = new Order(1L, "on-1234", Arrays.asList(
			new OrderedItem(1L, productA, 2),
			new OrderedItem(2L, productC, 1),
			new OrderedItem(3L, productD, 10)
		));
		
		System.out.println("order total: " + order.totalPrice());
		
	}
	
	private static <T> List<T> filter(List<T> list, Predicate<? super T> predicate){
		List<T> result = new ArrayList<>();
		for(T t : list){
			if (predicate.test(t)){
				result.add(t);
			}
		}
		return result;
	}
	
	private static <T, R> List<R> map(List<T> list, Function<T, R> function){
		List<R> result = new ArrayList<R>();
		for(T t : list){
			result.add(function.apply(t));
		}
		return result;
	}
	
	private static <T> BigDecimal total(List<T> list, Function<T, BigDecimal> mapper){
		BigDecimal total = BigDecimal.ZERO;
		for(T t : list){
			total = total.add(mapper.apply(t));
		}
		return total;
	}
	
	@Data
	@AllArgsConstructor
	static class Product{
		private Long id;
		private String name;
		private BigDecimal price;
	}
	
	@ToString(callSuper=true)
	static class DiscountedProduct extends Product{
		public DiscountedProduct(Long id, String name, BigDecimal price) {
			super(id, name, price);
		}
	}
	
	@Data
	@AllArgsConstructor
	static class OrderedItem{
		private Long id;
		private Product product;
		private int quantity;
		
		public BigDecimal getItemTotal(){
			return product.getPrice().multiply(new BigDecimal(quantity));
		}
	}
	@Data
	@AllArgsConstructor
	static class Order{
		private Long id;
		private String orderNumber;
		private List<OrderedItem> items;
		
		public BigDecimal totalPrice(){
			return total(items, item -> item.getItemTotal());
		}
	}
}

