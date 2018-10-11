


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

ArrayList<Order> orders;


Main(String[] array) throws IOException{
	
	orders = new ArrayList<Order>();
	
	for (int i=0;i<array.length;i++){
		if (array[i].contains(".csv")){
			ReadCSV.readCSV(array[i], orders);
		}
		else if (array[i].contains(".xml")){
			ReadXML.readXML(array[i],orders);
		}
		else{
			System.out.println("Wrong data format: " + array[i]);
		}
	}
	
	
}
	
void addOrder(String ClientId, long RequestId,String Name,int Quantity,BigDecimal Price){
	orders.add(new Order(ClientId,RequestId, Name,Quantity,Price));
}

void printOrders(boolean c) throws FileNotFoundException{
	if(!c){
		System.out.println("All orders: ");
		for (Order o: orders){
			System.out.println(o);
		}
	}
	else{
		LocalDateTime now = LocalDateTime.now();
		String isoFormat = DateTimeFormatter.ISO_INSTANT.format(now.toInstant(ZoneOffset.UTC));
		System.out.println(isoFormat);
		PrintWriter pw = new PrintWriter(new File(isoFormat + ".csv"));
		StringBuilder sb = new StringBuilder();
	    sb.append("All orders: ");
	    sb.append("\n");
	    for (Order o: orders){
			sb.append(o);
			sb.append("\n");
		}
	    pw.write(sb.toString());
	    pw.close();
	    System.out.println("Csv file created");
	}
}

void amountOfOrders(boolean c) throws FileNotFoundException{
	if(!c){
		System.out.println("The number of orders: " + orders.size());
	}
	else{
		LocalDateTime now = LocalDateTime.now();
		String isoFormat = DateTimeFormatter.ISO_INSTANT.format(now.toInstant(ZoneOffset.UTC));
		System.out.println(isoFormat);
		PrintWriter pw = new PrintWriter(new File(isoFormat + ".csv"));
	    pw.write("The number of orders: " + orders.size());
	    pw.close();
	    System.out.println("Csv file created");
	}
}

void amountOfOrdersForClientWithId(String Id,boolean c) throws FileNotFoundException{
	int amount = 0;
	for (Order o: orders){
		if (o.getClientId().equals(Id)) amount++;
	}
	if(!c){
		System.out.println("The number of orders for client with Id: " + Id+ " is: " + amount);
	}
	else{
		LocalDateTime now = LocalDateTime.now();
		String isoFormat = DateTimeFormatter.ISO_INSTANT.format(now.toInstant(ZoneOffset.UTC));
		System.out.println(isoFormat);
		PrintWriter pw = new PrintWriter(new File(isoFormat + ".csv"));
	    pw.write("The number of orders for client with Id: " + Id+ " is: " + amount);
	    pw.close();
	    System.out.println("Csv file created");
	}
}

void totalPrice(boolean c) throws FileNotFoundException{
	BigDecimal price = new BigDecimal("0.00");
	for (Order o: orders){
		price = price.add(o.getPrice());
	}
	if(!c){
		System.out.println("The total value of all orders: " + price.setScale(2).toPlainString());
	}
	else{
		LocalDateTime now = LocalDateTime.now();
		String isoFormat = DateTimeFormatter.ISO_INSTANT.format(now.toInstant(ZoneOffset.UTC));
		System.out.println(isoFormat);
		PrintWriter pw = new PrintWriter(new File(isoFormat + ".csv"));
	    pw.write("The total value of all orders: " + price.setScale(2).toPlainString());
	    pw.close();
	    System.out.println("Csv file created");
	}
}

