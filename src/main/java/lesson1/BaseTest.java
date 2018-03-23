package lesson1;

//import com.sun.org.glassfish.gmbal.Description;
import lesson1.pages.OnlinerMainPage;
import lesson1.pages.OnlinerMobilePhonesPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public abstract class BaseTest {

    public OnlinerMainPage getMainPage() {
        return mainPage;
    }

    protected OnlinerMainPage mainPage;


    public void setChromeProperties() {
        Properties properties = new Properties();

        try {
            properties.load(BaseTest.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException i) {
        }
        String pathToChromeDriver = "";

        final String osVersion = properties.getProperty("prefered.os.version");
        switch (osVersion) {
            case "Win":
                pathToChromeDriver = new File("").getAbsolutePath()
                        + File.separator + "drivers" + File.separator + "chromedriver.exe";
                break;
            case "Mac":
                pathToChromeDriver = new File("").getAbsolutePath() + File.separator
                       + "drivers" + File.separator + "chromedriver";
                break;

            default:
               throw new AssertionError("Platform is undefined");

        }
        System.setProperty("webdriver.chrome.driver", pathToChromeDriver);

    }

    @BeforeSuite
    public void setProperties(){
        //
    }

    @BeforeClass
    public void setupTests(){
        System.out.println("BeforeClass");
        setChromeProperties();
        mainPage = new OnlinerMainPage( new ChromeDriver()).navigateToMainPage("https://www.onliner.by");
    }


    @AfterClass
    public void close(){
        mainPage.getDriver().quit();
    }
}
