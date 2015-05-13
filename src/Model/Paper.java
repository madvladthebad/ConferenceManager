package Model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;



/***
 * 
 * @author Matt Dufalo
 * Last Edited 2/24/2014
 * 
 * This Class represents a paper object.
 *
 */

public class Paper implements java.io.Serializable {

	String title;
	String authorName;
	File file;
	File[] reviews = new File[3];
	boolean reviewStatus;
	private int recommend;
	int acceptReject;

	User[] Reviewers = new User[4];

	private List<SubprogramChair> subChairList;
	private Conference confList;
	/**
	 * @author Matt Dufalo
	 * This class constructs the paper object.
	 * 
	 * title - The title of a paper
	 * authorName - the author of the papers name.
	 * fileName - the filename of the paper.
	 * @author Melaku Mehiretu (conf changed from int to conference)
	 */
	public Paper(String title, String authorName, File fileName, Conference Conf)
	{
		this.title = title;
		this.authorName = authorName;
		this.file = fileName;
		acceptReject = 0;
		recommend = 0;
		subChairList = new ArrayList<SubprogramChair>();

		addToConf(Conf);

	}
	/**
	 * @author Vladimir Gudzyuk
	 * @return the list of conferences associated with this paper.
	 */
	public Conference getconference()
	{
		return confList;
	}
	/**
	 * Vladimir Gudzyuk
	 * @param conf adds conference to this papers conference list.
	 */
	public void addToConf(Conference conf) {
		confList = conf;

	}
	/**
	 * @author Melaku Mehiretu
	 * @return If this paper is recommended by the program chair or not. 
	 */
	public String getRecommendation()
	{
		String recommendation = "Not decided yet!";
		if(recommend == 0)
		{
			recommendation = "Not decided yet!";
		}
		else if(recommend == 1)
		{
			recommendation = "RECOMMENDED!";
		}
		else if(recommend == 2)
		{
			recommendation = "NOT RECOMMENDED!";
		}
		return recommendation;
	}
	/**
	 * @author Melaku Mehiretu
	 * @return The int value of recommendation
	 */
	public int recomendationValue()
	{
		return recommend;
	}
	/**
	 * @author Melaku Mehiretu
	 * @param rec True if recommended, and false otherwise.
	 */
	public void setRecommendation(int rec)
	{
		recommend = rec;
	}
	/**
	 * 
	 * @author Vladimir Gudzyuk
	 * @return list of supprogram chairs tied to this paper.
	 */
	public List<SubprogramChair> getChairList()
	{
		return subChairList;
	}
	/**
	 * @author Vladimir Gudzyuk
	 * @param theChair adds program chair to list
	 */
	public void addSubChair(SubprogramChair theChair) 
	{
		if (!(subChairList.contains(theChair)))
		{
			subChairList.add(theChair);
		}
	}
	/**
	 * 
	 *@author Matt Dufalo
	 * @return the file associated with the paper in this object.
	 */
	public File getFile()
	{
		return file;
	}
	/**
	 * @author Matt Dufalo
	 * @param file sets file of paper object to this.
	 */
	public void setFile(File file)
	{
		this.file = file;
	}

	/**
	 * @author Matt Dufalo
	 * returns name of author on this paper object.
	 */
	public String getAuthor()
	{
		return this.authorName;
	}

	/**
	 * Author: Matt Dufalo
	 * 
	 * @return The title of this paper.
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * Author: Matt Dufalo
	 * @return A string representing whether or not a paper has been reviewed
	 */
	public String getReviewStatus()
	{
		if(reviewStatus == true)
		{
			return "Reviewed.";
		} else {
			return "Not yet reviewed.";
		}

	}

	/**
	 * Author: Matt Dufalo
	 * Sets review status to true once paper has received review.
	 */
	public void setReviewStatus()
	{
		reviewStatus = true;
	}

