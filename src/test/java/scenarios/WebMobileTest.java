package scenarios;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import setup.BaseTest;
import setup.DataProviders;

import static org.testng.Assert.assertTrue;

import java.util.Objects;

public class WebMobileTest extends BaseTest {

    @Test(groups = {"web"}, description = "Google search test",
          dataProvider = "WebTestDataProvider", dataProviderClass = DataProviders.class)
    public void webSearchTest(String url, String pageTitle, String searchWord) throws Exception {

        getDriver().get(url);

        new WebDriverWait(getDriver(), 20).until(
            wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));

        assert ((WebDriver) getDriver()).getTitle().equals(pageTitle) : "This is not Google page";

        getPo().getWelement("googleSearchField").sendKeys(searchWord, Keys.ENTER);

        assertTrue(getPo().getWelements("searchResults").stream()
                            .map(WebElement::getText)
                            .filter(Objects::nonNull)
                            .map(String::trim)
                            .anyMatch(text -> text.toLowerCase().contains(searchWord.toLowerCase())));

        System.out.println("Google search is done");
    }
}
