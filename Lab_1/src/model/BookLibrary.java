package model;
import java.util.ArrayList;

import dataAccessLayer.LibraryDAO;
import role.User;

public class BookLibrary {
	private ArrayList<Book> books;
	private String adminEmail;
	private ArrayList<User> users;
	
	public void setUsers(ArrayList<User> users)
	{
		this.users = users;
	}
	
	public ArrayList<User> getUsers()
	{
		return users;
	}
	
	public ArrayList<Book> getBooks()
	{
		return this.books;
	}
	
	public void setAdminEmail(String email)
	{
		adminEmail = email;
	}

	public String getAdminEmail()
	{
		return adminEmail;
	}
	
	public void addBook(Book newBook)
	{
		books.add(newBook);
	}

	public boolean deleteBook(Book delBook)
	{
		return books.remove(delBook);
	}

	public Book getByName(String name)
	{
		for (Book book : books) {
			if (book.getBookName().compareTo(name) == 0)
			{
				return book;
			}
		}
		return null;
	}
	
	public Book getByIndex(int index)
	{
		if (index>=0 && index<books.size())
		{
			return books.get(index);
		}		
		return null;
	}
	
	public int size() {
		return books.size();
	}
	
	private void createBookList()
	{
		if (books == null)
		{
			books = new ArrayList<Book>();
		}
	}

	
	public void loadBooks(String fileName)
	{
		LibraryDAO lLoader = new LibraryDAO();
		ArrayList<Book> gettedBooks = lLoader.load(fileName);
		createBookList();
		if (gettedBooks == null) return;

		for (Book book : gettedBooks) {
			if (books.indexOf(book) == -1)
			{
				this.addBook(book);
			}
		}
	}
	
	public void saveBooks(String fileName)
	{
		LibraryDAO lLoader = new LibraryDAO();
		lLoader.save(books, fileName);
	}
}
