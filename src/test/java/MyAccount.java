import common.BasePage;
import common.BaseTest;
import org.testng.annotations.Parameters;
import pageobject.CustomerInforPageObject;
import pageobject.HomePageObject;
import pageobject.LoginPageObject;
import pageobject.RegisterPageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class MyAccount extends BaseTest {
    WebDriver driver;
    String firstName, lastName, validEmail, invalidEmail, notFoundEmail, password, day, month, year;
    BasePage basePage;
    HomePageObject homePage;
    RegisterPageObject registerPage;
    LoginPageObject loginPage;
    CustomerInforPageObject customerInforPage;

    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName) {
        driver = getDriverBrowser(browserName);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        firstName = "Fc";
        lastName = "Xom Le";
        validEmail = "abc" + generateFakeNumber() + "@gmail.com";
        invalidEmail = "invalid@gmail";
        notFoundEmail = "notfound" + generateFakeNumber() + "@gmail.com";
        password = "1234567";
        day = "29";
        month = "June";
        year = "1998";
        basePage = new BasePage();
        homePage = new HomePageObject(driver);
        registerPage = new RegisterPageObject(driver);
        loginPage = new LoginPageObject(driver);
        customerInforPage = new CustomerInforPageObject(driver);


        homePage.openHeaderPageByName(driver, "Register");
        registerPage.clickToRadioButtonByID(driver, "gender-female");
        registerPage.enterToTextboxByID(driver, "FirstName", firstName);
        registerPage.enterToTextboxByID(driver, "LastName", lastName);
        registerPage.enterToTextboxByID(driver, "Email", validEmail);
        registerPage.selectDropdownByName(driver, "DateOfBirthDay", day);
        registerPage.selectDropdownByName(driver, "DateOfBirthMonth", month);
        registerPage.selectDropdownByName(driver, "DateOfBirthYear", year);
        registerPage.enterToTextboxByID(driver, "Password", password);
        registerPage.enterToTextboxByID(driver, "ConfirmPassword", password);
        registerPage.clickToRegisterButton();
        homePage.openHeaderPageByName(driver, "My account");


    }
    @Test
    public void MyAccount_01_Customer_Infor() {
        customerInforPage.clickToRadioButtonByID(driver,"gender-male");
        customerInforPage.enterToTextboxByID(driver,"FirstName","Hoa Hong");
        customerInforPage.enterToTextboxByID(driver, "LastName", "24 ");
        customerInforPage.selectDropdownByName(driver,"DateOfBirthDay","06");
        customerInforPage.selectDropdownByName(driver,"DateOfBirthMonth","May");
        customerInforPage.selectDropdownByName(driver,"DateOfBirthYear","1997");
        customerInforPage.enterToTextboxByID(driver,"Email","ngocanh@gmail.com");
        customerInforPage.enterToTextboxByID(driver,"Company","SmartOSC");
        customerInforPage.clickToSaveButton(driver,"Save");
        // Firstname, Lastname, Date of birth, Email , Company, -> Verify thong tin
        Assert.assertEquals(customerInforPage.getMessageSuccessByText(driver);

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

