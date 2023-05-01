
public abstract class User {
	
	String firstName;
	String lastName;
	String username;
	String password;
	
	public User() {
		this.firstName = null;
		this.lastName = null;
		this.username = null;
		this.password = null;
	}
	
	public User(String firstName, String lastName, String username, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	
	public void setFirstName(String newFirst) {
		firstName = newFirst;
	}
	public void setLastName(String newLast) {
		lastName = newLast;
	}
	public void setUsername(String newUsername) {
		username = newUsername;
	}
	public void setPassword(String newPassword) {
		password = newPassword;
	}
	
	
	
}