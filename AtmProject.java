package com.bank.atm;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
//INTERFACE WITH ABSTRACT NON-STATIC METHOD
public interface AtmManagementSystem
{
	void developProject() throws SQLException, InputMismatchException;
}
//MAIN MATHOD TO START EXECUTION
class MainClass 
{
	public static void main(String[] args)
	{
		AtmManagementSystem rv = new Implementation();		
		
		try 
		{
			rv.developProject();
		} 
		catch (InputMismatchException e) 
		{
			e.getMessage();
		} 
		catch (SQLException e) 
		{
			e.getMessage();
		}
		 
		
	}
}
//IMPLEMENTATION CLASS FOR AtmManagementSystem INTERFACE
class Implementation implements AtmManagementSystem
{
	private static String url="jdbc:mysql://localhost:3306?user=root&password=12345";
	
	public void developProject() throws SQLException, InputMismatchException
	{
		System.out.println("************ Welcome to Our Bank ************");
		//To connect java application with database application
		Connection connection=DriverManager.getConnection(url);
		//To verify user pin with user entered pin
		//Calling Storing procedure from database
		CallableStatement callableStatement=connection.prepareCall("CALL shakeerbank_database.RetreiveDataFromAcctHolderUsing_pin(?)");       
        // User input for PIN Number
		Scanner scan = new Scanner(System.in);
        System.out.print("Enter PIN Number : ");
        int customerPin = scan.nextInt();
        //assigning user input for place holders	
        callableStatement.setInt(1, customerPin);
        //execute query	
        ResultSet resultSet=callableStatement.executeQuery();
        //if last record present
        if(resultSet.last())
        {
        	//to come cursor from last position to bfr position
        	resultSet.beforeFirst();
        	while(resultSet.next())
       		{
       			Implementation i=new Implementation();
                         
                //options
                System.out.println("1. Check balance");
                System.out.println("2. Deposit");
           		System.out.println("3. Withdraw");
    			System.out.println("4. Set New Pin");
            	System.out.println("5. Exit");
                System.out.println("Please select any one option :");
                      
                int option = scan.nextInt();
              	switch (option) 
              	{
              		//for balance checking
              		case 1 :    
                    {
                                	
                    	i.getBalance();
                        tq();
                    }
                    break;
                    //for deposit
                    case 2 :     
                    {
                        i.depositAmount();
                        tq();
                    }
                    break;
                    //for withdrawal
                    case 3 :    
                    {
                        i.withdrawalAmount();
                        tq();
                    }
                    break;
                	//to set new pin			
                    case 4 :
                    {
                        i.setPin();
                    }
                    break;
                    //to exit jvm        
                    case 5 :
                    {
                        System.exit(0);
                    }
                    default: System.err.println("Please select valid option");
                 }
       		}
        }
        else
        {
        	System.err.println("Data not found....");
        }
        scan.close();
    }	
	//Balance enquiry implementation
	public void getBalance() throws SQLException, InputMismatchException
	{	
		//To connect java application with database application
		Connection connection=DriverManager.getConnection(url);
		//To verify user pin with user entered pin
		//Calling Storing procedure from database
		CallableStatement callableStatement=connection.prepareCall("CALL shakeerbank_database.RetreiveDataFromAcctHolder");
        	
        ResultSet resultSet=callableStatement.executeQuery();
        	
        if(resultSet.last())
        {
        	resultSet.beforeFirst();
        	while(resultSet.next())
       		{
        		System.out.println("Your current balance in your account : " + resultSet.getDouble("balance"));
       		}
        }
        else
        {
        	System.err.println("Data not found....");
        }       
	}
	
	//Deposit Amount Implementation
	public void depositAmount() throws SQLException, InputMismatchException
	{
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Enter your deposit amount : ");
        double deposit = scan.nextDouble();
			
	    if (deposit>=100)
		{
	    	Connection connection = DriverManager.getConnection(url);
	    	//To verify user pin with user entered pin
			//Calling Storing procedure from database
	    	CallableStatement callableStatement=connection.prepareCall("CALL shakeerbank_database.RetreiveDataFromAcctHolder");
	        
		    ResultSet resultSet=callableStatement.executeQuery();
		    
		    if(resultSet.last())
	        {
	        	resultSet.beforeFirst();
	        	while(resultSet.next())
	       		{	    	
	        		CallableStatement callableStatement2=connection.prepareCall("CALL shakeerbank_database.UpdateBalance(?,?)");
	        		
	        		double totalAmount= deposit + resultSet.getDouble("balance");
	        		
	        		callableStatement2.setDouble(1, totalAmount);
		        	int pin=resultSet.getInt("pin");
		        	
					callableStatement2.setInt(2, pin); 
					
					callableStatement2.executeUpdate();	
	    	 
					System.out.println("Amount successfully deposited");
		     	       		    		    	        		    		    				    			    						 		    				
					//Storing transactions in database
		    		
					Date date = new Date();
					String currentDate=date.toString();
					
					CallableStatement callableStatement3=connection.prepareCall("CALL shakeerbank_database.StoringTransactions(?)");
		    				
					
					callableStatement3.setString(1, currentDate);
	    				
					String transaction=deposit+" deposited into your bank account";
					
					callableStatement3.setString(1, transaction);
					
					callableStatement3.executeUpdate();
	       		}
	        }
		}		        
	    else
		{
			System.err.println("Please deposit more than 100 Rupees only");
		}
	    scan.close();
	  }
	          
