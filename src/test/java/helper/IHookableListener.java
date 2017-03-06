package helper;

import java.util.Arrays;
import java.util.Map;

import org.testng.IHookCallBack;
import org.testng.IHookable;
import org.testng.ITestResult;
import org.testng.SkipException;

public class IHookableListener implements IHookable {

	
	ReadPropertyFile readPropFile;
	Map<String, String> propertyMap; 
	String whatToRun;
	
	
	public IHookableListener() {
		readPropFile = new ReadPropertyFile();
		propertyMap = readPropFile.getMap();
		whatToRun = propertyMap.get("whattorun");	
	}
	
	
	
	/**
	 * This method will read all data provider values one by one and 
	 * for all data who we don't want to run we set a attribute as 'disabled' with that test run
	 * 
	 * This set attribute will be further used in ITestListeners in testFinish() method,
	 * where we remove all such methods from reporting
	 * 
	 * 
	 * 
	 * Read all data provider values using getParameters() method
	 * parms[2]: represent suite value in csv data file
	 * parms[0]: represent testcaseid value in csv data file
	 */
	@Override
	public void run(IHookCallBack callBack, ITestResult testResult) {
		Object[] parms = callBack.getParameters();
		
		if(whatToRun.equals("suite")){
			String [] testsuitestorun = propertyMap.get("testsuitestorun").split(",");
			boolean isRunSuitePresent = Arrays.asList(testsuitestorun).contains(parms[2]);
			
			if(isRunSuitePresent){
				callBack.runTestMethod(testResult);
			}else{
				testResult.setAttribute("disabled", true); 
				throw new SkipException("skipping test");
			}
		}else if (whatToRun.equals("testids")) {
			String [] testidstorun = propertyMap.get("testidstorun").split(",");
			boolean isRunTestIdPresent = Arrays.asList(testidstorun).contains(parms[0]);
			
			if(isRunTestIdPresent){
				callBack.runTestMethod(testResult);
			}else{
				testResult.setAttribute("disabled", true);
				throw new SkipException("skipping test");
			}
			
		}

	}
}
