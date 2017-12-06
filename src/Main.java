import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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
	static BufferedWriter writer;

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
			for (int i =0; i<files.size() ; i++){
				customers = new XSinglyLinkedList<Customer>();
				System.out.println("***************************"+files.get(i)+"***************************");
				readCSVFile(files.get(i));
				writeOutput(files.get(i).toString(), "Pat", runApproach("Pat"), 0.0, 0);
				writeOutput(files.get(i).toString(), "Pat", runApproach("Mat"), 0.0, 0);
				writeOutput(files.get(i).toString(), "Pat", runApproach("Max"), 0.0, 0);
				writeOutput(files.get(i).toString(), "Pat", runApproach("Pac"), 0.0, 0);
				
				System.out.println("***************************"+files.get(i)+"***************************");
				writer.flush();
			}
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
	}

	private static void readCSVFile(File file) throws NumberFormatException, IOException {

		bReader = new BufferedReader(new FileReader(file));
		String line = "";
		String[] customerInfo;
		while((line = bReader.readLine()) != null) {
			customerInfo = line.split(",");
			int arrival = Integer.valueOf(customerInfo[0]);
			int uid = Integer.valueOf(customerInfo[1]);
			int orderTime = Integer.valueOf(customerInfo[2]);
			Double value = Double.valueOf(customerInfo[3].substring(1));
			int patience = Integer.valueOf(customerInfo[4]);
			customers.add(new Customer(arrival,uid,orderTime,value,patience));
			//System.out.println(customers.get(customers.size()-1).toString());
		}
		//System.out.println();

	}

	private static double[] runApproach(String s) {
		switch (s) {
		case "Pat":
			customers.setIterator(s);
			break;
		case "Mat":
			customers.setIterator(s);
			break;
		case "Max":
			customers.setIterator(s);
			break;
		case "Pac":
			customers.setIterator(s);
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
		return new double[]{profit,count};
	}
	
	public static void writeOutput(String s, String person, double[] d, double maxP, int maxC) throws IOException{
		 writer = new BufferedWriter(new FileWriter(s.substring(0,s.length()-3)+"txt"));
		if(s.equals("Pat")){
			writer.write("Maximum profit possible: $"+maxP);
			writer.newLine();
			writer.write("Maximum number of customers served possible: "+maxC);
			writer.newLine();
		}
		writer.write(person+"’s approach profit: $"+d[0]);
		writer.newLine();
		writer.write(person+"'s approach number of disappointed customers: "+(maxP-d[1]));
		writer.newLine();
	}

}
