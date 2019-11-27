package dps.smoke.servers.tests;



import java.io.IOException;
import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import dps.smoke.common.InsertsMethods;
import utilities.CSVFileDataProvider;
import utilities.EnvironmentUtil;
import utilities.ExcelReportUtility;
import utilities.ReportUtility;
import utilities.SSHLibraryUtil;



public class Test1SSH {
	 EnvironmentUtil eu=new EnvironmentUtil();
	 Map m;
	 String e;

  @Test()
  public void f() throws IOException {
	  SSHLibraryUtil ssh=new SSHLibraryUtil();
		 String res= ssh.checkSSHHostConnection(m.get("server").toString(), m.get("servers_user").toString(), m.get("servers_pwd").toString());
		System.out.println(res);
		 if(res.contains("Issue"))
			 throw new ArithmeticException(res);
  }
  
	@Parameters({ "env" })
	@BeforeClass
	public void beforeClass(String env) throws IOException {
		e=env;   
		m=eu.getEnvironment(env);
	
	}

}
