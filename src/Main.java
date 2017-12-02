import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import DataStructures.XArrayList;
import DataStructures.XSinglyLinkedList;
import Restaurant.Customer;

public class Main  {

	static XArrayList<File> files = new XArrayList<File>();
	static XSinglyLinkedList<Customer> customers = new XSinglyLinkedList<Customer>();
	static BufferedReader bReader;
	static Iterator<Customer> restaurantIterator;
	
	public static void main(String[] args) {
		String line = "";
		try {
			bReader = new BufferedReader(new FileReader("input.txt"));
			while((line = bReader.readLine()) != null) {
				files.add(new File(line));
				System.out.println("Found files: "+files.get(files.size()-1));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
		try {
			readCSVFile(files);
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
		
		runApproach("Pat");
		runApproach("Mat");
		runApproach("Max");
		runApproach("Pac");
		
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
			System.out.println();
		}
	}

	private static void runApproach(String s) {
		switch (s) {
		case "Pat":
			// customers.setIterator(String s);
			break;
		case "Mat":
			// customers.setIterator(String s);
			break;
		case "Max":
			// customers.setIterator(String s);
			break;
		case "Pac":
			// customers.setIterator(String s);
			break;
		default:
			break;
		}
		restaurantIterator = customers.iterator();
		int count = 0;
		double profit =0.0;
		Customer cust;
		System.out.println("----- "+s+" -----");
		while(restaurantIterator.hasNext()) {
			cust = restaurantIterator.next();
			System.out.println(cust.toString());
			count++;
			profit =Double.sum(profit, cust.getValue());
		}
		DecimalFormat df = new DecimalFormat("#.##");
		System.out.println("Customers served: "+count + "  Profit: " + df.format(profit));
		System.out.println("----- "+s+" -----");
		System.out.println();
		customers.resetCustomers();
	}

}
