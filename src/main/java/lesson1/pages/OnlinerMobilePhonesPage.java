package lesson1.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OnlinerMobilePhonesPage extends BasePage {

    @FindBy(xpath = "//div[span[normalize-space()='Размер экрана']]/following-sibling::div//div[contains(@class, 'schema-filter-control_select')][1]/select")
    WebElement screenDimensionMinSelect;

    Select minSizeDropdown = new Select(screenDimensionMinSelect);

    @FindBy(xpath = "//div[span[normalize-space()='Размер экрана']]/following-sibling::div//div[contains(@class, 'schema-filter-control_select')][2]/select")
    WebElement screenDimensionMaxSelect;

    Select maxSizeDropdown = new Select(screenDimensionMaxSelect);

    @FindBy(className = "schema-filter-button__sub_main")
    WebElement numberOfResults;

    @FindBy(className = "schema-product__price")
            WebElement price;

    List<WebElement> listOfMobilePhonesItems;


    public OnlinerMobilePhonesPage(WebDriver driver) {
        super(driver);
    }

    public OnlinerMobilePhonesPage selectMinimumSize(String size) {
        minSizeDropdown.selectByVisibleText(size);
        waitForLoader();
        return this;
    }

    public OnlinerMobilePhonesPage selectMaximumSize(String size) {
        maxSizeDropdown.selectByVisibleText(size);
        waitForLoader();
        return this;
    }

    private void waitForLoader() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.className("schema-filter-button__state_animated")));
        } catch (TimeoutException te) {/**/}
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("schema-filter-button__state_animated")));
    }

    WebDriverWait wait = new WebDriverWait(driver, 5L);

    public String getResultNumber() {
        return numberOfResults.getText().replaceAll("[^-?0-9]+", "");
    }

    public List<WebElement> getMobilePhonesList() {
        listOfMobilePhonesItems = driver.findElements(By.className("schema-product__group"));
        return listOfMobilePhonesItems;
    }

    public WebElement getMobilePhoneById(int phoneId) {
        return getMobilePhonesList().get(phoneId);
    }

    double wholePrice = 0.0;

    public double getAveragePrice(){
        for( int i =0; i<= getMobilePhonesList().size()-1; i++){
            wholePrice = + getPrice(i);
        }
        System.out.println("Average price is: " + wholePrice/getMobilePhonesList().size());
        return wholePrice/getMobilePhonesList().size();
    }

    public double getPrice(int phoneId) {
        return Double.parseDouble(
                getMobilePhoneById(phoneId).findElement(By.className("schema-product__price"))
                        .getText().replaceAll("[^-?0-9,]+", "").replace(',', '.'));
    }

    public String getImageSrc(int phoneId) {
        return getMobilePhoneById(phoneId).findElement(By.tagName("img")).getAttribute("src");
    }
}