void totalPriceforClientWithId(String Id, boolean c) throws FileNotFoundException{
	
	BigDecimal price = new BigDecimal("0.00");
	for (Order o: orders){
		if (o.getClientId().equals(Id)) price = price.add(o.getPrice());
	}
	if(!c){
		System.out.println("The total value of orders for client with Id: "+ Id+ " is: " + price.setScale(2).toPlainString());
	}
	else{
		LocalDateTime now = LocalDateTime.now();
		String isoFormat = DateTimeFormatter.ISO_INSTANT.format(now.toInstant(ZoneOffset.UTC));
		System.out.println(isoFormat);
		PrintWriter pw = new PrintWriter(new File(isoFormat + ".csv"));
	    pw.write("The total value of orders for client with Id: " + Id+ " is: " + price.setScale(2).toPlainString());
	    pw.close();
	    System.out.println("Csv file created");
	}
}

void printOrdersforClientWithId(String Id,boolean c) throws FileNotFoundException{
	if (!c){
		System.out.println("Orders for client with Id: " + Id + " : ");
		for (Order o: orders){
			if (o.getClientId().equals(Id)) System.out.println(o);
		}
	}
	else{
		LocalDateTime now = LocalDateTime.now();
		String isoFormat = DateTimeFormatter.ISO_INSTANT.format(now.toInstant(ZoneOffset.UTC));
		System.out.println(isoFormat);
		PrintWriter pw = new PrintWriter(new File(isoFormat + ".csv"));
		StringBuilder sb = new StringBuilder();
	    sb.append("Orders for client with Id: " + Id + " : ");
	    sb.append("\n");
	    for (Order o: orders){
			if (o.getClientId().equals(Id)){
				sb.append(o);
				sb.append("\n");
			}
		}
	    pw.write(sb.toString());
	    pw.close();
	    System.out.println("Csv file created");
	}
}

void averageOrderPrice(boolean c) throws FileNotFoundException{
	BigDecimal price = new BigDecimal("0.00");
	int amount = 0;
	for (Order o: orders){
		price = price.add(o.getPrice());
		amount++;
	}
	if (!c){
		System.out.println("Average order value: " + (price.divide(new BigDecimal(amount), 2, RoundingMode.HALF_UP)));
	}
	else{
		LocalDateTime now = LocalDateTime.now();
		String isoFormat = DateTimeFormatter.ISO_INSTANT.format(now.toInstant(ZoneOffset.UTC));
		System.out.println(isoFormat);
		PrintWriter pw = new PrintWriter(new File(isoFormat + ".csv"));
	    pw.write("Average order value: "  + (price.divide(new BigDecimal(amount), 2, RoundingMode.HALF_UP)));
	    pw.close();
	    System.out.println("Csv file created");
	}
}


void averageOrderPriceforClientWithId(String Id,boolean c) throws FileNotFoundException{
	BigDecimal price = new BigDecimal("0.00");
	int amount = 0;
	for (Order o: orders){
		if (o.getClientId().equals(Id)){
		price = price.add(o.getPrice());
		amount++;
		}
	}
	if(!c){
		System.out.println("Average order value for client with Id: " + Id + " is: " + (price.divide(new BigDecimal(amount + ".00"), 2, RoundingMode.HALF_UP)));
	}
	else{
		LocalDateTime now = LocalDateTime.now();
		String isoFormat = DateTimeFormatter.ISO_INSTANT.format(now.toInstant(ZoneOffset.UTC));
		System.out.println(isoFormat);
		PrintWriter pw = new PrintWriter(new File(isoFormat + ".csv"));
	    pw.write("Average order value for client with Id: " + Id + " is: " + (price.divide(new BigDecimal(amount + ".00"), 2, RoundingMode.HALF_UP)));
	    pw.close();
	    System.out.println("Csv file created");
	}
}

boolean checkForClientWithId(String Id){
	for (Order o: orders){
		if (o.getClientId().equals(Id)){
		return true;
		}
	}
return false;
}

boolean checkIfCSV(){
	System.out.print("Would you like to generate a csv file? /N ");
	Scanner Scanning = new Scanner(System.in);
	String s1 = Scanning.nextLine();
	if (s1.equals("Y") || s1.equals("y")){
		return true;
	}
	else if(s1.equals("N") || s1.equals("n")){
		return false;
	}
	else{
		System.out.println("Wrong answer format.");
		return false;
	}
	
}


