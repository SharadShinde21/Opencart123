package testCases;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

//-------------------------------BaseClass Parent of the All class---------------------
public class TC001_AccountRegistrationTest  extends BaseClass {  
	@Test(groups = {"Regression","Master"})
	public void verify_account_registration()
	{
		logger.info("***** Starting TC001_AccountRegistrationTest ***** "); //Log Method
		
		try {
			
	
		//*****Home Page****
		HomePage hp=new HomePage(driver);    //Home Page class methods
		hp.clickMyAccount();
		logger.info("Clicked on my Account Link");
		hp.clickRegister();
		logger.info("Clicked on my Register Link");
		
		
		//******Account Registration Page*******
		AccountRegistrationPage repage=new AccountRegistrationPage(driver); //Account registarionpage method
		logger.info("Providing Customer Details");
		
		repage.SetFirstName(randomString().toUpperCase());      //randomly generated the firstname
		repage.SetLastName(randomString().toUpperCase());       //randomly generated the lastname
		repage.SetEmail(randomString()+"@gmail.com");           //randomly generated the email.
		
		String password=randomAlphaNumberic();
		
		repage.SetPassword(password);             //randamly generated the number+charadter
		repage.SetConfirmPassword(password);
		
		repage.SetTelephone(randomNumber());                  //randamly generated the telepnone number
		repage.SetPrivacyPolicy();
		repage.clickContinue();
		
		logger.info("Validating Expected Message...");
		String confmsg=repage.getConfirmationMsg();
		if(confmsg.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}
		else {
			logger.error("Failed Test...");
			logger.debug("Debug logs...");
			Assert.assertTrue(false);
		}
		
		  //Assert.assertEquals(confmsg,"Your Account Has Been Created!");
	  }
		
		catch (Exception e) {
			
			Assert.fail();
		}
		
		logger.info("***** Finished TC001_AccountRegistrationTest ***** "); //Log Method
	
	}
	
}
