package scenarios;

import data.DataProviders;
import org.testng.annotations.Test;
import setup.BaseTest;

public class nativeMobileTests extends BaseTest {

    @Test(groups = {"native"}, description = "Register to account test",
          dataProvider = "NativeTestDataProvider", dataProviderClass = DataProviders.class)
    public void registerToAccountTest(String email, String username, String password) throws IllegalAccessException, NoSuchFieldException, InstantiationException {

        getPo().getWelement("registrationButton").click();

        getPo().getWelement("registrationEmail").sendKeys(email);

        getPo().getWelement("registrationUserName").sendKeys(username);

        getPo().getWelement("registrationPassword").sendKeys(password);

        getPo().getWelement("registrationConfirmPassword").sendKeys(password);

        getPo().getWelement("registrationNewAccountButton").click();

        System.out.println("Registration is done");
    }

    @Test(groups = {"native"}, description = "Sign in to account test",
          dataProvider = "NativeTestDataProvider", dataProviderClass = DataProviders.class)
    public void signInToAccountTest(String email, String password, String username)
        throws IllegalAccessException, NoSuchFieldException, InstantiationException, InterruptedException {

        getPo().getWelement("loginEmailField").sendKeys(email);

        getPo().getWelement("passwordField").sendKeys(password);

        getPo().getWelement("signInBtn").click();

        System.out.println("Sign in is done");

        getPo().getWelement("addNewExpenseButton").isDisplayed();

        System.out.println("Assertion is done");
    }
}
