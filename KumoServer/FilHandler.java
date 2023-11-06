//package KumoServer;
//
//import java.io.File;
//import java.util.Scanner;
//
//public class FilHandler {
//	Main main= new Main();
//	public String fileReader(String fileName){
//		File file= new File(main.serverUserListPath+fileName+".txt");
//
//		String result="";
//		try {
//			Scanner sc=new Scanner(file);
//			while(sc.hasNextLine()){
//				result=sc.nextLine()+"\n"+result;
//			}
//			sc.close();
//		} catch (Exception e) {
//			System.out.println(e);
//			System.out.println("Error while reading");
//		}
//
//		return result;
//	}
//}
