public class AccountTester
{
	public static void	main(String[] args)
	{
		Account	acc1 = new Account("Hong Gil-Dong", 1000);
		Account acc2 = new Account("Kim Chul-Su", 2000);
		
		acc1.deposit(2000);
		acc1.transfer(acc2, 2000);
		
		acc1.printinfo();
		acc2.printinfo();
		
		acc1.withdraw(2000);
	}
}