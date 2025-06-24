package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage
{
	public LoginPage(WebDriver driver)
	{
		super(driver);
	}
	
	//Login page link
	@FindBy(xpath = "//input[@id='input-email']")
	WebElement txtmailAddress;
	
	@FindBy(xpath = "//input[@id='input-password']")
	WebElement txtPassword;
	
	@FindBy(xpath = "//input[@value='Login']")
	WebElement btnLogin;
	
	//Login page Method
	public void setEmail(String email)
	{
		txtmailAddress.sendKeys(email);
	}
	
	public void setPassword(String pwd)
	{
		txtPassword.sendKeys(pwd);
	}
	
	public void clickLogin()
	{
		btnLogin.click();
	}
	

}












