package library;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import conncetion.Connectiondatabase;

public class User 
{
	boolean yesno=true;
	Connection conn=Connectiondatabase.getConnect();
	PreparedStatement pst=null;
	ResultSet rs=null;
	Scanner sc=new Scanner(System.in);
	HomePage home=new HomePage();
 	public void userPage()
	{
		System.out.println("1 --> Add User\n2 --> Delete User\n3 --> Update User\n4 --> Search User\n5 --> Display All Users\n6 --> Back");
		int choice=sc.nextInt();
		switch(choice)
		{
		case 1:insertUser();break;
		case 2:deleteUser();break;
		case 3:updateUser();break;
		case 4:searchUser();break;
		case 5:allUser();break;
		case 6:home.startpage();
		default:System.out.println("Enter Correct Input");
		        userPage();
		        break;
		}
	}
 	public void insertUser()
 	{
 		System.out.println("Enter User ID");
		String userId=sc.next();
		System.out.println("Enter user Name");
		sc.nextLine();
		String userName=sc.nextLine();
		try
		{
			//conn=Connectiondatabase.getConnect();
			String str="insert into user(userid,username)"+"values("+"'"+userId+"',"+"'"+userName+"')";
			pst=conn.prepareStatement(str);
			pst.execute();
			System.out.println("Inserted Succesfully");
			while(yesno) 
			{
				System.out.println("\nDo you want to 'Add' Another User ? Press(Y/N)");
				char yesOrNo=sc.next().charAt(0);
				if(yesOrNo=='y' || yesOrNo=='Y')
				{
					insertUser();
					yesno=false;
				}
				else if(yesOrNo=='n' || yesOrNo=='N')
				{
					userPage();
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
 	public void deleteUser()
 	{
 		System.out.println("Enter the User Id");
		String userId=sc.next();
		try
		{
			//conn=Connectiondatabase.getConnect();
			String str="delete from user where userid='"+userId+"'";
			pst=conn.prepareStatement(str);
			pst.execute();
			System.out.println("Deleted Sucessfully");
			while(yesno)
			{
				System.out.println("Do you want to Delete another User (Y/N");
				char delete=sc.next().charAt(0);
				if(delete=='y' || delete=='Y')
				{
					deleteUser();
					yesno=false;
				}
				else if(delete=='n' || delete=='N')
				{
					userPage();
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
 	public void updateUser()
 	{
 		System.out.println("Enter the User Id");
		String userId=sc.next();
		particularuser(userId);
		System.out.println("Do you want to Change the data give 'Y' to respectively columns.Otherwise 'N'");
		System.out.print("User Name = ");
		char un=sc.next().charAt(0);
		if(un=='y' || un=='Y')
		{
			System.out.println("Enter the User Name ");
			sc.nextLine();
			String userName=sc.next();
			try
			{
				//conn=Connectiondatabase.getConnect();
				String str="update user set username=+'"+userName+"' where userid='"+userId+"'";
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
				updateUser();
				yesno=false;
			}
			else if(update=='n' || update=='N')
			{
				userPage();
				yesno=false;
			}
			else
			{
				System.out.println("Enter Y or N");
				yesno=true;
			}
		}
 	}
 	public void particularuser(String userId)
 	{
 		try
		{
			//conn=Connectiondatabase.getConnect();
			String str="select * from user where userid='"+userId+"'";
			pst=conn.prepareStatement(str);
			rs=pst.executeQuery();
			while(rs.next())
			{
				System.out.println(rs.getString("userid")+" "+rs.getString("username"));
			}
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
 	}
 	public void searchUser()
 	{
 		int count=0;
 		System.out.println("\nEnter User ID");
		String userId=sc.next();
		try
		{
			//conn=Connectiondatabase.getConnect();
			String str="select * from user where userid='"+userId+"'";
			pst=conn.prepareStatement(str);
			rs=pst.executeQuery();
			while(rs.next())
			{
				System.out.println(rs.getString("userid")+" "+rs.getString("username"));
			}
			ischeckempty(count);
			while(yesno) 
			{
				System.out.println("Do You want to search another User Detail (Y/N)");
				char search=sc.next().charAt(0);
				if(search=='y' || search=='Y')
				{
					searchUser();
					yesno=false;
				}
				else if(search=='n' || search=='N')
				{
					userPage();
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
 	public void allUser()
 	{
 		int count=0;
 		try
		{
			conn=Connectiondatabase.getConnect();
			String str="select * from user order by userid";
			pst=conn.prepareStatement(str);
			rs=pst.executeQuery();
			while(rs.next())
			{
				System.out.println("User Id  |  User Name");
				System.out.println(rs.getString("userid")+" "+rs.getString("username"));
				count++;
			}
			ischeckempty(count);
			while(yesno)
			{
				System.out.println("1 --> Exit");
				int exit=sc.nextInt();
				if(exit==1)
				{
					userPage();
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
 	public void ischeckempty(int count)
 	{
 		boolean insert=true;
 		if(count==0)
		{
			System.out.println("No Record");
			System.out.println("Do you want to add user? Press...(y/n)");
			char input=sc.next().charAt(0);
			while(insert)
			{
				if(input=='y' || input=='Y')
				{
					insertUser();
					insert=false;
				}
				else if(input=='n' || input=='N')
				{
					userPage();
					insert=false;
				}
				else
				{
					System.out.println("Enter Y or N");
					insert=true;
				}
			}
		}
 	}
}
