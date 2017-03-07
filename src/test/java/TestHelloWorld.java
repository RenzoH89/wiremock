import com.github.tomakehurst.wiremock.client.WireMock;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.when;
import static org.hamcrest.core.Is.is;


/**
 * Created by renzo on 5-3-2017.
 */
public class TestHelloWorld {

    StubResponse stubResponse = new StubResponse();


    @Before
    public void setupStub() {
        RestAssured.port = 9999;
        Stub.getInstance().startStubServer();
    }

    @Test
    public void hello_world_returns_200_and_hello_world_message() {
        stubResponse.setStubHelloWorld();

        when().
                get("/hello").
                then().
                statusCode(200).
                body(is("Hello World"));

        WireMock.verify(exactly(1), getRequestedFor(urlEqualTo("/hello")));
    }

    @Test
    public void bye_world_returns_200_and_hello_world_message() {
        stubResponse.setStubByeWorld();

        when().
                get("/bye").
                then().
                statusCode(200).
                body(is("Bye World"));

        WireMock.verify(exactly(1), getRequestedFor(urlEqualTo("/bye")));
    }

    @After
    public void tearDown(){
        Stub.getInstance().stopStubServer();
    }
}