package com.herokuapp.booker.restful.cucumber.steps;

import com.herokuapp.booker.restful.restfulinfo.RestfulBookerSteps;
import com.herokuapp.booker.restful.utils.TestUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

public class RestfulBookerDefStep {

    static String firstname = "John" + TestUtils.getRandomValue();
    static String lastname = "Brown" + TestUtils.getRandomValue();
    static int totalprice = 2500;
    static boolean depositpaid= true;

    static int bookingId;

    static ValidatableResponse response;

    @Steps
    RestfulBookerSteps restfulBookerSteps;
    @When("^User sends a GET request to list endpoint$")
    public void userSendsAGETRequestToListEndpoint() {
        response = new RestfulBookerSteps().getbookid();

    }

    @Then("^User must get back a valid status code (\\d+)$")
    public void userMustGetBackAValidStatusCode(int code) {
        response = response.statusCode(code);
    }

    @When("^User sends a POST request to list endpoint of restful-booker$")
    public void userSendsAPOSTRequestToListEndpointOfRestfulBooker() {
        HashMap<Object,Object> bookingdates = new HashMap<>();
        bookingdates.put("checkin","2023-02-23");
        bookingdates.put("checkout","2023-02-25");

        response = restfulBookerSteps.createbooking(firstname, lastname, totalprice,
                depositpaid,bookingdates );

        bookingId = response.log().all().extract().path("bookingid");
        System.out.println(bookingId);
    }


    @When("^User sends a PUT request to list endpoint of restful-booker$")
    public void userSendsAPUTRequestToListEndpointOfRestfulBooker() {
        HashMap<Object,Object> bookingdates = new HashMap<>();
        bookingdates.put("checkin","2023-02-23");
        bookingdates.put("checkout","2023-02-25");

        firstname = firstname + "_updated";
        response = restfulBookerSteps.updateBooking(bookingId,firstname, lastname, totalprice,
                depositpaid,bookingdates);
        HashMap<String, Object> bookingMap = restfulBookerSteps.getbookingById(bookingId);
        Assert.assertThat(bookingMap, hasValue(firstname));
        System.out.println(bookingId);
    }

    @When("^User sends a DELETE request to list endpoint of restful-booker$")
    public void userSendsADELETERequestToListEndpointOfRestfulBooker() {
        response =   restfulBookerSteps.deleteBooking(bookingId);
    }

    @And("^User should verify that restful-booker is deleted with (\\d+)$")
    public void userShouldVerifyThatRestfulBookerIsDeletedWith(int code) {
        response.assertThat().statusCode(code);
    }
}
