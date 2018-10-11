import java.math.BigDecimal;

public class Order {
	
	private String ClientId; 
	private long RequestId;
	private String Name;
	private int Quantity;
	private BigDecimal Price;
	public Order(String clientId2, long requestId2, String name2, int quantity2, BigDecimal price2) {
		ClientId = clientId2;
		RequestId = requestId2;
		Name = name2;
		Quantity = quantity2;
		Price = price2;
	}
	public Order(){
		
	}
	public String toString(){
		return "ClientId: " + ClientId + " RequestId: " + RequestId + " Name: " +  Name +
				" Quantity: " + Quantity + " Price: " + Price.setScale(2).toPlainString(); 
	} // SFORMTUJ JAKOS TO WYSWIETLANIE
	
	public void setClientId(String n){
		ClientId = n;
	}
	public void setRequestId(long n){
		RequestId = n;
	}
	public void setName(String n){
		Name = n;
	}
	public void setQuantity(int n){
		Quantity = n;
	}
	public void setPrice(BigDecimal n){
		Price = n;
	}
	
	public String getClientId(){
		return ClientId;
	}
	public long getRequestId(){
		return RequestId;
	}
	public String getName(){
		return Name;
	}
	public int getQuantity(){
		return Quantity;
	}
	public BigDecimal getPrice(){
		return Price;
	}
}
