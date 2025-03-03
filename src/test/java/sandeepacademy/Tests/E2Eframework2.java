package sandeepacademy.Tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import sandeepacademy.TestCompo.BaseTest;
import sandeepacademy.pageobjects.CartPage;
import sandeepacademy.pageobjects.CheckOutPage;
import sandeepacademy.pageobjects.ConfirmationPage;
import sandeepacademy.pageobjects.ProductCatalogue;

public class E2Eframework2  extends BaseTest {
	
	@Test
	public void E2EDemo() throws InterruptedException, IOException
	{
		String productName="ZARA COAT 3";
		ProductCatalogue productCatalogue=landingPage.login("example987@gmail.com","Apple@123");
		productCatalogue.addProductToCart(productName);
		CartPage cartPage=productCatalogue.goToCartPage();
		Boolean match=cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckOutPage checkOutPage=cartPage.goToCheckOut();
		checkOutPage.selectCountry("India");
		ConfirmationPage confirmationPage=checkOutPage.submitOrder();
		String message=confirmationPage.getConfirmationMessaage();
		Assert.assertTrue(message.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
					
		
	// 1. Login to the application
	// 2. Add a product to the cart
	// 3. Go to the cart page
	// 4. Verify the product is displayed in the cart
	// 5. Go to the checkout page
	// 6. Select the country
	// 7. Submit the order
	// 8. Verify the confirmation message
	// 9. Logout
	// 10. Close the browser
		
	// This is a add in branch AB-1234
		
	}

}
