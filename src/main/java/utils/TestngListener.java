package utils;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

public class TestngListener implements ISuiteListener, ITestListener, IInvokedMethodListener {

    ////////////////////////////////////////////////////
    ///////////////// ISuiteListener //////////////////
    //////////////////////////////////////////////////
    @Override
    public void onStart(ISuite suite) {
	System.out.println("\n" + "**********************************************");
	System.out.println("Starting Test Suite; By Mahmoud El-Sharkawy! *");
	System.out.println("**********************************************" + "\n");
	ExtentReport.initReports();
    }

    @Override
    public void onFinish(ISuite suite) {
	System.out.println("\n" + "**********************************************");
	System.out.println("Finished Test Suite; By Mahmoud El-Sharkawy! *");
	System.out.println("**********************************************" + "\n");
	ExtentReport.flushReports();
    }

    ///////////////////////////////////////////////////
    ///////////////// ITestListener //////////////////
    /////////////////////////////////////////////////
    @Override
    public void onStart(ITestContext context) {
	System.out.println("\n" + "**************************************************** " + "Test: ["
		+ context.getName() + "] Started" + " ****************************************************" + "\n");
    }

    @Override
    public void onFinish(ITestContext context) {
	System.out.println("\n" + "**************************************************** " + "Test: ["
		+ context.getName() + "] Finished" + " ****************************************************" + "\n");
    }

    @Override
    public void onTestStart(ITestResult result) {
//	ExtentReport.createTest(result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
	ExtentReport.pass(result.getMethod().getMethodName() + " is Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
	ExtentReport.fail(result.getMethod().getMethodName() + " is Failed");
	if (result.getThrowable() != null) {
	    ExtentReport.fail(result.getThrowable());
	}
    }

    @Override
    public void onTestSkipped(ITestResult result) {
	ExtentReport.skip(result.getMethod().getMethodName() + " is Skipped");
	if (result.getThrowable() != null) {
	    ExtentReport.skip(result.getThrowable());
	}
    }

    ////////////////////////////////////////////////////////////
    ///////////////// IInvokedMethodListener //////////////////
    //////////////////////////////////////////////////////////
    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
	ITestNGMethod testMethod = method.getTestMethod();
	if (testMethod.getDescription() != null && !testMethod.getDescription().equals("")) {
	    ExtentReport.createTest(testMethod.getDescription());
	} else {
	    ExtentReport.createTest(testResult.getName());
	}
	System.out.println("\n" + "*******************************************");
	if (method.isConfigurationMethod()) {
	    System.out.println("Starting Configuration Method (Setup or Teardown): [" + testResult.getName() + "]");
	    ExtentReport.removeTest(testResult.getName());
	} else {
	    System.out.println("Starting Test Case: [" + testResult.getName() + "]");
	}
	System.out.println("*******************************************" + "\n");
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
	System.out.println("\n" + "*******************************************");
	if (method.isConfigurationMethod()) {
	    System.out.println("Finished Configuration Method (Setup or Teardown): [" + testResult.getName() + "]");
	} else {
	    System.out.println("Finished Test Case: [" + testResult.getName() + "]");
	}
	System.out.println("*******************************************" + "\n");
    }

}
