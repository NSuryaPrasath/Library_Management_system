package library;

import java.util.Scanner;
import java.sql.*;

import conncetion.Connectiondatabase;

public class Book
{
	boolean yesno=true;
	Connection conn=Connectiondatabase.getConnect();
	PreparedStatement pst=null;
	ResultSet rs=null;
	Scanner sc=new Scanner(System.in);
	HomePage home=new HomePage();
 	public void bookPage()
	{
		System.out.println("1 --> Insert Book\n2 --> Delete Book\n3 --> Updata Book\n4 --> Search Book\n5 --> Display Books\n6 --> Exit");
		int choice=sc.nextInt();
		switch(choice)
		{
		case 1:bookInsert();break;
		case 2:bookDelete();break;
		case 3:bookUpdate();break;
		case 4:bookSearch();break;
		case 5:bookDisplay();break;
		case 6:home.startpage();
		default:System.out.println("Enter Correct Input");
		        bookPage();
		        break;
		}
	}
	public void bookInsert()
	{
		System.out.println("Enter BooK ID");
		String bookId=sc.next();
		System.out.println("Enter Book Name");
		sc.nextLine();
		String bookName=sc.next();
		System.out.println("Enter Author Name");
		String author=sc.nextLine();
		try
		{
			conn=Connectiondatabase.getConnect();
			String str="insert into book(bookid,bookname,author)"+"values("+"'"+bookId+"',"+"'"+bookName+"','"+author+"')";
			pst=conn.prepareStatement(str);
			pst.execute();
			System.out.println("Inserted Succesfully");
			while(yesno) 
			{
				System.out.println("\nDo you want to 'Add' Another Book ? Press(Y/N)");
				char yesOrNo=sc.next().charAt(0);
				if(yesOrNo=='y' || yesOrNo=='Y')
				{
					bookInsert();
					yesno=false;
				}
				else if(yesOrNo=='n' || yesOrNo=='N')
				{
					bookPage();
					yesno=false;
				}
				else
				{
					System.out.println("Enter Y or N");
					yesno=true;
				}
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	public void bookDelete()
	{
		System.out.println("Enter the Book Id");
		String bookId=sc.next();
		try
		{
			conn=Connectiondatabase.getConnect();
			String str="delete from book where bookid='"+bookId+"'";
			pst=conn.prepareStatement(str);
			pst.execute();
			System.out.println("Deleted Sucessfully");
			while(yesno)
			{
				System.out.println("Do you want to Delete another book (Y/N");
				char delete=sc.next().charAt(0);
				if(delete=='y' || delete=='Y')
				{
					bookDelete();
					yesno=false;
				}
				else if(delete=='n' || delete=='N')
				{
					bookPage();
					yesno=false;
				}
				else
				{
					System.out.println("Enter Y or N");
					yesno=true;
				}
			}
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
	}
	public void bookUpdate()
	{
		System.out.println("Enter the Book Id");
		String bookId=sc.next();
		particularbook(bookId);
		System.out.println("Do you want to Change the data give 'Y' to respectively columns.Otherwise 'N'");
		System.out.print("Book Name = ");
		char bn=sc.next().charAt(0);
		if(bn=='y' || bn=='Y')
		{
			System.out.println("Enter the Book Name ");
			sc.nextLine();
			String bookname=sc.next();
			try
			{
				String str="update book set bookname=+'"+bookname+"' where bookid='"+bookId+"'";
				pst=conn.prepareStatement(str);
				pst.execute();
				System.out.println("Updated Successfully");
			}
			catch(Exception e)
			{
				System.out.println(e.toString());
			}
		}
		System.out.println("Change Author Name Press(Y/N) ");
		char an=sc.next().charAt(0);
		if(an=='y' || an=='Y')
		{
			System.out.println("Enter the Author Name ");
			sc.nextLine();
			String authorname=sc.next();
			try
			{
				String str="update book set bookname=+'"+authorname+"' where bookid='"+bookId+"'";
				pst=conn.prepareStatement(str);
				pst.execute();
				System.out.println("Updated Successfully");
			}
			catch(Exception e)
			{
				System.out.println(e.toString());
			}
		}
		while(yesno)
		{
			System.out.println("Do you want to update another book (Y/N");
			char update=sc.next().charAt(0);
			if(update=='y' || update=='Y')
			{
				bookUpdate();
				yesno=false;
			}
			else if(update=='n' || update=='N')
			{
				bookPage();
				yesno=false;
			}
			else
			{
				System.out.println("Enter Y or N");
				yesno=true;
			}
		}
	}
	public void particularbook(String bookId)
	{
		try
		{
			//conn=Connectiondatabase.getConnect();
			String str="select * from book where bookid='"+bookId+"'";
			pst=conn.prepareStatement(str);
			rs=pst.executeQuery();
			while(rs.next())
			{
				System.out.println(rs.getString("bookid")+" "+rs.getString("bookname")+" "+rs.getString("author"));
			}
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
	}
	public void bookSearch()
	{
		System.out.println("\nEnter Book ID");
		String bookId=sc.next();
		try
		{
			//conn=Connectiondatabase.getConnect();
			String str="select * from book where bookid='"+bookId+"'";
			pst=conn.prepareStatement(str);
			rs=pst.executeQuery();
			while(rs.next())
			{
				System.out.println(rs.getString("bookid")+" "+rs.getString("bookname")+" "+rs.getString("author"));
			}
			while(yesno) 
			{
				System.out.println("Do You want to search another book (Y/N)");
				char search=sc.next().charAt(0);
				if(search=='y' || search=='Y')
				{
					bookSearch();
					yesno=false;
				}
				else if(search=='n' || search=='N')
				{
					bookPage();
					yesno=false;
				}
				else
				{
					System.out.println("Enter Y or N");
					yesno=true;
				}
			}
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
	}
	public void bookDisplay()
	{
		try
		{
			conn=Connectiondatabase.getConnect();
			String str="select * from book order by bookid";
			pst=conn.prepareStatement(str);
			rs=pst.executeQuery();
			while(rs.next())
			{
				System.out.println("Book Id  |  Book Name");
				System.out.println(rs.getString("bookid")+" "+rs.getString("bookname")+" "+rs.getString("author"));
			}
			while(yesno)
			{
				System.out.println("1 --> Exit");
				int exit=sc.nextInt();
				if(exit==1)
				{
					bookPage();
					yesno=false;
				}
				else
				{
					System.out.println("Enter Correct Value");
					yesno=true;
				}
			}
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
	}
}
