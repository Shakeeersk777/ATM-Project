import java.util.Scanner;
//interface to hide implementation
interface AtmManagementSystem
{
	void developProject();
}
//class to provide implementation for interface
class Implementation implements AtmManagementSystem
{
	public void developProject()
	{
		Scanner scan = new Scanner(System.in);
        int pin = 123456;

        System.out.println("************ Welcome to Our Bank ************\n");

        System.out.print("Enter PIN Number : ");   // User input for PIN Number
        int customerPin = scan.nextInt();
       
        double balance = 50000.0;	// Default account balance

        if (customerPin == pin)     // if user input matches with PIN
        {
            //options
            System.out.println("1. Check balance");
			System.out.println("2. Deposit");
			System.out.println("3. Withdraw");
			System.out.println("4. Exit");
            System.out.println("Please select any one option :");
            int option = scan.nextInt();

            switch (option) 
			{
                case 1 :    //for balance checking
                {
                    System.out.println("Your current balance in your account : " + balance);
                    tq();
                }
				break;

                case 2 :     //for deposit
                {
                    System.out.print("Enter your deposit amount : ");
                    double deposit = scan.nextInt();
                    double totalAmount = deposit + balance;
                    System.out.println("Your current balance in your account : " + totalAmount);
                    tq();
                }
				break;

                case 3 :    //for withdrawal
                {
                    System.out.print("Enter your withdrawal amount : ");
                    double withdraw = scan.nextInt();
					if (withdraw<100)
					{
						System.out.println("Please enter amount above 100 only");
					}
					else if (withdraw>=balance)
					{
						System.out.println("Insufficient funds");
					}
					else
					{
						double currentAmount = balance - withdraw;
						System.out.println("Your current balance in your account : " + currentAmount);
						tq();
					}
                }
				break;

                case 4 :
                {
                   break;
                }
                default: System.out.println("Please select valid option");
            }
        }
        //if user input not matched then else will be executed
        else
        {
            System.out.println("Invalid PIN");
        }
	}
    // developer defined static method for wish
    public static void tq()
    {
        System.out.println("***** Thank you for using our ATM *****");
    }
}
//mainclass for calling Implementation class
class MainClass
{
    public static void main(String[] args)
    {
        Implementation implementation = new Implementation();
		implementation.developProject();
    }
}
