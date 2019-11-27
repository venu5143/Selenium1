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

public class M1TestArchive {


	TestEngine objTE;
	ReportUtility res1;
	KillProcessUtil kpu;
	private HashMap<String, String> eleMap = new PropertyFileUtil("locators").getWebElementMapping();
	private HashMap<String, String> prop = new PropertyFileUtil("environment").getWebElementMapping();
	private HashMap<String, String> cred = new PropertyFileUtil("credentials").getWebElementMapping();
	@Test
	public void advancedViewArchive() throws IOException, InterruptedException {
		res1.writeTestcase("Archive-1", "Validating the Archive Tab - Advanced view link");
		objTE.clickByPartialLink("INW010");
		objTE.clickByLink("Data Acquisition and Scheduling");
		objTE.clickByLink("Archive");
		Thread.sleep(15000);
		objTE.clickByPartialLink("advanced view");
		Thread.sleep(2000);
		objTE.verifyTextByID(eleMap.get("archive.advancedview.text.id"), "is not blank");
		objTE.clickByID(eleMap.get("archive.switchview.link.id"));
		
		int num1 = objTE
				.getRowsInWebTableByXpath(eleMap.get("archive.condition.gridtable.xpath"));
		objTE.clickByPartialLink("add condition");
		Thread.sleep(2000);
		int num = objTE
				.getRowsInWebTableByXpath(eleMap.get("archive.condition.gridtable.xpath"));

		if (num > num1) {
			res1.writeResult("Validating the Add Condition link", "Pass", "");
		} else {
			res1.writeResult("Validating the Add Condition link", "Fail", "");
		}
		
		objTE.clickByID(eleMap.get("archive.field.btn.id"));
		objTE.clickByXpath(eleMap.get("archive.field.option.xpath"));
		objTE.clickByID(eleMap.get("archive.operator.btn.id"));
		objTE.clickByXpath(eleMap.get("archive.operator.option.xpath"));
		objTE.clickByID(eleMap.get("archive.value.btn.id"));
		objTE.clickByXpath(eleMap.get("archive.value.option.xpath"));
		res1.writeResult("Validating Adding of New Condition", "Pass", "");
		objTE.clickByID(eleMap.get("archive.save.button.id"));
		res1.writeResult("Saving the Archive values entered", "Pass", "");
		objTE.verifyElementExistingUsingXpath(eleMap.get("common.success.message.xpath"),"Verifying Message 'Your changes have been successfully saved.'");
		
		
	}

	@Test(dependsOnMethods="advancedViewArchive")
	public void cleanUPViewArchive() throws IOException, InterruptedException {
		res1.writeTestcase("Archive-2", "Validating the Clean UP Activity in Archive Tab - Advanced view link");
		objTE.clickByPartialLink("INW010");
		objTE.clickByLink("Data Acquisition and Scheduling");
		objTE.clickByLink("Archive");
		Thread.sleep(15000);
		
		int n=objTE.getgridRowsInWebTableByXpath(eleMap.get("archive.grid.tr.xpath"));
		
		
		if (n == 3) {
			
			objTE.clickByXpath(eleMap.get("archive.second.row.checkbox.xpath"));
			objTE.clickByID(eleMap.get("archive.delete.link.id"), "Clicking on Delete Column link");
			
		} else {
			res1.writeResult("QA Recheck the adding of the Row in prevous Steps", "Fail", "");
//		 System.out.println("");
		}
		objTE.clickByID(eleMap.get("archive.save.button.id"));
		res1.writeResult("Saving the Archive values Cleaning up Automation", "Pass", "");
		objTE.verifyElementExistingUsingXpath(eleMap.get("common.success.message.xpath"),"Verifying Message 'Your changes have been successfully saved.'");
		
		
	}


	@BeforeMethod
	public void loginApp() throws InterruptedException, IOException {
		objTE.launchApp(prop.get("URL"));
		objTE.enterByID(eleMap.get("login.username.text.id"), cred.get("username"));
		objTE.enterByID(eleMap.get("login.password.text.id"), cred.get("pwd"));
		objTE.clickByID(eleMap.get("login.login.btn.id"));
		objTE.clickByID(eleMap.get("login.agree.btn.id"));
	}

	@AfterMethod
	public void logoutApp() throws InterruptedException {
		objTE.clickByXpath(eleMap.get("home.logout.link.xpath"));
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
		objTE.closeBrowser();
		kpu.killProcess("iexplore.exe");
	}

}
