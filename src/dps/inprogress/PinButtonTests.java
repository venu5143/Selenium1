package dps.inprogress;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import dps.donebackup.ReportUtility;
import testengine.TestEngine;
import utilities.KillProcessUtil;
import utilities.PropertyFileUtil;
//import utilities.ReportUtility;

public class PinButtonTests {

	TestEngine objTE;
	ReportUtility res1;
	KillProcessUtil kpu;
	private HashMap<String, String> eleMap = new PropertyFileUtil("locators")
			.getWebElementMapping();
	private HashMap<String, String> prop = new PropertyFileUtil("environment")
			.getWebElementMapping();
	private HashMap<String, String> cred = new PropertyFileUtil("credentials")
			.getWebElementMapping();

	
	@DataProvider(name = "appsPinned")
	public Object[][] createData1() {
		
//		/view2/images/pin_down.png
//		Unpinned
		Map m1 = new HashMap(); 
		m1.put("1","MVC001"); 
		m1.put("2","UNI045"); 
		m1.put("3","UNI004"); 
		m1.put("4","WFM310");
		m1.put("5","MVC010");
		m1.put("6","UNI002");
		m1.put("7","AGA300");
		m1.put("8", "AVA001");
	 return new Object[][] {
			  { m1},
	 };
	}
	
	@DataProvider(name = "appsNotPinned")
	public Object[][] createData2() {
//		/view2/images/pin_left.png
//		Pin to this list
		Map m2 = new HashMap(); 
		m2.put("1","SFI001"); 
		m2.put("2","COV020"); 
	 return new Object[][] {
			  { m2},
	 };
	}
	
	@Test(priority = 1,dataProvider="appsPinned")
	public void loginSuccessful(Map m) throws Exception {
		res1.writeTestcase(
				"Login",
				"Login Test for success by validating the 'security disclaimer' - change to wording");
		objTE.launchBrowser();
		objTE.launchApp(prop.get("URL"));
		System.out.println(cred.get("username")+"::::"+cred.get("pwd"));
		objTE.enterByID(eleMap.get("login.username.text.id"),
				cred.get("username"));
		objTE.enterByID(eleMap.get("login.password.text.id"), cred.get("pwd"));
		objTE.clickByID(eleMap.get("login.login.btn.id"));
		Thread.sleep(500);

		objTE.clickByID(eleMap.get("login.agree.btn.id"));
		int k=1;
		for(int i=0;i<m.size();i++)
		{
		objTE.verifyElementByXpath("//*[contains(text(),'"+m.get(k+"")+"')]/../img", "The Image is displayed for the Application");
		objTE.verifyImgSelectedByXpath("//*[contains(text(),'"+m.get(k+"")+"')]/../img", "pin_down.png", "The Pin Image is Selected for the Given Application", m.get(k+"")+"");
		k++;
		}
		Thread.sleep(500);
		objTE.clickByXpath(eleMap.get("home.logout.link.xpath"));
		objTE.closeBrowser();

	}


	@Test(priority = 2,dataProvider="appsNotPinned")
	public void loginSuccessful1(Map m) throws Exception {
		res1.writeTestcase(
				"Login",
				"Login Test for success by validating the 'security disclaimer' - change to wording");
		objTE.launchBrowser();
		objTE.launchApp(prop.get("URL"));
		System.out.println(cred.get("username")+"::::"+cred.get("pwd"));
		objTE.enterByID(eleMap.get("login.username.text.id"),
				cred.get("username"));
		objTE.enterByID(eleMap.get("login.password.text.id"), cred.get("pwd"));
		objTE.clickByID(eleMap.get("login.login.btn.id"));
		Thread.sleep(500);

		objTE.clickByID(eleMap.get("login.agree.btn.id"));
		int k=1;
		for(int i=0;i<m.size();i++)
		{
		objTE.verifyElementNotDisplayedByXpath("//*[contains(text(),'"+m.get(k+"")+"')]/../img", "The Image is Hidden for the Application");
		objTE.verifyImgNotSelectedByXpath("//*[contains(text(),'"+m.get(k+"")+"')]/../img", "pin_left.png", "The Image is Not Selected for the Given Application",m.get(k+"")+"");
		k++;
		}
		Thread.sleep(500);
		objTE.clickByXpath(eleMap.get("home.logout.link.xpath"));
		objTE.closeBrowser();

	}
	
	@BeforeClass
	public void beforeClass() {

		objTE = new TestEngine();
		res1 = new ReportUtility();
		kpu = new KillProcessUtil();

	}

}
