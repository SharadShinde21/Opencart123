package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {                    //base page is parent of all classes
	  WebDriver driver;
	  
	  public BasePage(WebDriver driver)
	  {
		  this.driver=driver;
		  PageFactory.initElements(driver, this);
	  }

}
