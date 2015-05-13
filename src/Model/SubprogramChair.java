package Model;

import java.util.ArrayList;
import java.util.List;


@SuppressWarnings("serial")
public class SubprogramChair extends User{
	
	private int myConferenceId;
	private String myConfTitle;
	private String myConfDescription;
	private List<Model.Paper> paperList;
	private int recommendationNumber;
	private int IdCount;
	private String paperSummary;
	
	DatabaseManager data;
	/**
	 * @author Alex Stringham
	 * Creates a Subprogram Chair
	 */
	
	public SubprogramChair(String firstName, String lastName, String emailAddress, 
			int userId, int roleId, int conferenceId,
			String conferenceDescription, String confTitle) 
	{
		super(firstName, lastName, emailAddress,userId);
		paperList = new ArrayList<Model.Paper>();
		setRole("SubProgram Chair", 2);
		setMyConferenceId(conferenceId);
		setMyConfTitle(confTitle);
		setMyConfDescription(conferenceDescription);
	}
	
	/**
	 * @author Alex Stringham
	 * 
	 * Designates reviewers for a given conference.
	 * @param conf
	 * @param theUser
	 */
	public void designateReviewers(Conference conf, User theUser)
	{
		//initializes a new reviewer to a specified conference
		theUser.setRole("Subprogram Chair", 3);
							
		//registers a reviewer to a conference		
		data.registerToConference(conf, theUser);
		
	}
	
	public int getMyConferenceId() {
		return myConferenceId;
	}

	public void setMyConferenceId(int myConferenceId) {
		this.myConferenceId = myConferenceId;
	}

	public String getMyConfTitle() {
		return myConfTitle;
	}

	public void setMyConfTitle(String myConfTitle) {
		this.myConfTitle = myConfTitle;
	}

	public String getMyConfDescription() {
		return myConfDescription;
	}

	public void setMyConfDescription(String myConfDescription) {
		this.myConfDescription = myConfDescription;
	}
	

	
	/**
	 * @author Alex Stringham
	 * @return gets the number (1 to 5) of the 
	 * subprogram chairs recommendation
	 */
	public int getPaperRecommendation()
	{
		return recommendationNumber;
	}
	
	/**
	 * @author Alex Stringham
	 * @param recommendation 
	 * sets the number recommendation of the
	 * subprogram chair
	 */
	public void setPaperRecommendation(int recommendation)
	{
		recommendationNumber = recommendation;
	}
	
	/**
	 * @author Alex Stringham
	 * @return gets the recommendation summary
	 * from the subprogram chair
	 */
	public String getPaperSummary()
	{
		return paperSummary;
	}
	
	/**
	 * @author Alex Stringham
	 * @param summary
	 * sets the paper recommendation summary
	 * for the subprogram chair
	 */
	public void setPaperSummary(String summary)
	{
		paperSummary = summary;
	}
	
//	/**
//	 * @author Alex Stringham
//	 * @return list of papers assigned to a reviewer
//	 */
//	public List<Integer> assignPapersToReviewers()
//	{
//		
//		return paperList;
//	}
	
	/**
	 * @author Alex Stringham
	 * @param thePaperList  dd
	 * sets a list of paper ID's
	 */
	public void paperList(List<Model.Paper> thePaperList)
	{
		thePaperList = paperList;
	}
	
	public List<Model.Paper> getPaperList()
	{
		return paperList;
	}
	
//	/**
//	 * @author Alex Stringham
//	 * Assigns papers to reviewers
//	 * 
//	 */
//	public void assignPapersToReviewers(Reviewer theReviewer, Paper thePaper) 
//	{
//		theReviewer.addPaperToList(thePaper.paperId);
//	}

	/**
	 * @author Alex Stringham
	 * @param paperId
	 * Adds a paper to the list.
	 */
	public void addPaperToList(Model.Paper thePaper)
	{
		if (!(paperList.contains(thePaper))) {
			paperList.add(thePaper);
		}
		
	}
	
	@Override
	public String toString()
	{
		return getLastName() +", " + getFirstName();
	}
}
