package RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIInterface extends Remote{ 
	public int add(int x, int y) throws RemoteException;
	public void printMessage(String x) throws RemoteException;
	public void printAndForward(String msg) throws RemoteException;
}
