public class Animal {
	boolean live;
	int age;
	String name;
	
	void setName(String name) {
		this.name = name;
	}
	String getName() {
		return name;
	}
	boolean getLive() {
		return live;
	}
	public Animal(String name, int age, boolean live) {
		this.name = name;
		this.age = age;
		this.live = live;
	}
}
