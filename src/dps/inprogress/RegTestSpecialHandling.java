package dps.inprogress;




import java.io.IOException;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

import dps.regression.common.SpecialHandlingMethods;
import testengine.TestEngine;
import utilities.KillProcessUtil;
import utilities.PropertyFileUtil;
import utilities.ReportUtility;

public class RegTestSpecialHandling {
	SpecialHandlingMethods objTE;
	String b;
	ReportUtility res1;
	KillProcessUtil kpu;
	private HashMap<String, String> eleMap = new PropertyFileUtil("locators").getWebElementMapping();
	private HashMap<String, String> prop = new PropertyFileUtil("environment").getWebElementMapping();
	private HashMap<String, String> cred = new PropertyFileUtil("credentials").getWebElementMapping();
//	@Test(priority=1)
//	public void dateSpecialHandling1() throws IOException, InterruptedException {
//		res1.writeTestcase("SpecialHandling", "Validating the Special Handling Module");
//		objTE.addInstructionInPullDocs("MVC001");
//	}

//	@Test(priority=2)
//	public void dateSpecialHandling2() throws IOException, InterruptedException {
//		objTE.login();
//		res1.writeTestcase("2SpecialHandling", "Validating the Special Handling Module");
//		objTE.runTestJob("COV020");
//		Thread.sleep(20000);
//	}

//	@Test(priority=3)
//	public void dateSpecialHandling3() throws IOException, InterruptedException {
//		res1.writeTestcase("3SpecialHandling", "Validating the Special Handling Module");
//		 b=objTE.verifyStatusMonitor("COV020");
//		 if(b.equals(null)){
//			 System.out.println("No Batch");
//		 }
//	}

	@Test(priority=4)
	public void dateSpecialHandling4() throws IOException, InterruptedException {
		objTE.login();
		res1.writeTestcase("4SpecialHandling", "Validating the Special Handling Module");
		objTE.navigateToSpecialHandling("COV020","R0UIHT");
	}

	
	@BeforeMethod
	public void loginApp() throws InterruptedException {
		
	}

//	@AfterMethod
//	public void logoutApp() throws InterruptedException {
//		objTE.logout();
//	}

	@BeforeClass
	public void beforeClass() {
		WebDriver d = null;
		objTE = new SpecialHandlingMethods(d);
		res1 = new ReportUtility();
		kpu = new KillProcessUtil();
		objTE.launchBrowser();
	}

//	@AfterClass
//	public void afterClass() throws Exception {
//		objTE.closeBrowser();
//		kpu.killProcess("iexplore.exe");
//	}

}
