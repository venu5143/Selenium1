package testengine;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;

























import utilities.PropertyFileUtil;
import utilities.ReportUtility;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.reports.utils.Utils;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;
public class TestEngine1 {

	protected WebDriver driver;
	ReportUtility res;
	private HashMap<String, String> prop = new PropertyFileUtil("environment").getWebElementMapping();
	public void takesnap(String path){
//		File src=TakesScreenshot(driver)
	}
	public void launchBrowser() {
		res= new ReportUtility();
		System.setProperty("webdriver.ie.driver",
				prop.get("IEDriverPath"));
		driver = new InternetExplorerDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		ATUReports.setWebDriver(driver);
  	   ATUReports.indexPageDescription = "OpenAxes Project";
	   ATUReports.setAuthorInfo("Venu Gorantla",  Utils.getCurrentTime(), "1.1");
	}

	public void launchApp(String URL) {
		driver.get(URL);
	}

	public void enterByXpath(String pr, String dt) {
		WebElement obj = driver.findElement(By.xpath(pr));
		obj.sendKeys(dt);
//		res.writeResult("Click", result, testdata);
	}

	public void enterByID(String pr, String dt) {
		WebElement obj = driver.findElement(By.id(pr));
		obj.clear();
		obj.sendKeys(dt);
	}

