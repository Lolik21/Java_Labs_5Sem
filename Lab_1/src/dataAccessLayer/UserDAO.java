package dataAccessLayer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import role.Admin;
import role.IAdmin;
import role.User;

public class UserDAO {
	private final int USER_NAME = 0;
	private final int USER_PASSWD = 1;
	private final int USER_EMAIL = 2;	
	private final int USER_MARK = 3;
	public final String ADMIN_MARK = "Admin";
	
	public ArrayList<User> getUsers(String fileName){
		BufferedReader br;
		if (fileName == null) return null;
		try{
			ArrayList<User> loadedUsers = new ArrayList<User>();
			FileInputStream stream = new FileInputStream(fileName);
			br = new BufferedReader(new InputStreamReader(stream));
			String strLine;
			while ((strLine = br.readLine()) != null){
				User newUser = getUser(strLine);				
				loadedUsers.add(newUser);					  
			}
			br.close();
			return loadedUsers;
		}
		catch (IOException e) {			
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	private User getUser(String Line){
		String[] splitedLine = Line.split(":");
		String name = splitedLine[USER_NAME];
		String passwd = splitedLine[USER_PASSWD];
		String email = splitedLine[USER_EMAIL];
		User newUser;
		if (splitedLine.length > USER_MARK){
			if (splitedLine[USER_MARK].compareTo(ADMIN_MARK) == 0){
				newUser = new Admin(name, passwd, email);
				return newUser;
			}
		}
		newUser = new User(name,passwd,email);	
		return newUser;
	}
	
	public void save(ArrayList<User> users,String fileName)
	{
		BufferedWriter br;
		try
		{
			FileOutputStream stream = new FileOutputStream(fileName);
			br = new BufferedWriter(new OutputStreamWriter(stream));
			for (User user : users) {
				br.write(user.getName()+ ":" + user.getPasswd()+":"+user.getEmail());
				if (user instanceof IAdmin)
				{
					br.write(":" + ADMIN_MARK);
				}
				br.newLine();
			}
			br.close();
		}
		catch (IOException e) {			
			System.out.println(e.getMessage());
		}
	}

}
