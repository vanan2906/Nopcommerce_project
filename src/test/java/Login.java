import common.BasePage;
import common.BaseTest;
import org.testng.annotations.Parameters;
import pageobject.HomePageObject;
import  pageobject.LoginPageObject;
import  pageobject.RegisterPageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;
public class Login extends BaseTest {
    WebDriver driver;
    String firstName, lastName, validEmail, invalidEmail, notFoundEmail, password;
    BasePage basePage;
    HomePageObject homePage;
    RegisterPageObject registerPage;
    LoginPageObject loginPage;

@Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName) {
    driver= getDriverBrowser(browserName);
        //driver = new ChromeDriver();
        //driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        firstName = "Fc";
        lastName = "Xom Le";
        validEmail = "abc" + generateFakeNumber() + "@gmail.com";
        invalidEmail= "invalid@gmail";
        notFoundEmail = "notfound" + generateFakeNumber() + "@gmail.com";
        password = "1234567";
        basePage = new BasePage();
        driver.get("https://demo.nopcommerce.com/");
        homePage = new HomePageObject(driver);
        registerPage = new RegisterPageObject(driver);
        loginPage = new LoginPageObject(driver);


//        Pre-condition
        homePage.clickToRegisterLink();
        registerPage.inputToFirstnameTextbox(firstName);
        registerPage.inputToLastnameTextbox(lastName);
        registerPage.selectDateOfBirthday("1","January","1914");

        registerPage.inputToEmailTextbox(validEmail);
        registerPage.inputToPasswordTextbox(password);
        registerPage.inputToConfirmpasswordTextbox(password);

        registerPage.clickToRegisterButton();
        homePage.clicktoLogOut();
        
//        Logout -> CLick Loginin


    }

    @Test
    public void Login_01_Empty_Data() {
        homePage.clickToLoginLink();
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

        homePage.clickToLoginLink();
        loginPage.inputToEmailTextbox(notFoundEmail);
        loginPage.inputToPasswordTextbox(password);

        loginPage.clickToLoginButton();

        Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
        ////div[@class='message-error validation-summary-errors']


    }

    @Test
    public void Login_04_Existed_Email_But_Empty_Password() {
        homePage.clickToLoginLink();

        loginPage.inputToEmailTextbox(validEmail);

        loginPage.clickToLoginButton();

        Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
        ////div[@class='message-error validation-summary-errors']
    }

    @Test
    public void Login_05_Existed_Email_But_Wrong_Password() {
        homePage.clickToRegisterLink();
        registerPage.inputToFirstnameTextbox(firstName);
        registerPage.inputToLastnameTextbox(lastName);
        registerPage.inputToEmailTextbox(validEmail);
        registerPage.inputToPasswordTextbox(password);
        registerPage.inputToConfirmpasswordTextbox(password);

        registerPage.clickToRegisterButton();
        //Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
        //registerPage.clickToLogoutLink();
        // //a[@class='ico-logout']

        //registerPage.clickToLogoutLink();
        homePage.clickToLoginLink();

        loginPage.inputToEmailTextbox(validEmail);
        loginPage.inputToPasswordTextbox("AAAAAAAAAA");
        loginPage.clickToLoginButton();

        Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
        ////div[@class='message-error validation-summary-errors']
    }

    @Test
    public void Login_06_Success() {
        homePage.clickToLoginLink();
        loginPage.inputToEmailTextbox(validEmail);
        loginPage.inputToPasswordTextbox(password);
        loginPage.clickToLoginButton();

        Assert.assertTrue(homePage.isMyAccountIsDisplayed());
        ////div[@class='message-error validation-summary-errors']


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
