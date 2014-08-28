import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

class ExtensionFileFilter extends FileFilter {

	String description;
	String extensions[];

	public ExtensionFileFilter(String description, String extension) {
		this(description, new String[] { extension });
	}

	public ExtensionFileFilter(String description, String extensions[]) {
		if (description == null) {
			this.description = "." + extensions[0] + " file";
		} else {
			this.description = description;
		}
		this.extensions = (String[]) extensions.clone();
		toLower(this.extensions);
	}

	private void toLower(String array[]) {
		for (int i = 0, n = array.length; i < n; i++) {
			array[i] = array[i].toLowerCase();
		}
	}

	public String getDescription() {
		return description;
	}

	public boolean accept(File file) {
		if (file.isDirectory()) {
			return true;
		} else {
			String path = file.getAbsolutePath().toLowerCase();
			for (int i = 0, n = extensions.length; i < n; i++) {
				String extension = extensions[i];
				if ((path.endsWith(extension) && (path.charAt(path.length()
						- extension.length() - 1)) == '.')) {
					return true;
				}
			}
		}
		return false;
	}
}

public class JFileChooserOpen {

	JFileChooser fileChooser;

	public JFileChooserOpen() {

		// TODO Auto-generated constructor stub

		String path = System.getProperty("user.home")+File.separator+"Desktop";
		
		fileChooser = new JFileChooser(path);
		FileFilter jpegFilter = new ExtensionFileFilter(null, "json");

		fileChooser.setAcceptAllFileFilterUsed(false);

		fileChooser.addChoosableFileFilter(jpegFilter);
		fileChooser.showOpenDialog(null);

	}
}