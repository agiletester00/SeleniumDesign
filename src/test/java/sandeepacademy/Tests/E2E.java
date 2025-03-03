package sandeepacademy.Tests;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import sandeepacademy.pageobjects.LandingPage;

public class E2E {
	
	@Test
	public void E2EDemo() throws InterruptedException
	{
		String productName="ZARA COAT 3";
		System.setProperty("webdriver.chrome.driver","c:/WEBDRIVERS/chromedriver.exe");
		
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		driver.get("https://rahulshettyacademy.com/client");
//		driver.findElement(By.id("userEmail")).sendKeys("example987@gmail.com");
//		driver.findElement(By.id("userPassword")).sendKeys("Apple@123");
//		driver.findElement(By.id("login")).click();
		LandingPage landingPage=new LandingPage(driver);
		landingPage.login("example987@gmail.com","Apple@123");
		
		List<WebElement> elements=driver.findElements(By.cssSelector(".row .mb-3"));
		
		WebElement element=elements.stream().filter(item->item.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
		
//		System.out.println(element.isPresent());
//		System.out.println(element.size());
		element.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		

		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		WebElement cart=driver.findElement(By.cssSelector("[routerlink*='cart']"));
		wait.until(ExpectedConditions.elementToBeClickable(cart));

		cart.click();
		
		List<WebElement> cartProducts=driver.findElements(By.cssSelector(".cartSection h3"));
		boolean match=cartProducts.stream().anyMatch(item->item.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector(".totalRow button")).click();
		driver.findElement(By.cssSelector("[placeholder='Select Country']")).sendKeys("India");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results button")));
		
		List<WebElement> cartProduct= driver.findElements(By.cssSelector(".ta-results button"));
		WebElement e=cartProduct.stream().filter(cProduct->cProduct.findElement(By.cssSelector("span")).getText().equalsIgnoreCase("India")).findFirst().orElse(null);
		e.click();
		driver.findElement(By.cssSelector(".actions a")).click();
		String message=driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(message.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		
		
		
		
	
		
		
		
	}

}
