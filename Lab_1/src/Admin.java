import java.util.Scanner;

public class Admin extends User implements IAdmin {

	public Admin(String Name, String Passwd, String Email) {
		super(Name, Passwd, Email);
	}
	
	private void onCreateNewBookAction(BookLibrary library, Scanner scanner)
	{
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
	
	private void onDeleteBookAction(BookLibrary library, Scanner scanner)
	{
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
	
	private void onChangeBookAction(BookLibrary library, Scanner scanner)
	{
		System.out.println("������� �������� : ");
		String Name = scanner.nextLine();
		System.out.println("������� ����� �������� : ");
		String NewDesc = scanner.nextLine();
		
		Book cngBook = library.GetByName(Name);
		if (cngBook != null)
		{
			cngBook.setDesctiption(NewDesc);
			System.out.println("�������� ��������");
		}
		else			
		{
			System.out.println("����� �� ����������");
		}
	}
	
	public void PerformAction(BookLibrary library, Scanner scanner)
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
				ShowCatalogAction(library,scanner);
				break;
			case 2:
				SerchBookAction(library,scanner);
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
	
	public String getAdminEmail() {		
		return this.getEmail();
	}
	


}
