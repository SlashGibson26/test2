package sk.machine.rest;

public class ResponseData {

    private final int status;
    private final String response;

    public ResponseData(int status, String response) {
        this.status = status;
        this.response = response;
    }

    public int getStatus() {
        return status;
    }

    public String getResponse() {
        return response;
    }
}
