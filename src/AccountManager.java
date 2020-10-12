
/*
 * Josue Nunez
 * 
 * This program allows a user to create a username and password and stores the information in a Hashtable. 
 * Both username and password get checked against a regex. 
 * The program includes two usernames that have been pre-programmed to simulate a returning user. "david@123", "Password99!" & "jnunez@fakemail.com", "12345678!Aa"
 * For demonstration purposes, after logging in the user has the option to see all the saved usernames and passwords. 
 */
import java.util.Hashtable;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;


//public class AccountManager extends Required{
public class AccountManager implements Requirements{

	
	/*
	 * THIS METHOD IS FOR VALIDATING THE USERNAME. IT IS REQUIRED AS PER THE INTERFACE IMPLEMENTED. 
	 */
		@Override
		public boolean validateUsername(String text) {
			
			//myPolicy details: 
					//1. at least 1 lowercase 
					//2. at least 1 digit
					//3.
					//4. at least 1 of these: !@#$%
					//5. at least 8 chars long
			
			String myPolicy ="((?=.*[a-z])(?=.*\\d)(?=.*[!@#$%]).{8,})";
			Pattern pt = Pattern.compile(myPolicy); 	//this converts myPolicy into something that it can check against
			Matcher mt = pt.matcher(text);
			
			return mt.matches();
			}


	/*
	 * THIS METHOD IS FOR VALIDATING THE PASSWORD. IT IS REQUIRED AS PER THE INTERFACE IMPLEMENTED. 
	 */
		@Override
		public boolean validatePassword(String text) {
			
		//myPolicy details: 
			//1. at least 1 lowercase 
			//2. at least 1 digit
			//3. at least 1 uppercase
			//4. at least 1 of these: !@#$%
			//5. at least 8 chars long
			
			String myPolicy ="((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[!@#$%]).{8,})"; 
			Pattern pt = Pattern.compile(myPolicy);
			Matcher mt = pt.matcher(text);
			
			return mt.matches();
		}
		
	
		
	/*
	 * MAIN METHOD BEGINS HERE
	 */
		public static void main(String[] args) {
				
			Scanner keyboard = new Scanner(System.in);
		
		//THIS IS A HASHTABLE DICTIONARY TO STORE THE USER ACCOUNTS
			Hashtable<String, String> userAccts = new Hashtable<String, String>();
			
		//CREATING THE AccountManager OBJECT TO IMPLEMENT THE REQUIREMENTS FROM THE Requirements Interface. 
			AccountManager required = new AccountManager();
			
		//ADDING INTIAL USERS. USED TO TEST THE LOGIN SCENERAIO 
			userAccts.put("jnunez@fakemail.com", "12345678!Aa");
			userAccts.put("david@123", "Password99!");
			
			String initialPrompt;
			char userInput;
			
			String username;
			String password;
			
			
		//THIS IS THE INITIAL PROMPT THAT ASKS THE USER IF THEY WANT A TO REGISTER OR LOG IN
			//userName1.add(JOptionPane.showInputDialog("Enter your username: "));	
			do {
			
			JOptionPane.showMessageDialog(null, "Ok, here we go!");	
			initialPrompt = JOptionPane.showInputDialog("Log In or Register?"
					+ " (L = Log In	 R = Register) ");
			//System.out.print("Log In or Register?"
			//		+ " (L = Log In	 R = Register) ");
			//initialPrompt = keyboard.next();
			 
			userInput = initialPrompt.charAt(0); 
			} while (userInput != 'R' && userInput != 'r' && userInput != 'L' & userInput!= 'l');

			
			
		//IF THEY CHOOSE TO REGISTER, THEY GET SENT HERE. 
			
				if (userInput == 'R' || userInput == 'r') {
					do {
						//System.out.print("Create Username (not case sensitive): ");
						//username = keyboard.next();
						username = JOptionPane.showInputDialog("Create Username: \n1. Case insensitive. \n2. Must be alphanumeric \n3. Must contain one of the following: !@#$%");
						
						
						//System.out.print("Create Password: ");
						//password = keyboard.next();
						password = JOptionPane.showInputDialog("Create Password: \n1. 8 characters min \n2. alphanumeric \n3. Must contain one of the following: !@#$%");
						
						if (required.validateUsername(username) == true && required.validatePassword(password) == true) {
							userAccts.put(username, password);	//Store the user's info in the Hashtable
							//System.out.println("Registered. Access granted!");
							JOptionPane.showMessageDialog(null, "Registered. Access granted!");
						} else if (required.validateUsername(username)==false) {
							//System.out.println("Not a valid username. Must be alpahnumeric & contain a special character (!@#$%)");
							JOptionPane.showMessageDialog(null,"Not a valid username. Must be alpahnumeric & contain a special character (!@#$%)");
						} else if (required.validatePassword(password)== false) {
							//System.out.println("Not a valid password. Must be a min of 8 character long, alphanumeric, and contain at least 1 special character.");
							JOptionPane.showMessageDialog(null, "Not a valid password. Must be a min of 8 character long, alphanumeric, and contain at least 1 special character.");
						}
					} while (required.validateUsername(username)== false || required.validatePassword(password)==false);
				} 
			
			
			
		//IF THEY CHOOSE TO LOG IN, THEY GET SENT HERE INSTEAD.	
			if (userInput == 'L' || userInput == 'l') {
				//System.out.print("Username(not case sensitive): ");
				//username = keyboard.next();
				//System.out.print("Password: ");
				//password = keyboard.next();	
				
				username = JOptionPane.showInputDialog("Username: (try this sample username: david@123) ");
				
				password = JOptionPane.showInputDialog("Password: (password = Password99!) ");
				
				
			//Now check if entered username is in the userAccts dictionary. It changes the provided username to lower case. 
				if (userAccts.containsKey(username.toLowerCase()) == true ) {
					if (userAccts.containsValue(password)) {
						//System.out.println("Welcome Back!");
						JOptionPane.showMessageDialog(null, "Welcome back!");
					} else {
						//System.out.println("Wrong password. Try again.");
						JOptionPane.showMessageDialog(null, "Wrong password. Try again.");
					}
				} else {
					//System.out.println("Username doesn't exist");
					JOptionPane.showMessageDialog(null, "Username doesn't exist");
				}
			}
			
		
		//FOR DEMONSTRATION PURPOSE ONLY. GIVES THE USER THE OPTION TO SEE ALL THE STORED USERNAMES AND PASSWORDS 
			
			//System.out.print("Would you like to see the list of stored usernames and passwords? Y or N ");
			//String answer = keyboard.next();
			String answer = JOptionPane.showInputDialog("Would you like to see the list of stored usernames and password? Y or N ");
			
			do {
			if (answer.charAt(0) == 'y' || answer.charAt(0) == 'Y') {
				//System.out.println(userAccts);
				JOptionPane.showMessageDialog(null, userAccts);
			} else if (answer.charAt(0) == 'n' || answer.charAt(0) == 'N') {
				//System.out.println("Ok, won't show it.");
				JOptionPane.showMessageDialog(null, "Ok, won't show it.");
				} 
			} while (answer.charAt(0) != 'Y' && answer.charAt(0) != 'y' && answer.charAt(0) != 'N' && answer.charAt(0) != 'n');
			
			JOptionPane.showMessageDialog(null, "You've reached the end of this program.");
			
			keyboard.close();
			System.exit(0);
		}
	}