package ru.nikulin.utils;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.internal.NameAndValue;
import io.restassured.internal.support.Prettifier;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

import java.util.HashMap;
import java.util.Map;

import static io.qameta.allure.Allure.getLifecycle;

public class AllureLoggingFilter implements Filter {

    private static Map<String, String> toMapConverter(Iterable<? extends NameAndValue> items) {
        Map<String, String> result = new HashMap<>();
        items.forEach(h -> result.put(h.getName(), h.getValue()));
        return result;
    }

    @Override
    public Response filter(FilterableRequestSpecification requestSpec,
                           FilterableResponseSpecification responseSpec,
                           FilterContext filterContext) {

        String url = requestSpec.getURI();

        Prettifier prettifier = new Prettifier();


        //TODO:
        // 1. ADD PARAMS
        // 2. MOVE HEADERS TO TOP FOR REQUEST
        String requestMsg = String.format("Request:\n%s %s\n%s\nHeaders:%s",
                requestSpec.getMethod(),
                url,
                (requestSpec.getBody() != null) ? prettifier.getPrettifiedBodyIfPossible(requestSpec) : "",
                toMapConverter(requestSpec.getHeaders()));

        Response response = filterContext.next(requestSpec, responseSpec);
        String responseMsg = String.format("Response:\nStatus - %s %s\n%s\nHeaders:%s",
                response.getStatusCode(),
                response.getStatusLine(),
                prettifier.getPrettifiedBodyIfPossible(response, response.getBody()),
                toMapConverter(response.getHeaders()));

        getLifecycle().addAttachment("Request: " + requestSpec.getUserDefinedPath(), "text/plain", "txt", requestMsg.getBytes());
        getLifecycle().addAttachment("Response: " + requestSpec.getUserDefinedPath(), "text/plain", "txt", responseMsg.getBytes());
        return response;
    }
}
