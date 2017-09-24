package role;
import java.util.Scanner;

import control.Control;

import data.BookLibrary;

public class User {
	private String Name;
	private String Passwd;
	private String Email;
	
	public User(String Name, String Passwd, String Email)
	{
		this.Name = Name;
		this.Passwd = Passwd;
		this.Email = Email;
	}
	
	public String getEmail()
	{
		return this.Email;
	}
	
	public String getName()
	{
		return this.Name;
	}
	
	public String getPasswd()
	{
		return this.Passwd;
	
	}
	
	public void PerformAction(BookLibrary library, Scanner scanner)
	{	
		Control control = new Control();
		control.onPerformUserAction(library, scanner);	
	}
		
}
