import java.util.Comparator;

public class compareByOrderID implements Comparator<Order> {
	
  /**
     * Compares two Order objects by primary key - Order ID to determine ordering
     * Returns 0 if the two items are equal
     * Return -1 if this Order's name comes alphabetically
     * before the other Order's name.
     * Returns 1 if the other Order's name comes
     * alphabetically before this Customer's name
     * @param the other Order object to compare to this
     * @return 0 (same Order), -1 (this Order ordered first)
     * or 1 (the other Order ordered first)
     */
  @Override
	public int compare(Order arg0, Order arg1) {
		return Integer.compare(arg0.getOrderID(), arg1.getOrderID());
				
	}
	
  /**
  * Compares two Customer objects by
  */
	public boolean equals(Order b, Object o) {
		if (o == this) {
	        return true;
	    } else if (!(o instanceof Order)) {
	        return false;
	    } else { // now safe to cast
	        Order p = (Order) o;
	        return b.getOrderID() - p.getOrderID() == 0;
	        		     
	    }
	}
	
}