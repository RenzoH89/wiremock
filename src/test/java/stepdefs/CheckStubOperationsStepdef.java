package stepdefs;

import com.github.tomakehurst.wiremock.client.WireMock;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import stub.Stub;

import static com.github.tomakehurst.wiremock.client.WireMock.exactly;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static io.restassured.RestAssured.when;
import static org.hamcrest.core.Is.is;

/**
 * Created by renzohoogendoorn on 07-03-17.
 */
public class CheckStubOperationsStepdef {
    private Response response;

    @Before
    public void setUp(){
        RestAssured.port = 9999;
        Stub.getInstance().startStubServer();
    }

    @When("^a user calls the hello world service$")
    public void aUserCallsTheHelloWorldService() throws Throwable {
        response = when().get("/hello");
        WireMock.verify(exactly(1), getRequestedFor(urlEqualTo("/hello")));
    }

    @Then("^the service returns a hello world message$")
    public void theServiceReturnsAHelloWorldMessage() throws Throwable {
        response.then().body(is("Hello World"));
    }

    @And("^the service responds with statuscode (\\d+)$")
    public void theServiceRespondsWithStatuscode(int statusCode) throws Throwable {
        response.then().statusCode(statusCode);
    }

    @After
    public void tearDown(){
        Stub.getInstance().stopStubServer();
    }
}
