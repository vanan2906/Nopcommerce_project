package pageobject;
import common.BasePage;
import org.openqa.selenium.WebDriver;
import ui.TableGridUI;
public class TableGridPageObject  extends BasePage{
    WebDriver driver;
    public TableGridPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void openpagingByPageNumber(String pageNumber) {
        waitForElementVisible(driver,TableGridUI.PAGINATION_PAGE_BY_NUMBER,pageNumber);
        clickToElement(driver, TableGridUI.PAGINATION_PAGE_BY_NUMBER,pageNumber);
    }

    public void enterToHeaderTextboxByLabel(String headerLabel, String value) {
        waitForElementVisible(driver,TableGridUI.HEADER_TEXTBOX_BY_LABEL,headerLabel);
        sendKeyToElement(driver,TableGridUI.HEADER_TEXTBOX_BY_LABEL,value,headerLabel);
    }

    public boolean isPageNumberActive(String numberPage) {
        waitForElementVisible(driver,TableGridUI.PAGINATION_PAGE_ACTIVE_BY_NUMBER,numberPage);
        return isElementDisplayed(driver,TableGridUI.PAGINATION_PAGE_ACTIVE_BY_NUMBER,numberPage);
    }
}
