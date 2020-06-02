import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.EventListenerList;
import javax.swing.text.DefaultCaret;

public class DetailsPanel extends JPanel {
	
	private static final long serialVersionUID = 6915622549267792262L;
	
	private EventListenerList listenerList = new EventListenerList();

	public DetailsPanel() {
		// Set size of panel
		Dimension size = getPreferredSize();
		size.height = 300;
		setPreferredSize(size);
		// Set panel border
		setBorder(BorderFactory.createEtchedBorder());
		
		// Create label
		JLabel tuningLabel = new JLabel("Select the guitar tuning: ");
		tuningLabel.setAlignmentX(LEFT_ALIGNMENT);
		
		// Create drop down
		String[] tuning = { "Standard", "Open G", "Open D", "C6", "Dsus4" };
		JComboBox<String> tuningList = new JComboBox<String>(tuning);
		
		// Create label
		JLabel sourceLabel = new JLabel("Select the source file: ");
		sourceLabel.setAlignmentX(LEFT_ALIGNMENT);
				
//		// Create drop down
//		String[] source = { "fur_elise_notes.txt", "guitar_range.txt", "hey_jude_notes.txt", "test_notes.txt", "twinkle_twinkle_notes.txt" };
//		JComboBox<String> sourceList = new JComboBox<String>(source);
		
		// Create load / create buttons
		JButton loadBtn = new JButton("Load");
		JButton createBtn = new JButton("Create");
		
		// Create text area for original notes
		JTextArea textArea = new JTextArea();
		
		// Can be modified by user
		textArea.setEditable(true);
		
		// Always scroll to top after reloading
		DefaultCaret caret = (DefaultCaret) textArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
		
		// Add text area to Scrollable
		JScrollPane scroll = new JScrollPane (textArea);
	    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		// Create Generate button
		JButton btn = new JButton("Generate");
		
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String tune = (String) tuningList.getSelectedItem();
//				String file = (String) sourceList.getSelectedItem();
//				
//				fireDetailEvent(new DetailEvent(this, tune, file));
			}
			
		});
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		
		// First column
		gc.anchor = GridBagConstraints.LINE_END;
		gc.weightx = 0.5;
		gc.weighty = 0.5;
		
		gc.gridx = 0;
		gc.gridy = 0;
		add(tuningLabel,gc);
		
		gc.gridx = 0;
		gc.gridy = 1;
		add(sourceLabel,gc);
		
		// Second column
		gc.anchor = GridBagConstraints.LINE_START;
			
		gc.gridx = 1;
		gc.gridy = 0;
		add(tuningList,gc);
		
//		gc.gridx = 1;
//		gc.gridy = 1;
//		add(sourceList,gc);  
		
		// Final row
		gc.weighty = 10;
		
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.gridx = 1;
		gc.gridy = 2;
		add (textArea, gc);
		gc.gridx = 1;
		gc.gridy = 3;
		add(btn,gc);
	}
	
	public void fireDetailEvent(DetailEvent event) {
		Object[] listeners = listenerList.getListenerList();
		
		for(int i = 0; i < listeners.length; i += 2) {
			if (listeners[i] == DetailListener.class) {
				((DetailListener) listeners[i + 1]).detailEventOccurred(event);
			}
		}
	}
	
	public void addDetailListener(DetailListener listener) {
		listenerList.add(DetailListener.class, listener);
	}
	
	public void removeDetailListener(DetailListener listener) {
		listenerList.remove(DetailListener.class, listener);
	}
}
