public class CashRegister {
	static int ItemNumber;
	double Price;
	double add=0;
	
	double addItem(double Price){
		this.Price += Price;
		System.out.println("registration");
		ItemNumber++;
		return Price;
	}
	
	double getTotal(){
		add += Price;
		return add;
	}
	
	int getCount(){
		return ItemNumber;
	}
	
	void clear(){
		Price = 0;
	}
}
