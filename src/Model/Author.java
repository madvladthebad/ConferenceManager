package Model;

public class Author extends User
{
	/**
	 * Create an author.
	 * @author Melaku Mehiretu
	 * @param fName First name of author.
	 * @param lName Last name of author.
	 * @param emailAd Email address of author.
	 * @param theUserId User Id of author. 
	 * @param theRoleId Role Id of author. 
	 */
	public Author(String fName, String lName, String emailAd,
			int theUserId)
	{
		super(fName, lName, emailAd, theUserId);
	}
	
	@Override
	public int getMyConferenceId() 
	{
		return 0;
	}
	/**
	 * @author Melaku Mehiretu
	 * An author is not tied to a conference yet.
	 */
	@Override
	public String getMyConfTitle() {
		
		return null;
	}

	

}
