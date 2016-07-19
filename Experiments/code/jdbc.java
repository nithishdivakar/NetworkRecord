/* SQL 
create database jdbc;
use jdbc;
create table student(r_no int primary key,name varchar(20));
*/
import java.sql.*;
import java.io.*;
//java -cp mysql-connector-java-5.0.8-bin.jar; jdbc
class jdbc{
	Connection connect=null;
	Statement statement=null;
	public static void main(String args[]){
		int choice;
		String line,name;
		int r_no;
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		jdbc J=new jdbc();
		do{
			choice=J.menu();
			switch(choice){
				case 0: System.exit(0);;
				case 1: try{
							System.out.print("Enter roll no: ");
							line=br.readLine();
							r_no=Integer.parseInt(line);
							System.out.print("Enter Name: ");
							name=br.readLine();
							J.insert(r_no,name);
						}
						catch(Exception e){
							System.out.println(e);
						}
						break;
				case 2: try{
							System.out.print("Enter roll no: ");
							line=br.readLine();
							r_no=Integer.parseInt(line);
							J.delete(r_no);
						}
						catch(Exception e){
							System.out.println(e);
						}
						break;
				case 3: J.view();
						break;
			}
		}while(true);
	}
	public jdbc(){
	try{
			Class.forName("com.mysql.jdbc.Driver");
			connect=DriverManager.getConnection("jdbc:mysql://localhost/student?user=root");
			statement=connect.createStatement();
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	int menu(){
		try{
			String s;
			System.out.print("\n1. Insert\t2. Delete\n3. View\t\t0. Exit\nEnter your choice (0-3):");
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			s=br.readLine();
			return Integer.parseInt(s);
		}
		catch(Exception e){
			return menu();
		}
	}
	void insert(int r_no,String name){	
		try{
			statement.executeUpdate("insert into student values('"+r_no+"','"+name+"')");
		}catch(Exception e){System.out.println(e);}
	}
	void delete(int r_no){
		try{
			statement.executeUpdate("delete from student where r_no='"+r_no+"'");
		}catch(Exception e){System.out.println(e);}
	}
	void view(){
		try{
			ResultSet resultset=statement.executeQuery("select * from student");
			while(resultset.next())
				System.out.println("Roll number : "+resultset.getString("r_no")+"\tName:" +resultset.getString("name"));
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
}