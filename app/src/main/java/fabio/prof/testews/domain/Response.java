package fabio.prof.testews.domain;

public class Response {
    private boolean connected;
    private String error;

    public Response() {}

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
