package dps.donebackup;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testengine.TestEngine;
import utilities.PropertyFileUtil;
import utilities.ReportUtility;

public class TestJobsValidation{
	TestEngine objTE;
	ReportUtility res1;
	String strDate;
	private HashMap<String, String> eleMap = new PropertyFileUtil("locators").getWebElementMapping();
	private HashMap<String, String> prop = new PropertyFileUtil("environment").getWebElementMapping();
	private HashMap<String, String> cred = new PropertyFileUtil("credentials").getWebElementMapping();
	String run_num="";
	HashMap<String, String> hm = new HashMap<String, String>();
	
	@DataProvider(name = "test")
	public Object[][] createData() {
		return new Object[][] {
				{ "MVC001","Mayil"},
//				{ "UNI045","Smoke Testing"},
//				{ "WFM310","Smoke Testing"},
//				{ "KAI050","Smoke Testing"},
//				{ "AVA004","Smoke Testing"},
//				{ "CNX020","Smoke Testing"},
//				{ "AGA100","Smoke Testing"},
//				{ "WPT001","Smoke Testing"},
//				{ "INW010","SmokeTesting"},
		};
	}
	
	@DataProvider(name = "proof")
	public Object[][] createData1() {
		return new Object[][] {
				{ "MVC001","07/04/2016"},
				{ "UNI045","07/04/2016"},
				
//				{ "ZRegTableETL", 3},
		};
	}


	@Test(priority=4,dataProvider = "test")
	public void verifyJobNumbers(String appCode,String lib) throws ClassNotFoundException, SQLException{
		String retStr="";
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn1 = DriverManager.getConnection(
				"jdbc:oracle:thin:@//cltdpsdb2.regulusgroup.net:1521/"
				+ "cdps2p","u_VenuGorantla","sharks1");
        Statement stmt = conn1.createStatement();
        ResultSet rset = stmt.executeQuery("SELECT application_v.application_code, job_v.test_job_run_id, job_v.Print_job_number,test_job_run.name, job_v.status_code, job_v.scheduled_start_time, test_job_run.status FROM dps2.job_v JOIN dps2.test_job_run ON job_v.test_job_run_id = test_job_run.id join dps2.application_v on test_job_run.dps_application_id = application_v.id where application_v.application_code='"+appCode+"' order by  job_v.scheduled_start_time desc");
        int i=0;
        while (rset.next())
        {
        	run_num=rset.getString(3);
            i++;
            if(i>0){
           	 break;
           }
        } 
        System.out.println(run_num);
        hm.put(appCode,run_num );
        stmt.close();
        conn1.close();
	}
	
	
	@Test(priority=5,dataProvider = "test")
    public void monitorJobs(String appCode,String lib) throws InterruptedException, IOException{
    	res1.writeTestcase(appCode, "Validating the Test Job in Monitor");
		objTE.launchBrowser();
	  	res1.writeResult("Opening the Monitor Application", "Pass", "");
    	objTE.launchApp("http://monitoruat.regulusgroup.net/dps_monitor/Login.jsf");
		objTE.enterByID("j_username", "admin","Enter the User Name");
		objTE.enterByID("j_password", "admin","Enter the Password");
		objTE.clickByXpath("//*[@name='submit']","Click on Submit button");
		Thread.sleep(15000);
//		objTE.assignWindowFromWindowHandles();
		objTE.clickByXpath("//*[contains(@name,'jobNumber')]");
		objTE.enterByXpath("//*[@name='Search:SearchForm:jobNumber']", hm.get(appCode),"Enter the JobNumber");
		//objTE.enterByXpath("//*[@name='Search:SearchForm:jobNumber']", hm.get(appCode));
		objTE.enterByXpath("//*[@name='Search:SearchForm:appCode']", appCode,"Enter the Application Code");
		objTE.clickByXpath("//*[@name='searchButton']","Click on Search button");
		Thread.sleep(5000);
		
//		System.out.println("::::::::::::::::"+src);
		
		objTE.selectByVisibleTextUsingID("refereshSeconds", "Every 1 minute", "Selecting the Refresh for Every Second");
		
		int flag=0;
		String src="";
		while(flag==0){
			 src=objTE.getAttributeSRCByXpath("//a[@id='Search:SearchForm:dataTable1_0:_id73']/img");
			 
			 if(src.contains("suspended") || src.contains("completed")){
				 flag=1;
			 }else{
				 Thread.sleep(2000);
			 }
		}
		if(flag==1){
			objTE.clickByXpath("//a[@id='Search:SearchForm:dataTable1_0:_id73']", "Click on the Main Job ");
			 Thread.sleep(5000);
			String adesc,asts,elapsed;
			int rws=objTE.getgridRowsInWebTableByXpath("//*[@id='JobDetail:JobDetailForm:dataTable1:tbody_element']/tr");
			res1.writeTestcase("", "Details from the Job Steps Page");
			for (int i = 1; i <= rws; i++) {
				adesc=objTE.getTextByXpath("//*[@id='JobDetail:JobDetailForm:dataTable1:tbody_element']/tr["+i+"]/td[2]/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td");
				asts=objTE.getAttributeTitleByXpath("//*[@id='JobDetail:JobDetailForm:dataTable1:tbody_element']/tr["+i+"]/td[1]/span/img");
				elapsed=objTE.getTextByXpath("//*[@id='JobDetail:JobDetailForm:dataTable1:tbody_element']/tr["+i+"]/td[5]");
				res1.writeResult(adesc, asts, elapsed);
			}
			
			
		}
//		objTE.selectByVisibleTextUsingID("site","Dallas","Selecting the Site in the Dropdown");
//		objTE.selectByVisibleTextUsingID("client","9001","Selecting the Client in the Dropdown");
//		
//		objTE.enterByID("batchId", "297008", "Entering the Batch ID");
//		
//		objTE.selectByVisibleTextUsingID("batchType","B1","Selecting the Batch Type in the Dropdown");
//		
//		objTE.clickByID("searchButton", "Click on Search Button");
		
		
//		Thread.sleep(15000);
		
//		WebElement ele=driver.findElement(By.xpath("//table[@id='Batches:BatchesForm:dataTable1']/tbody/tr"));
		
		

    }
	
	
	
	
	
//	@Test(priority=3,dataProvider = "proof")
//	public void runProofJob(String appCode, String dt)
//			throws InterruptedException, IOException {
//		objTE.clickByPartialLink(appCode,"Select the Application");
//		objTE.clickByLink("Real-time Content Control","Click on the Menu Link");
//		objTE.clickByLink("Proofing");
//		objTE.waitForElementByPartialLink("run new proof job");
//		objTE.clickByPartialLink("run new proof job");
//		objTE.enterByID("name", "QA Reg proof",
//				"Enter the Name of the Proofing Job");
//		objTE.clickByID("dataSet_Button",
//				"Select the Data Set for the Proofing Job");
//		objTE.clickByXpath("//div[@id='dataSet_Items']/table/tbody/tr[2]/td[2]");
//
//		objTE.enterByID("runAsDate", dt, "Enter the value in the Effective Date field");
//		objTE.clickByID("receiveNotifications",
//				"Click on the Receive Notifications");
//		objTE.clickByID("run", "Click on the Run Proofing Job");
//	}



	
	
	@BeforeClass
	public void beforeClass() {
		objTE = new TestEngine();
		strDate=objTE.getDateName();
		res1 = new ReportUtility();
	}

}