	/**
	 * Author: Matt Dufalo
	 * @return whether or not a paper has been accepted
	 */
	public String getAcceptRejectStatus()
	{

		if (acceptReject == 0)
		{
			return "No decision has been made.";
		} else if (acceptReject == 1) {
			return "Accepted";
		} else{
			return "Rejected";
		}
	}
	public int getAcceptReject(){
		return acceptReject;
	}
	/**
	 * Author: Matt Dufalo
	 * @param status True if paper is accpted, false otherwise
	 */
	public void setAcceptRejectStatus(int n)
	{
		acceptReject = n;
	}

	/**
	 * Author: Matt Dufalo
	 * 
	 * Assigns name of reviewer to this paper object
	 * @param name The name of the reviewer
	 */
	public void setReviewer(Reviewer person)
	{
		if(Reviewers[2] != null)
		{
			JOptionPane.showMessageDialog(null, "Too many reviewers assigned,cannot make assignment.");
		} else {
			int i = 0;
			while(Reviewers[i] != null)
			{
				i++;
			}
			Reviewers[i] = person;
		}
	}

	/**
	 * Author: Matt Dufalo
	 * @return The name of all the reviewers assigned to this paper.
	 */
	public String getReviewer()
	{
		if (Reviewers[0] == null)
		{
			return "No reviewer assigned yet.";
		} else {
			String reviewers = "\n";
			int i = 0;
			while (Reviewers[i] != null && i < 3)
			{
				reviewers +=  Reviewers[i].getFirstName() + " " + Reviewers[i].getLastName();
				if(i < 3)
				{
					i++;
				}
				if(i < 3 && Reviewers[i] != null)
				{
					reviewers += "\n ";
				}

			}
			return reviewers;
		}
	}
	/**
	 * @author Matt Dufalo
	 * 
	 * Get a specific review
	 * @param n the index for requested reviewer
	 * @return reviewers name
	 */
	public String getTheReviewer(int n)
	{
		if(Reviewers[n] != null)
		{
			return Reviewers[n].getFirstName() + " " + Reviewers[n].getLastName();
		}
		else {
			return "";
		}
	}
	/**
	 * Author: Matt Dufalo
	 * 
	 * Sets the review to this paper.
	 */
	public void setReview(File theReview)
	{
		int i = 0;
		while(reviews[i] != null)
		{
			i++;
		}
		reviews[i] = theReview;
	}
	public void setPaperFile(File theFile)
	{
		this.file = theFile;
	}

	/**
	 * Author: Matt Dufalo
	 * @return The reviews attached to this paper.
	 */
	public File getReview(int n)
	{
		return reviews[n];
	}


	/**
	 * @author Matt Dufalo
	 * Returns title of paper, overided to make lists properly display paper title
	 */
	@Override
	public String toString()
	{

		return title;
	}

	/**
	 * @author Matt Dufalo
	 * @return essentially a toString to get full status of paper.
	 */
	public String getFullStatus()
	{
		StringBuilder paperInfo = new StringBuilder();
		paperInfo.append("Title: "+ title + "\n");
		paperInfo.append("Author: " + authorName + "\n");
		paperInfo.append("Reviewers: " + getReviewer() + "\n");
		paperInfo.append("Review Status: "+getReviewStatus()+ "\n");
		paperInfo.append("Recommendation: " + getRecommendation() + "\n");
		paperInfo.append("Accept/Reject: "+getAcceptRejectStatus()+ "\n");
		return paperInfo.toString();
	}
	/**
	 * @author Matt Dufalo
	 * @return String for status of paper viewable to author
	 */
	public String getFullStatusAuthor()
	{
		StringBuilder paperInfo = new StringBuilder();
		paperInfo.append("Title: "+ title + "\n");
		paperInfo.append("Author: " + authorName + "\n");
		paperInfo.append("Conference: "+ getconference().getConfTitle() + "\n");
		paperInfo.append(getReviewStatus()+ "\n");
		paperInfo.append(getAcceptRejectStatus()+ "\n");
		return paperInfo.toString();
	}

}
