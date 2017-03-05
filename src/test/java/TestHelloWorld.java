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

    StubServer stubServer;

    @Before
    public void setupStub() {
        stubServer = new StubServer();
        RestAssured.port = 8090;
    }

    @Test
    public void hello_world_returns_200_and_hello_world_message() {

        when().
                get("/helloworld").
                then().
                statusCode(200).
                body(is("Hello World"));

        WireMock.verify(exactly(1), getRequestedFor(urlEqualTo("/helloworld")));
    }

    @After
    public void tearDown(){
        stubServer.stopStubServer();
    }
}