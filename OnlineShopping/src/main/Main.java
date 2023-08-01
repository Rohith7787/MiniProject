package main;

import java.util.Scanner;

public class Main extends Sql{
	
	private static String port = "3306";
	private static String databaseName = "OSS";
	private static String userName = "root";
	private static String password = "tiger";
	private static int id ; 
	public static void main(String[] args) 
	{
		Scanner input = new Scanner(System.in);
		boolean loop = true;
		boolean status = false;
		while(loop) 
		{
			System.out.println("\n==================================================");
			System.out.println("\t\tLogin");
			System.out.println("==================================================");
			System.out.println("(1) SignIn");
			System.out.println("(2) SignUp");
			System.out.print("Enter option: ");
			String option = input.next(); 
			Sql.LoadingMessage();
			
			
			if( option.equals("1") )
			{
				Login login = new Login(port , databaseName , userName , password) ;
				int result = login.mainLogin() ;
				if( result != 0 )
				{
					id = result ;
					System.out.println("Sucessful");
					status = true ;
				}
				else 
					continue ;
			}
			else if( option.equals("2") )
			{
				SignUp signup = new SignUp(port , databaseName , userName , password) ;
				signup.mainSignUp() ;
				continue ;
			}
			else 
			{
				System.out.println("Invalid Option ");
				continue ;
			}

			if( status )
				break ;
			System.out.println("Invalid UserName or Password ");

		}
		
		loop = true;
		while(loop) 
		{
			System.out.println("\n==================================================");
			System.out.println("\nOnline Shopping");
			System.out.println("==================================================");
//			System.out.println("~~ Enter \"X\" for update connection info");
			System.out.println("(1) Add / Remove Products");
//			System.out.println("(2) Survey");
//			System.out.println("(3) Insert Expense");
//			System.out.println("(4) Delete Expense");
//			System.out.println("(0) Exit");
			System.out.print("Enter option: ");
			String num = input.next();
			Sql.LoadingMessage();
			
			if(num.equals("1")) { 
				Insert insert = new Insert(port, databaseName, userName, password);
				insert.mainInsert( id );
			}
				
			
//			if(num.equals("X") || num.equals("x"))
//				updateConnection();
//			else if(num.equals("1")) {
//				Select select = new Select(port, databaseName, userName, password);
//				Select select = new Select(port , databaseName , userName , password) ;
//				select.mainSelect( id ) ;
//				continue ;
//				
//			}
//			else if(num.equals("2")) {
//				Survey survey = new Survey(port , databaseName , userName , password) ;
//				survey.mainSurvey( id ) ;
//			}
//			else if(num.equals("3")) { 
//				Insert insert = new Insert(port, databaseName, userName, password);
//				insert.mainInsert( id );
//			}
//			else if(num.equals("4")) {
//				Delete delete = new Delete(port, databaseName, userName, password);
//				delete.mainDelete(id);
//				System.out.println("Option : 4 ");
//			}
//			else if(num.equals("0")) 
//				loop = false;
//			else
//				loop = true;
		}	
		System.out.println("\n----------------------[Exit]----------------------\nProgram terminated");	
	}
	
	public static void updateConnection() 
	{
		Scanner input = new Scanner(System.in);
		
		System.out.println("\n=================[connection info]=================");
		System.out.print("Enter Port: ");
	    port = input.next();
		System.out.print("Enter database name: ");
		databaseName = input.next();
		System.out.print("Enter User name: ");
		userName = input.next();
		System.out.print("Enter password for Service: ");
	    password = input.next();
	}

}

	