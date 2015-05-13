package View;

import Model.DatabaseManager;
import Model.Paper;
import Model.PaperSerialization;
import Model.Reviewer;
import Model.SubprogramChair;
import Model.User;

import javax.swing.CellRendererPane;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.ListCellRenderer;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollBar;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JComboBox;

public class SubChairPanel extends JPanel
{

	private List <Reviewer> reviewers;
	private List <Paper> papers;
	private JList list;

	private DefaultListModel model;
	private Reviewer curReviewer;
	private Paper curPaper;
	private JTextArea textArea;
	private JButton btnAssignPapers;
	private JComboBox comboBox;
	protected String option = "Reviewers";
	protected int selectedIndex;
	public SubChairPanel(int width, int height, final User user, final DatabaseManager db)
	{
		setLayout(null);

		JLabel lblName = new JLabel(user.getFirstName() +" "+ user.getLastName());
		lblName.setBounds(12, 24, 80, 16);
		add(lblName);


		JLabel lblSubprogramChair = new JLabel("Subprogram Chair");
		lblSubprogramChair.setBounds(12, 45, 113, 16);
		add(lblSubprogramChair);
		JLabel lblConference = new JLabel("Conference: "+ user.getMyConfTitle());
		lblConference.setBounds(12, 65, 146, 16);
		add(lblConference);

		model = new DefaultListModel();
		//initializeReviewers(db, user);
		list = new JList(model);
		String [] listOption = new String [] {"Reviewers", "Papers"};
		comboBox = new JComboBox(listOption);

		//Reviewers are default lists
		initializeReviewers(db, user);
		listSelectionListener("Reviewers");

		comboBox.addActionListener(new ActionListener() {


			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox) e.getSource();
				option = (String) cb.getSelectedItem();

				if(option.equals("Papers"))
				{
					initializePapers(user);				

					btnAssignPapers.setText("Recommend Paper");				

				}
				else 
				{
					System.out.println("Action: "+option);
					initializeReviewers(db, user);					
					btnAssignPapers.setText("Assign Papers");

				}
				listSelectionListener(option);
				repaint();

			}

		});
		comboBox.setBounds(81, 121, 157, 22);
		add(comboBox);
		JScrollPane pane = new JScrollPane(list);
		// add(pane, BorderLayout.NORTH);
		pane.setBounds(12, 153, 226, 251);
		add(pane);
		//curReviewer = reviewers.get(1);
		textArea = new JTextArea();
		textArea.setEditable(false);
		//textArea.setText(curReviewer.revInfo());



		JScrollPane textPane = new JScrollPane(textArea);
		textPane.setBounds(310, 151, 290, 253);

		add(textPane);

		btnAssignPapers = new JButton("Assign Papers");
		btnAssignPapers.setEnabled(false);
		btnAssignPapers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Identify the action of the button
				JButton source = (JButton) e.getSource();
				String action = source.getText();

				if(action.equals("Assign Papers"))
				{
					AssignPapers paperAssign = new AssignPapers(curReviewer, user, db);
					paperAssign.pack();
					paperAssign.setVisible(true);
				}
				else if(action.equals("Recommend Paper"))
				{
					//Paper is recommended to Program chair
					curPaper.setRecommendation(1);
					textArea.setText("Status of this paper "+ 
							" has changed to: \n" + curPaper.getRecommendation());
					btnAssignPapers.setText("Unrecommend Paper");
				}
				else if(action.equals("Unrecommend Paper"))
				{
					//Paper is unrecommended to Program chair
					curPaper.setRecommendation(2);
					textArea.setText("Status of this paper "+ 
							" has changed to: \n" + curPaper.getRecommendation());
					btnAssignPapers.setText("Recommend Paper");
				}

				PaperSerialization.serializePapers();
			}
		});
		btnAssignPapers.setBounds(12, 417, 226, 25);
		add(btnAssignPapers);

		JLabel lblReviewers = new JLabel("List: ");
		lblReviewers.setBounds(12, 124, 113, 16);
		add(lblReviewers);





	}
	//Initialize the list with papers assigned to this subprogram chair
	private void initializePapers(User user) 
	{
		papers = new ArrayList<Paper>();
		for(int i = 0; i < PaperSerialization.papers.size(); i++)
		{
			Paper paper = PaperSerialization.papers.get(i);
			for(int j = 0;  j < paper.getChairList().size(); j++)
			{
				//add papers assigned to this sub chair to the paper list
				if(user.getUserId() == paper.getChairList().get(j).getUserId())
				{
					papers.add(paper);
					break;
				}
			}
		}
		model.clear();
		for (int i = 0; i < papers.size(); i++)
		{
			model.addElement(papers.get(i));

		}


	}
	/**
	 * Get all reviewers from the database
	 * @param db The database.
	 */
	public void initializeReviewers(DatabaseManager db, User user)
	{
		reviewers = new ArrayList<Reviewer>();
		for(int i = 0; i < db.getUsers().size(); i++)
		{
			//role id = 0 or null--> author, role id= 1 --> PChair
			//role id = 2 --> SubChair, 4 = reviewer
			User curUser = db.getUsers().get(i);
			if(curUser.getRoleId() == 4 )
			{
				if((curUser).getMyConferenceId() == user.getMyConferenceId())
					reviewers.add((Reviewer) curUser);
			}
		}
		model.clear();
		for (int i = 0; i < reviewers.size(); i++)
		{
			model.addElement(reviewers.get(i));

		}


	}	

	private void listSelectionListener(String source)
	{

		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent evt) {
				if (evt.getValueIsAdjusting())
					return;



				textArea.setText("");		        
				selectedIndex = list.getSelectedIndex();
				System.out.println("selected index:" + selectedIndex);
				if(option.equals("Papers") && selectedIndex != -1)
				{		
					curReviewer = null;
					curPaper = (Paper) list.getModel().getElementAt(selectedIndex);
					textArea.setText(curPaper.getFullStatus());
					if(curPaper.recomendationValue() == 1)
					{
						btnAssignPapers.setText("Unrecommend Paper");
					}
				}
				else if(option.equals("Reviewers")&& selectedIndex != -1)
				{		
					curPaper = null;
					curReviewer = (Reviewer) list.getModel().getElementAt(selectedIndex);				       
					textArea.setText(curReviewer.revInfo(curReviewer));			        
				}
				//button is enabled if reviewers option is selected and a reviewer is selected from the list or
				//if papers option is selected and a paper is selected from the list
				//other wise assign button is disabled.
				if((curReviewer != null && option.equals("Reviewers"))|| 
						(curPaper != null && option.equals("Papers")))
				{
					btnAssignPapers.setEnabled(true);

				}
				else 
				{
					btnAssignPapers.setEnabled(false);
				}
				repaint();
			}
		});
	}
}

