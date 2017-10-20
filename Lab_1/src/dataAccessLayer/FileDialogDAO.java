package dataAccessLayer;
import java.io.File;
import javax.swing.JFileChooser;

public class FileDialogDAO {
	public String getFileName()
	{
		JFileChooser fileChooser = new JFileChooser();
		int ret = fileChooser.showOpenDialog(null);
		if (ret == JFileChooser.APPROVE_OPTION)
		{
			File file = fileChooser.getSelectedFile();
			return file.getAbsolutePath();
		}
		return null;
	}
}
