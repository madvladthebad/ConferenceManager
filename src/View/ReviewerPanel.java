package View;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

import Model.Paper;
import Model.PaperSerialization;
import Model.User;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JTextArea;

/**
 * 
 * @author Matt Dufalo, Alex Stringham This class builds the panel that has the
 *         reviewer functionality.
 */
public class ReviewerPanel extends JPanel {

	private User user;

	private String userName;
	private JFileChooser chooser = new JFileChooser();
	private JList paperList;
	DefaultListModel listModel = new DefaultListModel();
	private JTextArea textArea;
	private Paper currentPaper;
	private File currentReview;

	//
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ReviewerPanel(int theWidth, int theHeight, User theUser) {

		user = theUser;

		userName = user.getFirstName() + " " + user.getLastName();
		// Window builder code
		setLayout(null);

		JLabel lblName = new JLabel(user.getFirstName() + " "
				+ user.getLastName());
		lblName.setBounds(12, 15, 300, 20);
		add(lblName);

		JButton btnSubmitReview = new JButton("Submit Review");
		btnSubmitReview.setBounds(460, 394, 146, 29);
		btnSubmitReview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(paperList.getSelectedValue()==null)
				{
					
				} else {
					int returnVal = chooser.showOpenDialog(ReviewerPanel.this);
					File file = null;
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						file = chooser.getSelectedFile();
					}
					currentReview = file;
					addReview(file);
					((Paper) paperList.getSelectedValue()).setReviewStatus();
					PaperSerialization.serializePapers();
				}
				
			}
		});
		add(btnSubmitReview);
		//get review form
		JButton btnGetReview = new JButton("Get Review Form");
		btnGetReview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Desktop.isDesktopSupported()) {

				    try {
				        File myFile = new File("Review Form.pdf");
				        Desktop.getDesktop().open(myFile);
				    } catch (IOException ex) {
				        // no application registered for file
				    }

					

				}

			}
		});
		btnGetReview.setBounds(91, 394, 193, 29);
		add(btnGetReview);

		paperList = new JList();
		paperList.setModel(listModel);
		paperList.setLayoutOrientation(JList.VERTICAL);
		paperList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		paperList.setBounds(35, 142, 123, 145);
		paperList.addListSelectionListener(new ListSelectionListener() {
			private Paper chosen;

			public void valueChanged(ListSelectionEvent e) {
				chosen = (Paper) paperList.getSelectedValue();
				currentPaper = chosen;
				updateText(chosen);
			}

		});
		updateList(paperList);
		add(paperList);
		//button for seeing users review
		JButton btnNewButton = new JButton("See my review");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (currentReview == null) {
					JOptionPane.showMessageDialog(null,
							"You haven't submitted one");
				} else {
					if (Desktop.isDesktopSupported()) {
						try {
							File myFile = ((Paper) paperList.getSelectedValue())
									.getReview(0);
							Desktop.getDesktop().open(myFile);
						} catch (IOException ex) {
							// no application registered for file
						}
					}
				}
			}
		});
		btnNewButton.setBounds(400, 258, 159, 29);
		add(btnNewButton);

		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(211, 143, 151, 145);
		add(textArea);
		//button to view selected paper
		JButton btnViewPaper = new JButton("View Paper");
		btnViewPaper.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentPaper == null) {
					JOptionPane.showMessageDialog(null,
							"A paper has not been selected.");
				} else if (Desktop.isDesktopSupported()) {
					try {
						File myFile = ((Paper) paperList.getSelectedValue())
								.getFile();
						Desktop.getDesktop().open(myFile);
					} catch (IOException ex) {
						// no application registered for file
					}
				}
			}
		});
		btnViewPaper.setBounds(330, 394, 115, 29);
		add(btnViewPaper);
		
		JLabel lblReviewer = new JLabel(user.role());
		lblReviewer.setBounds(12, 36, 146, 16);
		add(lblReviewer);
		
		JLabel lblConference = new JLabel("Conference: " + user.getMyConfTitle());
		lblConference.setBounds(12, 57, 146, 16);
		add(lblConference);
		
		JLabel lblPapers = new JLabel("Papers: ");
		lblPapers.setBounds(35, 106, 85, 20);
		add(lblPapers);

	}

	/**
	 * Adds File to papers review array
	 * 
	 * @param file
	 */
	public void addReview(File file) {
		((Paper) paperList.getSelectedValue()).setReview(file);
	}
	/**
	 * @author Matt Dufalo
	 * @param list updates Jlist to most recent information
	 */
	public void updateList(JList list) {
		System.out.println(userName);
		// listModel.clear();
		for (int i = 0; i < PaperSerialization.papers.size(); i++) {
			for (int j = 0; j < 3; j++) {

				if (userName.equals(PaperSerialization.papers.get(i)
						.getTheReviewer(j)) && !userName.equals(PaperSerialization.papers.get(i).getAuthor())) {
					listModel.addElement(PaperSerialization.papers.get(i));
				}
			}
		}
		this.repaint();
	}
	/**
	 * @author Matt Dufalo
	 * 
	 * @param thePaper updates status of text area to selected paper.
	 */
	public void updateText(Paper thePaper) {
		textArea.setText(thePaper.getFullStatus());
		this.repaint();

	}
}
