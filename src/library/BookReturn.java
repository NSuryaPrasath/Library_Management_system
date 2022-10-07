package library;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import conncetion.Connectiondatabase;

public class BookReturn 
{
	Scanner sc=new Scanner(System.in);
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	Date date = new Date();
    String retdate = formatter.format(date);
	//LocalDate retdate=java.time.LocalDate.now();
    DateFormat dateFormat = new SimpleDateFormat("hh.mm aa");
	String rettime = dateFormat.format(new Date()).toString();
	Connection conn=Connectiondatabase.getConnect();
	PreparedStatement pst=null;
	ResultSet rs=null;
	public void init()
	{
		System.out.println("*******Book Return*******");
		System.out.println("\n1 --> Return Book\n2 --> Display\n3 --> Delete Particular");
		int choice=sc.nextInt();
		switch(choice)
		{
		case 1:get();break;
		case 2:display();break;
		case 3:delete();break;
		default:System.out.println("Enter correct choice ");break;
		}
	}
	public void get()
	{
		try
		{
			System.out.println("Enter the User Id");
			String userId=sc.next();
			System.out.println("Enter the Book Id");
			String bookId=sc.next();
			String bookName=null,userName=null,issuedate=null;
			long fine;
			String str="select * from book where bookid='"+bookId+"'";
			pst=conn.prepareStatement(str);
			rs=pst.executeQuery();
			while(rs.next())
			{
				bookName=rs.getString("bookname");
			}
			String str1="select * from user where userid='"+userId+"'";
			pst=conn.prepareStatement(str1);
			rs=pst.executeQuery();
			while(rs.next())
			{
				userName=rs.getString("username");
			}
			String str2="select * from bookissue where bookId='"+bookId+"'";
			pst=conn.prepareStatement(str2);
			rs=pst.executeQuery();
			while(rs.next())
			{
				issuedate=rs.getString("issuedate");
			}
			//DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			//SimpleDateFormat dtf = new SimpleDateFormat("dd/MM/yyyy");
			Date date1 = formatter.parse(issuedate);
		    Date date2 = formatter.parse(retdate);
		    long diff=date2.getTime()-date1.getTime();
			//long daysBetween = Duration.between(issuedate, returndate).toDays();
		    if(diff>15)
		    {
		    	fine=(diff*10);
		    }
		    else
		    {
		    	fine=0;
		    }
			String in="insert into bookreturn(bookid,bookname,userid,username,issuedate,returndate,returntime,fine) values('"+bookId+"','"+bookName+"','"+userId+"','"+userName+"','"+issuedate+"','"+retdate+"','"+rettime+"','"+fine+"')";
			pst=conn.prepareStatement(in);
			pst.execute();
			System.out.println(userName+" returned successfully");
			init();
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
	}
	public void display()
	{
		try
		{
			String str="select * from bookreturn";
			pst=conn.prepareStatement(str);
			rs=pst.executeQuery();
			while(rs.next())
			{
				System.out.println(rs.getString("bookid")+" "+rs.getString("bookname")+" "+rs.getString("userid")+" "+rs.getString("username")+" "+rs.getString("issuedate")+" "+rs.getString("returndate")+" "+rs.getString("returntime")+" "+rs.getString("fine"));
			}
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
		init();
	}
	public void delete()
	{
		System.out.println("Enter the User Id");
		String userId=sc.next();
		try
		{
			String str="delete from bookreturn where userid='"+userId+"'";
			pst=conn.prepareStatement(str);
			pst.execute();
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
		init();
	}
}
