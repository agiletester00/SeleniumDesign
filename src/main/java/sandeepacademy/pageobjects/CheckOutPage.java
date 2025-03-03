package sandeepacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import sandeepacademy.AbstractComponents.AbstractComponents;

public class CheckOutPage extends AbstractComponents {
	
	WebDriver driver;
	public CheckOutPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(css=".actions a")
	WebElement submit;
	
	@FindBy(css=".ta-results button")
	List<WebElement> cartProducts;
	
//	driver.findElement(By.cssSelector(".actions a")).click();
	
	@FindBy(css=".actions a")
	WebElement placeOrder;
	
	By cartprods=By.cssSelector(".ta-results button");
	
	
	
	
	public void selectCountry(String countryName)
	{
		country.sendKeys(countryName);
		waitForElementToAppear(cartprods);
		WebElement country=cartProducts.stream().filter(cProduct->cProduct.findElement(By.cssSelector("span")).getText().equalsIgnoreCase("India")).findFirst().orElse(null);
		country.click();		
	}
	
	public ConfirmationPage submitOrder()
	{
		placeOrder.click();
		return new ConfirmationPage(driver);
		
	}
	

}
