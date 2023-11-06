package KumoServer;

import java.util.Scanner;

public class Main {
	Scanner sc= new Scanner(System.in);
	String serverUserListPath="/home/raghuram/Documents/KumoJr/Users/";
	String databasePath="/home/raghuram/Documents/KumoJr/Database/";
	
	
	public static void main(String[] args) {
		Main m= new Main();
		Server server= new Server();
		
		if(server.establishServerConnection()){
			System.out.println("Welcome to Kumo Cloud");
			System.out.println("1 Sign up\n2 Login\n3 Quiet.");
			String userChoice = m.stringScanner();
//		System.out.println(userChoice.getClass().getSimpleName());
			
			if (userChoice.equals("1")){
				Signup signup = new Signup();
				signup.getInput();
				
			} else if (userChoice.equals("2")) {
				Login login = new Login();
				if (login.login()){
					System.out.println("logged in");
				}
				
			}else {
				System.out.println("Bye");
			}
			
		}else{
			System.out.println("External Server Error 500");
		}
		
		
		
	}
	
	public String stringScanner(){
		String inp= sc.nextLine();
		return  inp;
	}
	
}