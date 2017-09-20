import java.util.ArrayList;

public class BookLibrary {
	private ArrayList<Book> Books;

	public void addBook(Book NewBook)
	{
		Books.add(NewBook);
	}

	private boolean DeleteBook(Book DelBook)
	{
		return Books.remove(DelBook);
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
