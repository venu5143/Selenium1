package tests.quantum;


import java.io.IOException;
import java.util.HashMap;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testengine.TestEngine;
import utilities.KillProcessUtil;
import utilities.PropertyFileUtil;
import utilities.ReportUtility;

public class FB18642 {
	
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
	
	@Test(priority=1)
	public void addAlternateImage() throws Exception {
		objTE.launchBrowser();
		objTE.launchApp(prop.get("URL"));
		objTE.enterByID(eleMap.get("login.username.text.id"), cred.get("username"));
		objTE.enterByID(eleMap.get("login.password.text.id"), cred.get("pwd"));
		objTE.clickByID(eleMap.get("login.login.btn.id"));
		objTE.clickByID(eleMap.get("login.agree.btn.id"));
		res1.writeTestcase("FB18642-1", "Adding the Alternate Image");
		objTE.clickByPartialLink("MVC001");
		objTE.clickByLink("Data Processing");
		Thread.sleep(5000);
		objTE.clickByLink("Alternate Images");
		Thread.sleep(5000);
		objTE.clickByPartialLink("add alternate image");
		Thread.sleep(2000);
		objTE.verifyCheckboxIsNotSelected("container",
				"Checking for the Checkbox to be unchecked By Default");
		objTE.verifyElementByIDIsDisabled("containerFileName");
		objTE.verifyElementByIDIsDisabled("unitsPerContainerFile");
		objTE.enterByID("name", "FB18642",
				"Entering the Name of Alternate Image");
		Thread.sleep(2000);
		objTE.enterByID("fileName", "QA",
				"Entering the File Name of Alternate Image");
		objTE.enterByID("unitsPerImageFile", "10",
				"Entering the 'Units Per Image File' of Alternate Image");
		Thread.sleep(2000);
		objTE.clickByID("save", "Saving the Alternative Image");
		Thread.sleep(10000);
		objTE.verifyElementByXpath("//*[contains(text(),'FB18642')]", "Verifying the Alternate Image added");
		objTE.clickByXpath(eleMap.get("home.logout.link.xpath"));
		objTE.closeBrowser();
		//kpu.killProcess("iexplore.exe");
	}
	
	@Test(priority=2)
	public void editAlternateImage() throws Exception {
		objTE.launchBrowser();
		objTE.launchApp(prop.get("URL"));
		objTE.enterByID(eleMap.get("login.username.text.id"), cred.get("username"));
		objTE.enterByID(eleMap.get("login.password.text.id"), cred.get("pwd"));
		objTE.clickByID(eleMap.get("login.login.btn.id"));
		objTE.clickByID(eleMap.get("login.agree.btn.id"));
		res1.writeTestcase("FB18642-2", "Editing the Alternate Image");
		objTE.clickByPartialLink("MVC001");
		objTE.clickByLink("Data Processing");
		Thread.sleep(5000);
		objTE.clickByLink("Alternate Images");
		Thread.sleep(5000);
			objTE.DoubleclickByXpath("//*[contains(text(),'FB18642')]","");
		Thread.sleep(2000);
		objTE.enterByID("name", "FB186421",
				"Rentering or changing the Name of Alternate Image");
		Thread.sleep(2000);
		objTE.clickByID("save", "Saving the Alternative Image");
		Thread.sleep(10000);
		objTE.verifyElementByXpath("//*[contains(text(),'FB18642')]", "Verifying the Alternate Image added");
		objTE.clickByXpath(eleMap.get("home.logout.link.xpath"));
		objTE.closeBrowser();
		//kpu.killProcess("iexplore.exe");
	}
	
	
	@Test(priority=3)
	public void runTestJobWithSnapshot() throws Exception {
		objTE.launchBrowser();
		objTE.launchApp(prop.get("URL"));
		objTE.enterByID(eleMap.get("login.username.text.id"), cred.get("username"));
		objTE.enterByID(eleMap.get("login.password.text.id"), cred.get("pwd"));
		objTE.clickByID(eleMap.get("login.login.btn.id"));
		objTE.clickByID(eleMap.get("login.agree.btn.id"));
		res1.writeTestcase("FB18642-3", "Running the Test Job with Snapshot");
		objTE.clickByPartialLink("MVC001");
		objTE.clickByLink("Test and Validation");
		objTE.clickByPartialLink("run new test job");
		Thread.sleep(10000);
		objTE.enterByID("nameRun", "RunFB18642");
		objTE.clickByPartialLink("library");
		objTE.enterByID("datasetNameLib", "Mayil");
		Thread.sleep(5000);
		objTE.clickByXpath("//*[@id='$Panel_0']/tbody/tr[2]/td/table/tbody/tr[5]/td/table/tbody/tr/td/a");
		Thread.sleep(5000);
		objTE.DoubleclickByXpath("//*[contains(text(),'MAyil')]", "Selecting the Library for Dataset");
		objTE.verifyTextByID("testDataSet", "MAyil");
		objTE.clickByID("runSnapshot", "Clicking the RunSnapShot Checkbox");
		objTE.clickByID("notifications");
		
		objTE.clickByID("runJob","Running the Job");
		
		objTE.clickByXpath(eleMap.get("home.logout.link.xpath"));
		objTE.closeBrowser();
		//kpu.killProcess("iexplore.exe");
	}
	
	@Test(priority=4,dataProvider = "browser")
	public void deleteAlternateImage(String version) throws Exception {
		objTE.launchBrowser(version);
		objTE.launchApp(prop.get("URL"));
		objTE.enterByID(eleMap.get("login.username.text.id"), cred.get("username"));
		objTE.enterByID(eleMap.get("login.password.text.id"), cred.get("pwd"));
		objTE.clickByID(eleMap.get("login.login.btn.id"));
		objTE.clickByID(eleMap.get("login.agree.btn.id"));
		res1.writeTestcase("FB18642-4", "Deleting the Alternate Image",version);
		objTE.clickByPartialLink("MVC001");
		objTE.clickByLink("Data Processing");
		Thread.sleep(5000);
		objTE.clickByLink("Alternate Images");
		Thread.sleep(5000);
		objTE.clickByXpath("//*[contains(text(),'FB186421')]", "");
		Thread.sleep(2000);
		objTE.clickONDeleteKeyboard();
		objTE.acceptAlert();
		Thread.sleep(5000);
		objTE.clickByXpath(eleMap.get("home.logout.link.xpath"));
		objTE.closeBrowser();
		//kpu.killProcess("iexplore.exe");
	}
	
	@BeforeClass
	public void beforeClass() {
		objTE = new TestEngine();
		res1 = new ReportUtility();
		kpu = new KillProcessUtil();
		
	}

	@AfterClass
	public void afterClass() throws Exception{
		kpu.killProcess("iexplore.exe");
	}

}
