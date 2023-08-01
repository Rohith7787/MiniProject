package main;

import java.sql.Date;
import java.util.Scanner;

public class Insert extends Sql{

	public Insert() {
		// TODO Auto-generated constructor stub
	}
	
	public Insert(String port, String databaseName, String userName, String password) {
		super(port, databaseName, userName, password);
	}
	public void mainInsert( int id )
	{
		Scanner input = new Scanner(System.in);
		boolean loop = true ;
		while( loop )
		{
			System.out.println("\n==================================================");
			System.out.println("\tInsert");
			System.out.println("==================================================");
			System.out.println("(1) Add Products");
			System.out.println("(2) Delete Product");
			System.out.println("(3) Homepage");
			System.out.print("Enter option: ");
			String option = input.next(); 
			Sql.LoadingMessage();
			
			
			if( option.equals("1") )
			{
				System.out.println("\n---------------------[Products]---------------------\n");
				System.out.print("Enter Product Name : ");
				String product_name = input.next() ;
				System.out.print("Enter Price : ");
				int price = Integer.parseInt(input.next()) ;
				boolean status = insertExpense( product_name , price , id ) ;
				if( status )
				{
					System.out.println( "Product Added " ) ;
					continue ;
				}
				else
				{
					System.out.println( "Invalid Input" ) ;
					continue ;
				}
			}
			else if( option.equals("2") )
			{	
				try {
					String query = "select product_id, name, price from product where user_id = ? ;" ;
					preparedStatement = connect.prepareStatement(query) ;
					preparedStatement.setInt(1, id);
					resultSet = preparedStatement.executeQuery() ;
					while(resultSet.next())
					{
					  //  System.out.print(9);
						System.out.println(resultSet.getInt(1)+ "\t\t" +resultSet.getString(2)+"\t\t"+resultSet.getInt(3));
					}
					System.out.println("\n---------------------[End]-----------------------\n");
					
					System.out.print( "Enter Id of Product need to be Delete : " ) ;
					int val = Integer.parseInt(input.next()) ;
					DeleteVal( val , "product" , id) ;
					return ;
					
				}
				catch(Exception ex) { 
					System.out.println(ex);
					return ; 
				}
				
//				System.out.println("\n---------------------[Income]---------------------\n");
//				System.out.print("Enter Amount : ");
//				double amount = Double.parseDouble(input.next()) ;
//				System.out.print("Enter Date(dd/mm/yyyy) : ");
//				String date = input.next() ;
//				System.out.print("Enter Description : ");
//				String desc = input.next() ;
//				boolean status = insertIncome( id , amount , date , desc ) ;
//				if( status )
//				{
//					System.out.println( "Income Added " ) ;
//					continue ;
//				}
//				else
//				{
//					System.out.println( "Invalid Input" ) ;
//					continue ;
//				}
			}
			else if( option.equals("3") )
			{
				return ;
			}
			else 
			{
				System.out.println("Invalid Option ");
				continue ;
			}	
//			
		}
		input.close() ;
 		return ;
	}
	public boolean insertExpense(String product_name , int price , int id )
	{
		try {
			String query = "INSERT INTO product (name, price,user_id) VALUES (?,?,?)" ;
			preparedStatement = connect.prepareStatement(query) ;
			preparedStatement.setString(1, product_name);
			preparedStatement.setInt(2, price);
			preparedStatement.setInt(3, id );

			//			preparedStatement.setDouble(2, amount);
//			preparedStatement.setString(4, desc);
			int val = preparedStatement.executeUpdate() ;
//			System.out.println("Rows affected : " + val);
			return true ;
			
		}
		catch(Exception ex) { 
			System.out.println(ex);
			return false ; 
		}
		
		
	}
	
	public void DeleteVal(int id , String type , int user)
	{
		try {
			Scanner input = new Scanner(System.in);
			System.out.print( "Enter your password for security : " ) ;
			String password = input.next();
			String query = "delete x from " + type + " x join users u on x.user_id = u.user_id  where x."+ type + "_id = " + id + " && u.user_id = "+ user + " && u.pass = " + password + " ;" ;
			int val = statement.executeUpdate(query) ;
//			System.out.println( val ) ;
			if(  val != 0 )
				System.out.println("Succesfully Deleted");
			else
				System.out.println("Select valid Id");
		}
		catch( Exception ex )
		{
			System.out.println(ex);
			return ;
		}
	}
	
	
//	public boolean insertIncome(int id , double amount , String date , String desc )
//	{
//		try {
//			String query = "INSERT INTO income (user_id, amount, income_date , income_desc) VALUES (?,?,?,?)" ;
//			preparedStatement = connect.prepareStatement(query) ;
//			preparedStatement.setInt(1, id);
//			preparedStatement.setDouble(2, amount);
//			preparedStatement.setString(3, date);
//			preparedStatement.setString(4, desc);
//			int val = preparedStatement.executeUpdate() ;
////			System.out.println("Rows affected : " + val);
//			return true ;
//			
//		}
//		catch(Exception ex) { 
////			System.out.println(ex);
//			return false ; 
//		}
//	}
	
}
