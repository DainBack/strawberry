//7313
public class CircleTest {
	public static void main(String args[]) {
		Circle c1 = new Circle(10);
		Circle c2 = new Circle(10);
		Circle c3 = new Circle(30);
		
		System.out.println(c1);
		System.out.println("the radius of c1 is "+ c1.getRadius());
		
		if(c1.equals(c2))
			System.out.println("Two Circle are same");
		else
			System.out.println("Two Circle are differnet");
		
		if(c1.equals(c3))
			System.out.println("Two circles are same");
		else
			System.out.println("Two Circle are differnet");
	}
}
