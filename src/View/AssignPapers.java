package View;

import java.awt.Component;
import java.awt.Dimension;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Model.Conference;
import Model.DatabaseManager;
import Model.Paper;
import Model.PaperSerialization;
import Model.Reviewer;
import Model.User;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AssignPapers extends JFrame
{
	private List<Paper> papers;
	private Paper curPaper;
	private DefaultListModel model;
	private JList list;
	private JTextArea textArea;
	private DatabaseManager myDm;
	protected JButton btnAssign;
	public AssignPapers(final Reviewer reviewer, User subChair, DatabaseManager dm) 
	{

		getContentPane().setLayout(null);
		myDm = dm;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setPreferredSize(new Dimension(450, 280));
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 432, 255);

		panel.setLayout(null);

		JLabel lblPapers = new JLabel("Papers");
		lblPapers.setBounds(12, 41, 56, 16);
		panel.add(lblPapers);

		model = new DefaultListModel();

		btnAssign = new JButton("Assign");
		btnAssign.setEnabled(false);
		btnAssign.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				assignPaper(curPaper, reviewer);

			}
		});
		btnAssign.setBounds(323, 37, 97, 25);
		panel.add(btnAssign);
		panel.setPreferredSize(new Dimension(432, 255));
		getContentPane().add(panel);

		list = new JList(model);
		initializePapers(subChair);
		listSelectionListener();
		list.setBounds(12, 80, 144, 162);
		panel.add(list);
		JScrollPane pane = new JScrollPane();
		pane.setBounds(12, 80, 144, 162);
		panel.add(pane);
		//textArea.setText(curReviewer.revInfo());


		textArea = new JTextArea();
		if(model.isEmpty())
		{
			textArea.setText("There are no papers \nassigned to this "+
					"\nsubprogram chair.");
		}
		textArea.setEditable(false);
		JScrollPane textPane = new JScrollPane(textArea);			
		textPane.setBounds(268, 80, 152, 141);
		this.setLocationRelativeTo(null);
		panel.add(textPane);
		//curReviewer = reviewers.get(1);




	}

	/**
	 *@author Melaku Mehiretu
	 * List of papers not including subprogram chairs own paper.
	 * @param user Subprogram chair of this conference. 
	 */
	private void initializePapers(User user) 
	{
		for(int i = 0; i < PaperSerialization.papers.size(); i++)
		{
			//sub chairs cannot assign thier own paper to a reviewer
			//Sub chair can see only papers that are assigned to him
			curPaper = PaperSerialization.papers.get(i);
			if((curPaper.getconference().getConfId() ==
					user.getMyConferenceId()))
			{	
				int j;
				for(j = 0; j < curPaper.getChairList().size(); j++)
				{
					//all subchairs assigned to this paper
					String subChairName = curPaper.getChairList().get(j).getFirstName() + " " + 
							curPaper.getChairList().get(j).getLastName()+" ";
					//subChairName=subChairName.trim();
					String curSubName = user.getFirstName() + " " + user.getLastName();
					//check if the paper author is this sub chair
					//model.addElement(curPaper);
					System.out.println(subChairName);
					System.out.println(curSubName);
					if(!(curSubName.equals(subChairName)))
					{
						model.addElement(curPaper);
					}
					//model.addElement(curPaper);
					//Paper is not submmited by this sub chair
//					if (j >= curPaper.getChairList().size())
//					{
//						model.addElement(curPaper);
//					}
				}


			}

		}


	}

	private void listSelectionListener() 
	{
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent evt) {
				if (evt.getValueIsAdjusting())
					return;

				btnAssign.setEnabled(true);

				int i = list.getSelectedIndex();
				curPaper = (Paper) list.getModel().getElementAt(i);
				textArea.setText(curPaper.getFullStatus());
			}
		});

	}
	/**
	 * Make sure a reviewer is not assigned to his own paper. 
	 * @author Melaku Mehiretu
	 * @param paper The paper to be assigned to a reviewer.
	 * @param rev The reviewer of the paper. 
	 */
	private void assignPaper(Paper paper, Reviewer rev)
	{

		paper.setReviewer(rev);
		PaperSerialization.serializePapers();
		dispose();
	}
	/**
	 * @author Melaku Mehiretu
	 * @param paper The Paper we are trying to find the conference for. 
	 * @return The conference where this paper is currently submitted at. 
	 */
	private Conference getConference(Paper paper)
	{
		Conference conf = null;
		for(int i = 0; i < myDm.getConferences().size(); i++)
		{
			//if(myDm.getConferences().get(i).getConfId() == )
			//
		}
		return conf;
	}
}
