package dataAccessLayer;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import role.IAdmin;
import role.User;

public class UserSaver {
	public void Save(ArrayList<User> Users,String FileName)
	{
		BufferedWriter br;
		try
		{
			FileOutputStream stream = new FileOutputStream(FileName);
			br = new BufferedWriter(new OutputStreamWriter(stream));
			for (User user : Users) {
				br.write(user.getName()+ ":" + user.getPasswd()+":"+user.getEmail());
				if (user instanceof IAdmin)
				{
					br.write(":" + "Admin");
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
