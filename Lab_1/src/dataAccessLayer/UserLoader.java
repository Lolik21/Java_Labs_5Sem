package dataAccessLayer;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import role.Admin;
import role.User;

public class UserLoader {
	private final int USER_NAME = 0;
	private final int USER_PASSWD = 1;
	private final int USER_EMAIL = 2;	
	private final int USER_MARK = 3;
	private final String ADMIN_MARK = "Admin";
	
	public ArrayList<User> GetUsers(String FileName){
		BufferedReader br;
		if (FileName == null) return null;
		try{
			ArrayList<User> LoadedUsers = new ArrayList<User>();
			FileInputStream stream = new FileInputStream(FileName);
			br = new BufferedReader(new InputStreamReader(stream));
			String strLine;
			while ((strLine = br.readLine()) != null){
				User NewUser = GetUser(strLine);				
				LoadedUsers.add(NewUser);					  
			}
			br.close();
			return LoadedUsers;
		}
		catch (IOException e) {			
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	private User GetUser(String Line){
		String[] SplitedLine = Line.split(":");
		String Name = SplitedLine[USER_NAME];
		String Passwd = SplitedLine[USER_PASSWD];
		String Email = SplitedLine[USER_EMAIL];
		User NewUser;
		if (SplitedLine.length > USER_MARK){
			if (SplitedLine[USER_MARK].compareTo(ADMIN_MARK) == 0){
				NewUser = new Admin(Name, Passwd, Email);
				return NewUser;
			}
		}
		NewUser = new User(Name,Passwd,Email);	
		return NewUser;
	}

}
