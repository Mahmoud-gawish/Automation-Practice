package liveproject.phptravels.tests.SignUp;

import java.io.File;
import java.util.Date;
import java.util.Map;

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
import liveproject.phptravels.apis.PhpTravels_APIs;
import utils.Spreadsheet;

@Epic("PHPTRAVELS")
@Feature("API")
public class Api_SignUp_Test {
    PhpTravels_APIs apis;
    Spreadsheet spreadSheet;
    Date date = new Date();

    String firstName, lastName, mobileNumber, email, password;
    String currentTime = date.getTime() + "";

    @BeforeClass
    public void beforeClass() {
	apis = new PhpTravels_APIs();
	spreadSheet = new Spreadsheet(
		new File("src/test/resources/TestData/LiveProject_PhpTravels_SignUp_TestData.xlsx"));
	spreadSheet.switchToSheet("API");
    }

    @Test(description = "Valid User Sign Up")
    @Description("When I enter valid data in the sign up form And click the signup button, Then I should be registered successfully And be navigated to the user account page And I can see my user data and Hi message")
    @Story("Sign Up")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("focus-case-1637098")
    @Issue("bug-tracker#1")
    public void testingValidUserSignUp() {
	firstName = spreadSheet.getCellData("FirstName", 2);
	lastName = spreadSheet.getCellData("LastName", 2);
	mobileNumber = spreadSheet.getCellData("Mobile Number", 2);
	email = spreadSheet.getCellData("Email", 2) + currentTime + "@test.com";
	password = spreadSheet.getCellData("Password", 2);

	Response signUp = apis.userSignUp(firstName, lastName, mobileNumber, email, password);
	Map<String, String> cookies = signUp.getCookies();
	Response account = apis.userAccount(cookies);
	Assert.assertTrue(account.getBody().asString().contains("Hi, " + firstName + " " + lastName),
		"No/Wrong Hi Message!; The Account response doesn't contain the expected message: " + "[Hi, " + firstName
			+ " " + lastName + "]");
    }

    @Test(description = "Invalid User Sign Up - Email Already Exists", dependsOnMethods = { "testingValidUserSignUp" })
    @Description("Given i already signed up with an email, When I use the same email for new sign up , Then I should get an error message ")
    @Story("Sign Up")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("focus-case-1637099")
    @Issue("bug-tracker#1")
    public void testingInvalidUserSignUp_emailAlreadyExists() {
	firstName = spreadSheet.getCellData("FirstName", 3);
	lastName = spreadSheet.getCellData("LastName", 3);
	mobileNumber = spreadSheet.getCellData("Mobile Number", 3);
	email = spreadSheet.getCellData("Email", 3) + currentTime + "@test.com";
	password = spreadSheet.getCellData("Password", 3);

	Response signUp = apis.userSignUp(firstName, lastName, mobileNumber, email, password);
	Assert.assertTrue(signUp.getBody().asString().contains(spreadSheet.getCellData("Expected Alert Message", 3)),
		"No/Wrong Error Message!; The message should be: ["
			+ spreadSheet.getCellData("Expected Alert Message", 3) + "]");

    }

    @Test(description = "Invalid User Sign Up - Wrong Email Format")
    @Description("When I use a wrong email format on the sign up , Then I should get an error message ")
    @Story("Sign Up")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("focus-case-1637100")
    @Issue("bug-tracker#1")
    public void testingInvalidUserSignUp_emailWrongFormat() {
	firstName = spreadSheet.getCellData("FirstName", 4);
	lastName = spreadSheet.getCellData("LastName", 4);
	mobileNumber = spreadSheet.getCellData("Mobile Number", 4);
	email = spreadSheet.getCellData("Email", 4) + currentTime;
	password = spreadSheet.getCellData("Password", 4);

	Response signUp = apis.userSignUp(firstName, lastName, mobileNumber, email, password);
	Assert.assertTrue(signUp.getBody().asString().contains(spreadSheet.getCellData("Expected Alert Message", 4)),
		"No/Wrong Error Message!; The message should be: ["
			+ spreadSheet.getCellData("Expected Alert Message", 4) + "]");

    }
}
