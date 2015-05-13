package Model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class PaperSerialization {
	public static List<Paper> papers = new ArrayList<Paper>();
	public static void unserializePapers()
	{
		try
		{
			FileInputStream fileIn = new FileInputStream("Papers.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			papers = (ArrayList<Paper>) in.readObject();
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
	
	}
	public static void serializePapers()
	{
		try
		{
			FileOutputStream fileOut = new FileOutputStream("Papers.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(papers);
			out.close();
			System.out.println("Serialized data is saved.");
		} catch (IOException i)
		{
			i.printStackTrace();
		}
	}
	public List<Paper> getPaperList()
	{
		return papers;
	}

}
