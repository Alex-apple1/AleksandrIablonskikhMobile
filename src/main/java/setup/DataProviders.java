package setup;

import org.testng.annotations.DataProvider;

import com.google.gson.Gson;
import entities.NativeAndroidTestData;
import entities.WebTestData;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

public class DataProviders {

    @DataProvider(name = "NativeTestDataProvider")
    public static Object[][] NativeTestDataProvider() throws FileNotFoundException {
        Reader reader = new FileReader("src/test/resources/NativeAndroidTestsData.json");
        NativeAndroidTestData data = new Gson().fromJson(reader, NativeAndroidTestData.class);

        return new Object[][] {
            {data.getEmail(), data.getUsername(), data.getPassword(), data.getPageTitle()},
        };
    }

    @DataProvider(name = "WebTestDataProvider")
    public static Object[][] WebTestDataProvider() throws FileNotFoundException {
        Reader reader = new FileReader("src/test/resources/WebTestsData.json");
        WebTestData data = new Gson().fromJson(reader, WebTestData.class);

        return new Object[][]{
            {data.getURL(), data.getPageTitle(), data.getSearchWord()},
        };
    }
}
