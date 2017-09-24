package role;
import java.util.Scanner;

import control.Control;
import data.Book;
import data.BookLibrary;

public class Admin extends User implements IAdmin {

	public Admin(String Name, String Passwd, String Email) {
		super(Name, Passwd, Email);
	}
	
	public void onCreateNewBookAction(BookLibrary library, Scanner scanner)
	{
		System.out.println("¬ведите название : ");
		String Name = scanner.nextLine();
		System.out.println("¬ведите описание : ");
		String Desc = scanner.nextLine();
		
		Book NewBook = new Book();
		NewBook.setBookName(Name);
		NewBook.setDesctiption(Desc);
		if (library.GetByName(Name) == null)
		{
			library.addBook(NewBook);
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
		String Name = scanner.nextLine();
		Book DelBook = library.GetByName(Name);
		if (DelBook != null)
		{
			library.DeleteBook(DelBook);
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
		
		Book cngBook = library.GetByName(Name);
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
	
	public void PerformAction(BookLibrary library, Scanner scanner)
	{
		Control control = new Control();
		control.onPerformAdminAction(library, scanner);	
	}
	
	public String getAdminEmail() {		
		return this.getEmail();
	}
	


}
