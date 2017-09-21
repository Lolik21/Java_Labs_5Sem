import java.util.Scanner;

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
	
	private final int BOOK_PAGE_CNT = 10;
	
	private void ShowBooks(BookLibrary library, int Count, int offset)
	{
		int BCount = 0;
		if (Count + offset > library.Size())
		{
			BCount = (Count + offset) - library.Size(); 
		}
		for (int i = offset; i < BCount; i++)
		{
			Book book = library.GetByIndex(i);
			System.out.println(book.getBookName() +" : "+ book.getDesctiption());
		}
	}
	
	private void ProssesShowCatalogParam(BookLibrary library,int Param,Integer offset)
	{
		
		switch (Param) {
		case 1:
			offset = offset + BOOK_PAGE_CNT;
			if (offset > library.Size()) 
			{
				offset = offset - BOOK_PAGE_CNT;			
			}
			ShowBooks(library,BOOK_PAGE_CNT,offset);
			break;
		case 2:
			offset = offset - BOOK_PAGE_CNT;
			if (offset < 0) 
			{
				offset = 0;			
			}
			ShowBooks(library,BOOK_PAGE_CNT,offset);
			break;				
		}
	}
	
	protected void ShowCatalogAction(BookLibrary library, Scanner scanner)
	{
		int ChousedParam = -1;
		Integer offset = 0;
		ShowBooks(library, BOOK_PAGE_CNT, offset);
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
	
	protected void SerchBookAction(BookLibrary library, Scanner scanner)
	{
		System.out.println("Введите название книги :");
		String BookName = scanner.nextLine();
		Book FindBook = library.GetByName(BookName);
		if (FindBook == null) return;
		System.out.println("Книга найдена :");
		System.out.println(FindBook.getBookName() +" : "+FindBook.getDesctiption());
	}
	
	private void OfferBook(BookLibrary bookLibrary, Scanner scanner)
	{
		
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
	}
	
	public void PerformAction(BookLibrary library, Scanner scanner)
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
				ShowCatalogAction(library,scanner);
				break;
			case 2:
				SerchBookAction(library,scanner);
				break;
			case 3:
				OfferBook(library,scanner);
				break;
			}
		}
		
		
	}
		
}
