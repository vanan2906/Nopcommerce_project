import common.BasePage;
import common.BaseTest;
import org.testng.annotations.Parameters;
import pageobject.HomePageObject;
import pageobject.LoginPageObject;
import pageobject.RegisterPageObject;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Pattern_Object extends BaseTest {
    WebDriver driver;
    String firstName, lastName, validEmail, invalidEmail, notFoundEmail, password;
    BasePage basePage;
    HomePageObject homePage;
    RegisterPageObject registerPage;
    LoginPageObject loginPage;

    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName) {
        driver = getDriverBrowser(browserName);
        //driver = new ChromeDriver();
        //driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        firstName = "Fc";
        lastName = "Xom Le";
        validEmail = "abc" + generateFakeNumber() + "@gmail.com";
        invalidEmail = "invalid@gmail";
        notFoundEmail = "notfound" + generateFakeNumber() + "@gmail.com";
        password = "1234567";
        basePage = new BasePage();
        driver.get("https://demo.nopcommerce.com/");
        homePage = new HomePageObject(driver);
        registerPage = new RegisterPageObject(driver);
        loginPage = new LoginPageObject(driver);


//        Pre-condition
        homePage.openHeaderPageByName(driver, "Register");
        registerPage.enterToTextboxByID(driver, "FirstName", firstName);
        registerPage.enterToTextboxByID(driver, "LastName", lastName);
        registerPage.enterToTextboxByID(driver, "Email", validEmail);
        registerPage.enterToTextboxByID(driver, "Password", password);
        registerPage.enterToTextboxByID(driver, "ConfirmPassword", password);


        registerPage.clickToRegisterButton();
        homePage.openHeaderPageByName(driver, "Log out");

//        Logout -> CLick Loginin


    }


    @Test
    public void Login_01_Empty_Data() {
        homePage.openHeaderPageByName(driver, "Log in");
        loginPage.clickToLoginButton();
        Assert.assertEquals(loginPage.getErrorMessageAtEmailMessage(), "Please enter your email");

    }

    @Test
    public void Login_02_Invalid_Email() {
        homePage.openHeaderPageByName(driver, "Log in");

        loginPage.enterToTextboxByID(driver, "Email", invalidEmail);
        loginPage.clickToLoginButton();
        Assert.assertEquals(loginPage.getErrorMessageAtEmailMessage(), "Wrong email");


    }

    @Test
    public void Login_03_Email_Not_Login_Yet() {

        homePage.openHeaderPageByName(driver, "Log in");
        loginPage.enterToTextboxByID(driver, "Email", notFoundEmail);
        loginPage.enterToTextboxByID(driver, "Password", password);

        loginPage.clickToLoginButton();

        Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
        ////div[@class='message-error validation-summary-errors']


    }

    @Test
    public void Login_04_Existed_Email_But_Empty_Password() {
        homePage.openHeaderPageByName(driver, "Log in");


        loginPage.enterToTextboxByID(driver, "Email", validEmail);
        loginPage.clickToLoginButton();

        Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
        ////div[@class='message-error validation-summary-errors']
    }

    @Test
    public void Login_05_Existed_Email_But_Wrong_Password() {
        homePage.openHeaderPageByName(driver, "Register");
        registerPage.enterToTextboxByID(driver, "FirstName", firstName);
        registerPage.enterToTextboxByID(driver, "LastName", lastName);
        registerPage.enterToTextboxByID(driver, "Email", validEmail);
        registerPage.enterToTextboxByID(driver, "Password", password);
        registerPage.enterToTextboxByID(driver, "ConfirmPassword", password);

        registerPage.clickToRegisterButton();

        homePage.openHeaderPageByName(driver, "Log in");
        loginPage.enterToTextboxByID(driver, "Email", validEmail);
        loginPage.enterToTextboxByID(driver, "Password", "AAAAAAAAAA");

        loginPage.clickToLoginButton();

        Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
        ////div[@class='message-error validation-summary-errors']
    }

    @Test
    public void Login_06_Success() {
        homePage.openHeaderPageByName(driver, "Log in");
        loginPage.enterToTextboxByID(driver, "Email", validEmail);
        loginPage.enterToTextboxByID(driver, "Password", password);


        loginPage.clickToLoginButton();

        Assert.assertTrue(homePage.isMyAccountIsDisplayed());



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
