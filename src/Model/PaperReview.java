//package Model;
//
//
//public class PaperReview implements java.io.Serializable {
//	/**
//	 * list of integers in order of reviews according to paper review submission sheet
//	 */
//	private int[] reviewRatings;
//	
//	String myReviewerName;
//	
//	String mySummaryComments;
//	
//	public PaperReview(int[] theList, String theSummary, String theReviewer) 
//	{
//		
//		reviewRatings = theList;
//		mySummaryComments = theSummary;
//		myReviewerName = theReviewer;
//	}
//	public int[] getReviewRating() 
//	{
//		return reviewRatings;
//	}
//	
//	public String getSummary() 
//	{
//		return mySummaryComments;
//	}
//	
//	public String getReviewName()
//	{
//		return myReviewerName;
//	}
//	
//	@Override
//	public String toString() 
//	{
//		StringBuilder reviewInfo = new StringBuilder();
//		
//		reviewInfo.append(reviewRatings.toString());
//		reviewInfo.append("/nSummary Comments :" +"/n" + mySummaryComments);
//		return reviewInfo.toString();
//	}
//
//	}
