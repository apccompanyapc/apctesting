package org.apctesting.testBase;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apctesting.utils.ConfigurationReader;


public class BaseUrl {
    protected Response response;
    protected String token = ConfigurationReader.get("token");
    protected static void baseUrl() {
        RestAssured.baseURI = ConfigurationReader.get("baseUrl");
    }

}
