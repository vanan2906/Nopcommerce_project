package pageobject;

import common.BasePage;
import org.openqa.selenium.WebDriver;
import ui.RegisterPageUI;

public class RegisterPageObject extends BasePage {
    WebDriver driver;
    public RegisterPageObject(WebDriver driver){
        this.driver = driver;
    }

    public void clickToRegisterButton() {
        waitForElementClickable(driver, RegisterPageUI.REGISTER_BUTTON);
        clickToElement(driver, RegisterPageUI.REGISTER_BUTTON);
    }

    public String getErrorMessageAtFirstNameTextbox() {
        waitForElementVisible(driver, RegisterPageUI.FIRST_NAME_ERROR_MESSAGE);

        return getElementText1(driver, RegisterPageUI.FIRST_NAME_ERROR_MESSAGE);
    }

    public String getErrorMessageAtLastNameTextbox() {
        waitForElementVisible(driver, RegisterPageUI.LAST_NAME_ERROR_MESSAGE);

        return getElementText1(driver, RegisterPageUI.LAST_NAME_ERROR_MESSAGE);
    }

    public String getErrorMessageAtEmailTextbox() {
        waitForElementVisible(driver,RegisterPageUI.EMAIL_ERROR_MESSAGE);

        return getElementText1(driver, RegisterPageUI.EMAIL_ERROR_MESSAGE);
    }

    public String getErrorMessageAtPasswordTextbox() {
        waitForElementVisible(driver,RegisterPageUI.PASSWORD_ERROR_MESSAGE);

        return getElementText1(driver, RegisterPageUI.PASSWORD_ERROR_MESSAGE);
    }

    public String getErrorMessageAtConfirmPasswordTextbox() {
        waitForElementVisible(driver,RegisterPageUI.CONFIRM_PASSWORD_ERROR_MESSAGE);

        return getElementText1(driver, RegisterPageUI.CONFIRM_PASSWORD_ERROR_MESSAGE);
    }

    public void inputToFirstnameTextbox(String firstName) {
        waitForElementVisible(driver, RegisterPageUI.FIRST_NAME_TEXTBOX);
        sendKeyToElement(driver, RegisterPageUI.FIRST_NAME_TEXTBOX, firstName);
    }

    public void inputToLastnameTextbox(String lastName) {
        waitForElementVisible(driver, RegisterPageUI.LAST_NAME_TEXTBOX);
        sendKeyToElement(driver, RegisterPageUI.LAST_NAME_TEXTBOX, lastName);
    }

    public void inputToEmailTextbox(String emailAddress) {
        waitForElementVisible(driver, RegisterPageUI.EMAIL_TEXTBOX);
        sendKeyToElement(driver, RegisterPageUI.EMAIL_TEXTBOX, emailAddress);
    }

    public void inputToPasswordTextbox(String passWord) {
        waitForElementVisible(driver, RegisterPageUI.PASSWORD_TEXTBOX);
        sendKeyToElement(driver, RegisterPageUI.PASSWORD_TEXTBOX, passWord);
    }

    public void inputToConfirmpasswordTextbox(String confirmPassword) {
        waitForElementVisible(driver, RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX);
        sendKeyToElement(driver, RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX, confirmPassword);
    }



    public String getRegisterSuccessMessage() {
        waitForElementVisible(driver,RegisterPageUI.REGISTER_SUCCESS_MESSAGE);

        return getElementText1(driver, RegisterPageUI.REGISTER_SUCCESS_MESSAGE);
    }

    ;

    public void clickToLogoutLink() {
        waitForElementClickable(driver, RegisterPageUI.LOGOUT_LINK);
        clickToElement(driver, RegisterPageUI.LOGOUT_LINK);
    }

    public String getErrorExistingEmailMessage() {
        waitForElementVisible(driver,RegisterPageUI.EXISTING_EMAIL_ERROR_MESSAGE);

        return getElementText1(driver, RegisterPageUI.EXISTING_EMAIL_ERROR_MESSAGE);
    }

    public String getErrorMessageAtEmailtextbox() {
        waitForElementVisible(driver,RegisterPageUI.EXISTING_EMAIL_ERROR_MESSAGE);

        return getElementText1(driver, RegisterPageUI.EXISTING_EMAIL_ERROR_MESSAGE);
    }

    public void clickToGenderRadioButton() {

        waitForElementVisible(driver, RegisterPageUI.DAY_DROPDOWN);
    }
    public void selectDay(String day) {
        waitForElementVisible(driver, RegisterPageUI.DAY_DROPDOWN);
        getSelectItemDefaultDropdown(driver,RegisterPageUI.DAY_DROPDOWN);
    }
    public void selectMonth(String month) {
        waitForElementVisible(driver, RegisterPageUI.MONTH_DROPDOWN);
        getSelectItemDefaultDropdown(driver,RegisterPageUI.MONTH_DROPDOWN);
    }
    public void selectYear(String year) {
        waitForElementVisible(driver, RegisterPageUI.YEAR_DROPDOWN);
        getSelectItemDefaultDropdown(driver,RegisterPageUI.YEAR_DROPDOWN);
    }
    public void selectDateOfBirthday(String day, String month, String year) {
        selectDay(day);
        selectMonth(month);
        selectYear(year);
    }


    public void enterToTextboxByID(String textboxID, String value) {
        waitForElementVisible(driver,RegisterPageUI.DYNAMIC_TEXTBOX_BY_ID, textboxID);
        sendKeyToElement(driver,RegisterPageUI.DYNAMIC_TEXTBOX_BY_ID,value,textboxID);
    }
}