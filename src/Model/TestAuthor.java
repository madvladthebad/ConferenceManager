package Model;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 
 * @author Melaku Mehiretu
 *
 */
public class TestAuthor {

	@Test
	public void testConstructor() 
	{
		User author = new Author("Vamm", "Square", "vamsquared@gmail.com", 1);
		
		//Test if the constructor is not null
		assertNotNull("author object is expected not to be null", author);
	}
	@Test
	public void testMethodes()
	{
		User author = new Author("Vamm", "Square", "vamsquared@gmail.com", 1);
		//Test if fName, lName, email address, userID and role id are stored in the author object
		System.out.println(author.toString());
		
		//test if setting the user id updates user id
		author.setUserId(10);
		
		System.out.println(author.toString());		
		
	}

}
