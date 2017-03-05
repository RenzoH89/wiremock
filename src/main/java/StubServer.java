import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

/**
 * Created by renzo on 5-3-2017.
 */
public class StubServer {

    public StubServer(){
        startStubServer();
    }

    private WireMockServer wireMockServer;

    public static void main (String[] args){
        new StubServer();
    }

    private void setStubResponses(){
        stubFor(get(urlEqualTo("/helloworld"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody("Hello World")
                        .withStatusMessage("Everything was just fine!")
                        .withHeader("Content-Type", "text/plain")));
}

    private void startStubServer() {
        wireMockServer = new WireMockServer(wireMockConfig().port(8090));
        WireMock.configureFor(8090);
        wireMockServer.start();
        setStubResponses();
    }

    public void stopStubServer(){
        wireMockServer.stop();
    }
}
