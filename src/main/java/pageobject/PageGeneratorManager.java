package pageobject;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
    public static TableGridPageObject tableGridPage(WebDriver driver){
        return new TableGridPageObject(driver);
    }
}
