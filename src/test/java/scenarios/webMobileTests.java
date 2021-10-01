package scenarios;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import setup.BaseTest;

public class webMobileTests extends BaseTest {

    AppiumDriver<WebElement> appiumDriver;

    public void searchKeyword() {
        appiumDriver.findElementsByXPath("//*[@name='q']").stream().map(WebElement::sendKeys).toString()
    }

    @Test(groups = {"web"}, description = "Make sure that we've opened IANA homepage")
    public void simpleWebTest()
        throws NoSuchFieldException, IllegalAccessException, InstantiationException, InterruptedException {

        final String googleUrl = "https://www.google.com/";

        final String searchData = "EPAM";

        getDriver().get(googleUrl); // open IANA homepage

        // Make sure that page has been loaded completely
        new WebDriverWait(getDriver(), 10).until(
            wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete")
        );

        getDriver().findElementByXPath("//*[@name='q']").sendKeys(searchData);

        getDriver().findElementByXPath().submit();

//        getPo().getWelement("googleSearchField").sendKeys(searchData);

        Thread.sleep(2000);

        //        getPo().getWelement("googleSearchField").sendKeys(searchData);

        // Check IANA homepage title
        //        assert ((WebDriver) getDriver()).getTitle().equals("Internet Assigned Numbers Authority") : "This is not IANA homepage";

        // Log that test finished
        System.out.println("Site opening done");
    }
}
