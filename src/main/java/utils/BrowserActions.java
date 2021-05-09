package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class BrowserActions {
    static WebDriver driver;

//    @Step("Navigate to URL: [{url}]")
    public static void navigateToUrl(WebDriver driver, String url) {
	try {
	    Logger.logStep("[Browser Action] Navigate to URL [" + url + "]");
	    driver.get(url);
	    ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
	} catch (Exception e) {
	    Logger.logMessage(e.getMessage());
	}
    }

//    @Step("Close All Opened Browser Windows.....")
    public static void closeAllOpenedBrowserWindows(WebDriver driver) {
	Logger.logStep("[Browser Action] Close all Opened Browser Windows");
	if (driver != null) {
	    driver.quit();
	}
    }

//    @Step("Maximize the Browser Window")
    public static void maximizeWindow(WebDriver driver) {
	try {
	    Logger.logStep("[Browser Action] Maximize the Browser Window");
	    driver.manage().window().maximize();
	} catch (Exception e) {
	    Logger.logMessage(e.getMessage());
	}
    }

}
