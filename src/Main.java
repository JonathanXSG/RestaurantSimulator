import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	static Scanner readFile;
	static ArrayList<File> files = new ArrayList<File>();
	static ArrayList<Customer> customers = new ArrayList<Customer>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			readFile = new Scanner(new File("input.txt"));
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
		
		while(readFile.hasNext()) {
			files.add(new File(readFile.nextLine()));
			System.out.println("Found files: "+files.get(files.size()-1));
		}
		for (File file : files) {
			try {
				readFile = new Scanner(file);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			while(readFile.hasNextLine()) {
				int arrival = Integer.valueOf(readFile.nextInt());
				int uid = Integer.valueOf(readFile.nextInt());
				int orderTime = Integer.valueOf(readFile.nextInt());
				Double value = Double.valueOf(readFile.nextDouble());
				int patience = Integer.valueOf(readFile.nextInt());
				customers.add(new Customer(arrival,uid,orderTime,value,patience));
				System.out.println(customers.get(customers.size()-1));
			}
		}
		
	}

}
