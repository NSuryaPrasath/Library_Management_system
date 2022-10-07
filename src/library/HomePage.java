package library;

import java.util.Scanner;
import java.sql.*;

public class HomePage 
{
	static Scanner sc=new Scanner(System.in);
	Connection conn=null;
	Statement stmt=null;
	ResultSet rs=null;
	public void adminLogin()
	{
		System.out.print("\nEnter User Name => ");
		String username=sc.next();
		System.out.print("\nEnter Password =>");
		String password=sc.next();
		if(username.equals("Admin"))
		{
			if(password.equals("admin"))
			{
				System.out.println("\nLogged In Successfully");
				startpage();
			}
			else
			{
				System.out.println("\n***PassWord is InCorrect***\nEnter Correct Password");
				adminLogin();
			}
		}
		else
		{
			System.out.println("\n***User Name is InCorrect***\nEnter Correct User Name");
			adminLogin();
		}
	}
	public void startpage()
	{
		Book book=new Book();
		User user=new User();
		BookReturn bookret=new BookReturn();
		BookIssue bookiss=new BookIssue();
		System.out.println("\n       Welcome Admin ");
		System.out.println("\n1 --> Book Details\n2 --> User Details\n3 --> Book Issue \n4 --> Book Return");
		int choice=sc.nextInt();
		switch(choice)
		{
		case 1:book.bookPage();break;
		case 2:user.userPage();break;
		case 3:bookiss.init();break;
		case 4:bookret.init();break;
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HomePage hp=new HomePage();
		hp.adminLogin();
	}
}
