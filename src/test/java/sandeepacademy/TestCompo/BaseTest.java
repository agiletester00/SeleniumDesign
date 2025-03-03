package sandeepacademy.TestCompo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import sandeepacademy.pageobjects.LandingPage;

public class BaseTest {
	public WebDriver driver;
	public LandingPage landingPage;
	
	public WebDriver initializeDriver() throws IOException
	{
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\sandeepacademy\\resources\\GlobalData.properties");
		prop.load(fis);
		
		String browserName= System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
		
//		prop.getProperty("browser");
		if (browserName.contains("Chrome"))
		{
			ChromeOptions options =new ChromeOptions();
			if(browserName.contains("headless"))
			{
				options.addArguments("headless");
			}
//			System.setProperty("webdriver.chrome.driver","c:/WEBDRIVERS/chromedriver.exe");
			driver=new ChromeDriver(options);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		else if(browserName.contains("Firefox"))
		{
			//firefox
			
			FirefoxOptions options =new FirefoxOptions();
			if(browserName.contains("headless"))
			{
				options.addArguments("--headless");
			}
			
			driver=new FirefoxDriver(options);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		else if(browserName.equalsIgnoreCase("edge"))
		{
			//edge
		}
		return driver;
	}
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
	{
		String jsonContent=FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
		
		ObjectMapper mapper=new ObjectMapper();
		
		List<HashMap<String,String>> data=mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>() {
		});
		return data;
		
	}
	
	public String getScreenShot(String testCaseName,WebDriver driver) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File path=new File(System.getProperty("user.dir")+"//screenshots//"+testCaseName+".png");
		FileUtils.copyFile(source,path);
		return System.getProperty("user.dir")+"//screenshots//"+testCaseName+".png";
		
	}
	
	@BeforeMethod(alwaysRun = true)
	public LandingPage lunchApplication() throws IOException
	{
		driver=initializeDriver();
		landingPage=new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
		
	}
	@AfterMethod
	public void tearDown()
	{
//		driver.manage().deleteAllCookies();
		driver.quit();
	}
}
