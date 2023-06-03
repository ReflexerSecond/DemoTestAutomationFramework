package ru.nikulin.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.remote.http.HttpMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.nikulin.constants.Constants;
import ru.nikulin.constants.UserType;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

@Component
public class AeoApiClient {

    //TODO refactor it
    @Autowired
    ScenarioContext context;
    private String token;
    private UserType currentUserType;

    private RequestSpecification requestSpecification;

    AeoApiClient() {
        RestAssured.filters(new AllureLoggingFilter());
    }

    public void saveToken(UserType type, boolean forceToGenerateNew) {
        if (token == null || type != currentUserType || forceToGenerateNew) {
            currentUserType = type;
            HashMap<String, String> params = new HashMap<>();
            params.put("grant_type", "client_credentials");
            if (type.getPassword() != null && type.getUsername() != null) {
                params.put("username", type.getUsername());
                params.put("password", type.getPassword());
            }
            token = requestForToken(params);
        }
    }

    private String requestForToken(Map<String, String> params) {
        token = sendRequest(HttpMethod.POST, "/auth/oauth/v4/token", null, params, null)
                .then()
                .statusCode(200)
                .extract()
                .response()
                .getBody()
                .jsonPath()
                .get("access_token");
        requestSpecification.given().header("x-access-token", token);
        return token;
    }

    public Response sendRequest(HttpMethod method, String address, @Nullable Map<String, String> headers, @Nullable Map<String, String> params, @Nullable String body) {
        reset();
        if (headers != null)
            requestSpecification.given().headers(headers);
        if (params != null)
            requestSpecification.given().params(params);
        if (body != null && !body.isEmpty() && !body.isBlank())
            requestSpecification.given().body(body);

        Response response = requestSpecification.request(method.name(), address);
        context.setVar("lastResponse", response);
        return response;
    }

    public Response sendRequest(HttpMethod method, String address) {
        return sendRequest(method, address, null, null, null);
    }

    public void reset() {
        requestSpecification = RestAssured.given()
                .baseUri(Constants.baseUrl)
                .basePath(Constants.apiPath)
                .header("authorization", Constants.authHeader)
                .header("aeLang", "en_US")
                .header("aeSite", "AEO_US")
                .log()
                .everything();

        if (token != null)
            requestSpecification.given()
                    .header("x-access-token", token);
    }

}
