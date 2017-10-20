package control;

import java.util.Scanner;

import emailer.Emailer;
import model.Book;
import model.BookLibrary;

public class ConsoleControl {
	
	private final int BOOK_PAGE_CNT = 10;
	public void prossesShowCatalogParam(BookLibrary library,int param,Integer offset)
	{
		
		switch (param) {
		case 1:
			offset = offset + BOOK_PAGE_CNT;
			if (offset > library.size()) 
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
	
	public void onShowBooks(BookLibrary library, int count, int offset)
	{
		int bCount = 0;
		if (count + offset > library.size())
		{
			bCount = library.size();
		}
		else
		{
			bCount = BOOK_PAGE_CNT;
		}
		for (int i = offset; i < bCount; i++)
		{
			Book book = library.getByIndex(i);
			System.out.println(book.getBookName() +" : "+ book.getDesctiption());
		}
	}
	
	
	public void onShowCatalogAction(BookLibrary library, Scanner scanner)
	{
		int chousedParam = -1;
		Integer offset = 0;
		onShowBooks(library, BOOK_PAGE_CNT, offset);
		while (chousedParam != 0)
		{
			System.out.println("Выберите действие: ");
			System.out.println("0) Назад.");
			System.out.println("1) Следующая страница");
			System.out.println("2) Предыдущая страница");
			if(scanner.hasNextInt()) {
				chousedParam = scanner.nextInt();
				prossesShowCatalogParam(library,chousedParam,offset);
			}
		}
	}
	
	public void onSerchBookAction(BookLibrary library, Scanner scanner)
	{
		System.out.println("Введите название книги :");
		scanner.nextLine();
		String bookName = scanner.nextLine();
		Book findBook = library.getByName(bookName);
		if (findBook == null) 
		{
			System.out.println("Книга не найдена");
			return;
		}
		System.out.println("Книга найдена :");
		System.out.println(findBook.getBookName() +" : "+findBook.getDesctiption());
	}
	
	public void onOfferBookAction(BookLibrary bookLibrary, Scanner scanner)
	{
		scanner.nextLine();
		System.out.println("Введите название книги :");
		String bookName = scanner.nextLine();
		System.out.println("Введите описание: ");
		String description = scanner.nextLine();
		if (bookLibrary.getAdminEmail() != null)
		{
			Emailer emailer = new Emailer();
			emailer.sendMail("Вам предложена книга \n " + bookName + " : " 
					+ description, bookLibrary.getAdminEmail(),"Новая книга");
			System.out.println("Email успешно отправлен");
		}
		else
		{
			System.out.println("Email администратора не найден");
		}
	}  
	
	public void onPerformUserAction(BookLibrary library, Scanner scanner)
	{
		
		int chousedParam = -1;
		while (chousedParam != 0)
		{
			System.out.println("Выберите действие: ");
			System.out.println("0) Выход.");
			System.out.println("1) Показать каталог.");
			System.out.println("2) Поиск книги.");
			System.out.println("3) Предложить добавить книгу. ");
			if(scanner.hasNextInt()) {
				chousedParam = scanner.nextInt();
			}
			switch (chousedParam) {
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
		
		Book newBook = new Book();
		newBook.setBookName(Name);
		newBook.setDesctiption(Desc);
		if (library.getByName(Name) == null)
		{
			library.addBook(newBook);
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
		String name = scanner.nextLine();
		Book delBook = library.getByName(name);
		if (delBook != null)
		{
			library.deleteBook(delBook);
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
		String name = scanner.nextLine();
		System.out.println("Введите новое описание : ");
		String newDesc = scanner.nextLine();
		
		Book cngBook = library.getByName(name);
		if (cngBook != null)
		{
			cngBook.setDesctiption(newDesc);
			Emailer emailer = new Emailer();
			emailer.sendToUsers(library.getUsers(), "Изменено описание у " + 
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
		int chousedParam = -1;
		while (chousedParam != 0)
		{
			System.out.println("Выберите действие: ");
			System.out.println("0) Выход.");
			System.out.println("1) Показать каталог.");
			System.out.println("2) Поиск книги.");
			System.out.println("3) Добавить книгу.");
			System.out.println("4) Удалить книгу.");
			System.out.println("5) Изменить описание.");
			if(scanner.hasNextInt()) {
				chousedParam = scanner.nextInt();
			}
			switch (chousedParam) {
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
