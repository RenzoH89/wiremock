import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

/**
 * Created by renzo on 5-3-2017.
 */
public class Stub {

    private static Stub INSTANCE;
    private WireMockServer wireMockServer;

    private Stub(){}

    public static Stub getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Stub();
        }
        return INSTANCE;
    }

    public static void main (String[] args){
        getInstance().startStubServer();
    }

    public void startStubServer() {
        wireMockServer = new WireMockServer(wireMockConfig().port(9999));
        WireMock.configureFor(9999);
        wireMockServer.start();
    }

    public void stopStubServer(){
        wireMockServer.stop();
    }
}