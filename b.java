
import java.util.*;

class b {

	static public void main ( String a[] ) {

		try {
		a worker1 = new a ("bob"); 


		a worker2 = new a ("fred"); 

		worker1.start ();

		Thread.sleep (10);
		worker2.start ();

		System.out.println ( " val: " + worker1.getVal () );
		System.out.println ( " val: " + worker2.getVal () );
		System.out.println ( " val: " + worker1.getVal () );
		System.out.println ( " val: " + worker2.getVal () );
		System.out.println ( " val: " + worker1.getVal () );
		System.out.println ( " val: " + worker2.getVal () );
		System.out.println ( " val: " + worker1.getVal () );
		System.out.println ( " val: " + worker2.getVal () );
	
		//try { 

		//Thread.sleep (100);
		}
		catch ( Exception e ) {}

		//System.out.println ( " val: " + worker.getVal () );

	}
};

