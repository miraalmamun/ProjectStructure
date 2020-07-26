package dependency;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PurchaseOnline {
	
	
	@Test
	public void searchProd() {
		Assert.fail();
	}
	
	
	@Test(dependsOnMethods = {"searchProd"})
	public void buyProd() {
		
	}
	
	@Test(dependsOnMethods = {"buyProd"})
	public void confirmEmail() {
		
	}

}
