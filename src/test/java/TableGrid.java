import common.BasePage;
import common.BaseTest;
import org.testng.annotations.Parameters;
import pageobject.PageGeneratorManager;
import pageobject.TableGridPageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.sound.midi.ShortMessage;
import java.time.Duration;
import java.util.List;
import java.util.Random;

public class TableGrid extends BaseTest {
    WebDriver driver;
    BasePage basePage;
    TableGridPageObject tableGridPage;
    List<String> actualAllCountryValues;
    List<String> expectedAllCountryValues;


    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName) {
        driver = getDriverBrowser(browserName);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //driver = new ChromeDriver();
        //driver = new FirefoxDriver()
        tableGridPage = PageGeneratorManager.tableGridPage(driver);
        basePage = new BasePage();
    }

    @Test
    public void Table_01_Paging() {
        tableGridPage.openpagingByPageNumber("1");
        tableGridPage.sleepInSecond(2);
        Assert.assertTrue(tableGridPage.isPageNumberActive("1"));

        tableGridPage.openpagingByPageNumber("3");
        tableGridPage.sleepInSecond(2);
        Assert.assertTrue(tableGridPage.isPageNumberActive("3"));

        tableGridPage.openpagingByPageNumber("4");
        tableGridPage.sleepInSecond(2);
        Assert.assertTrue(tableGridPage.isPageNumberActive("4"));

        tableGridPage.openpagingByPageNumber("12");
        tableGridPage.sleepInSecond(2);
        Assert.assertTrue(tableGridPage.isPageNumberActive("12"));


    }

    @Test
    public void Table_02_Header_Textbox() {
        tableGridPage.refreshToPage(driver);
        tableGridPage.enterToHeaderTextboxByLabel("Country", "Malawi");
        tableGridPage.enterToHeaderTextboxByLabel("Females", "194740");
        tableGridPage.enterToHeaderTextboxByLabel("Males", "195140");
        tableGridPage.enterToHeaderTextboxByLabel("Total", "389880");


    }

    @Test
    public void Table_03() {
        tableGridPage.refreshToPage(driver);
        tableGridPage.getValueEachRowAtAllPage();
        Assert.assertEquals(actualAllCountryValues, expectedAllCountryValues);


    }

    public int generateFakeNumber() {


        Random rand = new Random();
        int ranNum = rand.nextInt(100) + 1;
        return ranNum;
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
