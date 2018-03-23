import java.util.Scanner;
public class money {
	public static void main(String args[]) {
		int[] unit = {500000, 10000, 1000, 500, 100, 50, 10, 1};
		
		Scanner scanner = new Scanner(System.in);
		int acount = scanner.nextInt();
		int[] s = new int[7];
		
		for(int i = 0; i < unit.length; i++) {
			int money = acount / unit[i];
			acount = acount - money*unit[i];
			System.out.println(unit[i]+"Count"+money);
		}
	}

}
