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

/**
 * @author Jonathan , Adahid
 */
public class Main  {

	static XArrayList<File> files = new XArrayList<File>();
	static XSinglyLinkedList customers = new XSinglyLinkedList();
	static BufferedReader bReader;
	static Iterator<Customer> restaurantIterator;
	static BufferedWriter writer;
	static int maxCustomers;
	static double maxProfit;

	public static void main(String[] args) {
		String line = "";
		try {
			bReader = new BufferedReader(new FileReader("input.txt"));

			//Read all the csv files from the input.txt
			while((line = bReader.readLine()) != null) {
				files.add(new File(line));
			}

			/**Iterates through the files found, reads them to save the customers to a SLL,
			 * it runs the four methods of serving and outputs the results.
			 */
			for (File e: files){
				String f = e.toString();
				customers = new XSinglyLinkedList();
				readCSVFile(e);
				writer = new BufferedWriter(new FileWriter(f.substring(0,f.length()-3)+"out"));
				writeOutput("MaxValues", new Pair<Double, Integer>(maxProfit,maxCustomers));
				writeOutput("Pat", runApproach("Pat"));
				writeOutput("Mat", runApproach("Mat"));
				writeOutput("Max", runApproach("Max"));
				writeOutput("Pac", runApproach("Pac"));
				writer.flush();
			}
			writer.close();
		} catch (NumberFormatException | IOException e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}

	/**
	 * @Author Jonathan
	 * Reads the csv files and saves the Customers information to a SLL
	 * @param file
	 * @throws NumberFormatException
	 * @throws IOException
	 */
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
		}
	}

	/**
	 * @Author Adahid
	 * Runs one of the approaches for serving the customers and returns the result of max profit
	 * and number of customers served.
	 * @param s  The string that specifies the serving approach to use
	 * @return  Pair of values that represent the max profit and number of customers served.
	 */
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
			return null;
		}

		restaurantIterator = customers.iterator();
		Integer count = 0;
		Double profit =0.0;

		for(Customer c: customers){
			count++;
			profit =Double.sum(profit, c.getValue());
		}
		customers.resetCustomers();
		return new Pair<Double, Integer>(profit, count);
	}

	/**
	 * @Author Adahid
	 * Uses the parameters provided to write to the output files the results
	 * @param person The string that specifies the serving approach of the values
	 * @param values Pair of values that represent the max profit and number of customers served.
	 * @throws IOException 
	 */
	public static void writeOutput(String person, Pair<Double, Integer> values) throws IOException{
		if(person.equals("MaxValues")){
			writer.append("Maximum profit possible: $"+ new DecimalFormat("#.00").format(values.getKey()));
			writer.newLine();
			writer.append("Maximum number of customers served possible: "+values.getValue());
		}
		else {
			writer.newLine();
			writer.append(person+"'s approach profit: $"+new DecimalFormat("#.00").format(values.getKey()));
			writer.newLine();
			writer.append(person+"'s approach number of disappointed customers: "+ (maxCustomers - values.getValue()));
		}

	}

}
