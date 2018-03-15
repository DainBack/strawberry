import java.util.Scanner;
import java.util.Random;
public class SimpleGame {
	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		Random r = new Random();
		int selected_value = r.nextInt(100);
		int count = 1, input_value;
		boolean done = false;
		System.out.println("I've decided on the number. Enter number.");
		
		do {
			System.out.print(count+">>");
			input_value = scanner.nextInt(); 
			if(selected_value == input_value) {
				System.out.println("Correct.");
				System.out.println("Play Again? (y/n)");
				if(scanner.next().equals("y")) {
					done = false;
					count = 0;
					System.out.println("I've decided on the number. Enter number.");
				}
				else break;
			}
			else if(selected_value > input_value) {
				System.out.println("The Number is biger");
			}
			else {
				System.out.println("The Number is smaller");
			}
			count++;
		}
		while(!done);
		System.out.println("The game is end.");
	}

}
