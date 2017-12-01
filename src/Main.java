import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import DataStructures.XArrayList;
import Restaurant.Customer;

public class Main {

	static Scanner readFile;
	static XArrayList<File> files = new XArrayList<File>();
	static XArrayList<Customer> customers = new XArrayList<Customer>();
	static BufferedReader bReader;
	ArrayList<E> l;
	
	public static void main(String[] args) {
		try {
			readFile = new Scanner(new File("input.txt"));
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
		while(readFile.hasNext()) {
			files.add(new File(readFile.nextLine()));
			System.out.println("Found files: "+files.get(files.size()-1));
		}
		
		try {
			readCSVFile(files);
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void readCSVFile(XArrayList<File> files) throws NumberFormatException, IOException {
		for (int i =0; i<files.size() ; i++) {
			File file = files.get(i);
			bReader = new BufferedReader(new FileReader(file));

			String line = "";
			String[] customerInfo;
			while((line = bReader.readLine()) != null) {
				customerInfo = line.split(",");
				int arrival = Integer.valueOf(customerInfo[0]);
				int uid = Integer.valueOf(customerInfo[1]);
				int orderTime = Integer.valueOf(customerInfo[2]);
				Double value = Double.valueOf(customerInfo[3]);
				int patience = Integer.valueOf(customerInfo[4]);
				customers.add(new Customer(arrival,uid,orderTime,value,patience));
				System.out.println(customers.get(customers.size()-1).toString());
			}
		}
	}

}
