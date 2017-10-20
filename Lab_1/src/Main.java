import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;

import dataAccessLayer.UserDAO;
import model.BookLibrary;
import dataAccessLayer.FileDialogDAO;
import role.IAdmin;
import role.User;



public class Main {

	public static void main(String[] args) {	
		Scanner scanner = null;
		try
		{
			scanner = new Scanner(System.in); 
			BookLibrary bookLibrary = new BookLibrary();
			UserDAO userDAO = new UserDAO();
			FileDialogDAO fileDialogDAO = new FileDialogDAO();
			String usersFileName = fileDialogDAO.getFileName();
			String booksFileName = fileDialogDAO.getFileName();
		 	if (usersFileName == null || booksFileName == null)
		 	{
		 		throw new UserException("Не выбраны файлы");
		 	}
			ArrayList<User> users = userDAO.getUsers(usersFileName);
			if (users.size() == 0)
			{
				throw new UserException("Пользователей нет");		
			}
			bookLibrary.loadBooks(booksFileName);
			bookLibrary.setAdminEmail(getAdminEmail(users));
			bookLibrary.setUsers(users);

			String name = getUserName(scanner);
			String passwd = getPasswd(scanner);
			User currentUser = getUser(name,users);

			if (currentUser == null)
			{
				throw new UserException("Пользователь не найден");
			}
			if (!checkPasswd(bookLibrary,currentUser, passwd))
			{
				throw new UserException("Пароль введён неверно");
			}
			
			currentUser.performAction(bookLibrary, scanner);

			userDAO.save(users, usersFileName);
			
			bookLibrary.saveBooks(booksFileName);
			
		}catch (UserException e) {
			System.out.println(e.getMessage());
		}finally {
			if (scanner != null) scanner.close();
		}
	
	}
	
	public static String getAdminEmail(ArrayList<User> Users)
	{
		for (User user : Users) {
			if (user instanceof IAdmin)
			{
				return user.getEmail();
			}
		}
		return null;
	}
	
	public static String getUserName(Scanner scanner)
	{
		System.out.println("Введите ваш логин:");
		return scanner.nextLine();
	}
	
	public static String getPasswd(Scanner scanner)
	{
		System.out.println("Введите ваш пароль:");
		return scanner.nextLine();
	}
	
	public static User getUser(String name, ArrayList<User> users)
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
	
	public static boolean checkPasswd(BookLibrary library, 
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
