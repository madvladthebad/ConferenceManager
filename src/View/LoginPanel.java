package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Model.DatabaseManager;
import Model.PaperSerialization;
import Model.User;
import javax.swing.JLabel;


/**
 * 
 * @author Vladimir Gudzyuk
 *
 */

@SuppressWarnings("serial")
public class LoginPanel extends JFrame

{
	/**
	 * creates buttons and textfield where user information will be entered
	 */
	JPanel loginPanel = new JPanel(new BorderLayout());
	
	JPanel southPanel = new JPanel();
	
	
	private static final Dimension DEFUALT_SIZE = new Dimension(400, 200);


	protected static final User User = null;
	
	private JButton myLoginButton = new JButton("Login...");
	
	private JButton createAccountButton = new JButton("Create an Account");
	
	User myUser;
	
	DatabaseManager theData;
	private final JLabel lblEmail = new JLabel("Email:");
	
	
	
	public LoginPanel()
	{
		super();
		
		theData = new DatabaseManager();
		theData.initializeUsers();
		theData.initializeConferences();
		
		System.out.println("users initialized");
		
		
		setPreferredSize(DEFUALT_SIZE);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((dim.width/2-this.getSize().width/2)/2 ,
				(dim.height/2-this.getSize().height/2)/2);
		southPanel.setLayout(null);
		myLoginButton.setBounds(74, 100, 84, 25);
		
		southPanel.add(myLoginButton);
		createAccountButton.setBounds(205, 100, 151, 25);
		
		southPanel.add(createAccountButton);
		
		loginPanel.add(southPanel, BorderLayout.CENTER);
		
		final JTextField loginInfo = new JTextField("JamesMiller@symantec.com", 36);
		loginInfo.setBounds(126, 47, 230, 22);
		southPanel.add(loginInfo);
		lblEmail.setBounds(74, 50, 56, 16);
		
		southPanel.add(lblEmail);
		
		
	loginInfo.addActionListener(new ActionListener()
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			String theUser = loginInfo.getText();
			
			User foundUser = null;
			
			for (int i =0; i < theData.getUsers().size(); i++) {
				if (theData.getUsers().get(i).getEmail().equals(theUser)) {
					System.out.println("loginSucuessful");
					
					
					
					foundUser = theData.getUsers().get(i);
					
					
//					MainWindow theUserPanel = new MainWindow( foundUser,  theData);
//					theUserPanel.setVisible(true);
//					
//					dispose();
					
				}
				
			}
			if (!(foundUser==null)) 
			{
				System.out.println(foundUser.toString());
				MainWindow theUserPanel = new MainWindow( foundUser,  theData);
				theUserPanel.setVisible(true);
				
				dispose();
			}
			
		}});
			
		setFocusable(true);
		
		getContentPane().add(loginPanel);
	
	
	
	myLoginButton.addActionListener(new ActionListener()
	{
		@Override
		public void actionPerformed(ActionEvent e) {
String theUser = loginInfo.getText();
			
			User foundUser = null;
			
			for (int i =0; i < theData.getUsers().size(); i++) {
				if (theData.getUsers().get(i).getEmail().equals(theUser)) {
					System.out.println("loginSucuessful");
					
					
					
					foundUser = theData.getUsers().get(i);
					
					
//					MainWindow theUserPanel = new MainWindow( foundUser,  theData);
//					theUserPanel.setVisible(true);
//					
//					dispose();
					
				}
				
			}
			if (!(foundUser==null)) 
			{
				System.out.println(foundUser.toString());
				MainWindow theUserPanel = new MainWindow( foundUser,  theData);
				theUserPanel.setVisible(true);
				
				dispose();
			}
		}});
	
	createAccountButton.addActionListener(new ActionListener()
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			Account theAccountCreation = new Account(theData);			
			theAccountCreation.pack();
			theAccountCreation.setVisible(true);
			
			
		}});
	
	
	this.setTitle("Account Login");
	
	setBackground(Color.BLUE);
	
	setResizable(false);
	 
	setVisible(true);
	
		
	}
	
	public static void main(String[] args) 
	{
		
		LoginPanel theLoginPanel = new LoginPanel();
		theLoginPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		PaperSerialization.unserializePapers();
		PaperSerialization.serializePapers();
		theLoginPanel.pack();
		
		theLoginPanel.setVisible(true); 

	}

	
	
	 }

