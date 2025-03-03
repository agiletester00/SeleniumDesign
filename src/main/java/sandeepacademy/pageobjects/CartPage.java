package sandeepacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import sandeepacademy.AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents {
	
	public CartPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	WebDriver driver;
	
	@FindBy(css=".cartSection h3")
	List<WebElement> productsTitles;
	

	
	@FindBy(css=".totalRow button")
	WebElement checkOut;
	
	public boolean verifyProductDisplay(String productName)
	{

		boolean match=productsTitles.stream().anyMatch(item->item.getText().equalsIgnoreCase(productName));
		return match;
		
	}
	public CheckOutPage goToCheckOut()
	{
		checkOut.click();
		return new CheckOutPage(driver);
		
	}
	
	
	
	
	
	

}