public static void main (String args[]) throws IOException{
	/*String csvName = "/Users/natalia/Documents/workspace/rekrutacyjne/src/zamowienie.csv";
	String xmlName = "/Users/natalia/Documents/workspace/rekrutacyjne/src/zamowienie.xml";
	String[] ar = {csvName, xmlName};*/
	Main main = new Main(args);
	System.out.println(" Welcome to the order system! \n");
    Scanner Scan = new Scanner(System.in);
    if (args.length != 0) {
	    boolean check = true;
	    while(check){
	    System.out.println("Input a command: (Or write: help) \n");
	    String s1 = Scan.nextLine();
	    boolean checkCSV;
	    String[] sar = s1.split(" ");
	    switch (sar[0]){
		    case "help":{
		        System.out.println("Input correct command to generate a report:");
		        System.out.println("sumOrders - The number of orders");
		        System.out.println("sumOrders [id] - The number of orders for client with Id");
		        System.out.println("sumAmount - The total value of all orders");
		        System.out.println("sumAmount [id] - The total value of orders for client with Id");
		        System.out.println("list - List of orders");
		        System.out.println("list [id] - List of orders for client with Id");
		        System.out.println("avg - Average value of orders");
		        System.out.println("avg [id] - Average value of orders for client with Id");
		        System.out.println("exit - End the application");
		        System.out.println(" ");
		        break;}
		    case "sumOrders": {
		    	checkCSV = main.checkIfCSV();
		    	if(sar.length == 1){
		    		main.amountOfOrders(checkCSV);
		    		break; 
		    	}
		    	else{
		    		if(main.checkForClientWithId(sar[1]) == true)
		    	    	main.amountOfOrdersForClientWithId(sar[1],checkCSV);
		    	    	else{
		    	    		System.out.println("No client with the inputted Id");
		    	    	}
		    	    	break;
		    	}
		    }
		    
		    case "sumAmount": {
		    	checkCSV = main.checkIfCSV();
		    	if(sar.length == 1){
		    		main.totalPrice(checkCSV);
		    		break; 
		    	}
		    	else{
		    		if(main.checkForClientWithId(sar[1]) == true)
		    	    	main.totalPriceforClientWithId(sar[1],checkCSV);
		    	    	else{
		    	    		System.out.println("No client with the inputted Id");
		    	    	}
		    	    	break;
		    	}
		    }
		    case "list": {
		    	checkCSV = main.checkIfCSV();
		    	if(sar.length == 1){
		    		main.printOrders(checkCSV);
		    		break; 
		    	}
		    	else{
		    		if(main.checkForClientWithId(sar[1]) == true)
		    	    	main.printOrdersforClientWithId(sar[1],checkCSV);
		    	    	else{
		    	    		System.out.println("No client with the inputted Id");
		    	    	}
		    	    	break;
		    	}
		    }
		    case "avg": {
		    	checkCSV = main.checkIfCSV();
		    	if(sar.length == 1){
		    		main.averageOrderPrice(checkCSV);
		    		break; 
		    	}
		    	else{
		    		if(main.checkForClientWithId(sar[1]) == true)
		    	    	main.averageOrderPriceforClientWithId(sar[1],checkCSV);
		    	    	else{
		    	    		System.out.println("No client with the inputted Id");
		    	    	}
		    	    	break;
		    	}
		    }
		    case "exit":{
		    	System.out.println("Goodbye!");
		    	System.exit(0);
		    	check = false;
		    	break;}
		    default: 
		    	System.out.println("Wprowadzono błędną komendę.");
	    	}
	    }
    }
    else{
    	System.out.println("No data input");
    }
 
    //Close
    Scan.close();
    System.out.println(" Closing Application ");
	
}
}



