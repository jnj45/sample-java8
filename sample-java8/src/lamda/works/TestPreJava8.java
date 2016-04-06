package lamda.works;

public class TestPreJava8 {
	
	TestFunctionInterface<CargoWorks> quantiryMerger = new TestFunctionInterface<CargoWorks>() {
		@Override
		public CargoWorks doSomething(CargoWorks c1, CargoWorks c2) {
			c1.setBoxQty(c1.getBoxQty() + c2.getBoxQty());
			return c1;
		}
	};
	
	public void preJava8Method(){
		
		CargoWorks c1 = new CargoWorks(1000);
		CargoWorks c2 = new CargoWorks(2000);
		
		CargoWorks mergeQuantity = quantiryMerger.doSomething(c1, c2);
		System.out.println("mergeQuantity : " + mergeQuantity.getBoxQty());
	}
	
	public static void main(String[] args){
		TestPreJava8 t =  new TestPreJava8();
		t.preJava8Method();
	}
	
	/*============================================================================================*/
	// 두 박스의 합
	TestFunctionInterface<CargoWorks> quantityAdder = (CargoWorks c1, CargoWorks c2) -> {
		c1.setBoxQty(c1.getBoxQty() + c2.getBoxQty());
		return c1;
	};
	// 수가 큰 박스
	TestFunctionInterface<CargoWorks> compareBoxes = (CargoWorks c1, CargoWorks c2) -> {
		if (c1.getBoxQty() >  c2.getBoxQty()){
			return c1;
		}else{
			return c2;
		}
	};
	// 또 다른 박스 작업
//	TestFunctionInterface<CargoWorks> otherJobs = (CargoWorks c1, cargoWorks c2) -> thatThingYouDo(c1, c2);
	
	public void applyBehavior(TestFunctionInterface<CargoWorks> applySomething, CargoWorks c1, CargoWorks c2){
		applySomething.doSomething(c1, c2);
	}
	
}
