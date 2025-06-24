package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

 
public class MyAccountPage extends BasePage {
	
public MyAccountPage (WebDriver driver)
{
	super(driver);
}

//MyAccount Page Heading

 @FindBy(xpath = "//h2[normalize-space()='My Account']") 
  WebElement msgHeading;

 //Logout link
 @FindBy(xpath = "//div[@class='list-group']//a[text()='Logout']")    //added step no 6
 WebElement lnkLogout;
 
 
//Action Method
 
 public boolean isMyAccountPageExists()
 {
	 try 
	 {
		return (msgHeading.isDisplayed());
	 }
	 catch (Exception e)
	 {
		return false;
	}
 }

 public void clickLogout()
 {
	 lnkLogout.click();
 }
 

}
