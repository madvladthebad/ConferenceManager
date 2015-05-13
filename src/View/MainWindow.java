package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import Model.DatabaseManager;
import Model.PaperSerialization;
import Model.User;

public class MainWindow extends JFrame
{

	private JPanel myPanel;
	private User myUser;
	private DatabaseManager myData;
	private static int WIDTH = 620;
	private static int HEIGHT = 450;
	public MainWindow(User user, DatabaseManager dm)
	{
		super("Conference Manager");  
		myUser = user;
		myData = dm;
		JTabbedPane pane = new JTabbedPane();
		JPanel author, progchair, subchair, reviewer;
		
		
		
		
		if(user.getRoleId() == 2)//subprogram chair
		{
			subchair = new SubChairPanel(WIDTH, HEIGHT, user, dm);
			reviewer = new ReviewerPanel(WIDTH, HEIGHT, user);
			author = new AuthorPanel(WIDTH, HEIGHT, user, dm);
			
			pane.add("Author", author);			
			pane.add("Reviewer", reviewer);
			pane.add("SubProgram Chair", subchair);
		}
		else if(user.getRoleId() == 4)//reviewer
		{
			author = new AuthorPanel(WIDTH, HEIGHT, user, dm);
			reviewer = new ReviewerPanel(WIDTH, HEIGHT, user);
			pane.add("Author", author);
			pane.add("Reviewer", reviewer);
		}
		else if(user.getRoleId() == 1)//pchair
		{
			progchair = new ProgramChairPanel(WIDTH, HEIGHT, user, dm);
			author = new AuthorPanel(WIDTH, HEIGHT, user, dm);			
			reviewer = new ReviewerPanel(WIDTH, HEIGHT, user);
			
			pane.add("Author", author);
			pane.add("Reviewer", reviewer);			
			pane.add("Program Chair", progchair);
			
		}
		else
		{
			author = new AuthorPanel(WIDTH, HEIGHT, user, dm);
			pane.add("Author", author);
		}		
		
		
		JPanel panel = new JPanel();
		panel.add(pane);
		JButton btn = new JButton("Log out");
		btn.setBackground(Color.RED);
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Log out and go to the main menu
				LoginPanel theLoginPanel = new LoginPanel();
				theLoginPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
				PaperSerialization.unserializePapers();
				theLoginPanel.pack();
				
				theLoginPanel.setVisible(true); 
				dispose();
				
			}
		});
		panel.add(btn);
		add(panel);
		
		
	    
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	    pack();
//	    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
//		this.setLocation((dim.width/2-this.getSize().width/2)/2 ,
//				(dim.height/2-this.getSize().height/2)/2);
	    this.setLocationRelativeTo(null);
	    setVisible(true); 
	    
	}
	
	
	public void setPanel(JPanel panel)
	{
		myPanel = panel;
	}
	
	public void setUser(User user)
	{
		myUser = user;
	}
	/**
	 * @param args
	 */

}
