package gui;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class ProjectFileFilter extends FileFilter {


	public boolean accept(File file) {
		
		if(file.isDirectory()) {
			return true;
		}
		
		String name = file.getName();
		
		String extension = Utils.getFileExtension(name);
		
		if(extension == null) {
			return false;
		}
		
		if(extension.equals("pdbf")) {
			return true;
		}
		return false;
	}

	public String getDescription() {
		
		return "Project Database File(*.pdbf)";
	}

}
