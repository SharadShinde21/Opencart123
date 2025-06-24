package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass
{
 
	@Test(groups = {"Sanity","Master"})
	public void verify_Login()
	{
		logger.info("***** Starting TC002_LoginTest***** ");
		
		
		try
		{
		//*****Home Page*****
		HomePage hp=new HomePage(driver);    //HomePage extends in this page
		hp.clickMyAccount();
		hp.clickLogin();
		
		
		//******Login Page*****
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));            //p is coming from config.properties
		lp.setPassword(p.getProperty("password"));
		lp.clickLogin();
		
		//******My Account Page******
		MyAccountPage macc= new MyAccountPage(driver);
		boolean targetpage =macc.isMyAccountPageExists();
		
		Assert.assertTrue(targetpage);
		//Assert.assertEquals(targetpage, true,"Login Failed");
		
		}
		catch (Exception e)
		{
			Assert.fail();
		}
		logger.info("***** Finishing TC002_LoginTest***** ");
		
	}
	
}
