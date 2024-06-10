import common.BasePage;
import common.BaseTest;
import org.openqa.selenium.UnhandledAlertException;
import org.testng.annotations.Parameters;
import pageobject.*;
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
    String firstName, lastName, validEmail, invalidEmail, notFoundEmail, oldpassword, newpassword, day, month, year;
    BasePage basePage;
    HomePageObject homePage;
    RegisterPageObject registerPage;
    LoginPageObject loginPage;
    CustomerInforPageObject customerInforPage;
    AddressPageObject addressPage;
    CustomerInforPageObject changePasswordPage;

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
        oldpassword = "1234567";
        newpassword = "1111111";
        day = "29";
        month = "June";
        year = "1998";
        basePage = new BasePage();
        driver.get("https://demo.nopcommerce.com/");

        homePage = new HomePageObject(driver);
        registerPage = new RegisterPageObject(driver);
        loginPage = new LoginPageObject(driver);
        customerInforPage = new CustomerInforPageObject(driver);
        addressPage = new AddressPageObject(driver);
        changePasswordPage = new CustomerInforPageObject(driver);


        homePage.openHeaderPageByName(driver, "Register");
        registerPage.clickToRadioButtonByID(driver, "gender-female");
        registerPage.enterToTextboxByID(driver, "FirstName", firstName);
        registerPage.enterToTextboxByID(driver, "LastName", lastName);
        registerPage.enterToTextboxByID(driver, "Email", validEmail);
        registerPage.selectDropdownByName(driver, "DateOfBirthDay", day);
        registerPage.selectDropdownByName(driver, "DateOfBirthMonth", month);
        registerPage.selectDropdownByName(driver, "DateOfBirthYear", year);
        registerPage.enterToTextboxByID(driver, "Password", oldpassword);
        registerPage.enterToTextboxByID(driver, "ConfirmPassword", oldpassword);
        registerPage.clickToRegisterButton();
        homePage.openHeaderPageByName(driver, "My account");


    }
    @Test
    public void MyAccount_01_Customer_Infor() {
        customerInforPage.clickToRadioButtonByID(driver,"gender-male");
        customerInforPage.enterToTextboxByID(driver,"FirstName","Hoa Hong");
        customerInforPage.enterToTextboxByID(driver, "LastName", "Van Anh");
        customerInforPage.selectDropdownByName(driver,"DateOfBirthDay","6");
        customerInforPage.selectDropdownByName(driver,"DateOfBirthMonth","May");
        customerInforPage.selectDropdownByName(driver,"DateOfBirthYear","1997");
        customerInforPage.enterToTextboxByID(driver,"Email",validEmail);
        customerInforPage.enterToTextboxByID(driver,"Company","SmartOSC");
        customerInforPage.clickToButtonByText(driver,"Save");

        Assert.assertEquals(customerInforPage.getMessageSuccessByText(driver),"The customer info has been updated successfully.");

    }
    @Test
    public void MyAccount_02_Update_address() {
        addressPage.clickToPageNavigationLink(driver, "Addresses");
        addressPage.clickToButtonByText(driver,"Add new");
        addressPage.enterToTextboxByID(driver,"Address_FirstName","Van Anh");
        addressPage.enterToTextboxByID(driver,"Address_LastName", "nguyen Thi");
        addressPage.enterToTextboxByID(driver,"Address_Email",validEmail);
        addressPage.enterToTextboxByID(driver,"Address_Company","ABC company");
        addressPage.selectDropdownByName(driver,"Address.CountryId","United States");
        addressPage.selectDropdownByName(driver,"Address.StateProvinceId","Arkansas");

        addressPage.enterToTextboxByID(driver,"Address_City","Ha Noi");
        addressPage.enterToTextboxByID(driver,"Address_Address1","123 Le Van Luong");
        addressPage.enterToTextboxByID(driver,"Address_Address2","456 Le Van Luong");
        addressPage.enterToTextboxByID(driver,"Address_ZipPostalCode","100000");
        addressPage.enterToTextboxByID(driver,"Address_PhoneNumber","0987654321");
        addressPage.enterToTextboxByID(driver,"Address_FaxNumber","123456789");
        addressPage.clickToButtonByText(driver,"Save");

        Assert.assertEquals(addressPage.getMessageSuccessByText(driver),"The new address has been added successfully.");
//        Assert.assertEquals(addressPage.getTextboxValueByClass(driver,"email"), validEmail);
//        Assert.assertEquals(addressPage.getTextboxValueByClass(driver,"phone"), "0987654321");

    }
    @Test
    public void MyAccount_03_Change_Password() {

        changePasswordPage.clickToPageNavigationLink(driver, "Change password");
        changePasswordPage.enterToTextboxByID(driver,"OldPassword",oldpassword);
        changePasswordPage.enterToTextboxByID(driver,"NewPassword",newpassword);
        changePasswordPage.enterToTextboxByID(driver,"ConfirmNewPassword",newpassword);
        changePasswordPage.clickToButtonByText(driver,"Change password");
//        Assert.assertEquals(addressPage.getMessageSuccessByText(driver),"Password was changed");
        changePasswordPage.clickToCloseButton(driver,"Close");
        homePage.refreshToPage(driver);


        homePage.openHeaderPageByName(driver,"Log out");
        homePage.openHeaderPageByName(driver,"Log in");
        homePage.enterToTextboxByID(driver,"Email",validEmail);
        homePage.enterToTextboxByID(driver,"Password",oldpassword);
        homePage.clickToButtonByText(driver,"Log in");
        Assert.assertEquals(homePage.getMessageErrorByText(driver),"Login was unsuccessful. Please correct the errors and try again.\n" +
                "No customer account found");


        homePage.enterToTextboxByID(driver,"Email",validEmail);
        homePage.enterToTextboxByID(driver,"Password",newpassword);
        homePage.clickToButtonByText(driver,"Log in");
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

