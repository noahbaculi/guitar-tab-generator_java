package common;

import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class OpenFile {
	
	// Declare variable
	JFileChooser fileChooser = new JFileChooser();
	public StringBuilder sb = new StringBuilder();
	String fileName;
	
	public void pickFile() throws Exception {
		// Only text files can be selected
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Text file (*.txt)", "txt", "text");
		fileChooser.setFileFilter(filter);
		
		if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			
			// Get the file
			java.io.File file = fileChooser.getSelectedFile();
			
			// Save the file name
			fileName = fileChooser.getName(file);
			
			// Create a scanner for the file
			Scanner input = new Scanner(file);
			
			// Read text from file
			while(input.hasNext()) {
				sb.append(input.nextLine());
				sb.append("\n");
			}
			
			// Close scanner
			input.close();
		}
		else {
			sb.append("No file was selected.");
			fileName = "";
		}
	}
	
	public String getName() {
		return this.fileName;
	}

}
