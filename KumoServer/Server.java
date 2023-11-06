package KumoServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import java.io.FileOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class Server  {
	static DataInputStream dataInputStream= null;
	static  DataOutputStream dataOutputStream=null;
	
	public boolean establishServerConnection() {
		boolean isStarts=false;
		try(ServerSocket serverSocket = new ServerSocket(8866)) {
			System.out.println("KumoServer.Server waiting!!!!");
			Socket socket= serverSocket.accept();
			System.out.println("Connected");
			
			dataInputStream = new DataInputStream(socket.getInputStream());
			dataOutputStream = new DataOutputStream(socket.getOutputStream());

			Server server = new Server();
			server.reciveFile("master.py");
			isStarts=true;
			dataOutputStream.close();
			dataInputStream.close();
			socket.close();
			
		
		}catch (Exception e){
			System.out.println(e);
			isStarts=false;
		}
		return  isStarts;
		
	}

	public boolean reciveFile(String fileName) {
		boolean isFileRecived=false;
		int bytes=0;
		try (FileOutputStream fileOutputStream = new FileOutputStream(fileName)){
			long size= dataInputStream.readLong();
			byte [] buffer = new byte[(int)size];
			
			while ( size>0 && (bytes = dataInputStream.read(buffer,0,buffer.length))!=-1){
				fileOutputStream.write(buffer,0,bytes);
				System.out.println("size"+size);
				size-=bytes;
			}
			isFileRecived=true;
		
		}catch (Exception e){
			System.out.println(e);
		}
		System.out.println("File received");
		
		

		return  isFileRecived;
	}

}
