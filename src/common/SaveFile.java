package common;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import views.GuitarGUI;

public class SaveFile {
	
	// Declare variable
	JFileChooser fileChooser = new JFileChooser();
	public StringBuilder sb = new StringBuilder();
	String fileName;
	
	public void pickFile() throws Exception {
		// Only text files can be selected
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Text file (*.txt)", "txt", "text");
		fileChooser.setFileFilter(filter);
		
		fileName = TabGenerator.outputFileName;
		fileName.toLowerCase();
		if (!fileName.contains(".txt")) {
			fileName += ".txt";
		}
		java.io.File file = new java.io.File(fileName);
		fileChooser.setSelectedFile(new java.io.File(fileName));
		
		if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			
			// Get file content and save to file
			String content = GuitarGUI.taGeneratedFile.getText();
			file = fileChooser.getSelectedFile();
			
			try {
				java.io.FileWriter fw = new java.io.FileWriter(file.getPath());
				fw.write(content);
				fw.flush();
				fw.close();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}	
		}
	}
}
