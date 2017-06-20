import java.rmi.Naming;

public class server {

  /**
   * Server binder program for the Arena
   * @param argv The command line arguments which are ignored.
   */
  public static void main (String[] argv) {
    try {
	Arena tester = new Arena ("bongo!");
      Naming.rebind ("Arena", tester );
      System.out.println ("Arena is ready.");

      int count =0;
      while ( count++ < 15 ) {
	      tank_lib.log ("server: arena has # tanks : " + tester.count() );
	      Thread.sleep(300);
      }

    } catch (Exception e) {
      System.out.println ("Arena failed: " + e);
    }
  }

}
