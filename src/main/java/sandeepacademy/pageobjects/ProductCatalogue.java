package sandeepacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import sandeepacademy.AbstractComponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents {
	
	public ProductCatalogue(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	WebDriver driver;
	
//	List<WebElement> elements=driver.findElements(By.cssSelector(".row .mb-3"));
	@FindBy(css=".row .mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	By productsBy=By.cssSelector(".row .mb-3");
	By addToCart=By.cssSelector(".card-body button:last-of-type");
	By toastMessage=By.cssSelector("#toast-container");
	
	public List<WebElement> getProductList()
	{
		waitForElementToAppear(productsBy);
		return products;
	}
	public WebElement getProductByName(String productName)
	{
		WebElement element=products.stream().filter(item->item.findElement(By.cssSelector("b"))
				.getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
		return element;
	}
	public void addProductToCart(String productName) throws InterruptedException
	{
		WebElement element=getProductByName(productName);
		element.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDissappear(spinner);
		
	}
	
	
	
	
	

}
