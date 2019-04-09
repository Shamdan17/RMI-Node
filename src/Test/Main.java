package Test;

import java.rmi.Naming;
import java.util.Scanner;

import RMI.RMIServer;

public class Main {
	
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the ip address you want to connect to (leaf if none): ");
		String childIP = in.nextLine();//Set to leaf if no child
		System.out.println("Enter any number other than -1 to be able to send a message");
		int sendmessage = in.nextInt();
		try {
			RMIServer server = new RMIServer();
			if(!childIP.equalsIgnoreCase("leaf")) {
				server.addChild(childIP);
			}
			Naming.rebind("//localhost/RMIImpl", server);//Bind the server to make it available to connect to
			System.out.println("Rebinding successful. Server is in operation");
			
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
