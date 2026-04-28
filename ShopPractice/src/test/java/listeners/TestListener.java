package listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ScreenshotUtil;
import base.BaseTest;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {

        Object currentClass = result.getInstance();
        BaseTest base = (BaseTest) currentClass;

        ScreenshotUtil.captureScreenshot(
                base.getDriver(),
                result.getName()
        );
    }
}