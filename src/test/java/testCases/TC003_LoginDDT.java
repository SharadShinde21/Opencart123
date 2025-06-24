package testCases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

/*Test Case:
 1)Data is valid- ligin sucess-test pass- logout
 2)data is valid--login failed- test fail
 3)data is Invalid- login sucess-test fail
 4)data is invaild- login failed-test passed
 */

public class TC003_LoginDDT extends BaseClass
{
	
	@Test(dataProvider = "LoginData",dataProviderClass = utilities.DataProviders.class,groups = "DataDriven")//data provider in different class
	public void verify_loginDDT (String email,String pwd,String exp) throws InterruptedException
	{   
		logger.info("***********Starting TC_003_LoginDDT*******");
		
		
		      try
		      {
		        //*****Home Page*****
				HomePage hp=new HomePage(driver);    //HomePage extends in this page
				hp.clickMyAccount();
				hp.clickLogin();
				
				
				//******Login Page*****
				LoginPage lp=new LoginPage(driver);
				lp.setEmail(email);           //p is coming from config.properties
				lp.setPassword(pwd);
				
				lp.clickLogin();
				
				//******My Account Page******
				MyAccountPage macc= new MyAccountPage(driver);
				boolean targetpage =macc.isMyAccountPageExists();
				
				
				if(exp.equalsIgnoreCase("Valid"))
				{
					if(targetpage==true)
					{
						macc.clickLogout();
						Assert.assertTrue(true);
					}
					else
					{
						Assert.assertTrue(false);
					}
				}
				if(exp.equalsIgnoreCase("Invalid"))
				{
					if(targetpage==true)
					{
						macc.clickLogout();
						Assert.assertTrue(false);
					}
					else
					{
						Assert.assertTrue(true);
					}
				}
		      }                     //try block close
		      
		      catch (Exception e)
		      {
				Assert.fail();
			}
		      Thread.sleep(5000);
				logger.info("***********Finishing TC_003_LoginDDT*******");
				
	}	
	

}
