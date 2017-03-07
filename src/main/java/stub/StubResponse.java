package stub;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

/**
 * Created by renzo on 7-3-2017.
 */
public class StubResponse {

    public void setStubResponses() {
        stubFor(get(urlEqualTo("/hello"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody("Hello World")
                        .withStatusMessage("Everything was just fine!")
                        .withHeader("Content-Type", "text/plain")));
    }
}
