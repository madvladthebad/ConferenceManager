package View;

import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Model.DatabaseManager;
import Model.Paper;
import Model.PaperSerialization;
import Model.User;

import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JTextArea;

public class AuthorPanel extends JPanel implements ActionListener {
	private JLabel name;
	private JLabel role;

	private JLabel searchPaper;
	private JLabel submittedPapers;
	private JLabel conferences;
	private JTextField search;
	private JButton submitPaper;
	private int myWidth, myHeight;
	private User user;
	@SuppressWarnings("rawtypes")
	private JList list;
	JTextArea textArea;
	JButton DeleteSelectedPaper;
	DefaultListModel listModel = new DefaultListModel();
	private JLabel lblChooseConference;
	private JButton btnViewPaper;
	private String userName;
	private Paper chosen;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private DatabaseManager myDm;
	private Paper currentPaper;

	/**
	 * @author Matt Dufalo
	 * 
	 *         Create a new Author panel that has all the labels, buttons, and
	 *         menus.
	 * @param width
	 *            Width of the panel.
	 * @param height
	 *            Height of the panel.
	 */
	public AuthorPanel(int width, int height, User theUser, DatabaseManager dm) {
		myWidth = width;
		myHeight = height;
		myDm = dm;
		setPreferredSize(new Dimension(width, height));
		user = theUser;
		userName = user.getFirstName() + " " + user.getLastName();
		name = new JLabel( user.getFirstName() + " "
				+ user.getLastName());
		name.setBounds(29, 31, 223, 20);
		role = new JLabel(user.role());
		role.setBounds(29, 64, 223, 20);
		submitPaper = new JButton("Submit Paper");
		submitPaper.setBounds(470, 380, 127, 29);
		submitPaper.addActionListener(this);
		setLayout(null);

		this.add(name);
		this.add(role);
		this.add(submitPaper);

		DeleteSelectedPaper = new JButton("Delete Selected Paper");
		DeleteSelectedPaper.setBounds(257, 380, 198, 29);
		DeleteSelectedPaper.addActionListener(this);
		add(DeleteSelectedPaper);

		// This part intializes list then adds to panel
		list = new JList(listModel);
		list.setLayoutOrientation(JList.VERTICAL);
		updateList(list);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(list.getSelectedValue() != null)
				{
					chosen = (Paper) list.getSelectedValue();
					textArea.setText(chosen.getFullStatusAuthor());
				}
				currentPaper = chosen;
			}
		});
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBounds(29, 111, 189, 234);
		add(list);

		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(374, 216, 223, 126);
		textArea.setText("Select a paper to see status");
		add(textArea);


		btnViewPaper = new JButton("View Paper");
		btnViewPaper.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(currentPaper == null)
				{
					JOptionPane.showMessageDialog(null, "A paper has not been selected.");
				} else {
					if (Desktop.isDesktopSupported()) {
						try {
							File myFile = ((Paper) list.getSelectedValue())
									.getFile();
							Desktop.getDesktop().open(myFile);
						} catch (IOException ex) {
							// no application registered for file
						}
					}
				}
			}
		});
		btnViewPaper.setBounds(127, 380, 115, 29);
		add(btnViewPaper);
		
		btnNewButton = new JButton("Review 1");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(list.getSelectedValue() == null)
				{
					
				} else{
					if(((Paper)list.getSelectedValue()).getAcceptReject() == 0)
					{
						JOptionPane.showMessageDialog(null, "Can't view until decision has been made.");
					} else {
					if(((Paper)list.getSelectedValue()).getReview(0) == null)
					{
						JOptionPane.showMessageDialog(null, "This review isn't assigned yet.");
					} else{
							if (Desktop.isDesktopSupported()) {
						    	try {
						        	File myFile = ((Paper)list.getSelectedValue()).getReview(0);
						        	Desktop.getDesktop().open(myFile);
						    	} catch (IOException ex) {
						        // no application registered for file
						    	}
							}
						}
					}
				
				}
				
			}
		});
		btnNewButton.setBounds(233, 170, 115, 29);
		add(btnNewButton);
		
		btnNewButton_1 = new JButton("Review 2");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(list.getSelectedValue() == null)
				{
					
				} else{
					if(((Paper)list.getSelectedValue()).getAcceptReject() == 0)
					{
						JOptionPane.showMessageDialog(null, "Can't view until decision has been made.");
					} else {
					if(((Paper)list.getSelectedValue()).getReview(1) == null)
					{
						JOptionPane.showMessageDialog(null, "This review isn't assigned yet.");
					} else{
							if (Desktop.isDesktopSupported()) {
								try {
						    		File myFile = ((Paper)list.getSelectedValue()).getReview(1);
						        	Desktop.getDesktop().open(myFile);
								} catch (IOException ex) {
						    	// no application registered for file
						    	}
							}
						}
					}
				
				}
				
			}
		});
		btnNewButton_1.setBounds(233, 205, 115, 29);
		add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("Review 3");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(list.getSelectedValue() == null)
				{
					
				} else{
					if(((Paper)list.getSelectedValue()).getAcceptReject() == 0)
					{
						JOptionPane.showMessageDialog(null, "Can't view until decision has been made.");
					} else {
						if(((Paper)list.getSelectedValue()).getReview(2) == null)
						{
							JOptionPane.showMessageDialog(null, "This review isn't assigned yet.");
						} else{
							if (Desktop.isDesktopSupported()) {
							    try {
							        File myFile = ((Paper)list.getSelectedValue()).getReview(2);
							        Desktop.getDesktop().open(myFile);
							    } catch (IOException ex) {
							        // no application registered for file
							    }
							}
						}
					}
				}
				
				
				
				
			}
		});
		btnNewButton_2.setBounds(233, 240, 115, 29);
		add(btnNewButton_2);
	}

	/**
	 * @author Matt Dufalo
	 * 
	 *         Create a submit paper button that brings up a file chooser.
	 *         choose a paper from the file chooser and when submit is pressed,
	 *         send the paper to a conference.
	 */
	private void submitPaper() {
		JFrame submitFrame = new SubmitPaperFrame(400, 400, user, this, list, myDm);
		submitFrame.setVisible(true);
	}

	/**
	 * @author Matt Dufalo
	 * 
	 *         action listeners for buttons on this panel
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == submitPaper) {
			submitPaper();
			updateList(list);
		}
		if (event.getSource() == DeleteSelectedPaper) {
			File myFile = new File("Conference Review Form.docx");
			Paper toRemove = (Paper) list.getSelectedValue();
			if(toRemove == null)
			{
				JOptionPane.showMessageDialog(null, "A paper hasn't been selected.");
			}
			deleteFile(toRemove.getFile().toPath());
			for(int i = 0; i < PaperSerialization.papers.size(); i++)
			{
			if(toRemove.getTitle().equals(PaperSerialization.papers.get(i).getTitle()))
				{
					textArea.setText("Select a paper to see status");
					PaperSerialization.papers.get(i).setPaperFile(myFile);
					
					PaperSerialization.papers.remove(i);
					updateList(list);
					PaperSerialization.serializePapers();
				}
			}
			

		}
	}

	private void deleteFile(Path path) {
		try {
		    Files.delete(path);
		} catch (NoSuchFileException x) {
		    System.err.format("%s: no such" + " file or directory%n", path);
		} catch (DirectoryNotEmptyException x) {
		    System.err.format("%s not empty%n", path);
		} catch (IOException x) {
		    // File permission problems are caught here.
		    System.err.println(x);
		}
		
	}

	/**
	 * @author Matt Dufalo
	 * 
	 *         Update status field when a paper is selected.
	 * @param target
	 *            the paper to to get status from
	 */
	public void updateText(Paper target) {

		textArea.setText(target.getFullStatusAuthor());

		this.repaint();
	}

	/**
	 * @author Matt Dufalo
	 * 
	 *         Updates Jlist that holds selectable papers.
	 * @param list
	 */
	public void updateList(JList list) {
		listModel.clear();

		for (int i = 0; i < PaperSerialization.papers.size(); i++) {
			if (userName.equals(PaperSerialization.papers.get(i).getAuthor())) {
				listModel.addElement(PaperSerialization.papers.get(i));
				
			}
		}
		
		this.repaint();
	}
	private static void copyFileUsingChannel(File source, File dest) throws IOException {
	    FileChannel sourceChannel = null;
	    FileChannel destChannel = null;
	    try {
	        sourceChannel = new FileInputStream(source).getChannel();
	        destChannel = new FileOutputStream(dest).getChannel();
	        destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
	       }finally{
	           sourceChannel.close();
	           destChannel.close();
	       }
	}

}
