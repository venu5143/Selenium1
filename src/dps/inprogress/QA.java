package dps.inprogress;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class QA {
	
	@DataProvider(name = "d")
	public Object[][] createData1() {
	 return new Object[][] {
			  { "X"},
			  { "Y"},
			  { "Z"},
	 };
	}
	
  @Test(dataProvider="d")
  public void A(String s) {
	  System.out.println("Inside A"+s);
  }
  
  @Test(dataProvider="d")
  public void B(String s) {
	  System.out.println("Inside B"+s);
  }
  @Test(dataProvider="d")
  public void C(String s) {
	  System.out.println("Inside C"+s);
  }
  @Test(dataProvider="d")
  public void D(String s) {
	  System.out.println("Inside D"+s);
  }
  @BeforeMethod
  public void beforeMethod() {
	  System.out.println("Inside BM");
  }

  @AfterMethod
  public void afterMethod() {
	  System.out.println("Inside AM");
  }

}
