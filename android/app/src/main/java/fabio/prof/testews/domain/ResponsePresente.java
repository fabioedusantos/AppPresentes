package fabio.prof.testews.domain;

import java.util.List;

public class ResponsePresente extends Response {
    private Presente response;

    public ResponsePresente() {}

    public Presente getResponse() {
        return response;
    }

    public void setResponse(Presente response) {
        this.response = response;
    }
}
