package KumoServer;


import java.io.*;
import java.util.Scanner;

public class Signup {
	Login login = new Login();
	Main main= new Main();
	Manipulation manipulation = new Manipulation();
	

	public void getInput(){
		Scanner sc = new Scanner(System.in);
		System.out.print("Username: ");
		String usr = sc.nextLine();
		if (manipulation.validate(usr,false)){
			sanitize(usr);
		}
		else{
			System.out.println("Please give a valid username!");
		}
	}
	
	public void register(String username, String password){
		 //For avoid multiple path declaration.
		try (
				
				FileWriter usr = new FileWriter(main.serverUserListPath+"Users.txt", true);
				FileWriter pwd = new FileWriter(main.serverUserListPath+"/Passwords.txt", true);
		)
		{
			usr.append(username+"\n");
			pwd.append(manipulation.hash(password)+"\n");
			File dir = new File(main.databasePath+username+"/");
			if (!dir.exists()){
				dir.mkdirs();
				System.out.println("Registered successfully...");
				System.out.println("Now you are redirected to Login page");
				login.login();
				
			}
			else{
				System.out.println("Registration unsuccessful...");
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void sanitize(String usr){
		int warnings = 1;
		Scanner sc = new Scanner(System.in);
		while (login.isExists(usr,"Users") && warnings<3){
			System.out.println("Username Already Exists...\nPlease choose another one!");
			System.out.print("Username: ");
			usr = sc.nextLine();
			warnings++;
		}
		if (!login.isExists(usr,"Users")){
			System.out.print("Password: ");
			String pwd = sc.nextLine();
			warnings = 0;
			while (!manipulation.validate(pwd,true) && warnings<3){
//				System.out.println("Please enter a valid password!");
				System.out.println("Your password must contains length 8, atleast 1 upper case, 1 lower case\n1 special character" +
						"\n1 number");
				System.out.print("Password: ");
				pwd = sc.nextLine();
				warnings++;
			}
			if (manipulation.validate(pwd,true)){
				register(usr,pwd);
			}
			else{
				System.out.println("Too many wrong attempts!\nTry again Later...");
			}
		}
		else{
			System.out.println("Too many attempts!\nTry again Later...");
		}
	}
	
}
