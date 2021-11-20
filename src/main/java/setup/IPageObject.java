package setup;

import java.util.List;
import org.openqa.selenium.WebElement;

public interface IPageObject {

    WebElement getWebElement(String weName) throws NoSuchFieldException, IllegalAccessException, InstantiationException;

    List<WebElement> getWebElements(String weNames) throws NoSuchFieldException,
        IllegalAccessException, InstantiationException;

}
