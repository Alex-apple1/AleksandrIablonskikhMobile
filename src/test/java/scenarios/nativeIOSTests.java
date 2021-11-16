package scenarios;

import org.testng.annotations.Test;
import setup.BaseTest;
import setup.DataProviders;

public class nativeIOSTests extends BaseTest {
    @Test(groups = {"native"}, description = "Register to account test",
          dataProvider = "NativeIOSTestDataProvider", dataProviderClass = DataProviders.class)
    public void registerToAccountTest(String email, String username, String password, String pageTitle) throws
        IllegalAccessException, NoSuchFieldException, InstantiationException {

        getPo().getWelement("registrationButton").click();

        getPo().getWelement("registrationEmail").sendKeys(email);

        getPo().getWelement("registrationUserName").sendKeys(username);

        getPo().getWelement("registrationPassword").sendKeys(password);

        getPo().getWelement("registrationConfirmPassword").sendKeys(password);

        getPo().getWelement("registrationNewAccountButton").click();

        System.out.println("Registration is done");

        getPo().getWelement("loginEmailField").sendKeys(email);

        getPo().getWelement("passwordField").sendKeys(password);

        getPo().getWelement("signInBtn").click();

        System.out.println("Sign in is done");

        assert (getPo().getWelement("pageTitle").getText().equals(pageTitle));

        System.out.println("Assertion is done");
    }

}
