package client;

import io.restassured.response.Response;
import org.db.apicore.core.RestAssuredHandler;

public class APIClient {

    private final RestAssuredHandler api;

    public APIClient() {
        this.api = new RestAssuredHandler();
    }

    public Response get(String endpoint) {
        return api.getRequest(endpoint);
    }

    public Response post(String endpoint, String body) {
        return api.postRequest(endpoint, body);
    }

    public Response put(String endpoint, String body) {
        return api.putRequest(endpoint, body);
    }

    public Response delete(String endpoint) {
        return api.deleteRequest(endpoint);
    }

    public Response patch(String endpoint, String body) {
        return api.patchRequest(endpoint, body);
    }
}
