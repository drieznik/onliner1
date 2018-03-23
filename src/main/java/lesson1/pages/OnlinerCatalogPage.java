package lesson1.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OnlinerCatalogPage extends BasePage{

    @FindBy (css = "[data-id='1']")
    WebElement electronicsLink;

    @FindBy(css = ".catalog-navigation-list__aside-list>.catalog-navigation-list__aside-item")
    WebElement mobilePhonesAndAccessoriesLink;

    @FindBy(xpath = "//span[normalize-space()='Мобильные телефоны']")
    WebElement mobilePhonesLink;

    public OnlinerCatalogPage(WebDriver driver) {
        super(driver);
    }

    public OnlinerCatalogPage openElectronicsMenu(){
        electronicsLink.click();
        return this;
    }

    public OnlinerCatalogPage openMobilePhonesAndAccessoriesMenu(){
        mobilePhonesAndAccessoriesLink.click();
        return this;
    }

    public OnlinerMobilePhonesPage openMobilePhonesPage(){
        mobilePhonesLink.click();
        return new OnlinerMobilePhonesPage(this.driver);
    }
}
