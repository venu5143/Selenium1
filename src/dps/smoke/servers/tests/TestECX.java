package dps.smoke.servers.tests;



import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import dps.smoke.common.Folders;
import dps.smoke.common.InsertsMethods;
import utilities.CSVFileDataProvider;
import utilities.EnvironmentUtil;
import utilities.ExcelReportUtility;
import utilities.ReportUtility;
import utilities.SSHLibraryUtil;
import utilities.SmokeReportUtility;



public class TestECX {
	 EnvironmentUtil eu=new EnvironmentUtil();
	 Map m;
	 Folders fdr;
	 String e,exec_path;
	 int num_kai;
	 SmokeReportUtility res1;
	 InsertsMethods objTE;
	 String strDate,strJobNumber;
  @Test()
  public void f() throws IOException, ClassNotFoundException, SQLException {
	  SSHLibraryUtil ssh=new SSHLibraryUtil();
//		 String res= ssh.checkSSHHostConnection(m.get("server").toString(), m.get("servers_user").toString(), m.get("servers_pwd").toString());
//		 ssh.copyFileFromLocalToRemote(m.get("server").toString(), m.get("servers_user").toString(), m.get("servers_pwd").toString(), "D:/QA/test.rpps.remit.20171211131003", "/home2/ecx/endpoints/vendors/rpps/in");
		 String res="";
		 ssh.copyFileFromRemoteToLocal(m.get("server").toString(), m.get("servers_user").toString(), m.get("servers_pwd").toString(), "D:/QA/test","/app2/DPSFiles/DPSStaging/usps_certified_data_extract/archive", "", "");
		System.out.println(res);
		 if(res.contains("Issue"))
			 throw new ArithmeticException(res);
  }
  
//	@Parameters({ "env" })
	@BeforeClass
	public void beforeClass() throws IOException {
		e="uat";
		objTE = new InsertsMethods();
		res1 = new SmokeReportUtility();
		strDate=res1.readSTRDate();
		m=eu.getEnvironment(e);
		
		
	}

}
