import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

//import java.io.CharConversionException;

class main {

	static HashTable<Customer> customerTable = new HashTable<>(100);
	static HashTable<Employee> employeeTable = new HashTable<>(100);

	static User userLoggedIn = null;

	static LinkedList<Product> orderProductList = new LinkedList<Product>();

	static String customerFilename = "CustomerDB.txt";
	static String employeeFilename = "EmployeeDB.txt";
	
	static String unshippedOrdersFileName = "unshippedOrders.txt";
	static String shippedOrdersFileName = "shippedOrders.txt";

	static Heap<Order> priorityQueue = new Heap<Order>(new ArrayList<Order>(), new priorityComparator1());
  
	static Heap<Order> shippedOrders = new Heap<Order>(new ArrayList<Order>(), new priorityComparator1());

	static BST<Product> products_name = new BST<Product>();
	static BST<Product> products_id = new BST<Product>();
	static BST<Product> products = new BST<Product>();

	static int orderID = 000;

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		// ***********************LOAD IN FILES******************************
		readAllFiles();

		// ***********************LOAD IN FILES******************************

		boolean managerMenuOnline = false;
		boolean employeeMenuOnline = false;
		boolean customerMenuOnline = false;
		Boolean loginSuccessful = false;
		String userInput;

		System.out.println("\n\nWelcome to Our Music Store!");
		while (userLoggedIn == null) {
			System.out.println("Enter 1 to Create Account");
			System.out.println("Enter 2 to Login");
			System.out.println("Enter 3 to exit");

			userInput = input.nextLine();
			// input.nextLine();
			if (userInput.equals("1")) {
				boolean dataApproved = false;
				String input_firstName = null;
				String input_lastName = null;
				String input_username = null;
				String input_password = null;
				String input_address = null;
				String input_city = null;
				String input_state = null;
				String input_zip = null;

				while (!dataApproved) {
					System.out.println("Enter your first name: ");
					input_firstName = input.nextLine();
					if (!(input_firstName.contains(","))) {
						dataApproved = true;
					} else {
						System.out.println("Invalid first name! First name may not contain commas. Please Try Again.");
					}
				}
				dataApproved = false;
				while (!dataApproved) {
					System.out.println("Enter your last name: ");
					input_lastName = input.nextLine();
					if (!(input_lastName.contains(","))) {
						dataApproved = true;
					} else {
						System.out.println("Invalid last name! Last name may not contain commas. Please Try Again.");
					}
				}
				dataApproved = false;
				while (!dataApproved) {
					System.out.println("Enter a username: ");
					input_username = input.nextLine();
					if (!(input_username.contains(","))) {
						dataApproved = true;
					} else {
						System.out.println("Invalid username! The username may not contain commas. Please Try Again.");
					}
				}
				dataApproved = false;
				while (!dataApproved) {
					System.out.println("Enter a password: ");
					input_password = input.nextLine();
					if (!(input_password.contains(","))) {
						dataApproved = true;
					} else {
						System.out.println("Invalid password! The password may not contain commas. Please Try Again.");
					}
				}
				dataApproved = false;
				while (!dataApproved) {
					System.out.println("Enter your street address: ");
					input_address = input.nextLine();
					if (!(input_address.contains(","))) {
						dataApproved = true;
					} else {
						System.out.println("Invalid Address! The Address may not contain commas. Please Try Again.");
					}
				}
				dataApproved = false;
				while (!dataApproved) {
					System.out.println("Enter the city of your residence: ");
					input_city = input.nextLine();
					if (!(input_city.contains(","))) {
						dataApproved = true;
					} else {
						System.out.println("Invalid Input for city! The city may not contain commas. Please Try Again");
					}
				}
				dataApproved = false;
				while (!dataApproved) {
					System.out.println("Enter the state of your residence: ");
					input_state = input.nextLine();
					if (!(input_state.contains(","))) {
						dataApproved = true;
					} else {
						System.out.println("Invalid State! The State may not contain commas. Please Try Again.");
					}
				}
				dataApproved = false;
				while (!dataApproved) {
					System.out.println("Enter the zip code of your residence: ");
					input_zip = input.nextLine();
					if (!(input_zip.contains(","))) {
						dataApproved = true;
					} else {
						System.out.println("Invalid Zip! The Zip may not contain commas. Please Try Again.");
					}
				}

				customerTable.add(new Customer(input_firstName, input_lastName, input_username, input_password,
						input_address, input_city, input_state, input_zip));
				userLoggedIn = new Customer(input_firstName, input_lastName, input_username, input_password,
						input_address, input_city, input_state, input_zip);
				
				System.out.println("--------------------------------------------");

				System.out.println("Successful Customer Creation and Login. Welcome, "
						+ customerTable.find(new Customer(input_username, input_password)).getFirstName() + "!\n");

				loginSuccessful = true;

				customerMenuOnline = true;

			} else if (userInput.equals("2")) { // LOGIN STARTUP

				System.out.println("Enter 1 if you are a customer");
				System.out.println("Enter 2 if you are an employee");
				userInput = input.nextLine();
				if (userInput.equals("1")) { // THEY ARE LOGGIN IN AS CUSTOMER
					while (!loginSuccessful) {
						System.out.println("Enter username: ");
						String entered_username = input.nextLine();
						System.out.println("Enter password: ");
						String entered_password = input.nextLine();
						System.out.println("--------------------------------------------");

						if (customerTable.find(new Customer(entered_username, entered_password)) == null) {
							System.out.println(
									"We could not find an account with that username/password. Please try again below!");
						} else {
							System.out.println("Successful Customer Login. Welcome Back, " + customerTable
									.find(new Customer(entered_username, entered_password)).getFirstName() + "!\n");
							userLoggedIn = customerTable.find(new Customer(entered_username, entered_password));
							loginSuccessful = true;
							customerMenuOnline = true;

						}
					}

				} else if (userInput.equals("2")) { // THEY ARE LOGGIN IN AS EMPLOYEE

					while (!loginSuccessful) {

						System.out.println("Enter username: ");
						String entered_username = input.nextLine();
						System.out.println("Enter password: ");
						String entered_password = input.nextLine();
						

						if (employeeTable.find(new Employee(entered_username, entered_password)) == null) {
							System.out.println(
									"We could not find an account with that username/password. Please try again below!");
						} else {
							userLoggedIn = employeeTable.find(new Employee(entered_username, entered_password));
							if (((Employee) userLoggedIn).getIsManager()) {
								System.out.println("--------------------------------------------");
								System.out.print("Successful Manager Login. Welcome Back, "
										+ userLoggedIn.getFirstName() + "!\n");
								employeeMenuOnline = true;
								managerMenuOnline = true;
								loginSuccessful = true;

							} else {
								System.out.println("--------------------------------------------");
								System.out.print("Successful Employee Login. Welcome Back, "
										+ userLoggedIn.getFirstName() + "!\n");
							}
							loginSuccessful = true;
							employeeMenuOnline = true;
						}

					}
				} else {
					System.out.println("Invalid option! Taking you back to the main menu...\n");
				}
			} else if (userInput.equals("3")) { // THEY ARE EXITING
				// write all files
				// exit
				// ***********************WRITE TO FILES******************************
				writeAllFiles();
				input.close();
				System.exit(0);

			} else {
				System.out.println("Please type a valid option!");
			}
			
			while (customerMenuOnline) { // CUSTOMER MENU OPTIONS

				System.out.println("1. Search for the Product by Name");
				System.out.println("2. Search for the Product by ID");
				System.out.println("3. List the Product Database Sorted by Name");
				System.out.println("4. List the Product Database Sorted by ID");

				System.out.println("5. Place an Order");
				System.out.println("6. View purchased orders");

				System.out.println("Enter Q to Quit");

				userInput = input.nextLine();

				if (userInput.equals("1")) {
					System.out.println("Please Enter the Product Name to Search");
					userInput = input.nextLine();
					Product search_output = products_name.searchByName(new Product(userInput), new CompareByName());
					if (search_output != null) {
						System.out.println("Product name: " + search_output.getName());
						System.out.println("Product ID: " + search_output.getUID());
						System.out.println("Product Singer: " + search_output.getSinger());
						System.out.println("Product Duration: " + search_output.getDuration());
						System.out.println("Product Genre: " + search_output.getGenre());
						System.out.println("Product Release Year: " + search_output.getReleaseYear());
						System.out.println("Product Cost: $" + search_output.getCost() + "\n");
					} else {
						System.out.println("There are no Products with this name.");
					}
				}

				if (userInput.equals("2")) {
					System.out.println("Please Enter the Product ID to Search");
					Double in = input.nextDouble();
					input.nextLine();
					Product search_output2 = products_id.searchByID(new Product(in), new CompareByID());
					if (search_output2 != null) {
						System.out.println("Product name: " + search_output2.getName());
						System.out.println("Product ID: " + search_output2.getUID());
						System.out.println("Product Singer: " + search_output2.getSinger());
						System.out.println("Product Duration: " + search_output2.getDuration());
						System.out.println("Product Genre: " + search_output2.getGenre());
						System.out.println("Product Release Year: " + search_output2.getReleaseYear());
						System.out.println("Product Cost: $" + search_output2.getCost() + "\n");
					} else {
						System.out.println("There are no Products with this ID.");
					}
				} else if (userInput.equals("3")) {
					products_name.sortByPrimary(new CompareByName());
					System.out.println("Name, ID, Singer, Cost, Duration, Genre, Release Year, Number in Stock Left");
					System.out.println(products_name.inOrderString().toString() + "\n");
				} else if (userInput.equals("4")) {
					products_id.sortBySecondary(new CompareByID());
					System.out.println("Name, ID, Singer, Cost, Duration, Genre, Release Year, Number in Stock Left");
					System.out.println(products_id.inOrderString().toString() + "\n");
				} else if (userInput.equals("5")) {
					System.out.println("Name, ID, Singer, Cost, Duration, Genre, Release Year, Number in Stock Left");
					System.out.println(products_name.inOrderString().toString() + "\n");

					boolean orderingActive = true;
					while (orderingActive) {
						System.out.println("Type the name of the product you would like, or Q if you are done: ");
						String nameOfProduct = input.nextLine();

						// System.out.println("Name of product is " + nameOfProduct);

						Product orderedProduct = products_name.searchByName(new Product(nameOfProduct),
								new CompareByName());
						orderID++;

						SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
						Date date = new Date();
						String strDate = formatter.format(date);

						if (orderedProduct != null) {
							orderProductList.addLast(orderedProduct);
							System.out.println("Added " + orderedProduct.getName() + " to your order: "
									+ orderedProduct.toString());
						} else if (orderedProduct == null && !nameOfProduct.equalsIgnoreCase("Q")) {
							System.out.println("Please enter a valid product name.");

						} else {
							System.out.println("This is your order: ");
							System.out.println(orderProductList.toString());

							while (true) {
								System.out.println("Confirm your order? N will bring you back to menu. Y/N");
								userInput = input.nextLine();
								int shippingSpeed = 1;
								if (userInput.equalsIgnoreCase("Y")) {
									while (true) {
										System.out.println(
												"What shipping speed would you like? 1. Standard 2. Rush 3. Overnight");
										userInput = input.nextLine();
										if (userInput.equals("1")) {
											shippingSpeed = 1;
											break;
										} else if (userInput.equals("2")) {
											shippingSpeed = 2;
											break;
										} else if (userInput.equals("3")) {
											shippingSpeed = 3;
											break;
										} else {
											System.out.println("Please choose a valid shipping speed");
										}
									}

									Order customerOrder = new Order(orderID, (Customer) userLoggedIn, strDate,
											orderProductList, shippingSpeed);
									((Customer) userLoggedIn).getunshippedOrders().addLast(customerOrder);
									priorityQueue.insert(customerOrder, new priorityComparator1());
									System.out.println("Order confirmed!");
									orderingActive = false;
									customerOrder = null;
									orderedProduct = null;
									break;

								} else if (userInput.equalsIgnoreCase("N")) {
									System.out.println("Exiting ordering menu, no order placed");
									orderingActive = false;
									break;

								} else {
									System.out.println("Please enter Y or N.");

								}
							}
						}

					}

				} else if (userInput.equals("6")) {
					Customer tempcust = (Customer) userLoggedIn;

					System.out.println("Shipped Orders: ");
					System.out.println((tempcust.getshippedOrders().toString()));
					System.out.println("Unshipped Orders: ");
					System.out.println((tempcust.getunshippedOrders().toString()));
				} else if (userInput.equals("Q")) {
					customerMenuOnline = false;
					loginSuccessful = false;
					userLoggedIn = null;
					
					System.out.println("Logging out... \n");
				}

			} while (employeeMenuOnline || managerMenuOnline) {

					System.out.println("1. Search for an order by order ID");
					System.out.println("2. Search for an order by customer first and last name");
					System.out.println("3. View order with the highest priority");
					System.out.println("4. View all orders sorted by priority");
					System.out.println("5. Ship an order");

					if (managerMenuOnline) {
						System.out.println("6. Enter a new product");
						System.out.println("7. Update existing product");
						System.out.println("8. Remove a product");
					}

					System.out.println("Q. Quit \n");

					System.out.println("Select an option: ");

					userInput = input.nextLine();
					if (userInput.equals("1")) {
						System.out.println("Enter order ID: ");
						Integer entered_order_id = input.nextInt();
						input.nextLine();
						Order result = priorityQueue.search(new Order(entered_order_id), new compareByOrderID());

						if (result != null) {
							System.out.println(result.toString());
						} else {
							System.out.println("There is no order with this ID.");
						}

					} else if (userInput.equals("2")) {

						System.out.println("Enter Customer first name: ");
						String enteredFirstName = input.nextLine();
						System.out.println("Enter Customer last name: ");
						String enteredLastName = input.nextLine();

						Order result = priorityQueue.search(
								new Order(new Customer(enteredFirstName, enteredLastName, true)),
								new compareByCustomerName());

						if (result != null) {
							System.out.println(result.toString());
						} else {
							System.out.println("There is no order associated with this name.");
						}

					} else if (userInput.equals("3")) {

						Order result = priorityQueue.getMax();
						if (result != null) {
							System.out.println(result.toString());
						} else {
							System.out.println("There are no orders at this time.");
						}

					} else if (userInput.equals("4")) {

						ArrayList<Order> orderedPriority = priorityQueue.sort(new priorityComparator1());
						for (int i = orderedPriority.size() - 1; i >= 1; i--) {
							System.out.print(orderedPriority.get(i) + " ");
						}
						System.out.println("");

					} else if (userInput.equals("5")) {
						
						if (!(priorityQueue.getMax() == null)) {
							Order shippedOrder = priorityQueue.getMax();
							priorityQueue.remove(1, new compareByOrderID());

							Customer customer = shippedOrder.getCustomer();

							customer.getshippedOrders().addLast(shippedOrder);
							int indexOfShippedOrder = customer.getunshippedOrders().findIndex(shippedOrder);
							customer.getunshippedOrders().positionIterator();

							customer.getunshippedOrders().advanceIteratorToIndex(indexOfShippedOrder);
							shippedOrders.insert(customer.getunshippedOrders().getIterator(), new priorityComparator1());
							customer.getunshippedOrders().removeIterator();

							System.out.println("Order " + shippedOrder.getOrderID() + " shipped!");
						
						} else {
							System.out.println("No orders to ship!");
						}
					} else if (managerMenuOnline && userInput.equals("6")) {
						System.out.println("Product name: ");
						String name = input.nextLine();
						System.out.println("UID: ");
						double UID = input.nextDouble();
						input.nextLine();
						System.out.println("Singer: ");
						String singer = input.nextLine();
						System.out.println("Song duration: ");
						double duration = input.nextDouble();
						input.nextLine();
						System.out.println("Genre: ");
						String genre = input.nextLine();
						System.out.println("Release Year: ");
						String release_year = input.nextLine();
						System.out.println("Cost: ");
						Double cost = input.nextDouble();
						input.nextLine();
						System.out.println("Number in stock: ");
						String numInStock = input.nextLine();
						Product newProduct = new Product(name, UID, singer, cost, duration, release_year, genre,
								numInStock);
						products_name.insert(newProduct, new CompareByName());
						System.out.println(products_name.inOrderString().toString() + "\n");

					} else if (managerMenuOnline && userInput.equals("7")) {
						System.out.println("Enter the product name you would like to update: ");
						String in = input.nextLine();
						Product toUpdate = products_name.searchByName(new Product(in), new CompareByName());
						System.out.println(toUpdate.toString());
						System.out.println("Enter 1 to update the cost: ");
						System.out.println("Enter 2 to update the number in stock: ");
						int in2 = input.nextInt();
						input.nextLine();
						if (in2 == 1) {
							System.out.println("Enter the cost amount to update");
							double coost = input.nextDouble();
							input.nextLine();
							toUpdate.setCost(coost);
						}
						if (in2 == 2) {
							System.out.println("Enter the number to update in stock");
							String no = input.nextLine();
							toUpdate.setNuminStock(no);
						}
					} else if (managerMenuOnline && userInput.equals("8")) {
						System.out.println("Enter the product name you would like to remove: ");
						String in = input.nextLine();

						Product toRemove = products_name.searchByID(new Product(in), new CompareByName());
						if (toRemove != null) {
							products_name.remove(toRemove, new CompareByName());
							System.out.println(products_name.inOrderString().toString() + "\n");

						} else {
							System.out.println("Product not found to remove");
						}

					} else if (userInput.equalsIgnoreCase("Q")) {
						employeeMenuOnline = false;
						managerMenuOnline = false;
						userLoggedIn = null;
						loginSuccessful = false;
						
						System.out.println("Logging out...");
					} else {
						System.out.println("Invalid option!");
					}

				}
		}

			
		} // end of main method

	public static void readAllFiles() {
		readUserFiles();
		readproductfile();
		readOrdersFile();


	}

	public static void writeAllFiles() {
		
		System.out.println("Saving files to directory...");
		
		writeUserFiles();
		productrewritefile();
    writeOrdersFile();
	}

	public static BST<Product> readproductfile() {
		boolean readable = true;
		BufferedReader buff;
		FileReader filereader;

		try {
			filereader = new FileReader("products.txt");
			buff = new BufferedReader(filereader);
			String line;

			while (readable) {

				line = buff.readLine();
				if (line == null) // finished reading
				{
					readable = false;
					break;
				}

				String[] vertices = line.split(",");
				products_name.insert(new Product(vertices[0], Double.parseDouble(vertices[1]), vertices[2],
						Double.parseDouble(vertices[3]), Double.parseDouble(vertices[4]), vertices[5], vertices[6],
						vertices[7]), new CompareByName());
				products_id.insert(new Product(vertices[0], Double.parseDouble(vertices[1]), vertices[2],
						Double.parseDouble(vertices[3]), Double.parseDouble(vertices[4]), vertices[5], vertices[6],
						vertices[7]), new CompareByID());

			}
			System.out.println("Product file was read successfully and product bst was instantiated!");
			buff.close();
		} catch (IOException e) {
			System.out.println("readfile(): Problem reading file. " + e.toString());
		}
		return products_name;
	}

	public static void productrewritefile() {
		FileWriter output = null;
		try {
			output = new FileWriter("products.txt");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		PrintWriter filewriter = new PrintWriter(output);
		filewriter.print(products_name.inOrderString());

		try {
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

public static void writeOrdersFile() {

		try {
			File out = new File(unshippedOrdersFileName);
			PrintWriter fileWriter = new PrintWriter(out);

			ArrayList<Order> priorityOrders = priorityQueue.sort(new priorityComparator1());

			for (int i = 1; i < priorityOrders.size(); i++) {
				fileWriter.println(priorityOrders.get(i).getOrderID());
				fileWriter.println(priorityOrders.get(i).getCustomer().getUsername());
				fileWriter.println(priorityOrders.get(i).getCustomer().getPassword());
				fileWriter.println(priorityOrders.get(i).getDate());
				fileWriter.println(priorityOrders.get(i).getOrderContents().getLength()); // amount of orders
				priorityOrders.get(i).getOrderContents().positionIterator();
				for (int j = 0; j < priorityOrders.get(i).getOrderContents().getLength(); j++) {
					priorityOrders.get(i).getOrderContents().advanceIteratorToIndex(j);
					fileWriter.println(priorityOrders.get(i).getOrderContents().getIterator().getName());
				}
				fileWriter.println(priorityOrders.get(i).getShippingSpeed());
				fileWriter.close();
			}

		} catch (FileNotFoundException e) {
			System.out.println("File " + unshippedOrdersFileName + " not found in folder");
		}
		
		try {
			File out = new File(shippedOrdersFileName);
			PrintWriter fileWriter = new PrintWriter(out);
			
			ArrayList<Order> shippedOrdersSorted = shippedOrders.sort(new priorityComparator1());
			
			for (int i = 1; i < shippedOrdersSorted.size(); i++) {
				
				if (shippedOrdersSorted.get(i) != null) {
				fileWriter.println(shippedOrdersSorted.get(i).getOrderID());
				fileWriter.println(shippedOrdersSorted.get(i).getCustomer().getUsername());
				fileWriter.println(shippedOrdersSorted.get(i).getCustomer().getPassword());
				fileWriter.println(shippedOrdersSorted.get(i).getDate());
				fileWriter.println(shippedOrdersSorted.get(i).getOrderContents().getLength()); // amount of orders
				shippedOrdersSorted.get(i).getOrderContents().positionIterator();
				for (int j = 0; j < shippedOrdersSorted.get(i).getOrderContents().getLength(); j++) {
					shippedOrdersSorted.get(i).getOrderContents().advanceIteratorToIndex(j);
					fileWriter.println(shippedOrdersSorted.get(i).getOrderContents().getIterator().getName());
				}
				fileWriter.println(shippedOrdersSorted.get(i).getShippingSpeed());
				fileWriter.close();
				}
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("File " + shippedOrdersFileName + " not found in folder");
		}

	}

	public static void readOrdersFile() {
		try {
			File in = new File(unshippedOrdersFileName);
			Scanner fileReader = new Scanner(in);

			while (fileReader.hasNext()) {
				LinkedList<Product> orderContents = new LinkedList<Product>();
				String orderID = fileReader.nextLine();
				String customerUsername = fileReader.nextLine();
				String customerPassword = fileReader.nextLine();
				Customer customer = customerTable.find(new Customer(customerUsername, customerPassword));
				String date = fileReader.nextLine();
				String amountOfOrders = fileReader.nextLine();
				int amountOfOrdersInt = Integer.parseInt(amountOfOrders);
				for (int i = 0; i < amountOfOrdersInt; i++) {
					String productName = fileReader.nextLine();
					orderContents.addLast(products_name.searchByName(new Product(productName), new CompareByName()));
				}
				String shippingSpeed = fileReader.nextLine();

				Order readOrder = new Order(Integer.parseInt(orderID), customer, date, orderContents,
						Integer.parseInt(shippingSpeed));

				priorityQueue.insert(readOrder, new priorityComparator1());
				customer.getunshippedOrders().addLast(readOrder);
			}
			fileReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("File " + unshippedOrdersFileName + " not found in folder");
		}
		
		try {
			File in = new File(shippedOrdersFileName);
			Scanner fileReader = new Scanner(in);
		
			while (fileReader.hasNext()) {
				LinkedList<Product> orderContents = new LinkedList<Product>();
				String orderID = fileReader.nextLine();
				String customerUsername = fileReader.nextLine();
				String customerPassword = fileReader.nextLine();
				Customer customer = customerTable.find(new Customer(customerUsername, customerPassword));
				String date = fileReader.nextLine();
				String amountOfOrders = fileReader.nextLine();
				int amountOfOrdersInt = Integer.parseInt(amountOfOrders);
				for (int i = 0; i < amountOfOrdersInt; i++) {
					String productName = fileReader.nextLine();
					orderContents.addLast(products_name.searchByName(new Product(productName), new CompareByName()));
				}
				String shippingSpeed = fileReader.nextLine();

				Order readOrder = new Order(Integer.parseInt(orderID), customer, date, orderContents,
						Integer.parseInt(shippingSpeed));
				
				customer.getshippedOrders().addLast(readOrder);
				shippedOrders.insert(readOrder, new priorityComparator1());
			}
			fileReader.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("File " + shippedOrdersFileName + " not found in folder");
		}
	}

	public static void readUserFiles() {
		// Create Customer HashTable from data in CustomerDB.txt file
		try {
			FileReader file = new FileReader(customerFilename);
			BufferedReader buff = new BufferedReader(file);
			boolean eof = false;

			while (!eof) {
				String[] customerData = new String[] { "", "", "", "", "", "", "", "" };
				int dataCounter = 0;
				String line = buff.readLine();

				if (line == null || line.equals("")) {
					eof = true;
				} else {
					for (int i = 0; i < line.length(); i++) {
						if (line.charAt(i) != ',' && dataCounter < 8) {
							customerData[dataCounter] += line.charAt(i);
						} else {
							i++; // skips the empty space
							dataCounter++;
						}
					}
					customerTable.add(new Customer(customerData[0], customerData[1], customerData[2], customerData[3],
							customerData[4], customerData[5], customerData[6], customerData[7]));
				}
			}
			System.out.println("customerDB file was read successfully and customer hash table was instantiated!");

			buff.close();
			file.close();

		} catch (Exception e) {
			System.out.println("Error -- " + e.toString());
		}

		// Create Employee HashTable from data in EmployeeDB.txt file
		try {
			FileReader file = new FileReader(employeeFilename);
			BufferedReader buff = new BufferedReader(file);
			boolean eof = false;
			while (!eof) {
				String[] employeeData = new String[] { "", "", "", "", "", "", "", "" };
				int dataCounter = 0;
				String line = buff.readLine();

				if (line == null || line.equals("")) {
					eof = true;
				} else {
					for (int i = 0; i < line.length(); i++) {
						if (line.charAt(i) != ',' && dataCounter < 5) {
							employeeData[dataCounter] += line.charAt(i);
						} else {
							i++; // skips the empty space
							dataCounter++;
						}
					}
					boolean isManager = false;
					if (employeeData[0].equals("true")) {
						isManager = true;
					}
					employeeTable.add(new Employee(isManager, employeeData[1], employeeData[2], employeeData[3],
							employeeData[4]));
				}
			}
			System.out.println("employeeDB file was read successfully and employee hash table was instantiated!");

			buff.close();
			file.close();

		} catch (Exception e) {
			System.out.println("Error -- " + e.toString());
		}

	}

	public static void writeUserFiles() {
		// Attempts to create file
		Boolean customerDB_need_rewrite = false;
		try {
			File myObj = new File(customerFilename);
			if (myObj.createNewFile()) {
				System.out.println("File created: " + myObj.getName());
			} else {
				System.out.println("File already exists.");
				customerDB_need_rewrite = true;
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		// Code for writing to file
		try {
			FileWriter myWriter = new FileWriter(customerFilename);
			if (customerDB_need_rewrite) {
				myWriter.flush();
			}
			myWriter.write(customerTable.toString());
			myWriter.close();
			System.out.println("Successfully wrote to the customer database.");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		Boolean employeeDB_need_rewrite = false;
		try {
			File myObj = new File(employeeFilename);
			if (myObj.createNewFile()) {
				System.out.println("File created: " + myObj.getName());
			} else {
				System.out.println("File already exists.");
				employeeDB_need_rewrite = true;
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		// Code for writing to file
		try {
			FileWriter myWriter = new FileWriter(employeeFilename);
			if (employeeDB_need_rewrite) {
				myWriter.flush();
			}
			myWriter.write(employeeTable.toString());
			myWriter.close();
			System.out.println("Successfully wrote to the employee database.");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

	}

} // End of class main