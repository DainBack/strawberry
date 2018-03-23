import java.util.Scanner;
public class practice2คั01 {
	public static void main(String args) {
		char a;
		Scanner rd = new Scanner(System.in); 
		System.out.println("Enter to Alphabet : ");
		a = (char) rd.nextInt();
		if(a >= 'A' && a <= 'Z') {
			a = (char) (a + 20) ;
			System.out.println(a);}
		else if ( a >= 'a' && a <= 'z') {
			a = (char) (a - 20);
			System.out.println(a);
		}
		else
			System.out.println("There are not Alphabet");
	}
}