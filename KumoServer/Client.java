package KumoServer;

import java.io.*;
import java.net.Socket;

public class Client {
	static DataOutputStream dataOutputStream= null;
	static DataInputStream dataInputStream= null;
	public boolean establishConnection() {
		boolean isConnected=false;
		try(Socket socket = new Socket("localhost",8866)) {
//			System.out.println("KumoServer.Server listening!");
			dataInputStream=new DataInputStream(socket.getInputStream());
			dataOutputStream= new DataOutputStream(socket.getOutputStream());
			
			System.out.println("Sending file to the server");
			String directoryPath="/home/raghuram/Documents/main.py";
			
			Client client=new Client();
			client.sendFile(directoryPath);
			isConnected =true;
			
		}catch (Exception e){
			System.out.println(e);
		}
		return isConnected;
	}
	
	public  boolean sendFile(String path){
		boolean isSuccess=false;
		int bytes=0;
		File file = new File(path);
		try(FileInputStream fileInputStream = new FileInputStream(file)) {
			dataOutputStream.writeLong(file.length());
			byte[] buffer= new byte[4*1024];
			while ((bytes=fileInputStream.read(buffer))!=-1){
				dataOutputStream.write(buffer ,0,bytes);
				dataOutputStream.close();
			}
			isSuccess=true;
			
		
		
		}catch (Exception e){
			System.out.println(e);
		}
		return  isSuccess;
	}
	
}
