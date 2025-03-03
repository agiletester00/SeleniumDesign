package sandeepacademy.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.*;

import sandeepacademy.TestCompo.BaseTest;
import sandeepacademy.TestCompo.RetryDemo;
import sandeepacademy.pageobjects.CartPage;
import sandeepacademy.pageobjects.CheckOutPage;
import sandeepacademy.pageobjects.ConfirmationPage;
import sandeepacademy.pageobjects.ProductCatalogue;

public class ErrorValidations  extends BaseTest {
	
	
	@Test(groups= "Purchase", dataProvider = "getData")
	public void E2EDemo2(HashMap<String,String> input) throws InterruptedException, IOException
	{
//		String productName="ZARA COAT 3";
		ProductCatalogue productCatalogue=landingPage.login(input.get("email"),input.get("password"));
		productCatalogue.addProductToCart(input.get("productName"));
		CartPage cartPage=productCatalogue.goToCartPage();
		Boolean match=cartPage.verifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match);
		CheckOutPage checkOutPage=cartPage.goToCheckOut();
		checkOutPage.selectCountry("India");
		ConfirmationPage confirmationPage=checkOutPage.submitOrder();
		String message=confirmationPage.getConfirmationMessaage();
		Assert.assertTrue(message.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
			
	}
	
	@Test(retryAnalyzer = RetryDemo.class)
	public void LoginErrorValidation() throws InterruptedException, IOException
	{
		
		landingPage.login("example987@gmail.com","Apple123");
		String message=landingPage.getErrorMessage();
		Assert.assertEquals(message,"Incorrect email or password.");
		
			
	}
//	
//	@DataProvider
//	public Object[][] getData()
//	{
//		return new Object[][]{{"example987@gmail.com","Apple@123","ZARA COAT 3"},{"agiletester00@gmail.com","Red@rose1","ADIDAS ORIGINAL"}};
//	}

	
	@DataProvider
	public Object[][] getData() throws IOException
	{
//		HashMap<String,String> map=new HashMap<String,String>();
//		map.put("email", "example987@gmail.com");
//		map.put("password", "Apple@123");
//		map.put("productName", "ZARA COAT 3");
//		
//		HashMap<String,String> map1=new HashMap<String,String>();
//		map1.put("email", "agiletester00@gmail.com");
//		map1.put("password", "Red@rose1");
//		map1.put("productName", "ADIDAS ORIGINAL");
		List<HashMap<String, String>> data =getJsonDataToMap(System.getProperty("user.dir")+"/src/test/java/sandeepacademy/data/PO.json");
		return new Object[][]{{data.get(0)},{data.get(1)}};
		
	}


	

}
