public class Account {
	int cash = 0;
	String name, accoutNumber;
	int total;
	static int number;
	
	void deposit(int cash01) {
		total = cash+cash01;
		System.out.println("Deposited. The remaining amount is "+total);
		}
	
	void withdraw(int cash02) {
		if(cash02 > total) {
			System.out.println("Lack of Money. The remaining amout is "+total);
		}
		else {
			total = total - cash02;
			System.out.println(cash02+"is withdrawed. The remaining amout is "+total);
		}
	}
	
	void transfer(Account acc2, int cash03) {
		if(cash03 > total) {
			System.out.println("Lack of Money. The remaining amout is "+total);
		}
		else {
			total = total - cash03;
			System.out.println(cash03+" is sent to "+acc2+". The remaining amout is "+total);
		}
	}
	
	void printinfo() {
		System.out.println("Account Name : "+name);
		System.out.println("Accout Number : "+accoutNumber);
		System.out.println("Current Amount : "+total);
	}

	Account(String name, int total){
		this.name = name;
		this.total = total;
		accoutNumber = "20170925"+number;
		number++;
	}
}
