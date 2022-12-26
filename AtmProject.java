package atm_project;
import java.util.Scanner;
//interface to hide implementation
interface AtmManagementSystem
{
	void developProject();
}
//class to provide implementation for interface
class Implementation implements AtmManagementSystem 
{
	private double balance = 50000.0;	// Default account balance
	private int pin = 123456;

	public void developProject()
	{
		Scanner scan = new Scanner(System.in);
        int pin = 123456;

        System.out.println("************ Welcome to Our Bank ************\n");

        System.out.println("************ Welcome to Our Bank ************");

        System.out.print("Enter PIN Number : ");   // User input for PIN Number

        int customerPin = scan.nextInt();

        double balance = 50000.0;	// Default account balance

        if (customerPin == pin)     // if user input matches with PIN

        Implementation i=new Implementation();

        // if user input matches with PIN
        if (customerPin == pin)     
        {
            //options
            System.out.println("1. Check balance");
			System.out.println("2. Deposit");
			System.out.println("3. Withdraw");
			System.out.println("4. Exit");
			System.out.println("4. Set New Pin");
			System.out.println("5. Exit");
            System.out.println("Please select any one option :");
            int option = scan.nextInt();

            switch (option) 
			{
                case 1 :    //for balance checking
                {
                    System.out.println("Your current balance in your account : " + balance);
                	i.getBalance();
                    tq();
                }
				break;

                case 2 :     //for deposit
                {
                    System.out.print("Enter your deposit amount : ");
                    double deposit = scan.nextInt();
                    double totalAmount = deposit + balance;
                    System.out.println("Your current balance in your account : " + totalAmount);
                	System.out.println("Enter deposit amount: ");
                	double deposit = scan.nextInt();
                    i.depositAmount(deposit);
                    tq();
                }
				break;

                case 3 :    //for withdrawal
                {
                    System.out.print("Enter your withdrawal amount : ");
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
                    i.withdrawalAmount(withdraw);
                    tq();
                }
				break;

				
                case 4 :
                {
                   break;
                	System.out.println("Enter Two Step Verfication password to set new pin: ");
                    int securityPin=scan.nextInt();
                    i.setPin(securityPin);
                }
                break;

                case 5 :
                {
                   System.exit(0);
                }
                default: System.out.println("Please select valid option");
                default: System.err.println("Please select valid option");
            }
        }
        //if user input not matched then else will be executed
        else
        {
            System.out.println("Invalid PIN");
            System.err.println("Invalid PIN");
        }
        scan.close();
	}
	//Balance Enquiry implementation
	public void getBalance()
	{
		System.out.println("Your current balance in your account : " + balance);
	}
	//Deposit Amount Implementation
	public void depositAmount(double deposit)
	{
		if (deposit>=100)
		{
	        double totalAmount = deposit + balance;
	        System.out.println("Your current balance in your account : " + totalAmount);
		}
		else
		{
			System.out.println("Please deposit more than 100 Rupees only");
		}
	}
	//Amount Withdrawal Implementation
	public void withdrawalAmount(double withdraw)
	{

		if (withdraw<100)
		{
			System.err.println("Please enter amount above 100 only");
		}
		else if (withdraw>balance)
		{
			System.err.println("Insufficient funds");
		}
		else
		{
			double currentAmount = balance - withdraw;
			System.out.println("Your current balance in your account : " + currentAmount);
		}
	}
    // developer defined static method for wish
    public static void tq()
    {
        System.out.println("***** Thank you for using our ATM *****");
        System.out.println("***** Thank you for visiting our ATM *****");
    }
    //to Set new pin
    public void setPin(int securityPin)
    {
    	Scanner scan = new Scanner(System.in);
    	int twoStepVerificationPin=1111;
    	if(securityPin==twoStepVerificationPin)
    	{
    		System.out.println("Enter new Pin: ");
    		int newPin=scan.nextInt();
    		this.pin=newPin;
    		System.out.println("Pin is successfully updated. Please contact 1234567890 incase you not done ");
    	}
    	else
    	{
    		System.err.println("Two Step Verfication password not matched");
    	}
    	scan.close();
    }
}
//mainclass for calling delevopProject() method
class MainClass
{
    public static void main(String[] args)
    {
        Implementation implementation = new Implementation();
		implementation.developProject();
    }
}
