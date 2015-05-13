package View;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Model.DatabaseManager;


/**
 * A control chooser class that lets a user to choose the game controls.
 * @author Melaku Mehiretu
 * @version 78
 *
 */
@SuppressWarnings("serial")
public class Account extends JFrame
{
	private JTextField fNameField;
	private JTextField lNameField;
	private JTextField emailField;
	public Account(final DatabaseManager dm) {
		getContentPane().setLayout(null);
		setPreferredSize(new Dimension(400,300));

		JLabel lblUserInformation = new JLabel("User Information");
		lblUserInformation.setForeground(Color.BLUE);
		lblUserInformation.setBounds(139, 13, 116, 16);
		getContentPane().add(lblUserInformation);

		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(47, 50, 97, 16);
		getContentPane().add(lblFirstName);

		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(47, 92, 97, 16);
		getContentPane().add(lblLastName);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(47, 137, 97, 16);
		getContentPane().add(lblEmail);

		fNameField = new JTextField();
		fNameField.setBounds(139, 47, 116, 22);
		getContentPane().add(fNameField);
		fNameField.setColumns(10);

		lNameField = new JTextField();
		lNameField.setBounds(139, 89, 116, 22);
		getContentPane().add(lNameField);
		lNameField.setColumns(10);

		emailField = new JTextField();
		emailField.setBounds(139, 134, 116, 22);
		getContentPane().add(emailField);
		emailField.setColumns(10);

		JButton btnCreateAccount = new JButton("Create Account");
		btnCreateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(fNameField.getText().trim().equals("") ||
						lNameField.getText().trim().equals("")||
						emailField.getText().trim().equals(""))
				{
					JOptionPane.showMessageDialog(null, "You can't leave any field blank!");
				}
				else
				{
					dm.signUp(fNameField.getText(), lNameField.getText(), emailField.getText());

					//Make sure to update the list once an account is created
					dm.initializeConferences();
					dm.initializeUsers();
					dispose();
				}


			}
		});
		btnCreateAccount.setBounds(139, 199, 176, 25);
		getContentPane().add(btnCreateAccount);
	}


}
