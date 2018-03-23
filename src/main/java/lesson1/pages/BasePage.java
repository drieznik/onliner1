package lesson1.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    public WebDriver getDriver() {
        return driver;
    }

    protected WebDriver driver;


    public BasePage(WebDriver driver) {
        this.driver = driver;

        PageFactory.initElements(driver, this);
    }

    public OnlinerMainPage navigateToMainPage(String url){
        driver.get(url);
        return new OnlinerMainPage(driver);
    }

}
