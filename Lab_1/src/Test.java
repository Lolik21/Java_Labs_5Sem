import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;


public class Test {

	public static void main(String[] args) {	
		Scanner scanner = null;
		try
		{
			scanner = new Scanner(System.in); 
			BookLibrary bookLibrary = new BookLibrary();
			UserLoader userLoader = new UserLoader();
			FileDialog fileDialog = new FileDialog();
			ArrayList<User> Users = userLoader.GetUsers(fileDialog.GetFileName());		
			if (Users.size() == 0)
			{
				throw new Exception("Пользователей нет");		
			}
			bookLibrary.LoadBooks(fileDialog.GetFileName());
			bookLibrary.setAdminEmail(GetAdminEmail(Users));

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
			if (user.getName() == name)
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
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(passwd.getBytes(StandardCharsets.UTF_8));
			String passwdHash = new String(hash, StandardCharsets.UTF_8);
			if (user.getPasswd() == passwdHash) return true;
			
		} catch (NoSuchAlgorithmException e) {			
			e.printStackTrace();
		}				
		return false;		
	}

}
