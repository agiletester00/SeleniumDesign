package sandeepacademy.TestCompo;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryDemo implements IRetryAnalyzer {

	int count=0;
	int maxtry=1;
	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		
		if(count<maxtry)
		{
			count++;
			return true;
			
		}
		else
		return false;
	}

}
