package dps.smoke.servers.tests;

import java.io.OutputStream;

public class Qqa {

	public static void main(String[] args) {
		try {
		    String command = "C:\\windows\\putty.exe -ssh -l lharding -pw ChangeMe1! archqa4";
		    String cmd2= "/usr/local/bin/sudo -H -u ecx /opt/csw/bin/sudosh";
		    Runtime r = Runtime.getRuntime ();
		    Process p = r.exec (command);
		    Thread.sleep(120000);
		    OutputStream out = p.getOutputStream();  
		    out.write(cmd2.getBytes());  
		    out.flush(); 
//		    p.
//		    Process pp=r.exec(cmd2);
//p.waitFor();
//		    p.destroy ();
		}
		catch (Exception e) {
		    e.printStackTrace ();
		}

	}

}
