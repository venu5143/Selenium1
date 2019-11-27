package tests.quantum;


import java.io.IOException;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testengine.TestEngine;
import utilities.KillProcessUtil;
import utilities.PropertyFileUtil;
import utilities.ReportUtility;

public class FB21114 {
//	static Logger log= Logger.getLogger(FB21114.class.getName());
	TestEngine objTE;
	ReportUtility res1;
	KillProcessUtil kpu;
	private HashMap<String, String> eleMap = new PropertyFileUtil("locators").getWebElementMapping();
	private HashMap<String, String> prop = new PropertyFileUtil("environment").getWebElementMapping();
	private HashMap<String, String> cred = new PropertyFileUtil("credentials").getWebElementMapping();
//	@DataProvider(name = "browser")
//	public Object[][] createData1() {
//	 return new Object[][] {
//			  { "11"},
//			   { "10"},
//			   { "9"},
//			   { "8"},
//			   { "7"},
//	 };
//	}
//	
	
	@Test(priority=1)
	public void runTestJobWithSnapshot() throws Exception {
		res1.writeTestcase("FB21114", "security disclaimer - change to wording");
		objTE.launchBrowser();
		objTE.launchApp(prop.get("URL"));
		objTE.enterByID(eleMap.get("login.username.text.id"), cred.get("username"));
		objTE.enterByID(eleMap.get("login.password.text.id"), cred.get("pwd"));
		objTE.clickByID(eleMap.get("login.login.btn.id"));
		Thread.sleep(5000);
		objTE.verifyTextByID("disclaimerWindow_title", "TransCentra Security Standard", "Verifying the Title of the Disclaimer window");
		objTE.verifyTextByXpath("//*[@id='disclaimerWindow']/table/tbody/tr[2]/td/div/div/span", "UNAUTHORIZED ACCESS TO THIS SYSTEM IS PROHIBITED.", "Verifying the Header of the Disclaimer window");
		objTE.verifyTextByXpath("//*[@id='disclaimerWindow']/table/tbody/tr[2]/td/div/div/p", "You must have explicit permission to access or configure this system. All activities performed on this system may be logged, and violations of this policy may result in applicable legal actions. There is no right to privacy on this system. Evidence of unauthorized use collected during monitoring may be used for administrative, criminal, or other adverse action. Use of this system constitutes consent to monitoring for these purposes.", "Verifying the Text of the Disclaimer window");
//		log.info(version+": Verifying the Login Disclaimer");
		objTE.clickByID(eleMap.get("login.agree.btn.id"));
		Thread.sleep(5000);
		objTE.clickByXpath(eleMap.get("home.logout.link.xpath"));
		objTE.closeBrowser();
		kpu.killProcess("iexplore.exe");
	}
	
	
	@BeforeClass
	public void beforeClass() {
		DOMConfigurator.configure("log4j.xml");
		objTE = new TestEngine();
		res1 = new ReportUtility();
		kpu = new KillProcessUtil();
		 
	}

	

}
