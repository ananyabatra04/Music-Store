import java.util.Comparator;

public class priorityComparator1 implements Comparator<Order> {
 
  /**
     * Compares two Order objects by primary key - priority to determine ordering
     * Returns 0 if the two items are equal
     * Return -1 if this Order's priority is less
     * than the other Order's.
     * Returns 1 if the other Customer's priority is greater
     * than the other Customer's 
     * @param the other Order object to compare to this
     * @return 0 (same Order), -1 (this Order ordered first)
     * or 1 (the other Customer ordered first)
     */
  @Override
  
	public int compare(Order arg0, Order arg1) {
		return Integer.compare(arg0.getPriority(),arg1.getPriority());
	}
}