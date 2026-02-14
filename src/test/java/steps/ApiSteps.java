package ${package}.steps;

import ${package}.client.ApiClient;
import ${package}.hooks.TestHooks;
import client.APIClient;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;

public class ApiSteps {

    private Response response;
    private final APIClient client = new APIClient(TestHooks.api);

    @When("I send GET request to {string}")
    public void sendGet(String endpoint) {
        response = client.get(endpoint);
    }

    @When("I send POST request to {string} with body:")
    public void sendPost(String endpoint, String body) {
        response = client.post(endpoint, body);
    }

    @Then("the response status should be {int}")
    public void verifyStatus(int status) {
        Assertions.assertThat(response.getStatusCode()).isEqualTo(status);
    }
}
