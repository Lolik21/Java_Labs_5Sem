package dataAccessLayer;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import data.Book;


public class LibraryLoader {
	public ArrayList<Book> Load(String FileName)
	{
		BufferedReader br;
		try
		{
			ArrayList<Book> LoadedBooks = new ArrayList<Book>();
			FileInputStream stream = new FileInputStream(FileName);
			br = new BufferedReader(new InputStreamReader(stream));
			String strLine;
			while ((strLine = br.readLine()) != null)   {
				Book NewBook = GetBook(strLine);				
				LoadedBooks.add(NewBook);					  
			}
			br.close();
			return LoadedBooks;
		}
		catch (IOException e) {			
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	private Book GetBook(String Line)
	{	
		String[] SplitedLine = Line.split(":");
		Book NewBook = new Book();
		NewBook.setBookName(SplitedLine[0]);
		NewBook.setDesctiption(SplitedLine[1]);		
		return NewBook;
	}
}
