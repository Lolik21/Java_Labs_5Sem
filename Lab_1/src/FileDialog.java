import java.io.File;
import javax.swing.JFileChooser;

public class FileDialog {
	public String GetFileName()
	{
		JFileChooser fileChooser = new JFileChooser();
		int ret = fileChooser.showOpenDialog(null);
		if (ret == fileChooser.APPROVE_OPTION)
		{
			File file = fileChooser.getSelectedFile();
			return file.getAbsolutePath();
		}
		return null;
	}
}
