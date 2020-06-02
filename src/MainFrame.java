//import java.awt.BorderLayout;
//import java.awt.Container;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.BufferedReader;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.List;
//
//import javax.swing.JFrame;
//import javax.swing.JScrollPane;
//import javax.swing.JTextArea;
//import javax.swing.text.DefaultCaret;
//
//import common.TabGenerator;
//
//public class MainFrame extends JFrame {
//	
//	private DetailsPanel detailsPanel;
//	protected static String tune;
//	protected static String sourceFile;
//	
//	public MainFrame(String title) {
//		super(title);
//		
//		// Set layout manager
//		setLayout(new BorderLayout());
//		
//		//Create Swing component
//		final JTextArea textArea = new JTextArea();
//		
//		// Cannot be modified by user
//		textArea.setEditable(false);
//		
//		// Always scroll to top after reloading
//		DefaultCaret caret = (DefaultCaret) textArea.getCaret();
//		caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
//		
//		// Add text area to Scrollable
//		JScrollPane scroll = new JScrollPane (textArea);
//	    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//	    scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
//		
//		detailsPanel = new DetailsPanel();
//		
//		detailsPanel.addDetailListener(new DetailListener() {
//
//			@Override
//			public void detailEventOccurred(DetailEvent e) {
//				// Clear pre existing content
//				textArea.setText("");
//				
//				// Set tune
//				tune = e.getTune();
//				
//				// Set source file
//				sourceFile = e.getFile();
//				
//				// Assign tuning references
//				TabGenerator.assignTuningReferences(true);
//				
//				// Save note groups
//				List<String> rawNotes = TabGenerator.readNoteGroups(sourceFile);
//				
//				// Add notes to text area
////				textArea.append("Notes found in file: " + rawNotes + "\n\n");
//				
//				// Transpose notes and add to text area
//				TabGenerator.transpose(rawNotes);
//				textArea.append("Tranposed notes: " + TabGenerator.noteGroups + "\n\n");
//				
//				// Validate the source
//				TabGenerator.validateSource(TabGenerator.noteGroups);
//				
//				// Record frets
//				TabGenerator.record();
//				
//				// Generate output file
//				TabGenerator.outputTabToFile(sourceFile);
//				
//				// Record output file name
//				String tranposeFileNameMod = "";
//				if (TabGenerator.transpose != 0) {
//					tranposeFileNameMod = "transposed_" + TabGenerator.transpose + "_";
//				}
//				String outputFileName = "tab_" + tranposeFileNameMod + sourceFile;
//				
//				// Create reader to read output file
//				BufferedReader reader;
//				
//				// Try to read file
//				try {
//					reader = new BufferedReader(new FileReader(outputFileName));
//					
//					// Try to add to text area
//					try {
////						for (int i = 1; i < 42; i++) {
////							textArea.append(reader.readLine() + "\n");
////						}
//						String check;
//						while ((check=reader.readLine()) != null) {
//							textArea.append(check + "\n");
//						}
//						// Close reader
//						reader.close();
//					} catch (IOException e1) {
//						e1.printStackTrace();
//					}
//				} catch (FileNotFoundException e1) {
//					e1.printStackTrace();
//				}
//			}
//			
//		});
//		
//		// Add Swing components to content pane
//		Container c = getContentPane();
//
//		c.add(scroll, BorderLayout.CENTER);
//		c.add(detailsPanel, BorderLayout.NORTH);
//
//	
//	}
//}
