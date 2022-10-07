package library;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import conncetion.Connectiondatabase;

public class BookIssue 
{
	Scanner sc=new Scanner(System.in);
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	Date date = new Date();
    String todaydate = formatter.format(date);
    DateFormat dateFormat = new SimpleDateFormat("hh.mm aa");
	String dateString = dateFormat.format(new Date()).toString();
	Connection conn=Connectiondatabase.getConnect();
	PreparedStatement pst=null;
	ResultSet rs=null;
	public void init()
	{
		System.out.println("******BOOK ISSUE********");
		System.out.println("\n1 --> Book Issue\n2 --> Display\n3 --> Delete");
		int choice=sc.nextInt();
		switch(choice)
		{
		case 1:bookissue();break;
		case 2:display();break;
		case 3:delete();break;
		default:System.out.println("Enter correct Input :");init();break;
		}
	}
	public void bookissue()
	{
		System.out.println("\nEnter User Id");
		String userId=sc.next();
		System.out.println("\nEnter Book Id");
		String bookId=sc.next();
    	try
    	{
    		String bookName=null,userName=null;
    		String str1="select * from book where bookid='"+bookId+"'";
    		pst=conn.prepareStatement(str1);
    		rs=pst.executeQuery();
    		while(rs.next())
    		{
    			bookName=rs.getString("bookname");
    		}
    		String str2="select * from user where userid='"+userId+"'";
    		pst=conn.prepareStatement(str2);
    		rs=pst.executeQuery();
    		while(rs.next())
    		{
    			userName=rs.getString("username");
    		}
			String str="insert into bookissue(userid,bookname,bookid,issuedate,issuetime,username)"+"values('"+userId+"','"+bookName+"','"+bookId+"','"+todaydate+"','"+dateString+"','"+userName+"')";
			pst=conn.prepareStatement(str);
			pst.execute();
			System.out.println("\nBook Issued Successfully");
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
			String str="select * from bookissue";
			pst=conn.prepareStatement(str);
			rs=pst.executeQuery();
			while(rs.next())
			{
				System.out.println(rs.getString("userid")+" "+rs.getString("username")+" "+rs.getString("bookname")+" "+rs.getString("issuedate")+" "+rs.getString("issuetime"));
			}
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
	}
	public void delete()
	{
		try
		{
			System.out.println("Enter the Book Id");
			String bookId=sc.next();
			String str="delete from bookissue where bookid='"+bookId+"'";
			pst=conn.prepareStatement(str);
			pst.execute();
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
	}
}
