package sk.machine.rest;

import java.io.IOException;

public interface RestApiAgent {

    ResponseData sendGetRequest(String url) throws IOException;

    ResponseData sendPostRequest(String url, String jsonData) throws IOException;

}
