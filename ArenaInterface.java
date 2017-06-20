import java.rmi.*;
/**
 * Remote Interface for "Arena" 
 */
public interface ArenaInterface extends Remote {

	  public long register () throws RemoteException ;

	  public void dump ()  throws RemoteException ;
	  public int  setName ( long l, String s )  throws RemoteException ;

	  public int move ( long l, String x) throws RemoteException ;

	  public int count ( ) throws RemoteException ;

}




