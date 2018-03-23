package lesson1.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OnlinerMainPage extends BasePage{
    @FindBy (partialLinkText = "Каталог")
    WebElement catalogLink;

    public OnlinerMainPage(WebDriver driver) {
        super(driver);
    }

    public OnlinerCatalogPage navigateToCatalogPage(){
        catalogLink.click();
        return new OnlinerCatalogPage(this.driver);
    }

}
