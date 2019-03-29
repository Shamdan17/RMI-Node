package RMI;

import java.net.Inet4Address;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import node.Node;

public class RMIServer  extends UnicastRemoteObject implements RMIInterface {
	private static final long serialVersionUID = 1L;

	
	ArrayList<Node> Children = new ArrayList<Node>();//Holds the IP addresses of all the children
	private static RMIInterface lookup;//Used to connect to the children's RMI Registries to invoke methods
	private static int rmiPort = 1099;
	
	
	public RMIServer() throws RemoteException, UnknownHostException { //Constructor
		super();
		String st = Inet4Address.getLocalHost().getHostAddress();
		System.setProperty("java.rmi.server.hostname",st);
		System.out.println("RMI Server proptery set. Inet4Address: "+st);
		
	}
	//=====Class Methods=====
	
	
	public void addChild(String ip) {// Add a child to the current not by its IP
		Children.add(new Node(ip));
	}
	
	
	//=====RMI Methods=====
	
	@Override
	public int add(int x, int y) throws RemoteException {//Adds two integers
		System.out.println("Addition operation performed");
		return x+y;
	}

	@Override
	public void printMessage(String s) throws RemoteException {//Prints a message s locally
		// TODO Auto-generated method stub
		System.out.println("Printed Message" + s);
	}

	@Override
	public void printAndForward(String msg) throws RemoteException {//Prints a message, then invokes the printing function in all the children.
		printMessage(msg);
		
		for(Node cur : Children) {
			try {
				lookup = (RMIInterface) Naming.lookup("//"+cur.getIP()+":"+rmiPort+"/RMIImpl");
				lookup.printAndForward(msg);
			} catch(MalformedURLException e) {
				System.err.println("MalformedURLException in child with IP: "+cur.getIP());
			} catch (NotBoundException e) {
				System.err.println("NotBoundException in child with IP: "+cur.getIP());
			}
		}
		
	}

}
