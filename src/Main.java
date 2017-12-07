import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Iterator;

import DataStructures.XArrayList;
import DataStructures.XSinglyLinkedList;
import Restaurant.Customer;
import javafx.util.Pair;

public class Main  {

	static XArrayList<File> files = new XArrayList<File>();
	static XSinglyLinkedList<Customer> customers = new XSinglyLinkedList<Customer>();
	static BufferedReader bReader;
	static Iterator<Customer> restaurantIterator;
	static BufferedWriter writer;
	static int maxCustomers;
	static double maxProfit;

	public static void main(String[] args) {
		String line = "";
		try {
			bReader = new BufferedReader(new FileReader("inout.txt"));
//			bReader = new BufferedReader(new FileReader("inputX.txt"));
			while((line = bReader.readLine()) != null) {
				files.add(new File(line));
				System.out.println("Found files: "+files.get(files.size()-1));
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		try {
			for (int i =0; i<files.size() ; i++){
				String f = files.get(i).toString();
				customers = new XSinglyLinkedList<Customer>();
				System.out.println("***************************"+f+"***************************");
				readCSVFile(files.get(i));
				writer = new BufferedWriter(new FileWriter(f.substring(0,f.length()-3)+"out"));
				writeOutput("MaxValues", new Pair<Double, Integer>(maxProfit,maxCustomers));
				writeOutput("Pat", runApproach("Pat"));
				writeOutput("Mat", runApproach("Mat"));
				writeOutput("Max", runApproach("Max"));
				writeOutput("Pac", runApproach("Pac"));
				
				System.out.println("***************************"+files.get(i)+"***************************");
				writer.flush();
			}
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
	}

	private static void readCSVFile(File file) throws NumberFormatException, IOException {
		bReader = new BufferedReader(new FileReader(file));
		maxCustomers=0;
		maxProfit=0.0;
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
			maxCustomers++;
			maxProfit+=value;
			//System.out.println(customers.get(customers.size()-1).toString());
		}
		System.out.println(maxCustomers + "   "+maxProfit);

	}

	private static Pair<Double, Integer> runApproach(String s) {
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
		Integer count = 0;
		Double profit =0.0;
		Customer cust;
//		System.out.println("----- "+s+" -----");
		while(restaurantIterator.hasNext()) {
			cust = restaurantIterator.next();
			System.out.println(cust.toString());
			count++;
			profit =Double.sum(profit, cust.getValue());
		}
		DecimalFormat df = new DecimalFormat("#.##");
		System.out.println("Customers served: "+count + "  Profit: " + df.format(profit));
//		System.out.println("----- "+s+" -----");
//		System.out.println();
		customers.resetCustomers();
		return new Pair<Double, Integer>(profit, count);
	}
	
	public static void writeOutput(String person, Pair<Double, Integer> values) throws IOException{
		if(person.equals("MaxValues")){
			writer.append("Maximum profit possible: $"+ new DecimalFormat("####.##").format(values.getKey()));
			writer.newLine();
			writer.append("Maximum number of customers served possible: "+values.getValue());
			writer.newLine();
		}
		else {
			writer.append(person+"'s approach profit: $"+new DecimalFormat("#.##").format(values.getKey()));
			writer.newLine();
			writer.append(person+"'s approach number of disappointed customers: "+ (maxCustomers - values.getValue()));
			writer.newLine();
		}
		
	}

}
