package pageobject;
import common.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ui.TableGridUI;

import java.util.ArrayList;
import java.util.List;

public class TableGridPageObject  extends BasePage {
    WebDriver driver;

    public TableGridPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void openpagingByPageNumber(String pageNumber) {
        waitForElementVisible(driver, TableGridUI.PAGINATION_PAGE_BY_NUMBER, pageNumber);
        clickToElement(driver, TableGridUI.PAGINATION_PAGE_BY_NUMBER, pageNumber);
    }

    public void enterToHeaderTextboxByLabel(String headerLabel, String value) {
        waitForElementVisible(driver, TableGridUI.HEADER_TEXTBOX_BY_LABEL, headerLabel);
        sendKeyToElement(driver, TableGridUI.HEADER_TEXTBOX_BY_LABEL, value, headerLabel);
        pressKeyToElement(driver, TableGridUI.HEADER_TEXTBOX_BY_LABEL, Keys.ENTER, headerLabel);

    }

    public boolean isPageNumberActive(String numberPage) {
        waitForElementVisible(driver, TableGridUI.PAGINATION_PAGE_ACTIVE_BY_NUMBER, numberPage);
        return isElementDisplayed(driver, TableGridUI.PAGINATION_PAGE_ACTIVE_BY_NUMBER, numberPage);
    }

    public List<String> getValueEachRowAtAllPage() {
        int totalPage = getElementSize(driver, TableGridUI.TOTAL_PAGINATION);
        System.out.println("Total page: " + totalPage);
        List<String> allRowValueAllPage = new ArrayList<String>();
        for (int index = 1; index < totalPage; index++) {
            clickToElement(driver, TableGridUI.PAGINATION_PAGE_BY_INDEX, String.valueOf(index));
            sleepInSecond(1);

            List<WebElement> allRowElementEachPage = getListWebElement(driver, TableGridUI.ALL_ROW_EACH_PAGE);
            for (WebElement eachRow : allRowElementEachPage) {
                allRowValueAllPage.add(eachRow.getText());

            }

        }
        for (String value : allRowValueAllPage) {
            System.out.println("--------------------------------------------------------------------------------------");
            System.out.println(value);
        }


        return allRowValueAllPage;
    }
}
