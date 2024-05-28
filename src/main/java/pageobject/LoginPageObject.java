package pageobject;
import common.BasePage;
import org.openqa.selenium.WebDriver;
import ui.LoginPageUI;
public class LoginPageObject extends BasePage {
    WebDriver driver;
    public LoginPageObject(WebDriver driver){
        this.driver = driver;
    }

//    public void clickToLoginLink() {
//        waitForElementClickable(driver,LoginPageUI.);
//
//    }

    public void clickToLoginButton() {
        waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
    }

    public String getErrorMessageAtEmailTextbox() {
        waitForElementClickable(driver, LoginPageUI.LOGIN_ERROR_MESSAGE);
        return getElementText1(driver, LoginPageUI.LOGIN_ERROR_MESSAGE);
    }

    public void inputToEmailTextbox(String emailAddress) {
        waitForElementClickable(driver, LoginPageUI.EMAIL_TEXTBOX);
        sendKeyToElement(driver,LoginPageUI.EMAIL_TEXTBOX,emailAddress);

    }

    public String getErrorMessageAtEmailMessage() {
        waitForElementClickable(driver, LoginPageUI.EMAIL_ERROR_MESSAGE);
        return  getElementText1(driver,LoginPageUI.EMAIL_ERROR_MESSAGE);
    }

    public void inputToPasswordTextbox(String password) {
        waitForElementClickable(driver, LoginPageUI.PASSWORD_TEXTBOX);
        sendKeyToElement(driver,LoginPageUI.PASSWORD_TEXTBOX,password);
    }
}