	//Amount Withdrawal Implementation
	public void withdrawalAmount() throws SQLException, InputMismatchException
	{
		Scanner scan = new Scanner(System.in);
		//To connect java application with database application
		Connection connection = DriverManager.getConnection(url);
		//To verify user pin with user entered pin
		//Calling Storing procedure from database
		CallableStatement callableStatement=connection.prepareCall("CALL shakeerbank_database.RetreiveDataFromAcctHolder");
        
	    ResultSet resultSet=callableStatement.executeQuery();
	    
	    if(resultSet.last())
        {
	    	double balance=resultSet.getDouble("balance");
        	resultSet.beforeFirst();
        	while(resultSet.next())
       		{
        		System.out.print("Enter your withdrawal amount : ");
    	        double withdraw = scan.nextDouble();
    	            	      	            	        
    	        if (withdraw<100)
				{
					System.err.println("Please enter amount above 100 only");
				}
				else if (withdraw>balance)
				{
					System.err.println("Insufficient funds");
				}
				else if(withdraw%2==0)
				{
					CallableStatement callableStatement2=connection.prepareCall("CALL shakeerbank_database.UpdateBalance(?,?)");
		            
		        	double totalAmount= resultSet.getDouble("balance") - withdraw;
		        	
		        	callableStatement2.setDouble(1, totalAmount);
		        	int pin=resultSet.getInt("pin");
		        	
					callableStatement2.setInt(2, pin); 
					
					callableStatement2.executeUpdate();	
					
					System.out.println("Your current balance in your account : " + totalAmount);
				}
				else
				{
					System.err.println("Please valid amount only");
				}
       		}
        }
	    else
	    {
	        System.err.println("Invalid PIN...");
	    }
		scan.close();
	}	
    
    //to Set new pin
    public void setPin() throws SQLException, InputMismatchException
    {
		
    	//To connect java application with database application
		Connection connection=DriverManager.getConnection(url);
		//To verify user pin with user entered pin
		//Calling Storing procedure from database
		CallableStatement callableStatement=connection.prepareCall("CALL shakeerbank_database.RetreiveDataFromAcctHolderUsing_pin_mobile(?,?)");
		
		Scanner scanner=new Scanner(System.in);
		System.out.println("Enter Old PIN : ");
		int pin=scanner.nextInt();
		callableStatement.setInt(1, pin);
		
		System.out.println("Enter your mobile number : ");
		String mobile=scanner.next();
		callableStatement.setString(2, mobile);
		
		ResultSet resultSet=callableStatement.executeQuery();
		
		if(resultSet.last())
		{
			resultSet.beforeFirst();
			while(resultSet.next())
			{
				Random random=new Random();
				int num=random.nextInt(10000);
				if(num<1000)
				{
					num+=1000;
				}		
				System.out.println("Otp : "+num);
				System.out.println("Enter Otp: ");
				int userOtp=scanner.nextInt();
				
				if(userOtp==num) 
				{
					System.out.println("Otp verified");
					
					CallableStatement callableStatement2=connection.prepareCall("CALL shakeerbank_database.UpdatePin(?,?)");
				    
					System.out.println("Enter New PIN : ");
				    int newPin=scanner.nextInt();				    
				    
				    callableStatement2.setInt(1, newPin);
				    callableStatement2.setString(2, mobile);			
				    		
				    callableStatement2.executeUpdate();				    
				    System.out.println("Pin is successfully updated. Please contact 1234567890 incase you not done ");	
				}
				else
				{
					System.err.println("Invalid Otp");
				}
			}
		}
		else
		{
			System.err.println("Data doesn't exist..");
		}
		scanner.close();
    }
    // developer defined static method for wish
    public static void tq()
    {
        System.out.println("***** Thank you for visiting our ATM *****");
    }
}
