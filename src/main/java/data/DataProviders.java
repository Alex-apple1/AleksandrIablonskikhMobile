package data;

import org.testng.annotations.DataProvider;

public class DataProviders {
    @DataProvider(name = "NativeTestDataProvider")
    public static Object[][] NativeTestDataProvider() {
        String email = "2000.1@mail.ru";
        String username = "Alex";
        String password = "alex1980";

        return new Object[][] {
            {email, username, password},
        };
    }

    @DataProvider(name = "WebTestDataProvider")
    public static Object[][] WebTestDataProvider() {
        String URL = "https://www.google.com/";
        String pageTitle = "Google";
        String searchWord = "EPAM";
        String appType = "web";

        return new Object[][] {
            {appType, URL, pageTitle, searchWord},
        };
    }
}
