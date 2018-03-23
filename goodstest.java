public class goodstest {
	public static void main(String args[]) {
		goods camera = new goods();
		
		camera.name = "nikon";
		camera.price = 400000;
		camera.numberOfStock = 30;
		camera.sold = 50;
		
		System.out.println("Product Name : "+camera.name);
		System.out.println("Product Price : "+camera.price);
		System.out.println("Stock Number : "+camera.numberOfStock);
		System.out.println("Sold Number : "+camera.sold);
		
		//sysout ctrl+space = System.out.println() 
	}
}
