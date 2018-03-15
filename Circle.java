public class Circle {
	int radius;

	Circle(){
		System.out.println("radius : " + radius);
	}
	
	Circle(int radius){
		this.radius = radius;
	}
	
	int getRadius() {
		return radius;
	}
	
	boolean equals(Circle c) {
		if(radius == c.radius)
			return true;
		else return false;
	}
}
