import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class UserLoader {
	public ArrayList<User> GetUsers(String FileName)
	{
		BufferedReader br;
		try
		{
			ArrayList<User> LoadedUsers = new ArrayList<User>();
			FileInputStream stream = new FileInputStream(FileName);
			br = new BufferedReader(new InputStreamReader(stream));
			String strLine;
			while ((strLine = br.readLine()) != null)   {
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
	
	private User GetUser(String Line)
	{
		String[] SplitedLine = Line.split("|");
		String Name = SplitedLine[0];
		String Passwd = SplitedLine[1];
		User NewUser = new User(Name,Passwd);	
		return NewUser;
	}

}
