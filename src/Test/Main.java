package Test;

import java.rmi.Naming;
import java.util.Scanner;

import RMI.RMIServer;

public class Main {
	
	public static void main(String args[]) {
		String childIP = "";//Set to ROOT if no child (I know this is wrong but whatever)
		int sendmessage = -1;
		try {
			RMIServer server = new RMIServer();
			if(!childIP.equalsIgnoreCase("root")) {
				server.addChild(childIP);
			}

			Naming.rebind("//localhost/RMIImpl", server);//Bind the server to make it available to connect to
			System.out.println("Rebinding successful. Server is in operation");
			
			Scanner in = new Scanner(System.in);
			if(sendmessage!=-1) {
				String message = in.nextLine();
				server.printAndForward(message);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
