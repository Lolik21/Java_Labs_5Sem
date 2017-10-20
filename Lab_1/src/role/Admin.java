package role;
import java.util.Scanner;

import control.ConsoleControl;
import model.Book;
import model.BookLibrary;

public class Admin extends User implements IAdmin {

	public Admin(String name, String passwd, String email) {
		super(name, passwd, email);
	}
	
	public void onCreateNewBookAction(BookLibrary library, Scanner scanner)
	{
		System.out.println("¬ведите название : ");
		String name = scanner.nextLine();
		System.out.println("¬ведите описание : ");
		String desc = scanner.nextLine();
		
		Book newBook = new Book();
		newBook.setBookName(name);
		newBook.setDesctiption(desc);
		if (library.getByName(name) == null)
		{
			library.addBook(newBook);
			System.out.println(" нига добавлена");
		}
		else
		{
			System.out.println(" нига уже существует");
		}
	}
	
	public void onDeleteBookAction(BookLibrary library, Scanner scanner)
	{
		System.out.println("¬ведите название : ");
		String name = scanner.nextLine();
		Book delBook = library.getByName(name);
		if (delBook != null)
		{
			library.deleteBook(delBook);
			System.out.println(" нига удалена");
		}
		else
		{
			System.out.println(" нига не существует");
		}
	}
	
	public void onChangeBookAction(BookLibrary library, Scanner scanner)
	{
		System.out.println("¬ведите название : ");
		String Name = scanner.nextLine();
		System.out.println("¬ведите новое описание : ");
		String NewDesc = scanner.nextLine();
		
		Book cngBook = library.getByName(Name);
		if (cngBook != null)
		{
			cngBook.setDesctiption(NewDesc);
			System.out.println("ќписание изменено");
		}
		else			
		{
			System.out.println(" нига не существует");
		}
	}
	
	public void performAction(BookLibrary library, Scanner scanner)
	{
		ConsoleControl consoleControl = new ConsoleControl();
		consoleControl.onPerformAdminAction(library, scanner);	
	}
	
	public String getAdminEmail() {		
		return this.getEmail();
	}
	
}
