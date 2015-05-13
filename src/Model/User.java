package Model;

import java.util.List;

public abstract class User implements java.io.Serializable
{
	/**
	 * First name of user.
	 */
	private String firstName;
	/**
	 * Last name of user.
	 */
	private String lastName;
	
	/**
	 * Email address of user.
	 */
	private String email;
	/**
	 * User Id of user from the SQL database.
	 */
	private int userId;
	/**
	 * Role Id of user from SQL database.
	 */
	private int roleId;
	/**
	 * Role of the user
	 */
	private String role;
	
	/**
	 * @author Melaku Mehiretu
	 * Create a new user using the given first name and last name.
	 * @param fName First name.
	 * @param lName Last name.
	 * @param emailAd Email address of this user
	 * @param theUserId User Id
	 * @param theRoleId Role Id
	 */
	public User(String fName, String lName, String emailAd,
			int theUserId)
	{
		firstName = fName;
		lastName = lName;
		email = emailAd;
		userId = theUserId; //next available id from the data base
		
	}
	/**
	 * @author Melaku Mehiretu
	 * @param role Name of the role given to this user.
	 * @param roleId Role id of this user.
	 */
	public void setRole(String role, int roleId) 
	{
		this.roleId = roleId;
		this.role = role;
	}
	
	/**
	 * @author Melaku Mehiretu
	 * @return First name of user.
	 */
	public String getFirstName() 
	{
		return firstName;
	}
	/**
	 * @author Melaku Mehiretu
	 * @return Last name of user.
	 */
	public String getLastName()
	{
		return lastName;
	}
	/**
	 * @author Melaku Mehiretu
	 * @return role name of this user.
	 */
	public String role()
	{
		return role;
	}
	/**
	 * @author Melaku Mehiretu
	 * Create a new paper object and store it in the given conference class
	 * @param conf The conference where the paper needs to be submitted to.
	 * @param paper The paper that needs to be submitted.
	 */
	private void submitPaper(Conference conf, Paper paper)
	{
		conf.submitPaper(paper);
	}
	/**
	 * @author Melaku Mehiretu
	 * Shows a list of papers submitted by this user.
	 */
	public List<Paper> viewMyPapers()
	{
		return null;
	}
	/**
	 * @author Melaku Mehiretu
	 * Removes a paper from the conference. 
	 * @param conf The conference where the paper is currently submitted to.
	 * @param paper The paper to be deleted.
	 */
	public void deletePaper(Conference conf, Paper paper)
	{
		conf.deletePaper(paper);
	}
	/**
	 * @author Melaku Mehiretu
	 * Display status of the paper such as, review, accept/reject
	 */
	public void viewPaperStatus(Paper paper)
	{
		paper.getAcceptRejectStatus();
	}
	
	
	/**
	 * Return a string representation of the user
	 * ([Last Name, First Name], [email], [role id]
	 */
	@Override
	public String toString()
	{
//		StringBuilder userInfo = new StringBuilder();
//		userInfo.append("Name: "+ lastName+ ","+ firstName);
//		userInfo.append("\nEmail: "+ email);
//		userInfo.append("\nRole Id: " + roleId);	
		
		return this.getFirstName() + " " + this.getLastName();
	}
	
	public String getUserStatus()
	{
		StringBuilder userInfo = new StringBuilder();
		userInfo.append("Name: "+ lastName+ ","+ firstName);
		userInfo.append("\nEmail: "+ email);
		userInfo.append("\nRole Id: " + roleId);	
		
		return userInfo.toString();
	}
	
	/**
	 * @author Melaku Mehiretu
	 * @return user's email address
	 */
	public String getEmail() 
	{
		return email;
	}
	/**
	 * @author Melaku Mehiretu
	 * @return user id of this user.
	 */
	public int getUserId() 
	{
		return userId;
	}
	/**
	 * @author Melaku Mehiretu
	 * @return The role id of this user.
	 */
	public int getRoleId()
	{
		return roleId;
	}
	/**
	 * Designate a user as a specific role.
	 * @param id The new role id.
	 */
	public void setUserId(int id)
	{
		userId = id;
	}
	/**
	 * @author Melaku Mehiretu
	 * @return The conference id
	 */
	public abstract int getMyConferenceId();
	/**
	 * @author Melaku Mehiretu
	 * @return conference title
	 */
	public abstract String getMyConfTitle();
	

}
