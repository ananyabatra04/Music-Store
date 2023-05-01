
public class Employee extends User{

	private boolean isManager;
	
	public Employee() {
		isManager = false;
		firstName = null;
		lastName = null;
		username = null;
		password = null;
	}
	
	public Employee(Boolean isManager, String firstName, String lastName, String username, String password) {
		this.isManager = isManager;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
	}
	
	public Employee(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public Boolean getIsManager() {
		return isManager;
	}
	public void promoteToManager() {
		isManager = true;
	}
	
	public String toString() {
		String result = isManager + ", " + firstName + ", " + lastName + ", " + username + ", " + password;
		
		
		return result;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		else if (!(o instanceof Employee)) {
			return false;
		}
		else {
			Employee myEmployee = (Employee) o;
			if (this.password.equals(myEmployee.password) && this.username.equals(myEmployee.username)) {
				return true;
			}
			else {
				return false;
			}
		}
		
	}
	
	@Override
	public int hashCode() {
		String key = username + password; //define key for this class
		int sum = 0;
		for (int i = 0; i < key.length(); i++) {
			sum += (int) key.charAt(i);
		}
		
		return sum;
	}
	
	
	
}