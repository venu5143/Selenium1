package tests.navigatorlite;


import java.io.IOException;
import java.util.HashMap;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

import testengine.TestEngine;
import utilities.PropertyFileUtil;
import utilities.ReportUtility;

public class NavTest1 {
	TestEngine objTE;
	ReportUtility res1;
	private HashMap<String, String> eleMap = new PropertyFileUtil("locators").getWebElementMapping();
	private HashMap<String, String> prop = new PropertyFileUtil("environment").getWebElementMapping();
	private HashMap<String, String> cred = new PropertyFileUtil("credentials").getWebElementMapping();
	@Test(priority=1)
	public void manageSecurityVerification() throws IOException, InterruptedException {
		res1.writeTestcase(1, "Validating the Manage Security Page From Home Page");
		objTE.clickByLink("Manage Security");
		objTE.verifyLink("Membership","Verifying the Membership Tab");
		objTE.verifyLink("Permissions","Verifying the Permissions Tab");
		objTE.verifyElementByID("viewUsers","Verifying the Users Radiobutton");
		objTE.verifyElementByID("viewRoles","Verifying the Roles Radiobutton");
		objTE.verifyElementByID("searchFilter_Field","Verifying the Search TextBox");
		objTE.verifyElementByID("expiredUsersCheck","Verifying the Only Expired Users Checkbox");
		objTE.verifyLink("[add user]","Verifying the add user Link");
		objTE.verifyIsSelectedByID("viewUsers","Validating whether Users Radiobutton is Selected By Default");
		//objTE.getTextByID("grid");
		objTE.verifyGridResultsByID("grid","User","Roles","Date Added","End Date","Verifing the Header in Membership Tab");
		
	}
	
	@Test(priority=2)
	public void manageSecurityAddRoles() throws IOException, InterruptedException {
		res1.writeTestcase(2, "Validating Adding the Roles from Manage Security Page");
		objTE.clickByLink("Manage Security");
		objTE.mouseOverByID("viewRoles");
		objTE.clickByID("viewRoles","Click on Roles Radiobutton");
	    objTE.clickByID("add","Click on Add Roles Link");
		objTE.verifyTextByID("roleWindow_title", "Role Detail");
		objTE.enterByID("roleName", "qarole4","Entering the Value in Rolename Field");
		objTE.enterByID("roleNotes", "qanotes4","Entering the Value in Notes Field");
		objTE.clickByID("roleNext","Click on Next button");
//		System.out.println(objTE.getRowsInWebTableByXpath("//*[@id='rolePermCell']/table/tbody"));
		objTE.clickByLink("Finish");
		Thread.sleep(5000);
		objTE.verifyTextByID("messagePanel", "Role successfully added.");
	}
	
//	
	

	@Test(priority=3)
	public void manageSecurityAddUsers() throws IOException, InterruptedException {
		res1.writeTestcase(3, "Validating Adding the Users Using Role Added from Manage Security Page");
		objTE.clickByLink("Manage Security");
		objTE.mouseOverByID("viewUsers");
		objTE.clickByID("viewUsers","Click on Users Radiobutton");
	    objTE.clickByID("add","Click on Add Roles Link");
		objTE.verifyTextByID("userWindow_title", "User Detail");
		objTE.enterByID("userName", "aab user1","Entering the Value in Username Field");
		objTE.enterByID("login", "aqauser","Entering the Value in Login Name Field");
		objTE.enterByID("password", "Aqauser?123","Entering the Value in Password Field");
		objTE.enterByID("confirmPassword", "Aqauser?123","Entering the Value in Password Field");
		objTE.clickByXpath("//*[contains(text(),'qarole4')]","Selecting the Role Available");
		objTE.clickByPartialLink("Add");
		objTE.enterByID("beginDate","08/06/2015");
		Thread.sleep(2000);
		objTE.clickByID("userNext","Click on Next button");
		Thread.sleep(2000);
//		System.out.println(objTE.getRowsInWebTableByXpath("//*[@id='rolePermCell']/table/tbody"));
		objTE.clickByLink("Finish");
		Thread.sleep(60000);
		objTE.verifyTextByID("messagePanel", "User successfully added.");
	}
	
	@Test(priority=4)
	public void manageSecurityDeleteUser() throws IOException, InterruptedException {
		res1.writeTestcase(4, "Validating Deleting the Users from Manage Security Page");
		objTE.clickByLink("Manage Security");
		objTE.mouseOverByID("viewUsers");
		objTE.clickByID("viewUsers","Click on Users Radiobutton");
		objTE.enterByID("searchFilter_Field", "aab user");
		objTE.clickByID("searchFilter_Button", "Searching or the created user");
	    Thread.sleep(2000);
		objTE.clickByXpath("//*[contains(text(),'aab user')]","Selecting of the Role to be Deleted");
		objTE.clickONDeleteKeyboard();
		objTE.acceptAlert();
		res1.writeResult("Deleting the User created in Previous Steps", "Pass", "qarole4");
	}
	@Test(priority=5)
	public void manageSecurityDeleteRole() throws IOException, InterruptedException {
		res1.writeTestcase(5, "Validating Deleting the Roles from Manage Security Page");
		objTE.clickByLink("Manage Security");
		objTE.mouseOverByID("viewRoles");
		objTE.clickByID("viewRoles","Click on Roles Radiobutton");
	    Thread.sleep(5000);
		objTE.clickByXpath("//*[contains(text(),'qarole4')]","Selecting of the Role to be Deleted");
		objTE.clickONDeleteKeyboard();
		objTE.acceptAlert();
		res1.writeResult("Deleting the Role created in Previous Steps", "Pass", "qarole4");
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
		objTE.clickByXpath(eleMap.get("home.logout.link.xpath"));
	}

	@BeforeClass
	public void beforeClass() {
		objTE = new TestEngine();
		res1 = new ReportUtility();
		objTE.launchBrowser();
	}

	@AfterClass
	public void afterClass() {
		objTE.closeBrowser();
	}

}
