package tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import testengine.TestEngine;
import utilities.EnvironmentUtil;
import utilities.KillProcessUtil;
import utilities.PropertyFileUtil;
import utilities.ReportUtility;

public class Test1 {
//Step 5
	 EnvironmentUtil eu=new EnvironmentUtil();
	 Map m;
//	 
	TestEngine objTE;
	ReportUtility res1;
	KillProcessUtil kpu;
	private HashMap<String, String> eleMap = new PropertyFileUtil("locators").getWebElementMapping();


	@Test
	public void advancedViewArchive() throws IOException, InterruptedException {
		res1.writeTestcase(1, "Validating the Archive Tab - Advanced view link");
		objTE.clickByPartialLink(m.get("appcode1").toString(),"Select the Application");

		objTE.clickByLink("Data Acquisition and Scheduling","Click on the Menu Link");
		objTE.clickByLink("Archive","Select the Archive tab");
		Thread.sleep(15000);
		objTE.clickByPartialLink("advanced view","Click on the Advanced View Option");
		Thread.sleep(2000);
		objTE.verifyTextByID(eleMap.get("archive.advancedview.text.id"), "is not blank","Verify the Condition that is available");
		objTE.clickByID(eleMap.get("archive.switchview.link.id"),"Click on Basic View Option");
		
		int num1 = objTE
				.getRowsInWebTableByXpath(eleMap.get("archive.condition.gridtable.xpath"));
		objTE.clickByPartialLink("add condition","Click on Add Condition Link");
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
//		objTE.verifyElementExistingUsingXpath(eleMap.get("common.success.message.xpath"),"Verifying Message 'Your changes have been successfully saved.'");
		Thread.sleep(10000);
int n=objTE.getgridRowsInWebTableByXpath(eleMap.get("archive.grid.tr.xpath"));
		
		
		if (n == 3) {
			
			objTE.clickByXpath(eleMap.get("archive.second.row.checkbox.xpath"),"Select the Condition to be Deleted");
			objTE.clickByID(eleMap.get("archive.delete.link.id"), "Clicking on Delete Column link");
			
		} else {
		 System.out.println("QA Recheck the adding of the Row in prevous Steps");
		}
		objTE.clickByID(eleMap.get("archive.save.button.id"),"Click on the Save button");
		res1.writeResult("Saving the Archive values Cleaning up Automation", "Pass", "");
		
		
	}

	@Test(dependsOnMethods="advancedViewArchive")
	public void cleanUPViewArchive() throws IOException, InterruptedException {
		res1.writeTestcase(2, "Validating the Clean UP Activity in Archive Tab - Advanced view link");
		objTE.clickByPartialLink(m.get("appcode1").toString(),"Select the Application");
		objTE.clickByLink("Data Acquisition and Scheduling","Click on the Menu Link");
		objTE.clickByLink("Archive","Select the Archive tab");
		Thread.sleep(15000);
		
		int n=objTE.getgridRowsInWebTableByXpath(eleMap.get("archive.grid.tr.xpath"));
		
		
		if (n == 3) {
			
			objTE.clickByXpath(eleMap.get("archive.second.row.checkbox.xpath"),"Select the Condition to be Deleted");
			objTE.clickByID(eleMap.get("archive.delete.link.id"), "Clicking on Delete Column link");
			
		} else {
		 System.out.println("QA Recheck the adding of the Row in prevous Steps");
		}
		objTE.clickByID(eleMap.get("archive.save.button.id"),"Click on the Save button");
		res1.writeResult("Saving the Archive values Cleaning up Automation", "Pass", "");
//		objTE.verifyElementExistingUsingXpath(eleMap.get("common.success.message.xpath"),"Verifying Message 'Your changes have been successfully saved.'");
		
		
	}


	@BeforeMethod
	public void loginApp() throws InterruptedException, IOException {
// Step 4
		objTE.launchApp(m.get("View2_URL").toString());
		objTE.enterByID(eleMap.get("login.username.text.id"), m.get("view2_username").toString());
		objTE.enterByID(eleMap.get("login.password.text.id"), m.get("view2_pwd").toString());
//	
		objTE.clickByID(eleMap.get("login.login.btn.id"));
		objTE.clickByID(eleMap.get("login.agree.btn.id"));
	}

	@AfterMethod
	public void logoutApp() throws InterruptedException {
		objTE.clickByXpath(eleMap.get("home.logout.link.xpath"));
	}

	// Step1
	@Parameters({ "env" })
	@BeforeClass
	// Step2 String env
	public void beforeClass(String env) throws IOException {
//		Step 3
		   m=eu.getEnvironment(env);
//		   
		objTE = new TestEngine();
		res1 = new ReportUtility();
		kpu = new KillProcessUtil();
		objTE.launchBrowser();
		
	}

	@AfterClass
	public void afterClass() throws Exception {
		objTE.closeBrowser();
	}

}
