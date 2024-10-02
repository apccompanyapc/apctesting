package org.apctesting.steps;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apctesting.utils.ConfigurationReader;
import org.junit.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class GithubApiSteps {
    private final String Accept_Version = "application/vnd.github+json";
    private final String Authorization = "Bearer " + ConfigurationReader.get("token");
    private final String X_GitHub_Api_Version = "2022-11-28";
    private final String orgsEndpoint = "/orgs/" + ConfigurationReader.get("organisationName") + "/repos";
    private final String reposEndpoint = "/repos/" + ConfigurationReader.get("organisationName");
    private Response response;

    @Given("User sets the base URL")
    public void user_sets_the_base_url() {
        RestAssured.baseURI = ConfigurationReader.get("baseUrl");
    }


    @When("user creates a {string}")
    public void userCreatesA(String repositoryName) {

        Map<String, Object> data = new HashMap<>();
        data.put("name", repositoryName);
        data.put("description", "This is a repository");
        data.put("homepage", "https://github.com");
        data.put("private", "false");
        data.put("has_issues", "true");
        data.put("has_projects", "true");
        data.put("has_wiki", "true");

        response = given().
                header("Accept", Accept_Version).
                header("Authorization", Authorization).
                header("X-GitHub-Api-Version", X_GitHub_Api_Version).
                contentType("application/json").
                body(data)
                .when()
                .post(orgsEndpoint);

    }

    @When("user sends a GET request to ListEndpoint")
    public void userSendsAGETRequestTo() {

        response = given().
                header("Accept", Accept_Version).
                header("Authorization", Authorization).
                header("X-GitHub-Api-Version", X_GitHub_Api_Version)
                .when()
                .get(orgsEndpoint);
    }

    @And("response body should contain a list")
    public void responseBodyShouldContainAList() {
        List<String> responseBodyIdList = response.jsonPath().getList("id");
        Assert.assertFalse("It is an empty list", responseBodyIdList.isEmpty());

    }

    @When("user sends a GET request to get {string}")
    public void userSendsAGETRequestToGet(String repositoryName) {
        response = given().
                header("Accept", Accept_Version).
                header("Authorization", Authorization).
                header("X-GitHub-Api-Version", X_GitHub_Api_Version)
                .when()
                .get(reposEndpoint + "/" + repositoryName);
    }

    @And("response body should have the name {string}")
    public void responseBodyShouldHaveTheName(String repoName) {
        Assert.assertEquals(response.jsonPath().get("name"), repoName);
    }

    @When("user sends a DELETE request to delete {string}")
    public void userSendsADELETERequestToDelete(String repositoryName) {
        response = given().
                header("Accept", Accept_Version).
                header("Authorization", Authorization).
                header("X-GitHub-Api-Version", X_GitHub_Api_Version)
                .when()
                .delete(reposEndpoint + "/" + repositoryName);

    }

    @When("user sends a PATCH request to update {string}")
    public void userSendsAPATCHRequestToUpdate(String repositoryName) {
        Map<String, Object> data = new HashMap<>();
        data.put("description", "This is an updated repository");

        System.out.println("data = " + data);
        response = given().
                header("Accept", Accept_Version).
                header("Authorization", Authorization).
                header("X-GitHub-Api-Version", X_GitHub_Api_Version).
                contentType("application/json").
                body(data)
                .when()
                .patch(reposEndpoint + "/" + repositoryName);

    }

    @Then("the response body should contain {string}")
    public void the_response_body_should_contain(String content) {
        response.then().body(containsString(content));
    }

    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(int statusCode) {
        response.then().statusCode(statusCode);
    }


}

