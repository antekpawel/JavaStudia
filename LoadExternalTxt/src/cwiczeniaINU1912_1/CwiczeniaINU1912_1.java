package cwiczeniaINU1912_1;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Scanner;

public class CwiczeniaINU1912_1 {
	
	public static void main(String[] args) {
		Scanner in = null;
		int id = 0;
		String firstName = "";
		String lastName = "";
		
		LinkedList<Person> list = new LinkedList<Person>();
		PrintWriter out = null;
		
		try {
			in = new Scanner(Paths.get("C:\\Users\\Antek\\"
					+ "eclipse-workspace\\CwiczeniaINU1912_1\\"
					+ "src\\cwiczeniaINU1912_1\\nazwiska.txt"));
			while (in.hasNext()) {
				id = in.nextInt();
				firstName = in.next();
				lastName = in.next();
				list.add(new Person(id, firstName, lastName));
				System.out.printf(
						"Wczytane dane -> id: %3d, "
						+ "imiê: %-10s, nazwisko: %-10s\n",
						id, firstName, lastName);
			}
			
			out = new PrintWriter("C:\\Users\\Antek\\"
					+ "eclipse-workspace\\CwiczeniaINU1912_1\\"
					+ "src\\cwiczeniaINU1912_1\\nazwiska_out.txt");
			
			for (Person e : list) {
				out.printf("%-15s %-15s %-2d\n", 
						e.getLastName(), 
						e.getFirstName(), 
						e.getID());
			}
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (in != null) in.close();
		}
	}
}
