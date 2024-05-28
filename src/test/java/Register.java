

import common.BasePage;
import common.BaseTest;
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
public class Register extends BaseTest {
    WebDriver driver;
    String firstName, lastName, emailAddress, password;
    BasePage basePage;
    HomePageObject homePage;
    RegisterPageObject registerPage;
    LoginPageObject loginPage;


    @BeforeClass
    public void beforeClass() {
        //Khởi tạo browser với Chrome
        driver = new ChromeDriver();
        //driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        firstName = "Fc";
        lastName = "Xom Le";
        emailAddress = "abc" + generateFakeNumber() + "@gmail.com";
        password = "1234567";
        basePage = new BasePage();
        driver.get("https://demo.nopcommerce.com/");
        homePage = new HomePageObject(driver);
        registerPage = new RegisterPageObject(driver);
        loginPage = new LoginPageObject(driver);
    }

    //@Test
    public void Register_01_Empty_data() {
        System.out.println("Home Page - Step 1: Click to Register link");
        homePage.clickToRegisterLink();

        System.out.println("Register page - Step 2: Click to Register button");
        registerPage.clickToRegisterButton();
        Assert.assertEquals(registerPage.getErrorMessageAtFirstNameTextbox(), "First name is required.");
        Assert.assertEquals(registerPage.getErrorMessageAtLastNameTextbox(), "Last name is required.");
        Assert.assertEquals(registerPage.getErrorMessageAtEmailTextbox(), "Email is required.");
        Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextbox(), "Password is required.");
        Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextbox(), "Password is required.");


    }

    // @Test
    public void Register_02_Invalid_Email() {
        homePage.clickToRegisterLink();
        registerPage.inputToFirstnameTextbox(firstName);
        registerPage.inputToLastnameTextbox(lastName);
        registerPage.inputToEmailTextbox("Vanh@123456");
        registerPage.inputToPasswordTextbox(password);
        registerPage.inputToConfirmpasswordTextbox(password);

        registerPage.clickToRegisterButton();
        Assert.assertEquals(registerPage.getErrorMessageAtEmailtextbox(), "Wrong email");


//


    }

    // @Test
    public void Register_03_Success() {

        homePage.clickToRegisterLink();
        registerPage.inputToFirstnameTextbox(firstName);
        registerPage.inputToLastnameTextbox(lastName);
        registerPage.inputToEmailTextbox(emailAddress);
        registerPage.inputToPasswordTextbox(password);
        registerPage.inputToConfirmpasswordTextbox(password);

        registerPage.clickToRegisterButton();
        Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
        //registerPage.clickToLogoutLink();
        // //a[@class='ico-logout']


    }

    // @Test
    public void Register_04_Existing_Email() {

        homePage.clickToRegisterLink();
        registerPage.inputToFirstnameTextbox(firstName);
        registerPage.inputToLastnameTextbox(lastName);
        registerPage.inputToEmailTextbox(emailAddress);
        registerPage.inputToPasswordTextbox(password);
        registerPage.inputToConfirmpasswordTextbox(password);

        registerPage.clickToRegisterButton();
        Assert.assertEquals(registerPage.getErrorExistingEmailMessage(), "The specified email already exists");


    }

    // @Test
    public void Register_05_Password_Less_Than_6_Chas() {

        homePage.clickToRegisterLink();
        registerPage.inputToFirstnameTextbox(firstName);
        registerPage.inputToLastnameTextbox(lastName);
        registerPage.inputToEmailTextbox(emailAddress);
        registerPage.inputToPasswordTextbox("123");
        registerPage.inputToConfirmpasswordTextbox("123");

        registerPage.clickToRegisterButton();
        Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextbox(), "Password must meet the following rules:\nmust have at least 6 characters");


    }

    // @Test
    public void Register_06_Invalid_Confirm_Password() {

        homePage.clickToRegisterLink();

        registerPage.inputToFirstnameTextbox(firstName);
        registerPage.inputToLastnameTextbox(lastName);
        registerPage.inputToEmailTextbox(emailAddress);
        registerPage.inputToPasswordTextbox(password);
        registerPage.inputToConfirmpasswordTextbox("123");

        registerPage.clickToRegisterButton();
        Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextbox(), "The password and confirmation password do not match.");


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



