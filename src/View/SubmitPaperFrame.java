
/**
 * @author Matt Dufalo
 * This class is a form for submitting papers.
 * 
 */
package View;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Model.Conference;
import Model.DatabaseManager;
import Model.Paper;
import Model.PaperSerialization;
import Model.User;

import javax.swing.JComboBox;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JScrollPane;

public class SubmitPaperFrame extends JFrame implements ActionListener {
	private JLabel paperName;
	private JTextField paperNameInput;
	private JLabel authorName;
	private JFileChooser chooser = new JFileChooser();
	private JButton findFileButton;
	private JButton submitPaper;
	private JLabel fileDisplay;
	private JPanel panel;
	private User user;
	private AuthorPanel aPanel;
	private JList list, confList;
	private JComboBox comboBox;
	private JLabel lblConference;
	File file = null;

	private List<Conference> conf;
	private DefaultComboBoxModel listModel;
	/**
	 * @author Matt Dufalo
	 * Contructs the frame to submit papers
	 * @param height height of the frame
	 * @param width  width of the frame
	 * @param user the user using the frame.
	 * @param authorPanel 
	 * @param myDm database manager used to get values
	 * @param list 
	 * @param userPanel 
	 */
	public SubmitPaperFrame(int height, int width, User user, 
			AuthorPanel authorPanel, JList theList, DatabaseManager myDm)
	{	
		this.setSize(406, 264);

		panel = new JPanel();
		panel.setSize(400, 400);
		this.user = user;
		aPanel = authorPanel;
		list = theList;
		paperName = new JLabel("Enter name of paper: ");
		paperName.setBounds(15, 9, 156, 20);
		paperNameInput = new JTextField(15);
		paperNameInput.setBounds(166, 6, 216, 26);
		fileDisplay = new JLabel("File: ");
		fileDisplay.setBounds(15, 126, 299, 20);

		authorName = new JLabel("Author Name: " + user.getFirstName() + " " + user.getLastName());
		authorName.setBounds(15, 45, 299, 20);
		findFileButton = new JButton("Find File");
		findFileButton.setBounds(15, 81, 91, 29);
		submitPaper = new JButton("Submit Paper");
		submitPaper.setBounds(15, 177, 127, 29);
		submitPaper.addActionListener(this);
		findFileButton.addActionListener(this);
		panel.setLayout(null);

		panel.add(paperName);
		panel.add(paperNameInput);
		panel.add(authorName);
		panel.add(fileDisplay);
		panel.add(findFileButton);
		panel.add(submitPaper);
			
		this.setLocationRelativeTo(null);
		getContentPane().add(panel);
		

		confList = new JList();
		
		listSelectionListener();

		listModel = new DefaultComboBoxModel();
		//initialize list model
		initializeConf(myDm, user);
		confList.setModel(listModel);
		comboBox = new JComboBox(listModel);
		comboBox.setModel(listModel);
		comboBox.setBounds(235, 78, 141, 22);
		panel.add(comboBox);

		lblConference = new JLabel("Conference:");
		lblConference.setBounds(152, 78, 70, 16);
		panel.add(lblConference);



	}


	/**
	 * @author Melaku Mehiretu
	 * @param myDm
	 * @param user2
	 */
	private void initializeConf(DatabaseManager myDm, User user2) 
	{
		conf = new ArrayList<Conference>();
		conf = myDm.getConferences();

		for (int i = 0; i < conf.size(); i++)
		{
			listModel.addElement(conf.get(i));

		}


	}

	/**
	 * @author Melaku Mehiretu
	 */
	private void listSelectionListener() 
	{
		confList.addListSelectionListener(new ListSelectionListener() {
			private Conference curConf;

			public void valueChanged(ListSelectionEvent evt) {
				if (evt.getValueIsAdjusting())
					return;

				int i = confList.getSelectedIndex();
				curConf = (Conference) confList.getModel().getElementAt(i);
			}
		});

	}


	/**
	 * @author Matt Dufalo
	 * Action performed for buttons on panel
	 */
	@Override
	public void actionPerformed(ActionEvent event)
	{
		//when find file button is pressed pull up file chooser and get file
		if(event.getSource() == findFileButton)
		{
			try {
				getFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		if(event.getSource() == submitPaper)
		{
			//if a user is program chair trying to submit a paper to their own conference. don't allow them
			if(((Conference)comboBox.getSelectedItem()).getConfId() == user.getMyConferenceId() 
					&& user.getRoleId() == 1)
			{
				JOptionPane.showMessageDialog(null, "A Program Chair cannot submit a paper to their own conference,"
						+ " pick a different conference");
			} else {
				//Creates paper object based on selected file and input fields
				if(paperNameInput.getText().isEmpty() || file == null)
				{
					JOptionPane.showMessageDialog(null, "Please name your paper and Pick a File");
				}else{
					String name = paperNameInput.getText();
					Paper addedPaper = new Paper(name, user.getFirstName() + " " 
							+ user.getLastName() ,file,((Conference)comboBox.getSelectedItem()));
					//file to be copied into database
					File  dest = new File("papers/" +addedPaper.getFile().getName());
					try {
						copyFile(file, dest);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//change file path to one in database
					addedPaper.setFile(dest);
					
					PaperSerialization.papers.add(addedPaper);
					PaperSerialization.serializePapers();

					
					aPanel.updateList(list);
					panel.repaint(); 
					this.dispose();	
				}
			}
			
			
			
		}
	}


	/**
	 * @author Matt Dufalo
	 * Opens file chooser to select paper
	 * @throws IOException
	 */
	private void getFile() throws IOException {
		
		int returnVal = chooser.showOpenDialog(SubmitPaperFrame.this);

		
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			file = chooser.getSelectedFile();
			//paperNameInput.setText(file.getName());
		}
		fileDisplay.setText("File: " + file.getName());
		
		  

	}
	/**
	 * @author Matt Dufalo
	 * Copies selected file into file directory
	 * @param sourceFile file to be copied
	 * @param destFile file after being copied
	 * @throws IOException
	 */
	private void copyFile(File sourceFile, File destFile)
			throws IOException {
		if (!sourceFile.exists()) {
			return;
		}
		if (!destFile.exists()) {
			destFile.createNewFile();
		}
		FileChannel source = null;
		FileChannel destination = null;
		source = new FileInputStream(sourceFile).getChannel();
		destination = new FileOutputStream(destFile).getChannel();
		if (destination != null && source != null) {
			destination.transferFrom(source, 0, source.size());
		}
		if (source != null) {
			source.close();
		}
		if (destination != null) {
			destination.close();
		}

	}
	
	
}
