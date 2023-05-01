/**
 * Order.java
 * @author Ananya Batra
 * @author Bence Danko
 * Course Project
 */

//Orders are added to the heap

//overnight shipping 1?
//rush shipping 2?
//standard shipping 3?

//order search:
//order id

//

import java.util.Comparator;

public class Order {

	private int orderID;
	private Customer customer;
	private String date;
	private LinkedList<Product> orderContents; //fill in Product based on your own product class
	private int shippingSpeed; 
	private int priority; 
	private int startYear = 2022;

    

//getters, setters, constructors go here
	
//*****************CONSTRUCTORS********************
	
	/**
	 * Creates a new Order
	 * @param orderID orderID 
	 * @param costumer customer for order
	 * @param date of the order
	 * @param orderContents whats in the order
	 * @param shippingSpeed decided by customer
	 * @param priority for the heap 
	 */
	public Order(int orderID, Customer customer, String date, LinkedList<Product> productList,
			int shippingSpeed) {
		
		this.orderID = orderID;
		this.customer = customer;
		this.date = date;
		orderContents = new LinkedList<Product>(productList);
		this.shippingSpeed = shippingSpeed;

		priority = 100 - shippingSpeed - setDateInt(date);
		
	}
	
	public Order(int orderID) {
		this.orderID = orderID;
	}
	
	public Order(Customer customer) {
		this.customer = customer;
	}

	

	
//*****************GETTERS********************


	/**
	 * get the order ID
	 * @return the order ID
	 */
	public int getOrderID() {
		return orderID;
	}
	
	/**
	 * return the customer of the order
	 * @return customer of order
	 */
	public Customer getCustomer() {
		return customer;
	}
	
	/**
	 * return date of order
	 * @return date of order
	 */
	public String getDate() {
		return date;
	}
	
	/**
	 * return product list
	 * @return product list of order
	 */
	public LinkedList<Product> getOrderContents() {
		return orderContents;
	}
	
	/**
	 * get the speed of shipping
	 * @return speed of shipping
	 */
	public int getShippingSpeed() {
		return shippingSpeed;
	}
	
	/**
	 * get the priority of the order
	 * @return priority of the order
	 */
	public int getPriority() {
		return priority;
	}
	
	

//*****************SETTERS********************
	
	/**
	 * set the order ID
	 * @param orderID of the order
	 */
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	
	/**
	 * set the customer
	 * @param customer of the order
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	/**
	 * return date of order
	 * @return date of order
	 */
	public void setDate(String date) {
		this.date = date;
	}
	
	public int setDateInt(String date) {
		String[] splitArray = date.split("/"); // "03/15/2022"
		int month = Integer.parseInt(splitArray[0]);
		int day = Integer.parseInt(splitArray[1]);
		int year = Integer.parseInt(splitArray[2]);
		
		
		int result = (startYear - year)*365 + month*30 + day;
		return result;
	}
	
	/**
	 * set the contents of the order
	 * @param orderContents of the order
	 */
	public void setOrderContents(LinkedList<Product> orderContents) {
		this.orderContents = orderContents;
	}
	
	/**
	 * set the shipping speed
	 * @param shippingSpeed of the order
	 */
	public void setShippingSpeed(int shippingSpeed) {
		 this.shippingSpeed = shippingSpeed;
	}
	
	/**
	 * set the priority of the order
	 * @param priority of the order
	 */
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	@Override
	public String toString() {
		return "Order ID: " + orderID + 
				"\nDate Ordered: " + date +
				"\nProducts: " + orderContents.toString();
	}
		
	

//*****************COMPARATORS********************
 
//public class priorityComparator implements Comparator<Order> {
//
//	public int compare(Order arg0, Order arg1) {
//		return arg0.priority - arg1.priority;
//	}
//	
//	public boolean equals(Object o) {
//		if (o == this) {
//	        return true;
//	    } else if (!(o instanceof Order)) {
//	        return false;
//	    } else { // now safe to cast
//	        Order p = (Order) o;
//	        return priority == p.priority;         
//	    }
//	}
//	
//}



public class priorityComparator1 implements Comparator<Order> {

	public int compare(Order arg0, Order arg1) {
		return Integer.compare(arg0.getPriority(),arg1.getPriority());
	}
}

public class orderIDComparator implements Comparator<Order> {

	public int compare(Order arg0, Order arg1) {
		return 0;
	}
	
	public boolean equals(Order order) {
		return false;
	}
	
}


}

////Should also contain three Comparator classes, 
//each with an equals and compare method to make comparison 
//depending on priority, customer name or order id.