package fabio.prof.testews.domain;

import java.util.List;

public class ResponseListaPresentes extends Response {
    private List<Presente> response;

    public ResponseListaPresentes() {}

    public List<Presente> getResponse() {
        return response;
    }

    public void setResponse(List<Presente> response) {
        this.response = response;
    }
}
