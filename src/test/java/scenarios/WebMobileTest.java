package scenarios;


import data.DataProviders;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import setup.BaseTest;

public class WebMobileTest extends BaseTest {

    @Test(groups = {"web"}, description = "Google search test",
          dataProvider = "WebTestDataProvider", dataProviderClass = DataProviders.class)
    public void webSearchTest(String appType, String url, String pageTitle, String searchWord) throws Exception {

        getDriver().get(url);

        new WebDriverWait(getDriver(), 20).until(
            wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));

        assert ((WebDriver) getDriver()).getTitle().equals(pageTitle) : "This is not Google page";

        setPageObject(appType, getDriver());

        getPo().getWelement("googleSearchField").sendKeys(searchWord, Keys.ENTER);

        System.out.println(getPo().getWelements("googleSearchResults").get(0).getText());

        assert (getPo().getWelements("googleSearchResults").get(0).getText().contains(searchWord));

        System.out.println("Google search is done");
    }
}
