package control;

import java.util.Scanner;

import data.Book;
import data.BookLibrary;
import emailer.Emailer;

public class Control {
	
	private final int BOOK_PAGE_CNT = 10;
	public void ProssesShowCatalogParam(BookLibrary library,int Param,Integer offset)
	{
		
		switch (Param) {
		case 1:
			offset = offset + BOOK_PAGE_CNT;
			if (offset > library.Size()) 
			{
				offset = offset - BOOK_PAGE_CNT;			
			}
			onShowBooks(library,BOOK_PAGE_CNT,offset);
			break;
		case 2:
			offset = offset - BOOK_PAGE_CNT;
			if (offset < 0) 
			{
				offset = 0;			
			}
			onShowBooks(library,BOOK_PAGE_CNT,offset);
			break;				
		}
	}
	
	public void onShowBooks(BookLibrary library, int Count, int offset)
	{
		int BCount = 0;
		if (Count + offset > library.Size())
		{
			BCount = library.Size();
		}
		else
		{
			BCount = BOOK_PAGE_CNT;
		}
		for (int i = offset; i < BCount; i++)
		{
			Book book = library.GetByIndex(i);
			System.out.println(book.getBookName() +" : "+ book.getDesctiption());
		}
	}
	
	
	public void onShowCatalogAction(BookLibrary library, Scanner scanner)
	{
		int ChousedParam = -1;
		Integer offset = 0;
		onShowBooks(library, BOOK_PAGE_CNT, offset);
		while (ChousedParam != 0)
		{
			System.out.println("Выберите действие: ");
			System.out.println("0) Назад.");
			System.out.println("1) Следующая страница");
			System.out.println("2) Предыдущая страница");
			if(scanner.hasNextInt()) {
				ChousedParam = scanner.nextInt();
				ProssesShowCatalogParam(library,ChousedParam,offset);
			}
		}
	}
	
	public void onSerchBookAction(BookLibrary library, Scanner scanner)
	{
		System.out.println("Введите название книги :");
		scanner.nextLine();
		String BookName = scanner.nextLine();
		Book FindBook = library.GetByName(BookName);
		if (FindBook == null) 
		{
			System.out.println("Книга не найдена");
			return;
		}
		System.out.println("Книга найдена :");
		System.out.println(FindBook.getBookName() +" : "+FindBook.getDesctiption());
	}
	
	public void onOfferBookAction(BookLibrary bookLibrary, Scanner scanner)
	{
		scanner.nextLine();
		System.out.println("Введите название книги :");
		String BookName = scanner.nextLine();
		System.out.println("Введите описание: ");
		String Description = scanner.nextLine();
		if (bookLibrary.getAdminEmail() != null)
		{
			Emailer emailer = new Emailer();
			emailer.SendMail("Вам предложена книга \n " + BookName + " : " 
					+ Description, bookLibrary.getAdminEmail(),"Новая книга");
			System.out.println("Email успешно отправлен");
		}
		else
		{
			System.out.println("Email администратора не найден");
		}
	}  
	
	public void onPerformUserAction(BookLibrary library, Scanner scanner)
	{
		
		int ChousedParam = -1;
		while (ChousedParam != 0)
		{
			System.out.println("Выберите действие: ");
			System.out.println("0) Выход.");
			System.out.println("1) Показать каталог.");
			System.out.println("2) Поиск книги.");
			System.out.println("3) Предложить добавить книгу. ");
			if(scanner.hasNextInt()) {
				ChousedParam = scanner.nextInt();
			}
			switch (ChousedParam) {
			case 1:
				onShowCatalogAction(library,scanner);
				break;
			case 2:
				onSerchBookAction(library,scanner);
				break;
			case 3:
				onOfferBookAction(library,scanner);
				break;
			}
		}	
	}
	public void onCreateNewBookAction(BookLibrary library, Scanner scanner)
	{
		scanner.nextLine();
		System.out.println("Введите название : ");
		String Name = scanner.nextLine();
		System.out.println("Введите описание : ");
		String Desc = scanner.nextLine();
		
		Book NewBook = new Book();
		NewBook.setBookName(Name);
		NewBook.setDesctiption(Desc);
		if (library.GetByName(Name) == null)
		{
			library.addBook(NewBook);
			System.out.println("Книга добавлена");
		}
		else
		{
			System.out.println("Книга уже существует");
		}
	}
	
	public void onDeleteBookAction(BookLibrary library, Scanner scanner)
	{
		scanner.nextLine();
		System.out.println("Введите название : ");
		String Name = scanner.nextLine();
		Book DelBook = library.GetByName(Name);
		if (DelBook != null)
		{
			library.DeleteBook(DelBook);
			System.out.println("Книга удалена");
		}
		else
		{
			System.out.println("Книга не существует");
		}
	}
	
	public void onChangeBookAction(BookLibrary library, Scanner scanner)
	{
		scanner.nextLine();
		System.out.println("Введите название : ");
		String Name = scanner.nextLine();
		System.out.println("Введите новое описание : ");
		String NewDesc = scanner.nextLine();
		
		Book cngBook = library.GetByName(Name);
		if (cngBook != null)
		{
			cngBook.setDesctiption(NewDesc);
			Emailer emailer = new Emailer();
			emailer.SendToUsers(library.getUsers(), "Изменено описание у " + 
								cngBook.getBookName(), "Изменено описание");
			System.out.println("Описание изменено");
		}
		else			
		{
			System.out.println("Книга не существует");
		}
	}
	
	public void onPerformAdminAction(BookLibrary library, Scanner scanner)
	{
		int ChousedParam = -1;
		while (ChousedParam != 0)
		{
			System.out.println("Выберите действие: ");
			System.out.println("0) Выход.");
			System.out.println("1) Показать каталог.");
			System.out.println("2) Поиск книги.");
			System.out.println("3) Добавить книгу.");
			System.out.println("4) Удалить книгу.");
			System.out.println("5) Изменить описание.");
			if(scanner.hasNextInt()) {
				ChousedParam = scanner.nextInt();
			}
			switch (ChousedParam) {
			case 1:
				onShowCatalogAction(library,scanner);
				break;
			case 2:
				onSerchBookAction(library,scanner);
				break;
			case 3:
				onCreateNewBookAction(library, scanner);
				break;
			case 4:
				onDeleteBookAction(library, scanner);
				break;
			case 5:
				onChangeBookAction(library, scanner);
				break;
			}
		}
		
	}
	
}
