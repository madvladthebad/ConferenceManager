package Model;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

public class ConferenceTest {

	@Test
	public void testConstructor() 
	{
		Conference conf = new Conference(1, "Coding Competition", 
				"All programmers, Location: UWT");
		//Test if the above code creates a non-null object
		assertNotNull("Expected value of conf is not null", conf);
	}
	
	@Test 
	public void testMethods()
	{
		File file = null;
		Conference conf = new Conference(1, "Coding Competition", 
				"All programmers, Location: UWT");
		Paper paper = new Paper("Object Oriented Prog", "Melaku", file, 
				new Conference(1, "Coding bat", "Game coding competition"));
		//test if the conference title, id, and description is stored in the conf object
		System.out.println(conf.toString());
		
		//test submitting a paper		
		conf.submitPaper(paper);
		//expected size of paper list is 1.
		assertEquals("Expected list size Vs actual: ", 1, conf.getPapers().size());
		
		//test deleting a paper
		conf.deletePaper(paper);
		//expected size of paper list is 0.
		assertEquals("Expected list size Vs actual: ", 0, conf.getPapers().size());
		
	}

}
