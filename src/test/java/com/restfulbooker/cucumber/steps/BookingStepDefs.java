package com.restfulbooker.cucumber.steps;

/*
 * Created By: Hiren Patel
 * Project Name: Restful-Booker-API-Serenity-Week-21
 */

import com.restfulbooker.restfulinfo.BookingSteps;
import com.restfulbooker.utils.TestUtils;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;

import static org.hamcrest.CoreMatchers.equalTo;

public class BookingStepDefs {
    static String firstName = "PrimUser" + TestUtils.getRandomValue();
    static String lastName = "PrimeUser" + TestUtils.getRandomValue();
    static int totalPrice = 500;
    static boolean depositPaid = true;
    static String checkIn = "2022-01-07";
    static String checkOut = "2022-01-20";
    static String additionalNeeds = "Breakfast";
    static int bookingId;
    static ValidatableResponse response;

    @Steps
    BookingSteps bookingSteps;

    @When("^User send GET request to see all booking$")
    public void userSendGETRequestToSeeAllBooking() {
        response = bookingSteps.getAllBookingInfo();
    }

    @Then("^User must get back a valid status code (\\d+)$")
    public void userMustGetBackAValidStatusCode(int statusCode) {
     response.statusCode(200);
    }

    @When("^I create new booking by providing firstName, lastName, totalPrice, depositPaid, checkIn,  checkOut, additionalNeeds$")
    public void iCreateNewBookingByProvidingFirstNameLastNameTotalPriceDepositPaidCheckInCheckOutAdditionalNeeds() {
        ValidatableResponse response = bookingSteps.createBooking(firstName, lastName, totalPrice, depositPaid, checkIn,
                checkOut, additionalNeeds);
        response.statusCode(200).log().ifValidationFails();
        bookingId = response.extract().path("bookingid");
    }

    @Then("^I verify that booking is created with firstName, lastName, totalPrice, depositPaid, checkIn,  checkOut, additionalNeeds in database$")
    public void iVerifyThatBookingIsCreatedWithFirstNameLastNameTotalPriceDepositPaidCheckInCheckOutAdditionalNeedsInDatabase() {
        ValidatableResponse response = bookingSteps.getBookingById(bookingId);
        response.statusCode(200).log().ifValidationFails();
        response.body("firstname", equalTo(firstName), "lastname", equalTo(lastName),
                "totalprice", equalTo(totalPrice), "depositpaid", equalTo(depositPaid),
                "bookingdates.checkin", equalTo(checkIn), "bookingdates.checkout", equalTo(checkOut),
                "additionalneeds", equalTo(additionalNeeds));
    }

    @When("^I update booking by providing firstName, lastName, totalPrice, depositPaid, checkIn,  checkOut, additionalNeeds$")
    public void iUpdateBookingByProvidingFirstNameLastNameTotalPriceDepositPaidCheckInCheckOutAdditionalNeeds() {
        firstName = firstName + "_updated";
        lastName = lastName + "_updated";
        additionalNeeds = "Bed and Breakfast";
        ValidatableResponse response = bookingSteps.updateBooking(bookingId, firstName, lastName, totalPrice, depositPaid, checkIn, checkOut, additionalNeeds);
        response.statusCode(200);
    }

    @Then("^I verify that booking is updated with firstName, lastName, additionalNeeds in database$")
    public void iVerifyThatBookingIsUpdatedWithFirstNameLastNameAdditionalNeedsInDatabase() {
        ValidatableResponse response = bookingSteps.getBookingById(bookingId);
        response.body("firstname", equalTo(firstName), "lastname", equalTo(lastName),
                "additionalneeds", equalTo(additionalNeeds));
    }

    @When("^I delete booking data$")
    public void iDeleteBookingData() {
        bookingSteps.deleteBooking(bookingId).statusCode(201);
    }

    @Then("^I verify that same booking data was deleted by getting data by Id$")
    public void iVerifyThatSameBookingDataWasDeletedByGettingDataById() {
        bookingSteps.getBookingById(bookingId).statusCode(404);
    }
}
