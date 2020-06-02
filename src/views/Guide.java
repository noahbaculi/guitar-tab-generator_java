package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.UIManager;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.SystemColor;
import java.awt.Toolkit;

public class Guide extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		try {
			Guide dialog = new Guide();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			// Center the GUI
			dialog.setLocationRelativeTo(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Guide() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Guide.class.getResource("/resources/guitar_64.png")));
		setTitle("Guitar Tab Generator Guide");
		setBackground(SystemColor.window);
		setMinimumSize(new Dimension(550, 500));
		setMaximumSize(new Dimension(550, 1000));
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.window);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(SystemColor.window);
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
					.addGap(0))
		);
		{
			JTextArea taGuide = new JTextArea();
			taGuide.setDisabledTextColor(SystemColor.windowText);
			taGuide.setBorder(new EmptyBorder(0, 0, 0, 0));
			taGuide.setBackground(SystemColor.window);
			// Cannot be selected
			taGuide.setEnabled(false);
			// Cannot be edited
			taGuide.setEditable(false);
			// Set the text for the guide
			taGuide.setText("Thanks for using the Guitar Tab Generator Program!\n\n----------------------------------------------------------------\n\nChoosing a file:\nUsers can load an existing text file or create a new text file.\n\nEditing the file:\nOnce the file loads you can...\n\t- Edit any of the notes (if you loaded an existing file)\n\t- Add all of the notes (if you created a file)\n\nFile requirements:\n- May contain song title and artist in the first few lines of the file\n- Must contain valid notes and/or chords\n- Must only contain notes within valid range\n- Must only have one note/chord in each line\n\nCreating measures:\n- To make a bar line...\n\tadd an empty line in between the two notes you want to split\n- To make a double bar...\n\tadd two empty lines in between the two notes you want to split\n\nGenerating the tab:\nAfter clicking \"Generate\", if there is an error please address it before trying again.\nThe tab should appear to the right of the window.\nThe measures cannot be resized.\nThe tab cannot be modified/edited.\n\nSaving the tab:\nIt is possible to save the tab as a file.\nChoose the location you would like to save the file to.\nYou may change the suggested file name.\nThe file can only be saved as a text file.\n\nWarning: Saving a new file with the same name as an old file will replace it!\n\n----------------------------------------------------------------\n\nPlease feel free to contact Noah Baculi (nbaculi@gmail.com) with any\nfeedback or suggestions for improvements in the project!\n");
			scrollPane.setViewportView(taGuide);
			// Scroll up
			taGuide.setCaretPosition(0);
		}
		contentPanel.setLayout(gl_contentPanel);
	}

}
