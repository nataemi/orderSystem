

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadCSV {
	
	public static void readCSV(String filename, ArrayList<Order> orders) throws IOException{
		
		BufferedReader reader = new BufferedReader(new FileReader(
				filename));

		// read file line by line
		String line = null;
		Scanner scanner = null;
		int index = 0;

		int linecount = 0;
		// dodaj tu jeszcze obsluge bledu !!!
		// dodaj tu ze pomija jak jakies wartosci nie ma !!!
		// pamietaj ze te double ma przechowywac do dwoch miejsc po przecinku
		// dodaj sprawdzenie warunkow dla wartosci
		
		while ((line = reader.readLine()) != null) {
			String[] sar = line.split(",");
			boolean correct = true;
			Order emp = new Order();
			if(linecount>0){
				if (sar.length == 5) {
					for (index=0;index < sar.length;index++) {
							switch(index){
								case 0:{
									sar[0] = sar[0].trim();
									if (sar[0].contains(" ")) correct = false;
									if(sar[0].length()>6) correct = false;
									try{
										emp.setClientId(sar[0]);
									}
									catch(Exception e){
										correct = false;
									}
									break;
								}
								case 1:{
									try{
										emp.setRequestId(Long.parseLong(sar[1]));
									}
									catch(Exception e){
										correct = false;
									}
									break;
								}
								case 2:{
									if(sar[2].length() > 255) correct = false;
									try{
										emp.setName(sar[2]);
									}
									catch(Exception e){
										correct = false;
									}
									break;
								}
								case 3:{
									try{
										emp.setQuantity(Integer.parseInt(sar[3]));
									}
									catch(Exception e){
										correct = false;
									}
									break;
								}
								case 4:{
									try{
										BigDecimal dec = new BigDecimal(sar[4]);
										emp.setPrice(dec);
										if (dec.scale() != 2) correct = false;
										//if (dec.compareTo(BigDecimal.ZERO) > 0) correct = false;
										
									}
									catch(Exception e){
										correct = false;
									}
									break;
								}
							}
					}
				}
				else{
					System.out.print("Incorrect format: " + line + "\n");
				}
			}
			if (linecount>0 & correct == true)orders.add(emp);
			if (correct == false) System.out.println("Incorrect data: " + line);
			linecount++;
		
		}
		

		
		//close reader
		reader.close();
		
		
	}
	

}
