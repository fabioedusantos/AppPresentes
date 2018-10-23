package fabio.prof.testews.domain;

public class ResponseManipulation extends Response {
    private boolean response;

    public ResponseManipulation() {}

    public boolean isResponse() {
        return response;
    }

    public void setResponse(boolean response) {
        this.response = response;
    }
}
