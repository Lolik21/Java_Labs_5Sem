package data;
import java.util.ArrayList;

import dataAccessLayer.LibraryLoader;
import role.User;

public class BookLibrary {
	private ArrayList<Book> Books;
	private String AdminEmail;
	private ArrayList<User> Users;
	
	public void setUsers(ArrayList<User> Users)
	{
		this.Users = Users;
	}
	
	public ArrayList<User> getUsers()
	{
		return Users;
	}
	
	public ArrayList<Book> getBooks()
	{
		return this.Books;
	}
	
	public void setAdminEmail(String Email)
	{
		AdminEmail = Email;
	}

	public String getAdminEmail()
	{
		return AdminEmail;
	}
	
	public void addBook(Book NewBook)
	{
		Books.add(NewBook);
	}

	public boolean DeleteBook(Book DelBook)
	{
		return Books.remove(DelBook);
	}

	public Book GetByName(String Name)
	{
		for (Book book : Books) {
			if (book.getBookName().compareTo(Name) == 0)
			{
				return book;
			}
		}
		return null;
	}
	
	public Book GetByIndex(int index)
	{
		if (index>=0 && index<Books.size())
		{
			return Books.get(index);
		}		
		return null;
	}
	
	public int Size() {
		return Books.size();
	}
	
	private void CreateBookList()
	{
		if (Books == null)
		{
			Books = new ArrayList<Book>();
		}
	}

	
	public void LoadBooks(String FileName)
	{
		LibraryLoader LLoader = new LibraryLoader();
		ArrayList<Book> GettedBooks = LLoader.Load(FileName);
		CreateBookList();
		if (GettedBooks == null) return;

		for (Book book : GettedBooks) {
			if (Books.indexOf(book) == -1)
			{
				this.addBook(book);
			}
		}


	}
}
