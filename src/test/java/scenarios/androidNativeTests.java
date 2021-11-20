package scenarios;

import org.testng.annotations.Test;
import setup.BaseTest;
import setup.DataProviders;

public class androidNativeTests extends BaseTest {

    @Test(groups = {"native"}, description = "Register to account test",
          dataProvider = "NativeTestDataProvider", dataProviderClass = DataProviders.class)
    public void registerToAccountTest(String email, String username, String password, String pageTitle) throws
        IllegalAccessException, NoSuchFieldException, InstantiationException {

        getPo().getWebElement("registrationButton").click();

        getPo().getWebElement("registrationEmail").sendKeys(email);

        getPo().getWebElement("registrationUserName").sendKeys(username);

        getPo().getWebElement("registrationPassword").sendKeys(password);

        getPo().getWebElement("registrationConfirmPassword").sendKeys(password);

        getPo().getWebElement("registrationNewAccountButton").click();

        System.out.println("Registration is done");

        getPo().getWebElement("loginEmailField").sendKeys(email);

        getPo().getWebElement("passwordField").sendKeys(password);

        getPo().getWebElement("signInBtn").click();

        System.out.println("Sign in is done");

        assert (getPo().getWebElement("pageTitle").getText().equals(pageTitle));

        System.out.println("Assertion is done");
    }
}
