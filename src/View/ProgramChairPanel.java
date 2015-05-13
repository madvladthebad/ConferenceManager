package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

import Model.Conference;
import Model.DatabaseManager;
import Model.PaperSerialization;
import Model.ProgramChair;
import Model.Reviewer;
import Model.SubprogramChair;
import Model.User;

import java.awt.Color;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import javax.swing.JTextArea;

import java.awt.Font;

import javax.swing.JLabel;

import java.awt.SystemColor;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Vladimir Gudzyuk
 *
 */
public class ProgramChairPanel extends JPanel
{
	private DefaultListModel paperModel;
	
	private DefaultListModel userModel;
	
	private DefaultListModel SubChairModel;
	
	private DefaultListModel accDecModel;
	
	private ProgramChair myProgramChair;
	
	private SubprogramChair currentSubChair;
	
	private Model.Paper selectedPaper;
	
	private DatabaseManager myDm;
	
	private List<SubprogramChair> subChairs;
	
	private List<User> genUser;
	
	private List<Model.Paper> confPapers;
	
	private String conferenceTitle;
	
	private Conference theConf;
	
	JTextArea paperAssignments;
	
	private User selectedUser;
	
	private SubprogramChair newSubChair;
	
	JTextArea PaperInformationText;
	
	
	/**
	 * @author Vladimir Gudzyuk
	 * constructor takes in 
	 */
	public ProgramChairPanel(int width, int height, User theUser, DatabaseManager db)
	{
		PaperInformationText = new JTextArea();
		
		paperAssignments = new JTextArea();
		
		paperModel = new DefaultListModel();
		
		userModel = new DefaultListModel();
		
		SubChairModel = new DefaultListModel();
		
		accDecModel = new DefaultListModel();
		
		genUser = new ArrayList<User>();
		
		myDm = db;
		
		myProgramChair = (ProgramChair) theUser;
		
		confPapers = new ArrayList<Model.Paper>();
		
		subChairs = new ArrayList<SubprogramChair>();
		
		//sets the conference refrence
		
		for (int i =0; i < db.getConferences().size(); i++)
		{
			if (db.getConferences().get(i).getConfDescription().equals((myProgramChair).getMyConfDescription()))
					{
				
				theConf = db.getConferences().get(i);
				System.out.println("Conference Selected from list");
				break;
					}
		}
		System.out.print(db.toString());
		
		
		addPapersList();
		addSubChairList();
		
		setBackground(SystemColor.menu);
		setForeground(Color.WHITE);
		setLayout(null);
		
		JButton btnNewButtonAssignPaper = new JButton("Assign Papers To...");
		btnNewButtonAssignPaper.setBounds(243, 158, 153, 23);
		add(btnNewButtonAssignPaper);
		
		
		JButton assignSubChairBttn = new JButton("Assign SubProgram Chair");
		assignSubChairBttn.setBounds(44, 416, 189, 23);
		add(assignSubChairBttn);
		
		final JList SubChairList = new JList(SubChairModel);
		SubChairList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		SubChairList.setBackground(Color.WHITE);
		
		JScrollPane subChairPane = new JScrollPane(SubChairList);
		subChairPane.setBounds(414, 54, 192, 138);
		add(subChairPane);
		
		//the paper list 
		final JList papersList = new JList(paperModel);
		papersList.setBorder(null);
		papersList.setBackground(Color.WHITE);
		papersList.setBounds(10, 52, 223, 138);
		add(papersList);
		
		addUserList();
		
		final JList userList = new JList(userModel);
		userList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		userList.setBackground(Color.WHITE);
		
		JScrollPane userPane = new JScrollPane(userList);
		userPane.setBounds(10, 274, 223, 131);
		add(userPane);
		
		//the accept button
		JButton acceptBtn = new JButton("Accept");
		acceptBtn.setBounds(414, 416, 89, 23);
		add(acceptBtn);
		//the reject button
		JButton rejectBtn = new JButton("Reject");
		rejectBtn.setBounds(517, 416, 89, 23);
		add(rejectBtn);
		
		paperAssignments.setEditable(false);
		paperAssignments.setBackground(Color.WHITE);
		paperAssignments.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		paperAssignments.setBounds(243, 23, 153, 77);
		add(paperAssignments);
		
		JLabel lblPaperAssignments = new JLabel("Paper Assignments:");
		lblPaperAssignments.setBounds(260, 0, 125, 23);
		add(lblPaperAssignments);
		
		JLabel lblSubprogramChairs = new JLabel("Subprogram Chairs:");
		lblSubprogramChairs.setBounds(439, 27, 167, 20);
		add(lblSubprogramChairs);
		
		JLabel lblUserList = new JLabel("User List:");
		lblUserList.setBounds(69, 240, 79, 23);
		add(lblUserList);
		
		JLabel lblPaperList = new JLabel("Paper List: Select a paper");
		lblPaperList.setBounds(10, 27, 159, 20);
		add(lblPaperList);
		
		JLabel programChairName = new JLabel("Name:");
		programChairName.setBounds(10, 0, 204, 23);
		add(programChairName);
		
		programChairName.setText("Name: "+myProgramChair.getFirstName() +" , "+ myProgramChair.getLastName());
		
		
		final JTextArea PaperInformationText = new JTextArea();
		PaperInformationText.setEditable(false);
		PaperInformationText.setBounds(354, 274, 252, 131);
		add(PaperInformationText);
		
		JLabel lblPaperInformation = new JLabel("Paper Information:");
		lblPaperInformation.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPaperInformation.setBounds(439, 234, 146, 29);
		add(lblPaperInformation);
		
		JLabel conferenceLabelTitle = new JLabel("Conference: ");
		conferenceLabelTitle.setFont(new Font("Tahoma", Font.BOLD, 12));
		conferenceLabelTitle.setBounds(206, 213, 223, 37);
		add(conferenceLabelTitle);
		
		conferenceLabelTitle.setText("Conference: " + theConf.getConfTitle());
		
		//adds action listner to assign sub chair button
		assignSubChairBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
			if (!(selectedUser==null)&& !(containsSubChair())) 
			{
				
				
				
				myProgramChair.designateSubProgramChairs(theConf, (Reviewer) selectedUser);
				SubprogramChair newChair = new SubprogramChair(selectedUser.getFirstName(), selectedUser.getLastName(), selectedUser.getEmail(), 
						selectedUser.getUserId(), 2, theConf.getConfId(),
						theConf.getConfDescription(), theConf.getConfTitle());
				subChairs.add(newChair);
				SubChairModel.addElement(newChair);
				
				repaint();
				
			}
		}});
		
		////////////////////////*****************\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		btnNewButtonAssignPaper.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
			if (!(currentSubChair==null) && !(selectedPaper ==null))
			{
				myProgramChair.assignPapersToSubChairs(currentSubChair, selectedPaper);
				
				selectedPaper.addSubChair(currentSubChair);
				
				PaperSerialization.serializePapers();
				
				paperAssignments.setText(assignmentText());
				
		        paperAssignments.repaint();
		        
				repaint();
				
			}
		}});
		
		acceptBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
			if (!(selectedPaper ==null)) 
			{
				
				//selectedPaper.setAcceptRejectStatus(true);
				myProgramChair.acceptDeclinePapers(theConf, theConf.getConfTitle(), 1);
				selectedPaper.setAcceptRejectStatus(1);
				
				//PaperSerialization.papers.add(selectedPaper);
				PaperSerialization.serializePapers();
				
				
				PaperInformationText.setText(selectedPaper.getFullStatus());
				PaperInformationText.repaint();
				repaint();
				
			}
		}});
		
		
		rejectBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
			if (!(selectedPaper ==null))	
			{
				myProgramChair.acceptDeclinePapers(theConf, theConf.getConfTitle(), 2);
				selectedPaper.setAcceptRejectStatus(2);
				
				//PaperSerialization.papers.add(selectedPaper);
				PaperSerialization.serializePapers();
				
				PaperInformationText.setText(selectedPaper.getFullStatus());
				PaperInformationText.repaint();
				repaint();
				
			}
		}});
		
		
		papersList.addListSelectionListener(new ListSelectionListener() {
		      public void valueChanged(ListSelectionEvent evt) {
		        if (evt.getValueIsAdjusting())
		          return;
		       
		        	
		        
		        int i = papersList.getSelectedIndex();
		        selectedPaper = (Model.Paper) papersList.getModel().getElementAt(i);
		        
		        
		        PaperInformationText.setText(selectedPaper.getFullStatus());
		        paperAssignments.setText(assignmentText());
		        paperAssignments.repaint();
		        repaint();
		        
		      }
		    });
		
		
		
		//subchair action
		
		
		SubChairList.addListSelectionListener(new ListSelectionListener() {
		      public void valueChanged(ListSelectionEvent evt) {
		        if (evt.getValueIsAdjusting())
		          return;
		       
		        	
		        
		        int i = SubChairList.getSelectedIndex();
		        
		        
		        currentSubChair = (SubprogramChair) SubChairList.getModel().getElementAt(i);
		        
		       
		        
		        repaint();
		        
		      }
		    });
		
		//user list action
		
		userList.addListSelectionListener(new ListSelectionListener() {
		      public void valueChanged(ListSelectionEvent evt) {
		        if (evt.getValueIsAdjusting())
		          return;
		        
		        
		        int i = userList.getSelectedIndex();
		        
		        selectedUser  = (User) userList.getModel().getElementAt(i);
		        
		      }
		    });
		
	}
	public void addPapersList()
	{
		
		
		for (int i =0; i < PaperSerialization.papers.size(); i++) 
		{
			
				if (PaperSerialization.papers.get(i).getconference().getConfId()==theConf.getConfId())
				{
					paperModel.addElement(PaperSerialization.papers.get(i));
				}
			}
			
		}
	
	
	public void addUserList() 
	{
		if (!(myDm.getUsers()==null)) 
		{
			
		
		for (int i = 0; i < myDm.getUsers().size(); i++) 
		{
			if ((myDm.getUsers().get(i).getRoleId() == 4) 
					&& myDm.getUsers().get(i).getMyConfTitle().equals(theConf.getConfTitle()))
					{
				
				genUser.add(myDm.getUsers().get(i));
					}
		}
		for (int i =0; i < genUser.size(); i++)
		{
			userModel.addElement(genUser.get(i));
			
		}
		}
	}
	
	public void addSubChairList() 
	{
		subChairs = new ArrayList<SubprogramChair>();
		for (int i = 0; i < myDm.getUsers().size(); i ++) {
			int a = myDm.getUsers().get(i).getRoleId();
			
			
			if (a == 2 
					&& ((SubprogramChair)myDm.getUsers().get(i)).getMyConferenceId()==myProgramChair.getMyConferenceId()) {
				subChairs.add(((SubprogramChair)myDm.getUsers().get(i)));
			}
		}
		
		
		for (int i =0; i < subChairs.size(); i++) 
		{
			SubChairModel.addElement(subChairs.get(i));
		}
	}
	public String assignmentText() {
		String Stuff = "";
		for (int i =0; i < selectedPaper.getChairList().size(); i++) {
			Stuff+=selectedPaper.getChairList().get(i) + "\n";
		}
		
		return Stuff;
	}
	private boolean containsSubChair() {
		
		for (int i =0; i <subChairs.size(); i++) 
		{
			if (subChairs.get(i).getFirstName().equals(selectedUser.getFirstName())
					&& subChairs.get(i).getLastName().equals(selectedUser.getLastName())) {
				return true;
			}
		}
		return false;
	}
}