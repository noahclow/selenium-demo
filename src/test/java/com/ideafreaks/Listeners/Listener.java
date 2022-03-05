package com.ideafreaks.Listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import resources.Base;
import resources.ExtentReporterNG;

import java.io.IOException;

public class Listener extends Base implements ITestListener {
    public static Logger log = LogManager.getLogger(Base.class.getName());

    ExtentTest test;
    ExtentReports extent= ExtentReporterNG.getReportObject();
    ThreadLocal<ExtentTest> extentTest =new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        log.info("Start test " + result.getName());
        test= extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("Passed test " + result.getName());
        extentTest.get().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.error("Failed test '" + result.getName() + "' from '" + (result.getMethod().getTestClass()) + "' screenshot captured!");
        extentTest.get().fail(result.getThrowable());
        WebDriver driver = null;

        String testMethodName = result.getMethod().getMethodName();

        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            extentTest.get().addScreenCaptureFromPath(getScreenShotPath(testMethodName,driver), result.getMethod().getMethodName());
            //getScreenShotPath(testMethodName, driver);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.info("Skipped test " + result.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onStart(ITestContext context) {
        log.info("Start test execution " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        log.info("Finish test execution " + context.getName());
        extent.flush();
    }
}
