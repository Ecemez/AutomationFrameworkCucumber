@login @regression

  Feature: Web driver University - Login Page

    Background:
      Given I access the web driver university login page

    @login1
    Scenario: Validation of Successful Login
      When I enter a username webdriver
      And I enter a password webdriver123
      And I click on the login button
      Then I should be presented with validation successful message

    Scenario: Validation of Unsuccessful Login
      When I enter a username webdriver
      And I enter a password password1234
      And I click on the login button
      Then I should be presented with the unsuccessful message

    @smoke @ignore
   Scenario Outline: Validate - Successful & Unsuccessful Login
     When I enter a username <username>
     And I enter a password <password>
     And I click on the login button
     Then I should be presented with the following login validation message <loginValidationMessage>

     Examples:
     | username  | password     | loginValidationMessage |
     | webdriver | webdriver123 | validation succeeded   |
     | webdriver | password123  | validation failed      |
     | joe_blogs | password1    | validation failed      |