

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.*;


import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class ReadXML{

public static void readXML (String filename, ArrayList<Order> orders){
try {

	DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
	Document doc = docBuilder.parse (new File(filename));

	// normalize text representation
	doc.getDocumentElement ().normalize ();

	NodeList listOfOrders = doc.getElementsByTagName("request");

	for(int s=0; s<listOfOrders.getLength() ; s++){

		Order emp = new Order();
		boolean correct = true;

		Node firstOrderNode = listOfOrders.item(s);
		if(firstOrderNode.getNodeType() == Node.ELEMENT_NODE){

			Element firstOrderElement = (Element)firstOrderNode; 
			String data;
			//-------
			NodeList clientIdList = firstOrderElement.getElementsByTagName("clientId");
			try{
			Element clientIdElement = (Element)clientIdList.item(0);
			NodeList textFNList = clientIdElement.getChildNodes();
			data = ((Node)textFNList.item(0)).getNodeValue().trim();
			if (data.contains(" ")) correct = false;
			if(data.length()>6) correct = false;
			emp.setClientId(data);
			}
			catch(Exception e){
				correct = false;
			}
			
			
			
			
			//------- 
			NodeList requestIdList = firstOrderElement.getElementsByTagName("requestId");
			try{
			Element requestIdElement = (Element)requestIdList.item(0);
			
			NodeList textLNList = requestIdElement.getChildNodes();
			data = ((Node)textLNList.item(0)).getNodeValue().trim();
			emp.setRequestId(Long.parseLong(data));
			}
			catch(Exception e){
				correct = false;
			}
			
			//----
			NodeList nameList = firstOrderElement.getElementsByTagName("name");
			try{
			Element nameElement = (Element)nameList.item(0);
			
			NodeList textnameList = nameElement.getChildNodes();
			data = ((Node)textnameList.item(0)).getNodeValue().trim();
			if(data.length() > 255) correct = false;
			emp.setName(data);
			}
			catch(Exception e){
				correct = false;
			}
			
			
			NodeList quantityList = firstOrderElement.getElementsByTagName("quantity");
			try{
			Element quantityElement = (Element)quantityList.item(0);
			
			NodeList textquantityList = quantityElement.getChildNodes();
			data = ((Node)textquantityList.item(0)).getNodeValue().trim();
			emp.setQuantity(Integer.parseInt(data));
			}
			catch(Exception e){
				correct = false;
			}
			
			
			NodeList priceList = firstOrderElement.getElementsByTagName("price");
			try{
				Element priceElement = (Element)priceList.item(0);
			
			NodeList textpriceList = priceElement.getChildNodes();
			data = ((Node)textpriceList.item(0)).getNodeValue().trim();
			emp.setPrice(new BigDecimal(data));
			if ((new BigDecimal(data)).scale() != 2) correct = false;
			}
			catch(Exception e){
				correct = false;
			}
		
		//------


			}//end of if clause

		if (correct) orders.add(emp);
		else System.out.println("Incorrect line ommited in XML file");
	}//end of for loop with s var


}catch (SAXParseException err) {
System.out.println ("** Parsing error" + ", line " + err.getLineNumber () + ", uri " + err.getSystemId ());
System.out.println(" " + err.getMessage ());

}catch (SAXException e) {
Exception x = e.getException ();
((x == null) ? e : x).printStackTrace ();

}catch (Throwable t) {
t.printStackTrace ();
}
//System.exit (0);

}//end of main


}

