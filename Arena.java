import java.rmi.*;
import java.rmi.server.*;

import java.util.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.List;

/**
 * Remote Class 
 */
public class Arena extends UnicastRemoteObject implements ArenaInterface {
  private String message;
  private static Integer sum;

  protected class client_info {
	  Date start;
	  Date logout;
	  int numCalls;
	  String name ;
	  int hitpoints;
	  int posX;
	  int posY;
  };

  protected class floor {
	  int width;
	  int length;
	  List obstacles; // rocks? pits? mines?
	  List bonuses;
  }

  HashMap<Long, client_info> userMap = new HashMap<Long, client_info> ();

  /**
   * Construct a remote object
   * @param msg the message of the remote object, such as "eat hot lead!".
   * @exception RemoteException if the object handle cannot be constructed.
   */
  public Arena (String msg) throws RemoteException {
    message = msg;
	sum=4;	
  }

  /**
   *  Random long used to identify clients calling this remote 
   *   object's methods. key must be passed to 'secure' methods.
   */
  protected long getKey () {
	double z=Math.random () * 1000000000;
	long zz = Math.round ( z );
	return zz;
  }

  /**
   * 	Return a key that the client needs to pass every time they make a 
   * 		call. 
   *
   */
  public long register () throws RemoteException 
    {
	Long key = getKey();

	client_info info = new client_info ();

	Calendar now = Calendar.getInstance ();
	    
	info.start = now.getTime ();

	userMap.put(key, info);
	
	tank_lib.log ("Arena : returning key : " + key );

	return key;
    }

  public int count() throws RemoteException {
	  return userMap.size();
  }

  protected void update (long key, client_info tainted) {

	  tank_lib.log ("arena: updating key for : " + tainted.name );
	  if ( userMap.containsKey ( key) ) {
		 
		  // should replace record with this key
		userMap.put ( key,tainted);
	  }
	  else {
		  tank_lib.log ("huh? code just tried to save a key that doesn't exist");
	  }

  } 

  public void dump ()  throws RemoteException {

	Iterator it = userMap.keySet().iterator();

	while(it.hasNext()) {
		Long currL = (Long) it.next();
		System.out.print(currL + "  " );
	 	System.out.println(userMap.get (currL) );
	}    

  }

  protected client_info validate ( Long key ) {
		tank_lib.log (" arena : validate : ");
	if (  userMap.containsKey ( key) ) {
		return (  userMap.get(key) );
	}
	else {
		tank_lib.log (" bad key");
		return null;
	}
  }

  public int setName ( long key, String newname ) throws RemoteException {
		  	
	  client_info ci = this.validate ( key );

	  if  ( ci == null ) {
		System.out.println ("    this bozo tried to pass: " + key);

		return -1;
	  }

	System.out.println ("    setting name for tank to : " + newname);

	ci.name=newname;
	 
	update (key, ci );

	return 1;

  }

  public int move ( long key, String cmd ) throws RemoteException {

	  // first check if long is valid
	  //
	  client_info ci = this.validate ( key );

	  if  ( ci == null ) {

		System.out.println (" intruder alert");
		System.out.println ("    this bozo tried to pass: " + cmd);

		return -1;
	  }
	  else {
			
		System.out.println (" welcome : " + ci.name);
		System.out.println ("    cmd passed: " + cmd);
	  }

	  return 0;

  }

}
