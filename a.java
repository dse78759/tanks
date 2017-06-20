class a extends Thread {

	int aa;

	static boolean locked;

	a () { 
		System.out.println (

		aa= 4; locked =false;}

	synchronized public void run () {

		try {

		if ( locked = true ) {

			wait (); 
		}

		else {
			locked = true;

			for ( int i=0; i< 1000; i++ ) {
			
				aa+=i;

			}

			locked = false;
			notify ();
			
		}
		}
		catch ( Exception e) {
			//were we interrupted?
			System.out.println ("exception!");
			System.out.println (e.toString() );
		}
	}

	int getVal () { return aa ; }

};
