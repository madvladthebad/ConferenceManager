package Model;

import java.util.ArrayList;
import java.util.List;



public class Conference implements java.io.Serializable
{
	/**
	 * String description about a conference such location,
	 *  and what the conference is about
	 */
	private String confDescription;
	/**
	 * Name of the conference.
	 */
	private String confTitle;
	/**
	 * Conference ID. 
	 */
	private int confId;
	/**
	 * List of registered users to a conference with different roles
	 */
	private List<User> users;
	/**
	 * List of submitted papers to a conference. 
	 */
	private List<Paper> papers;
	
	/**
	 * Create a conference based on data values from the SQL database.
	 * @author Melaku Mehiretu
	 * @param id ID of the conference.
	 * @param title Name of the conference. 
	 * @param description Location and topic of the conference. 
	 */
	public Conference(int id, String title, String description)
	{
		confId = id;
		confDescription = description;
		confTitle = title;
		papers = new ArrayList<Paper>();
		//users = conferenceManager.getUsers();
		//papers = conferenceManager.getPapers();
	}

	/**
	 * @author Melaku Mehiretu
	 * @return description of the conference. 
	 */
	public String getConfDescription()
	{
		return confDescription;
	}

	/**
	 * @author Melaku Mehiretu
	 * @return Name of this conference. 
	 */
	public String getConfTitle()
	{
		return confTitle;
	}

	/**
	 * @author Melaku Mehiretu
	 * @return Conference Id.  
	 */
	public int getConfId() 
	{
		return confId;
	}
	
	/**
	 * @author Melaku Mehiretu
	 * @return List of registered users that have already a role for this conference. 
	 */
	public List<User> getUsers() 
	{
		return users;
	}

	/**
	 * @author Melaku Mehiretu
	 * @return List of papers submitted for this conference. 
	 */
	public List<Paper> getPapers() 
	{
		return papers;
	}
	/**
	 * @author Melaku Mehiretu
	 * @return Remove a paper from the paper list.
	 */
	public void deletePaper(Paper paper) 
	{
		papers.remove(paper);
		//needs to be removed from the paper database too.
		
	}
	/**
	 * @author Melaku Mehiretu
	 * Submit paper to this conference. 
	 */
	public void submitPaper(Paper paper)
	{
		papers.add(paper);
		//Needs to be added to the paper database too		
	}
	@Override
	public String toString()
	{
		return confTitle;
	}
	

}
