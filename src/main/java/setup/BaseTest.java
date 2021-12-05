package setup;

import static java.lang.String.format;

import io.appium.java_client.AppiumDriver;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
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
    String tokenEncoded;

    @Override
    public AppiumDriver getDriver() {
        return appiumDriver;
    }

    public IPageObject getPo() {
        return po;
    }

    @Parameters({"platformName", "appType", "deviceName", "udid", "browserName",
        "app", "appPackage", "appActivity", "bundleId"})
    @BeforeSuite(alwaysRun = true)
    public void setUp(@Optional("") String platformName,
                      @Optional("") String appType,
                      @Optional("") String deviceName,
                      @Optional("") String udid,
                      @Optional("") String browserName,
                      @Optional("") String app,
                      @Optional("") String appPackage,
                      @Optional("") String appActivity,
                      @Optional("") String bundleId
    ) throws Exception {
        System.out.println("Before: app type - " + appType);
        Properties props = new PropertyReader().readPropertiesFromFile("test.properties");
        tokenEncoded = URLEncoder
            .encode(props.getProperty("apiKey"), StandardCharsets.UTF_8.toString());
        setAppiumDriver(platformName, deviceName, udid, browserName, app,
            appPackage, appActivity, bundleId);
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
                                 String bundleId
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

        Properties props = new PropertyReader().readPropertiesFromFile("test.properties");
        tokenEncoded = URLEncoder
            .encode(props.getProperty("apiKey"), StandardCharsets.UTF_8.name());

        try {
            //            appiumDriver = new AppiumDriver<>(new URL(System.getProperty("ts.appium")), capabilities);
            appiumDriver = new AppiumDriver<>(new URL(
                format("https://EPM-TSTF:%s@mobilecloud.epam.com/wd/hub", tokenEncoded)), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        appiumDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    protected void setPageObject(String appType, AppiumDriver appiumDriver) throws Exception {
        po = new PageObject(appType, appiumDriver);
    }
}
