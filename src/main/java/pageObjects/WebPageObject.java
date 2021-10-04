package pageObjects;

import io.appium.java_client.AppiumDriver;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WebPageObject  {

    @FindBy(xpath = "//*[@name='q']")
    WebElement googleSearchField;
    @FindBy(xpath = "//div[@id='rso']/div")
    List<WebElement> googleSearchResults;


    public WebPageObject(AppiumDriver appiumDriver) {
        PageFactory.initElements(appiumDriver, this);

    }
}
