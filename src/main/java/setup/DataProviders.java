package setup;

import com.google.gson.Gson;
import entities.NativeAndroidTestsData;
import entities.WebTestsData;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import org.testng.annotations.DataProvider;

public class DataProviders {


    @DataProvider(name = "NativeTestDataProvider")
    public static Object[][] NativeTestDataProvider() throws FileNotFoundException {
        Reader reader = new FileReader("src/test/resources/NativeTestsData.json");
        NativeAndroidTestsData data = new Gson().fromJson(reader, NativeAndroidTestsData.class);

        return new Object[][] {
            {data.getEmail(), data.getUsername(), data.getPassword(), data.getPageTitle()},
        };
    }

    @DataProvider(name = "WebTestDataProvider")
    public static Object[][] WebTestDataProvider() throws FileNotFoundException {
        Reader reader = new FileReader("src/test/resources/WebTestsData.json");
        WebTestsData data = new Gson().fromJson(reader, WebTestsData.class);

        return new Object[][]{
            {data.getURL(), data.getPageTitle(), data.getSearchWord()},
        };
    }
}
