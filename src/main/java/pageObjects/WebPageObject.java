package pageObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class WebPageObject  {

    final String searchData = "EPAM";

    @AndroidFindBy(xpath = "//*[@name='q']")
    WebElement googleSearchField;
    @AndroidFindBy(xpath = "//*[@id='input']")
    WebElement googleSearchButton;


    public WebPageObject(AppiumDriver appiumDriver) {
        PageFactory.initElements(appiumDriver, this);

    }
}
