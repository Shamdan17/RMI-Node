package Test;

import java.rmi.Naming;
import java.util.Scanner;

import RMI.RMIServer;

public class Main {
	
	public static void main(String args[]) {
		String childIP = "172.20.120.155";//Set to ROOT if no child (I know this is wrong but whatever)
		int sendmessage = 0;
		try {
			RMIServer server = new RMIServer();
			if(!childIP.equalsIgnoreCase("root")) {
				server.addChild(childIP);
				//server.addChild("172.20.128.207");
			}

			Naming.rebind("//localhost/RMIImpl", server);//Bind the server to make it available to connect to
			System.out.println("Rebinding successful. Server is in operation");
			
			Scanner in = new Scanner(System.in);
			while(sendmessage!=-1) {
				String message = in.nextLine();
				if(!message.equalsIgnoreCase("Exit"));
				server.printAndForward(" "+message);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
