package lesson1.tests;
import com.sun.org.glassfish.gmbal.Description;
import lesson1.BaseTest;
import lesson1.pages.OnlinerMobilePhonesPage;
import org.testng.Assert;
import org.testng.annotations.*;

public class OnlinerMobilePhonesTest extends BaseTest {

    private OnlinerMobilePhonesPage mobilePhonesPage;

    @BeforeMethod
    public void navigateToMobilePhonePage(){
        mobilePhonesPage = getMainPage()
                .navigateToCatalogPage()
                .openElectronicsMenu()
                .openMobilePhonesAndAccessoriesMenu()
                .openMobilePhonesPage();
    }

    @Test
    public void checkNumberOfPhonesWithOneDiagonal(){
        mobilePhonesPage.selectMinimumSize("1\"")
                .selectMaximumSize("1.77\"");
        Assert.assertEquals(377, Integer.parseInt(mobilePhonesPage.getResultNumber()));
    }

    @Test(dataProvider = "parseLocaleData")
    public void checkNumberOfPhonesWithDiagonalFromDataProvider(String diagonalFromTo, String expectedNumber) {
        String a = diagonalFromTo.split("\\:")[0];
        String b = diagonalFromTo.split("\\:")[1];
        mobilePhonesPage.selectMinimumSize(a)
                .selectMaximumSize(b);
        Assert.assertEquals(Integer.parseInt(mobilePhonesPage.getResultNumber()), Integer.parseInt(expectedNumber));

    }

    @Test
    public void checkPrice(){
        System.out.println("checkPrice");
        Assert.assertEquals(424.00, mobilePhonesPage.getPrice(2));
    }

    @Test
    public void checkImage(){
        Assert.assertNotNull(mobilePhonesPage.getImageSrc(3));
    }

    @Test
    public void averagePrice(){
        Assert.assertNotNull(mobilePhonesPage.getAveragePrice());
    }

    @AfterMethod
    public void navigateBackToMainPage(){
        //
    }

    @DataProvider
    public Object[][] parseLocaleData() {
        return new Object[][]{
                {"1\":1.77\"", "377"},
                {"2\":2.8\"", "670"},
                {"4\":5.1\"", "2956"},
        };
    }
}


