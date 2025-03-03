package sandeepacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import sandeepacademy.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents {
	
	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	WebDriver driver;
	
//	WebElement ele= driver.findElement(By.id("userEmail"));
	@FindBy(id="userEmail")
	WebElement username;
	
	@FindBy(id="userPassword")
	WebElement userpassword;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	public ProductCatalogue login(String name,String password)
	{
//		waitForWebElementToAppear(userpassword);
		username.sendKeys(name);
		userpassword.sendKeys(password);
		submit.click();
		return new ProductCatalogue(driver);
	}
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	public String getErrorMessage()
	{
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
	
	
	
	
	

}
