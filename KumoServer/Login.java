package KumoServer;

import java.io.*;
import java.util.Scanner;

public class Login {
	Main main = new Main();
	Manipulation manipulation= new Manipulation();
	
	public boolean isExists(String data, String filename) {
		String matchingString;
		Main main = new Main();
		try(FileReader fr = new FileReader(main.serverUserListPath +filename+ ".txt");
				) {
			Scanner scan = new Scanner(fr);
			while (scan.hasNextLine()) {
				matchingString = scan.nextLine();
				if (matchingString.equals(data)) {
					return true;
				}
			}
			return false;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	public boolean login() {
		boolean isSuccess=false;
		System.out.println("Enter Username: ");
		String usr= main.stringScanner();
		System.out.println("Enter your password: ");
		String pwd= main.stringScanner();
		
		boolean credentialsMatch= isExists(usr,"Users") && isExists(manipulation.hash(pwd),"Passwords");
		if (credentialsMatch){
			Client client= new Client();
			client.establishConnection();
			isSuccess=true;
		}
		return  isSuccess;
	}

	
}
