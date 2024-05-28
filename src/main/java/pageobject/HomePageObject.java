package pageobject;

import common.BasePage;
import org.openqa.selenium.WebDriver;
import ui.HomePageUI;

public class HomePageObject extends BasePage {
    WebDriver driver;

    // Hàm khởi tạo constructor, trùng tên với tên class
    public HomePageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void clickToRegisterLink() {
        waitForElementClickable(driver, HomePageUI.REGISTER_LINK);
        clickToElement(driver, HomePageUI.REGISTER_LINK);


    }

    public void clickToLoginLink() {
        waitForElementClickable(driver, HomePageUI.LOGIN_LINK);
        clickToElement(driver,HomePageUI.LOGIN_LINK);

    }

    public boolean isMyAccountIsDisplayed() {
        waitForElementClickable(driver,HomePageUI.LOGIN_SUCCESS_TEXT);
        return isElementDisplayed(driver,HomePageUI.LOGIN_SUCCESS_TEXT);
    }

    public void clicktoLogOut() {
        waitForElementClickable(driver,HomePageUI.LOGOUT_LINK);
        clickToElement(driver,HomePageUI.LOGOUT_LINK);
    }
    //Viết ra hàm mã giả tương ứng bám với flow của TCs
    // Tạo ra các action tương ứng theo page
    // Homepage:
    // Click to Register button
    // Register Page
    // Input to Firstname textbox, Input to Lastname, Email, Pw, Confirm pw
    // Click to Register button
    // Get Firstname/Lastname/Email/Password/Confirm Pw error message
    // Get Register success message
    // Click to Logout button

}
