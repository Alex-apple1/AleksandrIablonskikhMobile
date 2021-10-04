package scenarios;

import data.DataProviders;
import org.testng.annotations.Test;
import setup.BaseTest;

public class nativeMobileTests extends BaseTest {

    @Test(groups = {"native"}, description = "Register to account test",
          dataProvider = "NativeTestDataProvider", dataProviderClass = DataProviders.class)
    public void registerToAccountTest() throws IllegalAccessException, NoSuchFieldException, InstantiationException {

        getPo().getWelement("registrationButton").click();

        getPo().getWelement("registrationEmail").sendKeys("2000.1@mail.ru");

        getPo().getWelement("registrationUserName").sendKeys("Alex");

        getPo().getWelement("registrationPassword").sendKeys("alex1980");

        getPo().getWelement("registrationConfirmPassword").sendKeys("alex1980");

        getPo().getWelement("registrationNewAccountButton").click();

        System.out.println("Registration is done");
    }

    @Test(groups = {"native"}, description = "Sign in to account test",
          dataProvider = "NativeTestDataProvider", dataProviderClass = DataProviders.class)
    public void signInToAccountTest()
        throws IllegalAccessException, NoSuchFieldException, InstantiationException, InterruptedException {

        getPo().getWelement("loginEmailField").sendKeys("2000.1@mail.ru");

        getPo().getWelement("passwordField").sendKeys("alex1980");

        getPo().getWelement("signInBtn").click();

        System.out.println("Sign in is done");

        getPo().getWelement("addNewExpenseButton").isDisplayed();

        System.out.println("Assertion is done");
    }
}
