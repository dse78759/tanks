
import java.util.Date;
import java.util.Calendar;

public class tank_lib {

	public static void log ( String in ) {

		System.out.println ( miniTMS() + " - " + in );

	}
	
	/**
	 * tanks will be responded to within a set period, say 100 ms, so
	 * that even tanks on a bad network connection will not be hindered
	 * . This slows the whole simulation down to the advantage of the
	 * longest thinking tank, to a point
	 */
	public static int miniTMS () {

		Date curr = new Date();

		//System.out.println ( "Date info : "  + curr.toString () );

		Calendar now = Calendar.getInstance ();

		int hour = now.get ( Calendar.HOUR );
		int sec = now.get ( Calendar.SECOND );
		int msec = now.get ( Calendar.MILLISECOND );

		//System.out.println ( "from Cal(sec): "  + sec );
		//System.out.println ( "from Cal(msec): "  + msec );
		int mini = ((( hour*100) + sec ) *1000 ) + msec;
		//System.out.println ( "combined : " + mini ) ;

		return mini;

	  }
	  
	public static long randomRange ( int min, int max ) {

		int diff = max-min;

		double z=Math.random () *diff;

		long zz = Math.round ( z ) +1;
		
		System.out.println ( "made rand # : " + zz );

		return ( zz) ;
	}
};
