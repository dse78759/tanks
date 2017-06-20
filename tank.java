import java.rmi.Naming;

class tank {
	String myname="droid";

	public void mylog ( String msg ) {
		tank_lib.log ( myname + " : " + msg );
	}

  /**
   * Client program for using the Arena shared object .
   * @param argv The command line arguments which are ignored.
   */
  public static void main (String[] argv) {
	  tank t = new tank ();
	  t.run(argv);
	  
  }

  public void run  (String[] argv) {

	//mylog ("arg size : " + argv.length );

	if ( argv.length >0 ) { 
		myname = argv[0];
	  	mylog ("tank name : " + myname );
	}

    try {
      ArenaInterface hello = 
        (ArenaInterface) Naming.lookup ("//localhost/Arena");

	mylog ("got remote obj");

      Thread.sleep(400);
	
      long key = hello.register ();

      mylog ("my key is : " + key );

      String cmd="hi, I'm " + myname ;

      mylog ( "first make call with purposely bad key" );

      hello.move ( key-1, cmd);

      cmd="bye";

      mylog ( "then make 2 calls with good key" );

      hello.setName ( key, myname);

      hello.move ( key , cmd);
      
    } catch (Exception e) {
      mylog ("ArenaClient exception: " + e);
    }
  }
}
