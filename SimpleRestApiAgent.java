package sk.machine.rest;

import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class SimpleRestApiAgent implements RestApiAgent {

    private static final MediaType JSON_MEDIA_TYPE = MediaType.parse("application/json; charset=utf-8");

    @Override
    public ResponseData sendGetRequest(String url) throws IOException {
        var client = createClient();
        var request = new Request.Builder()
                .url(url)
                .get()
                .build();
        return executeCall(client, request);
    }

    @Override
    public ResponseData sendPostRequest(String url, String jsonData) throws IOException {
        var client = createClient();
        var body = RequestBody.create(jsonData, JSON_MEDIA_TYPE);
        var request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        return executeCall(client, request);
    }

    private OkHttpClient createClient() {
        return new OkHttpClient.Builder()
                .connectionSpecs(List.of(ConnectionSpec.MODERN_TLS,
                        ConnectionSpec.COMPATIBLE_TLS))
                .build();
    }

    private ResponseData executeCall(OkHttpClient client,
                                     Request request) throws IOException {
        try (var response = client.newCall(request).execute()) {
            var responseBody = Optional.ofNullable(response.body());
            var bodyString = responseBody.isPresent() ? responseBody.get().string() : "";
            return new ResponseData(response.code(), bodyString);
        } finally {
            client.connectionPool().evictAll();
        }
    }

}
