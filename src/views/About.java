package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.UIManager;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextPane;
import java.awt.Rectangle;

public class About extends JDialog {

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
			About dialog = new About();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			//dialog.setVisible(true);
			// Center the GUI
			dialog.setLocationRelativeTo(null);
			dialog.pack();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public About() {
		setModal(true);
		setPreferredSize(new Dimension(300, 320));
		setResizable(false);
		setBounds(new Rectangle(100, 100, 300, 320));
		setSize(new Dimension(300, 320));
		setIconImage(Toolkit.getDefaultToolkit().getImage(About.class.getResource("/resources/guitar_64.png")));
		setTitle("About Guitar Tab Generator");
		setBackground(SystemColor.window);
		setMinimumSize(new Dimension(300, 320));
		setMaximumSize(new Dimension(300, 320));
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBounds(new Rectangle(0, 0, 300, 320));
		contentPanel.setSize(new Dimension(300, 320));
		contentPanel.setMaximumSize(new Dimension(300, 320));
		contentPanel.setMinimumSize(new Dimension(300, 320));
		contentPanel.setBackground(SystemColor.window);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnNewButton.setBackground(SystemColor.window);
		btnNewButton.setIcon(new ImageIcon(About.class.getResource("/resources/guitar_128.png")));
		
		JTextArea txtrGuitarTabGenerator = new JTextArea();
		txtrGuitarTabGenerator.setDisabledTextColor(SystemColor.textText);
		txtrGuitarTabGenerator.setEnabled(false);
		txtrGuitarTabGenerator.setEditable(false);
		txtrGuitarTabGenerator.setFont(new Font("Avenir Next", Font.BOLD, 20));
		txtrGuitarTabGenerator.setBackground(SystemColor.window);
		txtrGuitarTabGenerator.setText("Guitar Tab Generator");
		
		JTextArea txtrGuitarTabGenerator_1 = new JTextArea();
		txtrGuitarTabGenerator_1.setDisabledTextColor(SystemColor.textText);
		txtrGuitarTabGenerator_1.setEnabled(false);
		txtrGuitarTabGenerator_1.setEditable(false);
		txtrGuitarTabGenerator_1.setBackground(SystemColor.window);
		txtrGuitarTabGenerator_1.setText("Guitar Tab Generator Version 1.2");
		
		JTextArea txtrNoahBaculi = new JTextArea();
		txtrNoahBaculi.setDisabledTextColor(SystemColor.textText);
		txtrNoahBaculi.setEnabled(false);
		txtrNoahBaculi.setEditable(false);
		txtrNoahBaculi.setBackground(SystemColor.window);
		txtrNoahBaculi.setText("2019-2020");
		
		JTextArea txtrNoahBaculiAnd = new JTextArea();
		txtrNoahBaculiAnd.setBackground(SystemColor.window);
		txtrNoahBaculiAnd.setText("Noah Baculi and Adriana Regalado");
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(42)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(txtrNoahBaculiAnd, GroupLayout.PREFERRED_SIZE, 218, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
									.addComponent(txtrGuitarTabGenerator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(txtrGuitarTabGenerator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(106)
							.addComponent(txtrNoahBaculi, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(81)
							.addComponent(btnNewButton)))
					.addContainerGap(180, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(12)
					.addComponent(btnNewButton)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(txtrGuitarTabGenerator, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addGap(27)
					.addComponent(txtrGuitarTabGenerator_1, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtrNoahBaculi, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtrNoahBaculiAnd, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(9, Short.MAX_VALUE))
		);
        
		contentPanel.setLayout(gl_contentPanel);
	}
}
