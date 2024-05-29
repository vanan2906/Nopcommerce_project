
import common.BasePage;
import common.BaseTest;
import org.testng.annotations.Parameters;
import pageobject.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;


public class Switch_Page extends BaseTest {
    WebDriver driver;
    String firstName, lastName, validEmail, invalidEmail, notFoundEmail, password;
    BasePage basePage;
    HomePageObject homePage;
    RegisterPageObject registerPage;
    LoginPageObject loginPage;
    AddressPageObject addressPage;
    CustomerInforPageObject customerInforPage;
    MyProductReviewPageObject myProductReviewPage;
    RewardPointReviewPageObject rewardPointPage;

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
        basePage = new BasePage();

        homePage = new HomePageObject(driver);
        registerPage = new RegisterPageObject(driver);
        loginPage = new LoginPageObject(driver);
        addressPage = new AddressPageObject(driver);
        customerInforPage = new CustomerInforPageObject(driver);
        myProductReviewPage = new MyProductReviewPageObject(driver);
        rewardPointPage = new RewardPointReviewPageObject(driver);



    }

    @Test
    public void User_01_Register() {
        homePage.clickToRegisterLink();
        registerPage.inputToFirstnameTextbox(firstName);
        registerPage.inputToLastnameTextbox(lastName);

        registerPage.inputToEmailTextbox(validEmail);
        registerPage.inputToPasswordTextbox(password);
        registerPage.inputToConfirmpasswordTextbox(password);

        registerPage.clickToRegisterButton();
        homePage.clicktoLogOut();

    }

    @Test
    public void User_02_login() {
        homePage.clickToLoginLink();
        loginPage.inputToEmailTextbox(validEmail);
        loginPage.inputToPasswordTextbox(password);
        loginPage.clickToLoginButton();
        Assert.assertTrue(homePage.isMyAccountIsDisplayed());


    }

    @Test
    public void User_03_Myaccount() {
        customerInforPage.openAddressPage(driver);
        addressPage.openMyProductReviewPage(driver);
        myProductReviewPage.openRewardPointPage(driver);
        rewardPointPage.openAddressPage(driver);
        addressPage.openRewardPointPage(driver);
        rewardPointPage.openMyProductReviewPage(driver);
        myProductReviewPage.openAddressPage(driver);
        addressPage.openCustomerInforPage(driver);
        customerInforPage.openMyProductReviewPage(driver);




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


