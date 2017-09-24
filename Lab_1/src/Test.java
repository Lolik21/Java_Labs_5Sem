import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;

import data.BookLibrary;
import dataAccessLayer.UserLoader;
import dataAccessLayer.UserSaver;
import dataAccessLayer.LibrarySaver;
import role.IAdmin;
import role.User;



public class Test {

	public static void main(String[] args) {	
		Scanner scanner = null;
		try
		{
			scanner = new Scanner(System.in); 
			BookLibrary bookLibrary = new BookLibrary();
			UserLoader userLoader = new UserLoader();
			String UsersFileName = "C:\\Users\\Ilya\\Documents\\Test_For_Java\\users.txt";
			String BooksFileName = "C:\\Users\\Ilya\\Documents\\Test_For_Java\\books.txt";
		//	FileDialog fileDialog = new FileDialog();
			ArrayList<User> Users = userLoader.GetUsers(UsersFileName);
			if (Users.size() == 0)
			{
				throw new Exception("Пользователей нет");		
			}
			bookLibrary.LoadBooks(BooksFileName);
			bookLibrary.setAdminEmail(GetAdminEmail(Users));
			bookLibrary.setUsers(Users);

			String Name = GetUserName(scanner);
			String Passwd = GetPasswd(scanner);
			User currentUser = GetUser(Name,Users);

			if (currentUser == null)
			{
				throw new Exception("Пользователь не найден");
			}
			if (!CheckPasswd(bookLibrary,currentUser, Passwd))
			{
				throw new Exception("Пароль введён неверно");
			}
			
			currentUser.PerformAction(bookLibrary, scanner);

			UserSaver userSaver = new UserSaver();
			userSaver.Save(Users, UsersFileName);
			LibrarySaver librarySaver = new LibrarySaver();
			librarySaver.Save(bookLibrary.getBooks(), BooksFileName);
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			if (scanner != null) scanner.close();
		}
	
	}
	
	public static String GetAdminEmail(ArrayList<User> Users)
	{
		for (User user : Users) {
			if (user instanceof IAdmin)
			{
				return user.getEmail();
			}
		}
		return null;
	}
	
	public static String GetUserName(Scanner scanner)
	{
		System.out.println("Введите ваш логин:");
		return scanner.nextLine();
	}
	
	public static String GetPasswd(Scanner scanner)
	{
		System.out.println("Введите ваш пароль:");
		return scanner.nextLine();
	}
	
	public static User GetUser(String name, ArrayList<User> users)
	{
		for (User user : users) {
			String CurName = user.getName();
			if (CurName.compareTo(name) == 0)
			{
				return user;
			}
		}
		return null;
	}
	
	public static boolean CheckPasswd(BookLibrary library, 
			 User user, String passwd)
	{
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
	        md.update(passwd.getBytes());

	        byte byteData[] = md.digest();

	        StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < byteData.length; i++) {
	         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
	        }
	        String Hex = sb.toString();
			if (user.getPasswd().compareTo(Hex) == 0) 
				return true;
			
		} catch (NoSuchAlgorithmException e) {			
			e.printStackTrace();
		}				
		return false;		
	}

}
