package ru.nikulin.utils;

import io.restassured.RestAssured;
import org.springframework.stereotype.Component;
import ru.nikulin.constants.UserType;

@Component
public class ApiClient {
    private static final String BASE_URL = "https://www.ae.com/ugp-api";
    private String token;
    private UserType currentUserType;

    ApiClient() {
        RestAssured.filters(new AllureLoggingFilter());
    }

    public String getToken(UserType type, boolean forceToGenerateNew) {
        if (token == null || type != currentUserType || forceToGenerateNew) {
            currentUserType = type;
            token = switch (type) {
                case AUTH -> generateAuthToken();
                case GUEST -> generateGuestToken();
            };
        }
        return token;
    }

    private String generateGuestToken() {
        return RestAssured.given()
                .baseUri("https://www.ae.com")
                .basePath("/ugp-api")
                .header("authorization", "Basic MjBlNDI2OTAtODkzYS00ODAzLTg5ZTctODliZmI0ZWJmMmZlOjVmNDk5NDVhLTdjMTUtNDczNi05NDgxLWU4OGVkYjQwMGNkNg==")
                .formParam("grant_type", "client_credentials")
                .post("/auth/oauth/v4/token")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .getBody()
                .jsonPath()
                .get("access_token");
    }

    private String generateAuthToken() {

        return RestAssured.given()
                .baseUri("https://www.ae.com/ugp-api")
                .basePath("/ugp-api")
                .header("authorization", "Basic MjBlNDI2OTAtODkzYS00ODAzLTg5ZTctODliZmI0ZWJmMmZlOjVmNDk5NDVhLTdjMTUtNDczNi05NDgxLWU4OGVkYjQwMGNkNg==")
                .formParam("grant_type", "client_credentials")
                .formParam("username", " ")
                .formParam("password", " ")
                .post("/auth/oauth/v4/token")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .getBody()
                .jsonPath()
                .get("access_token");
    }

}
