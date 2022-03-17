package framework.utils.listeners;

import lombok.SneakyThrows;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import framework.utils.interfaces.TestTemplate;

import static framework.reporting.ExtentTestFactory.getTest;

public class TestNGListener extends TestTemplate implements ITestListener {


    public TestNGListener() {
        super();
    }


    @Override
    public void onTestStart(ITestResult iTestResult) {

    }

    @SneakyThrows
    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        getTest().info(addScreenshot());
    }

    @SneakyThrows
    @Override
    public void onTestFailure(ITestResult result) {
        getTest().fail("Test Case failed").info(addScreenshot()).fail(result.getThrowable());
    }


    @SneakyThrows
    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        getTest().fail("Test Case skipped").info(addScreenshot()).info(iTestResult.getThrowable());
    }

    @SneakyThrows
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        getTest().info(addScreenshot());
    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }
}