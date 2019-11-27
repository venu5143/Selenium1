package tests;

import java.io.IOException;
import java.util.HashMap;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import testengine.TestEngine;
import utilities.KillProcessUtil;
import utilities.PropertyFileUtil;
import utilities.ReportUtility;

public class M1TestDataFiles {
	
	TestEngine objTE;
	ReportUtility res1;
	KillProcessUtil kpu;
	
	private HashMap<String, String> eleMap = new PropertyFileUtil("locators").getWebElementMapping();
	private HashMap<String, String> prop = new PropertyFileUtil("environment").getWebElementMapping();
	private HashMap<String, String> cred = new PropertyFileUtil("credentials").getWebElementMapping();

	@Test(priority=1)
	public void addDataFiles1() throws IOException, InterruptedException {
		res1.writeTestcase("Data Files-1", "Adding the Data Files Tab under Data Acquistion and Scheduling Module");
		objTE.clickByPartialLink("INW010");
		objTE.clickByLink("Data Acquisition and Scheduling");
		objTE.verifyLink("Data Files", "Verifying the Data Files Tab");
		objTE.clickByLink("Data Files");
		Thread.sleep(5000);
		int num1 = objTE
				.getRowsInWebTableByXpath("//table[@id='dataFileSpecsView_1_Grid']/tbody");
		objTE.clickByPartialLink("add file");
		Thread.sleep(3000);
		objTE.enterByID("name", "ARegName", "Entering the Friendly file Name");
		objTE.enterByID("fileName", "ARegNameFile", "Entering the file Name");
		
		objTE.clickByID("formatList_Button");
		objTE.clickByXpath("//div[@id='formatList_Items']/table/tbody/tr[3]/td[2]");
		
		objTE.clickByID("typeList_Button");
		objTE.clickByXpath("//div[@id='typeList_Items']/table/tbody/tr[3]/td[2]");
		objTE.clickByID("jobClass_Button");
		objTE.clickByXpath("//div[@id='jobClass_Items']/table/tbody/tr[1]/td[2]");
		objTE.clickByID("deliveryMethodList_Button");
		objTE.clickByXpath("//div[@id='deliveryMethodList_Items']/table/tbody/tr[3]/td[2]");
		
//		objTE.clickONEnterKeyboard();

		Thread.sleep(2000);
		objTE.clickByXpath("//div[@id='dataFileSpecWindow']/table/tbody/tr[2]/td/table/tbody/tr[3]/td/a");
	int num2 = objTE
		.getRowsInWebTableByXpath("//table[@id='dataFileSpecsView_1_Grid']/tbody");
	if(num2>num1){
			res1.writeResult("Validating the Saved Data Files file", "Pass", "");
		} else {
			res1.writeResult("Validating the Saved  Data Files file", "Fail", "");
		}
	
	objTE.clickByLink(eleMap.get("instruction.save.link.linktext"));
	Thread.sleep(2000);
	}
	
	@Test(priority=2)
	public void editDataFiles1() throws IOException, InterruptedException {
		res1.writeTestcase("Data Files-2", "Editing the Data Files Tab under Data Acquistion and Scheduling Module");
		objTE.clickByPartialLink("INW010");
		objTE.clickByLink("Data Acquisition and Scheduling");
		objTE.verifyLink("Data Files", "Verifying the Data Files Tab");
		objTE.clickByLink("Data Files");
		Thread.sleep(5000);
		int num1 = objTE
				.getRowsInWebTableByXpath("//table[@id='dataFileSpecsView_1_Grid']/tbody");
		objTE.DoubleclickByXpath("//*[contains(text(),'ARegName')]", "Selecting the file Added in Previous Steps");
		objTE.clickByID("formatList_Button");
		objTE.clickByXpath("//div[@id='formatList_Items']/table/tbody/tr[4]/td[2]");
		
		objTE.clickByID("typeList_Button");
		objTE.clickByXpath("//div[@id='typeList_Items']/table/tbody/tr[2]/td[2]");

		objTE.clickByID("deliveryMethodList_Button");
		objTE.clickByXpath("//div[@id='deliveryMethodList_Items']/table/tbody/tr[2]/td[2]");
		
//		objTE.clickONEnterKeyboard();

		Thread.sleep(2000);
		objTE.clickByXpath("//div[@id='dataFileSpecWindow']/table/tbody/tr[2]/td/table/tbody/tr[3]/td/a");
		Thread.sleep(2000);
	int num2 = objTE
		.getRowsInWebTableByXpath("//table[@id='dataFileSpecsView_1_Grid']/tbody");
	if(num2==num1){
			res1.writeResult("Editing the Saved Data Files file", "Pass", "");
		} else {
			res1.writeResult("Editing the Saved  Data Files file", "Fail", "");
		}
	
	objTE.clickByLink(eleMap.get("instruction.save.link.linktext"));
	Thread.sleep(2000);
	
	}
	@Test(priority=3)
	public void deleteDataFiles1() throws IOException, InterruptedException {
		res1.writeTestcase("Data Files-3", "Deleting the Data Files Tab under Data Acquistion and Scheduling Module");
		objTE.clickByPartialLink("INW010");
		objTE.clickByLink("Data Acquisition and Scheduling");
		objTE.verifyLink("Data Files", "Verifying the Data Files Tab");
		objTE.clickByLink("Data Files");
		Thread.sleep(5000);
		int num1 = objTE
				.getRowsInWebTableByXpath("//table[@id='dataFileSpecsView_1_Grid']/tbody");
		objTE.clickByXpath("//*[contains(text(),'ARegName')]", "Selecting the file Added in Previous Steps");
		objTE.clickONDeleteKeyboard();
//		objTE.acceptAlert();
		
		
	int num2 = objTE
		.getRowsInWebTableByXpath("//table[@id='dataFileSpecsView_1_Grid']/tbody");
	if(num2<num1){
			res1.writeResult("Deleting the Saved Data Files file", "Pass", "");
		} else {
			res1.writeResult("Deleting the Saved  Data Files file", "Fail", "");
		}
	
	objTE.clickByLink(eleMap.get("instruction.save.link.linktext"));
	Thread.sleep(2000);
	
	}
	
	
	@BeforeMethod
	public void loginApp() throws InterruptedException {
		objTE.launchApp(prop.get("URL"));
		objTE.enterByID(eleMap.get("login.username.text.id"), cred.get("username"));
		objTE.enterByID(eleMap.get("login.password.text.id"), cred.get("pwd"));
		objTE.clickByID(eleMap.get("login.login.btn.id"));
		objTE.clickByID(eleMap.get("login.agree.btn.id"));
	}

	@AfterMethod
	public void logoutApp() throws InterruptedException {
//		objTE.clickByXpath(eleMap.get("home.logout.link.xpath"));
	}


	@BeforeClass
	public void beforeClass() {
		objTE = new TestEngine();
		res1 = new ReportUtility();
		kpu = new KillProcessUtil();
		objTE.launchBrowser();
	}

	@AfterClass
	public void afterClass() throws Exception {
//		objTE.closeBrowser();
//		kpu.killProcess("iexplore.exe");
	}

}
