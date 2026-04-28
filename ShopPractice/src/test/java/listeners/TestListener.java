package listeners;

import org.testng.*;
import utils.ExtentManager;
import utils.ScreenshotUtil;

import com.aventstack.extentreports.*;

public class TestListener implements ITestListener {

    ExtentReports extent = ExtentManager.getReport();
    ExtentTest test;

    @Override
    public void onTestStart(ITestResult result) {

        test = extent.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        test.pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        test.fail(result.getThrowable());

        String path = ScreenshotUtil.captureScreenshot(
                base.BaseTest.getDriver(),
                result.getMethod().getMethodName()
        );

        test.addScreenCaptureFromPath(path);
    }

    @Override
    public void onFinish(ITestContext context) {

        extent.flush();
    }
}