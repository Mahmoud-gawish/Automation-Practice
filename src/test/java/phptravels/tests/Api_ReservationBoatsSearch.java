package phptravels.tests;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
import io.restassured.response.Response;
import phptravels.apis.PhpTravels_APIs;
import utils.ExcelFileManager;

@Epic("PHPTRAVELS")
@Feature("API")
public class Api_ReservationBoatsSearch {
    PhpTravels_APIs apis;
    ExcelFileManager spreadSheet;

    @BeforeClass
    public void beforeClass() {
	apis = new PhpTravels_APIs();
	spreadSheet = new ExcelFileManager(
		new File("src/test/resources/TestData/LiveProject_PhpTravels_ReservationBoatsSearch_TestData.xlsx"));
	spreadSheet.switchToSheet("API");
    }

    @Test(description = "PHPTRAVELS - API - Validating the search function of the Boats")
    @Description("Given I'm on the PHPTravels home page; When I enter the data needed to search for Boats And click the search button; Then I should be navigated to the Boats search results page, Then I should get the search results related to the search value entered")
    @Story("Reservation Search")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("Test_case")
    @Issue("Software_bug")
    public void testingBoatsSearch() {
	Response hotel = apis.boatsSearch(spreadSheet.getCellData("Country Name", 2),
		spreadSheet.getCellData("City Name", 2), spreadSheet.getCellData("Boat Name", 2),
		spreadSheet.getCellData("Boat Date", 2), spreadSheet.getCellData("Adults Count", 2));
	Assert.assertTrue(hotel.getBody().asString().contains(spreadSheet.getCellData("Expected Boat Name", 2)),
		"No/Wrong Boat Name!; The Hotel Name should be: [" + spreadSheet.getCellData("Expected Boat Name", 2)
			+ "]");

    }
}
