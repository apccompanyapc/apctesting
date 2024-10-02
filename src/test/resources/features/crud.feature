@test
Feature: GitHub API Testing

  Background:
    Given User sets the base URL

   Scenario Outline: Create a new repository
    When user creates a "<repository>"
    Then the response status code should be 201
     Examples: Repository names
       | repository   |
       | Repository_1 |
       | Repository_2 |
       | Repository_3 |
       | Repository_4 |

  Scenario: Get repository list
    When user sends a GET request to ListEndpoint
    And response body should contain a list
    Then the response status code should be 200

  Scenario: Get single repository
    When user sends a GET request to get "Repository_1"
    And response body should have the name "Repository_1"
    Then the response status code should be 200

  Scenario: Update single repository
    When user sends a PATCH request to update "Repository_1"
    Then the response body should contain "This is an updated repository"
    Then the response status code should be 200

  Scenario Outline: Delete single repository
    When user sends a DELETE request to delete "<repository>"
    Then the response status code should be 204
    Examples: Repository names
      | repository   |
      | Repository_1 |
      | Repository_2 |
      | Repository_3 |
      | Repository_4 |

