package Model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Reviewer extends User implements java.io.Serializable{

	private int myConferenceId;
	private String myConfTitle;
	private String myConfDescription;
	private Paper myPaper;
	private File myFile;
	private List<Integer> paperList;
	/**
	 * @author Alex Stringham
	 * Creates a Subprogram Chair
	 */

	public Reviewer(String firstName, String lastName, String emailAddress, 
			int userId, int roleId, int conferenceId,
			String conferenceDescription, String confTitle) 
	{
		super(firstName, lastName, emailAddress,userId);
		setRole("Reviewer", 4);
		setMyConferenceId(conferenceId);
		setMyConfTitle(confTitle);
		setMyConfDescription(conferenceDescription);
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
	 * Views the paper the reviewer has been assigned
	 */
	public List<Paper> viewAssignedPapers(User theUser)
	{
		return theUser.viewMyPapers();
	}

	/**
	 * @author Alex Stringham
	 * @param paperId
	 * Adds a paper to the list.
	 */
	public void addPaperToList(int paperId)
	{
		paperList.add(paperId);
	}

	/**
	 * @author Alex Stringham
	 * @return String fullReview
	 * Returns the reviewers review as a string
	 */
	public String submitReview()
	{

		StringBuffer buffer = new StringBuffer("");
		System.out.print("Review for:" + myPaper.title);
		String input = null;

		while (!input.equals(""))
		{
			buffer.append(input);
			buffer.append(" ");
		}

		String fullReview = buffer.toString();
		return fullReview;		
	}
	public String revInfo(Reviewer rev)
	{
		StringBuilder str = new StringBuilder();
		str.append("Paper " +"\tConference \tReview Status");
		str.append("\n===================================\n");

		List<Paper> paper = new ArrayList<Paper>();
		if(rev != null)
		{
			for(int i = 0; i < PaperSerialization.papers.size(); i++)
			{

				for(int j = 0; j < PaperSerialization.papers.get(i).Reviewers.length; j++)
				{
					//print out only papers that are tied to this reviewer
					if(PaperSerialization.papers.get(i).Reviewers[j] != null)
					{
						if(rev.getUserId() == PaperSerialization.papers.get(i).Reviewers[j].getUserId())
						{
							paper.add(PaperSerialization.papers.get(i));
							
						}

					}


				}
			}
		}

		for(int i = 0; i < paper.size(); i++)
		{
			str.append(paper.get(i).getTitle() + "\t"+ 
					paper.get(i).getconference().getConfTitle() +
					"\t" + paper.get(i).getReviewStatus()+"\n");
		}

		return str.toString();

	}
	@Override
	public String toString()
	{
		return getLastName() +", " + getFirstName();
	}



}
