package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FileDialog;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;

import java.awt.Panel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.ScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.text.DefaultCaret;

import common.OpenFile;
import common.SaveFile;
import common.TabGenerator;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import java.awt.Rectangle;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class GuitarGUI extends JFrame {

	private JPanel ctpMain;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton btnLoad;
	private JRadioButton rbExisting;
	public static String fileName;
	public static JTextArea taLoadedFile;
	private JButton btnGenerateTab;
	public static JTextArea taGeneratedFile;
	
	public static String tune;
	protected static String sourceFile;
	private JComboBox<String> cbTuning;
	private JButton btnSave;
	private JMenuBar menuBar;
	private JMenu mnHelp;
	private JMenuItem mntmGuide;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// Create GUI
					GuitarGUI frame = new GuitarGUI();
					// Allow GUI to be visible
					frame.setVisible(true);
					// Center the GUI
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GuitarGUI() {
		setMinimumSize(new Dimension(847, 500));
		initComponents();
		createEvents();
	}
	
	// This method contains all of the code for creating and initializing components
	private void initComponents() {
		
		// Set GUI title
		setTitle("Guitar Tab Generator");
		
		// Using window listener, confirm user close - see end of createEvents() method
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		// Set GUI bounds (size)
		setBounds(100, 100, 847, 500);
		
		// Add a menu bar
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		// Add a menu
		mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		// Add a menu item to help user
		mntmGuide = new JMenuItem("Guitar Tab Generator Guide");
		mntmGuide.setIcon(new ImageIcon(GuitarGUI.class.getResource("/resources/help_16.png")));
		mnHelp.add(mntmGuide);
		
		// Create main content pane
		ctpMain = new JPanel();
		ctpMain.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(ctpMain);
		
		// Set GUI icon image
		setIconImage(Toolkit.getDefaultToolkit().getImage(GuitarGUI.class.getResource("/resources/guitar_24.png")));
		
		// Create scroll pane for generated file
		JScrollPane spGenerated = new JScrollPane();
				
		// Create left panel for loading files
		JPanel pnlLoadFile = new JPanel();
		pnlLoadFile.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Load a file", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		// Create radio buttons for left panel
		rbExisting = new JRadioButton("Use an existing file");
		rbExisting.setSelected(true);
		buttonGroup.add(rbExisting);
		JRadioButton rbCreate = new JRadioButton("Create a new file");
		buttonGroup.add(rbCreate);
		
		// Create jButton to load file
		btnLoad = new JButton("Load");
		
		// Set left panel layout to Group Layout
		GroupLayout gl_pnlLoadFile = new GroupLayout(pnlLoadFile);
		gl_pnlLoadFile.setHorizontalGroup(
			gl_pnlLoadFile.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlLoadFile.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlLoadFile.createParallelGroup(Alignment.LEADING)
						.addComponent(rbExisting)
						.addGroup(gl_pnlLoadFile.createSequentialGroup()
							.addComponent(rbCreate)
							.addPreferredGap(ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
							.addComponent(btnLoad)))
					.addContainerGap())
		);
		gl_pnlLoadFile.setVerticalGroup(
			gl_pnlLoadFile.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_pnlLoadFile.createSequentialGroup()
					.addComponent(rbExisting)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(rbCreate)
					.addContainerGap(51, Short.MAX_VALUE))
				.addGroup(gl_pnlLoadFile.createSequentialGroup()
					.addContainerGap(45, Short.MAX_VALUE)
					.addComponent(btnLoad)
					.addContainerGap())
		);
		
		// Create Generate Tab button
		btnGenerateTab = new JButton("Generate Tab  >");
		btnGenerateTab.setEnabled(false);
		
		// Create scroll pane for loaded file
		JScrollPane spLoaded = new JScrollPane();
		
		cbTuning = new JComboBox<String>();
		cbTuning.setModel(new DefaultComboBoxModel<String>(new String[] {"Standard", "Open G", "Open D", "C6", "Dsus4"}));
		
		JLabel lblSelectTheGuitar = new JLabel("Select the guitar tuning:");
		
		JLabel lblFilePreview = new JLabel("File Preview:");
		
		// Add button to save tab as file
		btnSave = new JButton("Save File");
		btnSave.setEnabled(false);
		
		// Set content pane layout to Group Layout
		GroupLayout gl_ctpMain = new GroupLayout(ctpMain);
		gl_ctpMain.setHorizontalGroup(
			gl_ctpMain.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_ctpMain.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_ctpMain.createParallelGroup(Alignment.LEADING)
						.addComponent(spLoaded, GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
						.addComponent(cbTuning, 0, 255, Short.MAX_VALUE)
						.addComponent(pnlLoadFile, GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
						.addComponent(btnGenerateTab, Alignment.TRAILING)
						.addGroup(gl_ctpMain.createSequentialGroup()
							.addGap(6)
							.addComponent(lblSelectTheGuitar))
						.addGroup(gl_ctpMain.createSequentialGroup()
							.addGap(6)
							.addComponent(lblFilePreview)))
					.addGap(18)
					.addGroup(gl_ctpMain.createParallelGroup(Alignment.TRAILING)
						.addComponent(spGenerated, GroupLayout.DEFAULT_SIZE, 499, Short.MAX_VALUE)
						.addComponent(btnSave))
					.addGap(12))
		);
		gl_ctpMain.setVerticalGroup(
			gl_ctpMain.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_ctpMain.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_ctpMain.createParallelGroup(Alignment.TRAILING)
						.addComponent(spGenerated, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE)
						.addGroup(gl_ctpMain.createSequentialGroup()
							.addComponent(pnlLoadFile, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(lblFilePreview)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(spLoaded, GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblSelectTheGuitar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cbTuning, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_ctpMain.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnGenerateTab)
						.addComponent(btnSave))
					.addContainerGap())
		);
		
		// Create text area for loaded file
		taLoadedFile = new JTextArea();
		spLoaded.setViewportView(taLoadedFile);
		taLoadedFile.setEditable(false);
		
		pnlLoadFile.setLayout(gl_pnlLoadFile);
		ctpMain.setLayout(gl_ctpMain);
		
		// Create text area to display generated file
		taGeneratedFile = new JTextArea();
		spGenerated.setViewportView(taGeneratedFile);
		taLoadedFile.setEditable(false);
	}

	// This method contains all of the code for creating events
	private void createEvents() {
		// Load button event handler
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rbExisting.isSelected()) {
					// Open File Explorer
					OpenFile of = new OpenFile();
					try {
						of.pickFile();
					} catch (Exception exception) {
						exception.printStackTrace();
					}
					
					// IF "Cancel" button was pressed, only do something if no file data stored
					if (of.sb.toString().equals("No file was selected.")) {
						// If text area already contains data
						String text = taLoadedFile.getText().trim();
						if (text.equals("")) {
							// Display in text area
							taLoadedFile.setText(of.sb.toString());
						}
					}
					else {
						// Display in text area
						taLoadedFile.setText(of.sb.toString());
						
						// Clear generated text area
						taGeneratedFile.setText("");
					}
					
					// Save file name
					fileName = of.getName();
				}
				else {
					// Pop up window to create file name
					ImageIcon icon = new ImageIcon(GuitarGUI.class.getResource("/resources/guitar_64.png"));
					fileName = (String) JOptionPane.showInputDialog(null, "Enter a file name: ", "Create a new file", JOptionPane.INFORMATION_MESSAGE, icon, null, "");
					// If "OK" button was clicked
					if (fileName != null) {
						// If no name given, auto-generate name
						if (fileName.equals("")) {
							fileName = "untitled.txt";
						}
						// Clear loaded text area
						taLoadedFile.setText("");
						
						// Include guidelines for file creation
						taLoadedFile.append("[Enter song name here]\n\n[Enter notes here]");
						
						// Clear generated text area
						taGeneratedFile.setText("");
					}
				}
				
				// Scroll up
				taLoadedFile.setCaretPosition(0);
				
				// Enable Generate button and allow user to edit text if file was selected
				if (fileName != null && !fileName.equals("")) {
					btnGenerateTab.setEnabled(true);
					taLoadedFile.setEditable(true);
				}
				// Disable Generate button and deny user access to edit if no file was selected
				if (taLoadedFile.getText().equals("No file was selected.")) {
					btnGenerateTab.setEnabled(false);
					taLoadedFile.setEditable(false);
				}
				
				// Disable Save button
				btnSave.setEnabled(false);
			}
		});
		
		// Generate button event handler
		btnGenerateTab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Clear pre existing content
				taGeneratedFile.setText("");
				
				// Set tune
				tune = (String) cbTuning.getSelectedItem();
				
				// Call main method
				TabGenerator.main(null);
				
				// Check for errors
				StringBuilder error = TabGenerator.checkForErrors();
				
				// If errors detected, create pop up
				if (error.length() != 0) {
					// Display error message
					JOptionPane.showMessageDialog(null, error.toString(), "ERROR", JOptionPane.ERROR_MESSAGE);
					
					// Disable save button
					btnSave.setEnabled(false);
				}
				else {
					StringBuilder print = TabGenerator.getSB();
					
					// Display in text area
					taGeneratedFile.setText(print.toString());
					
					// Scroll up
					taGeneratedFile.setCaretPosition(0);
					
					btnSave.setEnabled(true);
				}
			}
		});
		
		// Save button event handler
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Open File Explorer
				SaveFile sf = new SaveFile();
				try {
					sf.pickFile();
				} catch (Exception exception) {
					exception.printStackTrace();
				}
			}
		});
		
		// Help menu event handler
		mntmGuide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Guide guide = new Guide();
				guide.setVisible(true);
				
			}
		});
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				ImageIcon icon = new ImageIcon(GuitarGUI.class.getResource("/resources/guitar_64.png"));
				int confirmed = JOptionPane.showConfirmDialog(null, 
				        "Are you sure you want to exit the program?", "Exit",
				        JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, icon);

			    if (confirmed == JOptionPane.YES_OPTION) {
			      e.getWindow().dispose();
			    }
			  }
		});
	}
}
