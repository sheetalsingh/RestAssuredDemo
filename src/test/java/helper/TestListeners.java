package helper;

import java.util.Iterator;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListeners implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		//result.getTestContext().getSkippedTests().removeResult(result);
		
		
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Remove all test from report which have the 'disabled' attribute 
	 * This is working for testng report that too if run using testng.xml
	 * Not working for allure reporting
	 */
	@Override
	public void onFinish(ITestContext context) {
		Iterator<ITestResult> iterator = context.getSkippedTests().getAllResults().iterator();
        while (iterator.hasNext()) {
            ITestResult result = iterator.next();
            if (Boolean.parseBoolean(result.getAttribute("disabled").toString())) {
                iterator.remove();  
            }
        }
	}

}
