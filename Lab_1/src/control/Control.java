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
			System.out.println("�������� ��������: ");
			System.out.println("0) �����.");
			System.out.println("1) ��������� ��������");
			System.out.println("2) ���������� ��������");
			if(scanner.hasNextInt()) {
				ChousedParam = scanner.nextInt();
				ProssesShowCatalogParam(library,ChousedParam,offset);
			}
		}
	}
	
	public void onSerchBookAction(BookLibrary library, Scanner scanner)
	{
		System.out.println("������� �������� ����� :");
		scanner.nextLine();
		String BookName = scanner.nextLine();
		Book FindBook = library.GetByName(BookName);
		if (FindBook == null) 
		{
			System.out.println("����� �� �������");
			return;
		}
		System.out.println("����� ������� :");
		System.out.println(FindBook.getBookName() +" : "+FindBook.getDesctiption());
	}
	
	public void onOfferBookAction(BookLibrary bookLibrary, Scanner scanner)
	{
		scanner.nextLine();
		System.out.println("������� �������� ����� :");
		String BookName = scanner.nextLine();
		System.out.println("������� ��������: ");
		String Description = scanner.nextLine();
		if (bookLibrary.getAdminEmail() != null)
		{
			Emailer emailer = new Emailer();
			emailer.SendMail("��� ���������� ����� \n " + BookName + " : " 
					+ Description, bookLibrary.getAdminEmail(),"����� �����");
			System.out.println("Email ������� ���������");
		}
		else
		{
			System.out.println("Email �������������� �� ������");
		}
	}  
	
	public void onPerformUserAction(BookLibrary library, Scanner scanner)
	{
		
		int ChousedParam = -1;
		while (ChousedParam != 0)
		{
			System.out.println("�������� ��������: ");
			System.out.println("0) �����.");
			System.out.println("1) �������� �������.");
			System.out.println("2) ����� �����.");
			System.out.println("3) ���������� �������� �����. ");
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
		System.out.println("������� �������� : ");
		String Name = scanner.nextLine();
		System.out.println("������� �������� : ");
		String Desc = scanner.nextLine();
		
		Book NewBook = new Book();
		NewBook.setBookName(Name);
		NewBook.setDesctiption(Desc);
		if (library.GetByName(Name) == null)
		{
			library.addBook(NewBook);
			System.out.println("����� ���������");
		}
		else
		{
			System.out.println("����� ��� ����������");
		}
	}
	
	public void onDeleteBookAction(BookLibrary library, Scanner scanner)
	{
		scanner.nextLine();
		System.out.println("������� �������� : ");
		String Name = scanner.nextLine();
		Book DelBook = library.GetByName(Name);
		if (DelBook != null)
		{
			library.DeleteBook(DelBook);
			System.out.println("����� �������");
		}
		else
		{
			System.out.println("����� �� ����������");
		}
	}
	
	public void onChangeBookAction(BookLibrary library, Scanner scanner)
	{
		scanner.nextLine();
		System.out.println("������� �������� : ");
		String Name = scanner.nextLine();
		System.out.println("������� ����� �������� : ");
		String NewDesc = scanner.nextLine();
		
		Book cngBook = library.GetByName(Name);
		if (cngBook != null)
		{
			cngBook.setDesctiption(NewDesc);
			Emailer emailer = new Emailer();
			emailer.SendToUsers(library.getUsers(), "�������� �������� � " + 
								cngBook.getBookName(), "�������� ��������");
			System.out.println("�������� ��������");
		}
		else			
		{
			System.out.println("����� �� ����������");
		}
	}
	
	public void onPerformAdminAction(BookLibrary library, Scanner scanner)
	{
		int ChousedParam = -1;
		while (ChousedParam != 0)
		{
			System.out.println("�������� ��������: ");
			System.out.println("0) �����.");
			System.out.println("1) �������� �������.");
			System.out.println("2) ����� �����.");
			System.out.println("3) �������� �����.");
			System.out.println("4) ������� �����.");
			System.out.println("5) �������� ��������.");
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
