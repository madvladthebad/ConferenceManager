/**
 * Author: Matt Dufalo
 * 
 * This test class tests the paper objects
 */
package Model;
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import org.junit.Test;


public class PaperTest {

	/**
	 * Author: Matt Dufalo
	 * 
	 * this class tests out paper construction as well as serializing and unserializing
	 * a paper object.
	 */
	@Test
	public void test() {
		//create arraylist to put papers into
		ArrayList<Paper> papers = new ArrayList<Paper>();
		File file = new File("Conference Review Form.docx");
		
		//create papers, add to arraylist
		Paper tester = new Paper("test", "Matt Dufalo", file, new Conference(1, "Las", "programming"));
		Paper tester2 = new Paper("test2", "Timbo Bambo", file, new Conference(2, "name", "acm programming"));
		papers.add(tester);
		papers.add(tester2);
		
		//info print out to see object has been made.
		System.out.println(tester.toString());
		//test to make sure paper is initialized not reviewed.
		assertEquals("are not equal ", "Not yet reviewed.", tester.getReviewStatus());
		//Test to make sure reviewer is initialized correctly
		assertEquals("are not equal ", "No reviewer assigned yet.", tester.getReviewer());
		//changes some of the tested object fields.
		tester.setReviewStatus();
		Reviewer testDumb = new Reviewer("Tim", "Dumbface", "TD@earthlink", 0, 0, 0, "uhhh", "yeah");
		Reviewer testDumbTwo = new Reviewer("Shauna", "Stupidhead", "SS@earthlink", 0, 0, 0, "uhhh", "yeah");
		Reviewer testDumbThree = new Reviewer("Patricia", "Diaz", "DiazP@weyerhauser.com", 0, 0, 0, "uhhh", "yeah");
		tester.setReviewer(testDumb);
		tester.setAcceptRejectStatus(1);
		
		//retest to make sure they changed
		assertEquals("are not equal ", "Reviewed.", tester.getReviewStatus());
		//assertEquals("are not equal ", "Tim Dumbface.", tester.getReviewer());
		
		tester.setReviewer(testDumbTwo);
		tester.setReviewer(testDumbThree);
		//assertEquals("are not equal ", "\n Tim Dumbface\nShauna Stupidhead\nPatricia Diaz.", tester.getReviewer());
		//view info on changed object.
		System.out.println(tester.toString());
		
		//Serialize arraylist object
		try
		{
			FileOutputStream fileOut = new FileOutputStream("Papers.gov");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(papers);
			out.close();
			System.out.println("Serialized data is saved.");
		} catch (IOException i)
		{
			i.printStackTrace();
		}
		//make a different paper arraylist object
		ArrayList<Paper> unserializedpapers = new ArrayList<Paper>();
		//Un-serialize old test paper object and set to new paper new test.
		try
		{
			FileInputStream fileIn = new FileInputStream("Papers.gov");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			unserializedpapers = (ArrayList<Paper>) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i)
		{
			i.printStackTrace();
			return;
		} catch (ClassNotFoundException c)
		{
			System.out.println("File not found");
			c.printStackTrace();
			return;
		}
		
		//view info of new test paper arraylist after un serializing the file..
		System.out.println(unserializedpapers.get(0).toString());
		for (int i = 0; i < 3; i++)
		{
			System.out.println(unserializedpapers.get(0).getTheReviewer(i));
		}
		PaperSerialization.unserializePapers();
		PaperSerialization.serializePapers();
	}

}
