public class AnimalTest {
	public static void main(String args[]) {
		Animal ani = new Animal("Watusi cattle", 3, true);
		ani.getLive();
		if(ani.getLive()) {
			System.out.println("is alive.");
		}
		else {
			System.out.println("is dead.");
		}
		System.out.println("Name : "+ani.getName());
		ani.setName("Penguin");
		System.out.println("Name : "+ani.getName());
		if(ani.getLive()) {
			System.out.println("is alive.");
		}
		else {
			System.out.println("is dead.");
		}
	}
}
