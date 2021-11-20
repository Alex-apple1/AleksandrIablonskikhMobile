package scenarios;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import setup.BaseTest;
import setup.DataProviders;

public class iOSWebTests extends BaseTest {

    @Test(groups = {"web"}, description = "Google search test",
          dataProvider = "WebTestDataProvider", dataProviderClass = DataProviders.class)
    public void webSearchTest(String url, String pageTitle, String searchWord) throws Exception {

        getDriver().get(url);

        new WebDriverWait(getDriver(), 50).until(
            wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));

        assert ((WebDriver) getDriver()).getTitle().equals(pageTitle) : "This is not Google page";

        getPo().getWebElement("googleSearchField").sendKeys(searchWord);
        getPo().getWebElement("googleSearchField").submit();

        new WebDriverWait(getDriver(), 50).until(
            wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));

        assert (getPo().getWebElements("searchResults").get(1).getText().contains(searchWord));

        System.out.println("Google search is done");

    }

}
