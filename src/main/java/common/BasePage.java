package common;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import static org.openqa.selenium.remote.Browser.FIREFOX;

public class BasePage {
    public void openPageUrl(WebDriver driver, String pageUrl) {
        driver.get(pageUrl);

    }

    public String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }

    public String getPageUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    public String getPageSourceCode(WebDriver driver) {
        return driver.getPageSource();
    }

    public void backToPage(WebDriver driver) {
        driver.navigate().back();
    }

    public void forwardToPage(WebDriver driver) {
        driver.navigate().forward();
    }

    public void refreshToPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    public Alert waitForAlertPresence(WebDriver driver) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        return explicitWait.until(ExpectedConditions.alertIsPresent());

    }

    public void acceptAlert(WebDriver driver) {
        waitForAlertPresence(driver).accept();

    }

    public void cancelAlert(WebDriver driver) {
        waitForAlertPresence(driver).dismiss();

    }

    public String getAlertText(WebDriver driver) {
        return waitForAlertPresence(driver).getText();

    }

    public void sendKeyToAlert(WebDriver driver, String textValue) {
        waitForAlertPresence(driver).sendKeys(textValue);

    }

    //Với window
    public void switchToWindowByID(WebDriver driver, String windowID) {
        // Get all id đang có
        Set<String> allWindowIDs = driver.getWindowHandles();
        //Duyệt qua các giá trị trong all Windows
        for (String id : allWindowIDs) {
            if (!id.equals(windowID)) {
                driver.switchTo().window(id);
                break;
            }
        }

    }

    public void switchToWindowByTitle(WebDriver driver, String tabTitle) {
        // Get all id đang có
        Set<String> allWindowIDs = driver.getWindowHandles();
        //Duyệt qua các giá trị trong all Windows
        for (String id : allWindowIDs) {
            driver.switchTo().window(id);
            String actualTitle = driver.getTitle();
            if (actualTitle.equals(tabTitle)) {
                break;
            }
        }

    }

    public void closeAllTabWithoutParent(WebDriver driver, String parentID) {
        // Get all id đang có
        Set<String> allWindowIDs = driver.getWindowHandles();
        //Duyệt qua các giá trị trong all Windows
        for (String id : allWindowIDs) {

            if (!id.equals(parentID)) {
                driver.switchTo().window(id);
                driver.close();
            }
            driver.switchTo().window(parentID);
        }

    }

    public WebElement getWebElement(WebDriver driver, String xpathLocator) {
        return driver.findElement(By.xpath(xpathLocator));

    }

    public void clickToElement(WebDriver driver, String xpathLocator) {
        getWebElement(driver, xpathLocator).click();

    }

    public void sendKeyToElement(WebDriver driver, String xpathLocator, String textValue) {
        WebElement element = getWebElement(driver, xpathLocator);
        element.clear();
        element.sendKeys(textValue);


    }

    public void sendKeyToElement(WebDriver driver, String xpathLocator, String textValue, String... params) {
        WebElement element = getWebElement(driver, xpathLocator);
        String finalLocator = String.format(xpathLocator, (Object[]) params);
        element.clear();
        element.sendKeys(textValue);
    }

    public String getElementText(WebDriver driver, String xpathLocator, String textValue) {
        return getWebElement(driver, xpathLocator).getText();

    }

    public List<WebElement> getListWebElement(WebDriver driver, String xpathLocator) {
        return driver.findElements(By.xpath(xpathLocator));

    }

    public void selectItemDefaultDropdown(WebDriver driver, String xpathLocator, String textItem) {
        Select select = new Select(getWebElement(driver, xpathLocator));
        select.selectByValue(textItem);
    }

    public String getSelectItemDefaultDropdown(WebDriver driver, String xpathLocator) {
        Select select = new Select(getWebElement(driver, xpathLocator));
        return select.getFirstSelectedOption().getText();
    }

    public Boolean isDropdownMultiple(WebDriver driver, String xpathLocator) {
        Select select = new Select(getWebElement(driver, xpathLocator));
        return select.isMultiple();
    }

    public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childItemLocator, String expectedItem) {
        getWebElement(driver, parentLocator).click();
        sleepInSecond(1);

        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy((By.xpath(childItemLocator))));

        for (WebElement item : allItems) {
            if (item.getText().trim().equals(expectedItem)) {
                JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
                jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
                sleepInSecond(1);

                item.click();
                sleepInSecond(1);
                break;
            }
        }
    }

    public void sleepInSecond(long time) {
        try {
            Thread.sleep(time * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public By getByXpath( String xpathLocator) {
        return By.xpath(xpathLocator);
    }
    public String getElementAttribute(WebDriver driver, String xpathLocator, String attributeName) {
        return getWebElement(driver, xpathLocator).getAttribute(attributeName);
    }

    public String getElementText1(WebDriver driver, String xpathLocator) {
        return getWebElement(driver, xpathLocator).getText();
    }

    public String getElementCssValue(WebDriver driver, String xpathLocator, String propertyName) {
        return getWebElement(driver, xpathLocator).getCssValue(propertyName);
    }

    public String getHexaColorFromRGBA(WebDriver driver, String rgbaColor) {
        return Color.fromString(rgbaColor).asHex();
    }

    public int getElementSize(WebDriver driver, String xpathLocator) {
        return getListWebElement(driver, xpathLocator).size();
    }

    public void checkToDefaultCheckboxRadio(WebDriver driver, String xpathLocator) {
        WebElement element = getWebElement(driver, xpathLocator);
        if (!element.isSelected()) {
            element.click();
        }
    }

    public void uncheckToDefaultCheckbox(WebDriver driver, String xpathLocator) {
        WebElement element = getWebElement(driver, xpathLocator);
        if (element.isSelected()) {
            element.click();
        }
    }

    public boolean isElementDisplayed(WebDriver driver, String xpathLocator) {
        return getWebElement(driver, xpathLocator).isDisplayed();

    }

    public boolean isElementEnabled(WebDriver driver, String xpathLocator) {
        return getWebElement(driver, xpathLocator).isEnabled();

    }

    public boolean isElementSelected(WebDriver driver, String xpathLocator) {
        return getWebElement(driver, xpathLocator).isSelected();

    }

    public void switchToFrameIframe(WebDriver driver, String xpathLocator) {
        driver.switchTo().frame(getWebElement(driver, xpathLocator));

    }

    public void switchToDefaultContent(WebDriver driver) {
        driver.switchTo().defaultContent();

    }

    public void hoverMouseToElement(WebDriver driver, String xpathLocator) {
        Actions action = new Actions(driver);
        action.moveToElement(getWebElement(driver, xpathLocator)).perform();

    }

    public void hightlightElement(WebDriver driver, String locator) {
        WebElement element = getWebElement(driver, locator);
        String originalStyle = element.getAttribute("style");
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        Duration.ofSeconds(30);
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    public void clickToElementByJS(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(driver, locator));
        Duration.ofSeconds(30);
    }

    public void scrollToElementOnTopByJS(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locator));
    }

    public void scrollToElementOnDownByJS(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", getWebElement(driver, locator));
    }

    public void scrollToBottomPageByJS(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void setAttributeInDOM(WebDriver driver, String locator, String attributeName, String attributeValue) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue + "');", getWebElement(driver, locator));
    }

    public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, locator));
    }

    public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + value + "')", getWebElement(driver, locator));
    }

    public String getAttributeInDOMByJS(WebDriver driver, String locator, String attributeName) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].getAttribute('" + attributeName + "');", getWebElement(driver, locator));
    }

    public String getElementValidationMessage(WebDriver driver, String locator) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", getWebElement(driver, locator));
    }

    public boolean isImageLoaded(WebDriver driver, String locator) {
        return (boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete " +
                        "&& typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
                getWebElement(driver, locator));
    }

    public void waitForElementVisible(WebDriver driver, String xpathLocator) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathLocator)));
    }
    protected void waitForElementVisible(WebDriver driver, String locator, String... params) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        String finalLocator = String.format(locator, (Object[]) params);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(finalLocator)));
    }
    public void waitForAllElementVisible(WebDriver driver, String xpathLocator) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(xpathLocator)));
    }
    public void waitForElementInvisible(WebDriver driver, String xpathLocator) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpathLocator)));
    }
    public void waitForAllElementInvisible(WebDriver driver, String xpathLocator) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver,xpathLocator)));
    }
    public void waitForElementClickable(WebDriver driver, String xpathLocator) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathLocator)));
    }
    public void openAddressPage(WebDriver driver, String xpathLocator) {
        waitForElementClickable(driver, BasePageUI.ADDRESS_LINK);
        clickToElement(driver, BasePageUI.ADDRESS_LINK);
    }
    public void openMyProductReviewPage(WebDriver driver, String xpathLocator) {
        waitForElementClickable(driver, BasePageUI.MY_PRODUCT_REVIEW_LINK);
        clickToElement(driver, BasePageUI.MY_PRODUCT_REVIEW_LINK);
    }
    public void openRewardPointPage(WebDriver driver, String xpathLocator) {
        waitForElementClickable(driver, BasePageUI.MY_REWARD_POINTS_LINK);
        clickToElement(driver, BasePageUI.MY_REWARD_POINTS_LINK);
    }
    public void openCustomerInforPage(WebDriver driver, String xpathLocator) {
        waitForElementClickable(driver, BasePageUI.CUSTOMER_INFOR_LINK);
        clickToElement(driver, BasePageUI.CUSTOMER_INFOR_LINK);
    }



}