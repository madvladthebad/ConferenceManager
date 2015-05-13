package Model;
import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Test;
/**
 * 
 * @author Vladimir Guzyuk
 *
 */
public class ProgramChairTest {
	
	@Test
	public void testChair() {
		File file = null;
		ProgramChair chairTest = new ProgramChair("Vladimir", "Gudzyuk", "cooCooCachoo@poop.com",
				 9 ,666, 0, "HELL", "Hyper Elementary Lessons League");
		
		Paper tester = new Paper("Why Hell is cool", "Matt Dufalo", file, new Conference(1, "Code testing", "Agile "));
		
		Paper tester2 = new Paper("Why Hell is Lame", "Vladimir Gudzyuk", file,new Conference(1, "Software development", "Waterfall "));
		
		Paper tester3 = new Paper("Hell is good for kids", "Matt Dufalo", file,new Conference(1, "Parallel Programming", "IBm "));
		
		Paper tester4 = new Paper("What the Hell?", "Alex Stringham", file,new Conference(1, "C++ and Java", "Programming languages"));
		
		Conference conf = new Conference(1, "Useless Discussions and Stuff", 
				"All programmers, Location: UWT");
		
		conf.submitPaper(tester);
		conf.submitPaper(tester2);
		conf.submitPaper(tester3);
		conf.submitPaper(tester4);
		
		System.out.println(chairTest.toString());
		
		chairTest.acceptDeclinePapers(conf, "Why Hell is cool", 1);
		
		chairTest.acceptDeclinePapers(conf,"Why Hell is Lame", 2);
		
		assertEquals("are not true", 0, tester4.acceptReject);
		
		assertEquals("are not false", 0, tester3.acceptReject);
		
		assertEquals("are not false", 1, tester.acceptReject);
		
		assertEquals("are not false", 2, tester2.acceptReject);
	}

}
