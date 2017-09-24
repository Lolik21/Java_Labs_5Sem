package dataAccessLayer;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import data.Book;

public class LibrarySaver {
	public void Save(ArrayList<Book> Books,String FileName)
	{
		BufferedWriter br;
		try
		{
			FileOutputStream stream = new FileOutputStream(FileName);
			br = new BufferedWriter(new OutputStreamWriter(stream));
			for (Book book : Books) {
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
