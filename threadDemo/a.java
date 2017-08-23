class a extends Thread {

	int aa;

	static boolean locked;

	a () { 

		aa= 4; 
		locked =false;
	}
	a( String name ) {
		super();
		this.setName(name);
		
	}

	/** add up a bunch of numbers
	 * 
	 */
	synchronized public void run () {

		System.out.println("in run for " + this.getName() );

		try {

		if ( locked = true ) {
			// will block until another thread calls notify
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
