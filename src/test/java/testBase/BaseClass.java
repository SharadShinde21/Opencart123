package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.checkerframework.checker.units.qual.degrees;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
public class BaseClass {
	
public static WebDriver driver;
public Logger logger;
public Properties p;


	@BeforeClass(groups = {"Sanity","Regression","Master"})
	@Parameters({"os","browser"})
	public void setup(String os,String br) throws IOException
	{
		//Loading config.properties file
		FileReader file=new FileReader("./src//test//resources/config.properties");
		p=new Properties();
		p.load(file);
		
		
		//Logger Method
		logger=(Logger) LogManager.getLogger(this.getClass());
		
		
		
		// Grid setup:
		
		//1.By using Remote exeution(selenium Grid)used 
		//execution_env=remote
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities capabilities=new DesiredCapabilities();
			
			//os
			if(os.equalsIgnoreCase("window"))
			{
				capabilities.setPlatform(Platform.WIN10);
			}
			else if(os.equalsIgnoreCase("mac"))
			{
				capabilities.setPlatform(Platform.MAC);
			}
			else
			{
				System.out.println("Not Matching os:");
				return;
			}
			
			//browser
			switch (br.toLowerCase())
			{
			case "chrome":capabilities.setBrowserName("chrome");break;
			case "edge":capabilities.setBrowserName("MicrosoftEdge");break;
			default:System.out.println("No Browser Matching");
				return;
			}
			driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
		}
		
		
		//2. Local Enviroment use by with selenim grid concept
		//execution_env=local
		
		if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		{
			switch (br.toLowerCase())
			{
			case "chrome":driver=new ChromeDriver(); break;
			case "edge":driver=new EdgeDriver(); break;
			case "firefox":driver=new FirefoxDriver(); break;
			default: System.out.println("Invalid browser name...");return;
			}
		}
	
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//Reading url from properties file.
		driver.get(p.getProperty("appURL2"));  
		
		//driver.get("http://localhost/opencart/upload/index.php");
		driver.manage().window().maximize();
		
	}
	
	@AfterClass(groups = {"Sanity","Regression","Master"})
	public void teardown()
	{
		driver.quit();
	}
	

	//This method use by TC001 Test 

	public String randomString()              //string use by name ,email
	{
		String generatedstring =RandomStringUtils.randomAlphabetic(5);
	    return generatedstring;
	}
	
	
	public String randomNumber()              //string use by number telephone
	{
		String generatednumber =RandomStringUtils.randomNumeric(10);
	    return generatednumber;
	}
	
	
	public String randomAlphaNumberic()              //string use by password number+string
	{
		String generatedstring =RandomStringUtils.randomAlphabetic(3);
		String generatednumber =RandomStringUtils.randomNumeric(3);
	    return (generatednumber+"@"+generatedstring);
	}
	
	
	public String captureScreen(String tname) throws IOException
	{
		String timeStamp=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot takesScreenshot=(TakesScreenshot)driver;
		File sourceFile=takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\"+tname+"_"+timeStamp+ ".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
		
		return targetFilePath;
		
	}
	   

}
