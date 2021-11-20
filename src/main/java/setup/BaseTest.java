package setup;

import static java.lang.String.format;

import io.appium.java_client.AppiumDriver;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import pageObjects.PageObject;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import utils.PropertyReader;

public class BaseTest implements IDriver {

    private static AppiumDriver appiumDriver; // singleton
    IPageObject po;
    protected static String API_KEY;

    @Override
    public AppiumDriver getDriver() {
        return appiumDriver;
    }

    public IPageObject getPo() {
        return po;
    }

    @Parameters({"platformName", "appType", "deviceName", "udid", "browserName",
        "app", "appPackage", "appActivity", "bundleId", "cloud"})
    @BeforeSuite(alwaysRun = true)
    public void setUp(String platformName,
                      String appType,
                      @Optional("") String deviceName,
                      @Optional("") String udid,
                      @Optional("") String browserName,
                      @Optional("") String app,
                      @Optional("") String appPackage,
                      @Optional("") String appActivity,
                      @Optional("") String bundleId,
                      @Optional("") boolean cloud
    ) throws Exception {
        System.out.println("Before: app type - " + appType);
        setAppiumDriver(platformName, deviceName, udid, browserName, app,
            appPackage, appActivity, bundleId, cloud);
        setPageObject(appType, appiumDriver);
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        System.out.println("After");
        appiumDriver.closeApp();
    }

    private void setAppiumDriver(String platformName,
                                 String deviceName,
                                 String udid,
                                 String browserName,
                                 String app,
                                 String appPackage,
                                 String appActivity,
                                 String bundleId,
                                 boolean cloud
    ) throws IOException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        //mandatory Android capabilities
        capabilities.setCapability("platformName", platformName);
        capabilities.setCapability("deviceName", deviceName);
        capabilities.setCapability("udid", udid);

        if (app.endsWith(".apk")) {
            capabilities.setCapability("app", (new File(app)).getAbsolutePath());
        }

        capabilities.setCapability("browserName", browserName);
        capabilities.setCapability("chromedriverDisableBuildCheck", "true");

        capabilities.setCapability("appPackage", appPackage);
        capabilities.setCapability("appActivity", appActivity);

        capabilities.setCapability("bundleId", bundleId);

        //        if(platformName.equals("iOS")) capabilities.setCapability("automationName","XCUITest");

        //        Properties props = new PropertyReader().readPropertiesFromFile("test.properties");
        //        API_KEY = URLEncoder.encode(props.getProperty("apiKey"), StandardCharsets.UTF_8.name());
        //
        //        try {
        //            appiumDriver = new AppiumDriver(
        //                //                new URL(format("https://EPM-TSTF:%s@mobilecloud.epam.com/wd/hub", API_KEY)), capabilities);
        //                new URL("https://EPM-TSTF:"
        //                    + API_KEY + "@mobilecloud.epam.com/wd/hub"), capabilities);
        //            //            appiumDriver = new AppiumDriver<>(new URL(System.getProperty("ts.appium")), capabilities);
        //        } catch (MalformedURLException e) {
        //            e.printStackTrace();
        //        }
        if (cloud) {
            try {
                String tokenEncoded = java.net.URLEncoder
                    .encode(System.getProperty("token"), StandardCharsets.UTF_8.name());
                appiumDriver = new AppiumDriver<>(new URL("https://EPM-TSTF:"
                    + tokenEncoded + "@mobilecloud.epam.com/wd/hub"), capabilities);
            } catch (MalformedURLException | UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } else {
            try {
                appiumDriver = new AppiumDriver<>(new URL("http://localhost:4723/wd/hub"), capabilities);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        // Timeouts tuning
        appiumDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    protected void setPageObject(String appType, AppiumDriver appiumDriver) throws Exception {
        po = new PageObject(appType, appiumDriver);
    }
}
