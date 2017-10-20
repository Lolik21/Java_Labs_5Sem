package role;
import java.util.Scanner;

import control.ConsoleControl;
import model.BookLibrary;

public class User {
	private String name;
	private String passwd;
	private String email;
	
	public User(String name, String passwd, String email)
	{
		this.name = name;
		this.passwd = passwd;
		this.email = email;
	}
	
	public String getEmail()
	{
		return this.email;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public String getPasswd()
	{
		return this.passwd;
	
	}
	
	public void performAction(BookLibrary library, Scanner scanner)
	{	
		ConsoleControl consoleControl = new ConsoleControl();
		consoleControl.onPerformUserAction(library, scanner);	
	}
		
}