	public void enterByID(String pr, String dt,String desc) throws IOException {
		WebElement obj = driver.findElement(By.id(pr));
		obj.clear();
		obj.sendKeys(dt);
		res.writeResult(desc, "Pass", dt);
		ATUReports.add(desc, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
	}
	public void clickByID(String pr) throws InterruptedException {
		Thread.sleep(1000);
		WebElement obj = driver.findElement(By.id(pr));
		obj.click();
	}

	public void clickByXpath(String pr) throws InterruptedException {
		WebElement obj = driver.findElement(By.xpath(pr));
		Thread.sleep(1000);
		obj.click();
		
	}
	
	public void clickByXpath(String pr,String desc) throws IOException, InterruptedException {
		Thread.sleep(2000);
		WebElement obj = driver.findElement(By.xpath(pr));
		obj.click();
		
		res.writeResult(desc, "Pass", "");
		ATUReports.add(desc, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
	}

	public void clickByPartialLink(String pr) throws IOException, InterruptedException {
		Thread.sleep(2000);
		WebElement obj = driver.findElement(By.partialLinkText(pr));
		if(obj.isEnabled()){
			obj.click();
		}
		
		res.writeResult("Click on the Link", "Pass", pr);
		ATUReports.add("Click on the Link", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
	}

	
	
	public void switchToFrameByElementUsingName(String loc_name) {
		driver.switchTo().frame(driver.findElement(By.name(loc_name)));
	}

	public void verifyLink(String link,String desc) throws InterruptedException, IOException {
		Thread.sleep(2000);
		WebElement obj = driver.findElement(By.linkText(link));

		if(obj.isDisplayed()==true){
			res.writeResult(desc, "Pass", "");
			ATUReports.add(desc, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		else{
			res.writeResult(desc, "Fail", "");
			ATUReports.add(desc, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
	
	public void clickByLink(String pr) throws InterruptedException, IOException {
		Thread.sleep(2000);
		WebElement obj = driver.findElement(By.linkText(pr));
		obj.click();
		res.writeResult("Click on the Link", "Pass", pr);
		ATUReports.add("Click on the Link", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
	}

	public void verifyTextByID(String string, String string2)
			throws IOException {
		String actualValue = driver.findElement(By.id(string)).getText();
		if (actualValue.contains(string2)) {
			res.writeResult("Verifying the Text from the Application", "Pass",
					actualValue);
			ATUReports.add("Verifying the Text from the Application", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} else {
			res.writeResult("Verifying the Text from the Application", "Fail",
					actualValue);
			ATUReports.add("Verifying the Text from the Application", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
	public void verifyTextByXpath(String loc, String exp_val)
			throws IOException {
		String actualValue = driver.findElement(By.xpath(loc)).getText();
		if (actualValue.contains(exp_val)) {
			res.writeResult("Verifying the Text from the Application", "Pass",
					actualValue);
		} else {
			res.writeResult("Verifying the Text from the Application", "Fail",
					actualValue);
		}
	}
	public void verifyTextByXpath(String loc, String exp_val,String desc)
			throws IOException {
		String actualValue = driver.findElement(By.xpath(loc)).getText();
		if (actualValue.contains(exp_val)) {
			res.writeResult(desc, "Pass",actualValue);
			ATUReports.add(desc, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} else {
			res.writeResult(desc, "Fail",actualValue);
			ATUReports.add(desc, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
	public void closeBrowser(){
		driver.quit();
	}

	public int getRowsInWebTableByXpath(String xpath) {
		
		WebElement tbl=driver.findElement(By.xpath(xpath));
		return tbl.findElements(By.tagName("tr")).size();
	}

	public void verifyElementByID(String loc, String desc) throws IOException, InterruptedException {
		Thread.sleep(2000);
		WebElement obj = driver.findElement(By.id(loc));

		if(obj.isDisplayed()==true){
			res.writeResult(desc, "Pass", "");
			ATUReports.add(desc, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		else{
			res.writeResult(desc, "Fail", "");
			ATUReports.add(desc, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		
	}

	public void verifyIsSelectedByID(String loc, String desc) throws IOException, InterruptedException {
		Thread.sleep(2000);
		WebElement obj = driver.findElement(By.id(loc));

		if(obj.isSelected()==true){
			res.writeResult(desc, "Pass", "");
			ATUReports.add(desc, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		else{
			res.writeResult(desc, "Fail", "");
			ATUReports.add(desc, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
	
	public void verifyIsSelectedByXpath(String loc,String val, String desc) throws IOException, InterruptedException {
		Thread.sleep(2000);
		WebElement obj = driver.findElement(By.xpath(loc));
		
		if(obj.getText().equals(val)){
			res.writeResult(desc, "Pass", "");
			ATUReports.add(desc, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		else{
			res.writeResult(desc, "Fail", "");
			ATUReports.add(desc, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	public String getTextByID(String loc) {
		WebElement obj = driver.findElement(By.id(loc));
		return obj.getText();
	}

	public void verifyGridResultsByID(String... args) throws IOException {
		int count=args.length;
		WebElement obj = driver.findElement(By.id(args[0]));
		
		String str=obj.getText();
		
		String actualValue[] = str.split("\n");
		int flag=0;
		for(int i=1;i<count-1;i++){
			if(args[i].equals(actualValue[i-1])){
			}else{
				flag++;
				break;
			}
		}
		if(flag==0){
			res.writeResult(args[count-1], "Pass", "");
			ATUReports.add(args[count-1], LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}else{
			res.writeResult(args[count-1], "Fail", "");
			ATUReports.add(args[count-1], LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		
		
		
	}

	public void mouserOverByID(String string) {
		WebElement obj = driver.findElement(By.id(string));
		Actions act=new Actions(driver);
		act.moveToElement(obj).build().perform();
	}

	public void clickONDeleteKeyboard() {
		Actions act=new Actions(driver);
		act.sendKeys(Keys.DELETE).build().perform();
		
	}

	public void acceptAlert() {
		driver.switchTo().alert().accept();
	}

	public void clickByID(String pr, String desc) throws InterruptedException, IOException {
		Thread.sleep(1000);
		WebElement obj = driver.findElement(By.id(pr));
		obj.click();
		res.writeResult(desc, "Pass", "");
		ATUReports.add(desc, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		
	}

	public void verifyElementByXpath(String loc, String desc) throws IOException, InterruptedException {
		Thread.sleep(2000);
		WebElement obj = driver.findElement(By.xpath(loc));

		if(obj.isDisplayed()==true){
			res.writeResult(desc, "Pass", "");
			ATUReports.add(desc, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		else{
			res.writeResult(desc, "Fail", "");
			ATUReports.add(desc, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		
	}

	public void clickONUPArrowANDEnter(String loc, int i) {
		WebElement ele=driver.findElement(By.id(loc));
		Actions act=new Actions(driver);
		act.click(ele).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
	}

	public void verifyElementExistingUsingXpath(String xpath,String desc) throws IOException {
		
		if(driver.findElement(By.xpath(xpath)).isDisplayed()){
			res.writeResult(desc, "Pass", "");
			ATUReports.add(desc, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	public void verifyTextByID(String string, String string2, String string3) throws IOException {
		String actualValue = driver.findElement(By.id(string)).getText();
		if (actualValue.contains(string2)) {
			res.writeResult(string3, "Pass",
					actualValue);
			ATUReports.add(string3, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} else {
			res.writeResult(string3, "Fail",
					actualValue);
			ATUReports.add(string3, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	public void waitForElementByID(String id){
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
	}
	public void waitForElementByXpath(String xpath){
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	}
	
	public boolean getIsSelected(String string) {
		return driver.findElement(By.id(string)).isSelected();
		
	}

	public void verifyPartialLinkIsDisabled(String pl) throws IOException {
		if(driver.findElement(By.partialLinkText(pl)).isEnabled()){
			res.writeResult("The Link is Enabled", "Fail", pl);
			ATUReports.add("The Link is Enabled", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}else{
			res.writeResult("The Link is Enabled", "Pass", pl);
			ATUReports.add("The Link is Enabled", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		
	}

	public void verifyByXpathContainsSRC(String loc, String val,
			String desc) throws IOException {
		WebElement obj = driver.findElement(By.xpath(loc));
		String str=obj.getAttribute("src");
		if(str.contains(val)){
			res.writeResult(desc, "Pass", "");
			ATUReports.add(desc, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		else{
			res.writeResult(desc, "Fail", "");
			ATUReports.add(desc, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		
	}

	public void DoubleclickByXpath(String xpath, String desc) throws IOException {
		WebElement obj = driver.findElement(By.xpath(xpath));
		Actions act=new Actions(driver);
		act.doubleClick(obj).build().perform();
	
			res.writeResult(desc, "Pass", "");
			ATUReports.add(desc, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

	
	}

	public void clickONEnterKeyboard() {
		Actions act=new Actions(driver);
		act.sendKeys(Keys.ENTER).build().perform();
		
	}

	public void verifyElementByIDIsDisabled(String id) throws IOException {
		// TODO Auto-generated method stub
	if(driver.findElement(By.id(id)).isEnabled()){
		res.writeResult("The Link is Enabled", "Fail", "");
		ATUReports.add("The Link is Enabled", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
	}else{
		res.writeResult("The Link is Enabled", "Pass", "");
		ATUReports.add("The Link is Enabled", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
	}
	}
	
	public void verifyElementByIDIsDisabled(String id,String desc) throws IOException {
		// TODO Auto-generated method stub
	if(driver.findElement(By.id(id)).isEnabled()){
		res.writeResult(desc, "Fail", "");
		ATUReports.add(desc, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
	}else{
		res.writeResult(desc, "Pass", "");
		ATUReports.add(desc, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
	}
	}
	public boolean getStatusElementByIDIsDisabled(String id,String desc) throws IOException {
		boolean sta;
	if(driver.findElement(By.id(id)).isSelected()){
		res.writeResult(desc, "Fail", "");
		ATUReports.add(desc, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		sta=true;
	}else{
		res.writeResult(desc, "Pass", "");
		ATUReports.add(desc, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		sta=false;
	}
	return sta;
	}

	public void verifyElementByIDIsNotDisabled(String id,String desc) throws IOException {
		// TODO Auto-generated method stub
	if(driver.findElement(By.id(id)).isEnabled()){
		res.writeResult(desc, "Pass", "");
		ATUReports.add(desc, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
	}else{
		res.writeResult(desc, "Fail", "");
		ATUReports.add(desc, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
	}
	}
	
	public void selectMultipleByXpath(String xpath) {
		List<WebElement> gridRecords=driver.findElements(By.xpath(xpath));
		Actions act=new Actions(driver);
		act.sendKeys(Keys.CONTROL).clickAndHold(gridRecords.get(0))
		.clickAndHold(gridRecords.get(1)).clickAndHold(gridRecords.get(2))
		.sendKeys(Keys.CONTROL).build().perform();

	}

	public void switchToWindow() {
		Set<String> wins=driver.getWindowHandles();
		//System.out.println(wins.size());
		for (String string : wins) {
			driver.switchTo().window(string);
		}
	}

	public void closeWindow() {
		driver.close();
		
	}

	public void switchToParentWindow() {
		Set<String> wins=driver.getWindowHandles();
		int i=0;
		for (String string : wins) {
			driver.switchTo().window(string);
			i++;
			if(i==1)
				break;
		}
	}

	public void enterByIDIfEnabled(String id, String data) {
		if(driver.findElement(By.id(id)).isEnabled()){
			driver.findElement(By.id(id)).sendKeys(data);
		}
		
	}

	public void verifyElementNonExistenceByID(String id, String desc) throws IOException {
		if(driver.findElement(By.id(id)).isDisplayed()){
			res.writeResult(desc, "Fail", "");
			ATUReports.add(desc, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}else{
			res.writeResult(desc, "Pass", "");
			ATUReports.add(desc, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	public void verifyLocationByXpath(String xpath, int x, int y,
			String desc) throws IOException {
		if(driver.findElement(By.xpath(xpath)).getLocation().getX()==x){
		if(driver.findElement(By.xpath(xpath)).getLocation().getY()==y){
			res.writeResult(desc, "Pass", "");
			ATUReports.add(desc, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}else
		{
			res.writeResult(desc, "Fail", "");
			ATUReports.add(desc, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		}
		else{
			res.writeResult(desc, "Fail", "");
			ATUReports.add(desc, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		
		
	}
	
	public void scrollAndSelectByID(String loc){
		WebElement scroll = driver.findElement(By.id(loc));
		Actions act=new Actions(driver);
		act.click(scroll).sendKeys(Keys.PAGE_DOWN).
		sendKeys(Keys.PAGE_DOWN).
		sendKeys(Keys.PAGE_DOWN).
		sendKeys(Keys.PAGE_DOWN).
		sendKeys(Keys.PAGE_DOWN).
		sendKeys(Keys.PAGE_DOWN).
		sendKeys(Keys.PAGE_DOWN).
		sendKeys(Keys.PAGE_DOWN).
		sendKeys(Keys.PAGE_DOWN).
		sendKeys(Keys.PAGE_DOWN).
		sendKeys(Keys.PAGE_DOWN).
		sendKeys(Keys.PAGE_DOWN).
		sendKeys(Keys.PAGE_DOWN).
		sendKeys(Keys.PAGE_DOWN).
		sendKeys(Keys.PAGE_DOWN).
		sendKeys(Keys.PAGE_DOWN).
		sendKeys(Keys.PAGE_DOWN).
		sendKeys(Keys.PAGE_DOWN).
		sendKeys(Keys.PAGE_DOWN).
		sendKeys(Keys.PAGE_DOWN).
		sendKeys(Keys.PAGE_DOWN).
		sendKeys(Keys.PAGE_DOWN).
		sendKeys(Keys.PAGE_DOWN).
		sendKeys(Keys.PAGE_DOWN).
		sendKeys(Keys.ENTER).build().perform();;
	}
	
}
