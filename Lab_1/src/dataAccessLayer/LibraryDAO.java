package dataAccessLayer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import model.Book;


public class LibraryDAO {
	public ArrayList<Book> load(String FileName)
	{
		BufferedReader br;
		try
		{
			ArrayList<Book> loadedBooks = new ArrayList<Book>();
			FileInputStream stream = new FileInputStream(FileName);
			br = new BufferedReader(new InputStreamReader(stream));
			String strLine;
			while ((strLine = br.readLine()) != null)   {
				Book newBook = getBook(strLine);				
				loadedBooks.add(newBook);					  
			}
			br.close();
			return loadedBooks;
		}
		catch (IOException e) {			
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	private Book getBook(String Line)
	{	
		String[] splitedLine = Line.split(":");
		Book newBook = new Book();
		newBook.setBookName(splitedLine[0]);
		newBook.setDesctiption(splitedLine[1]);		
		return newBook;
	}
	
	public void save(ArrayList<Book> books,String fileName)
	{
		BufferedWriter br;
		try
		{
			FileOutputStream stream = new FileOutputStream(fileName);
			br = new BufferedWriter(new OutputStreamWriter(stream));
			for (Book book : books) {
				br.write(book.getBookName() + ":" + book.getDesctiption());
				br.newLine();
			}
			br.close();
		}
		catch (IOException e) {			
			System.out.println(e.getMessage());
		}
	}
}
