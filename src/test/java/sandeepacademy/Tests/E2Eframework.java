package sandeepacademy.Tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import sandeepacademy.TestCompo.BaseTest;
import sandeepacademy.pageobjects.CartPage;
import sandeepacademy.pageobjects.CheckOutPage;
import sandeepacademy.pageobjects.ConfirmationPage;
import sandeepacademy.pageobjects.ProductCatalogue;

public class E2Eframework  extends BaseTest {
	
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
			
	}

}
