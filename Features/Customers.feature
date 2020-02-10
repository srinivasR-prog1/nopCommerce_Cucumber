Feature:Customers
 Background: Below are the common steps for each scenario
   Given User Launch Chrome browser
   When User opens URL "https://admin-demo.nopcommerce.com/login"
   And User enters Email as "admin@yourstore.com" and Password as "admin"
   And Click on Login button
   Then User can view Dashboard
   When User click on customers Menu
   And click on customers Menu Item

  @sanity
  Scenario: Add a new Customer

    When click on Add new button
    Then User can view Add new customer page
    When User enter customer info
    And click on save button
    Then User can view confirmation message "The new customer has been added successfully."
    And close browser

  @regression
  Scenario: Search Customer by EmailID
    When Enter customer Email
    And click on search button
    Then user should found Email in the Search Table
    And close browser

  @regression
  Scenario: Search Customer by Name
    When Enter customer FirstName
    And Enter customer LastName
    Then click on search button
    Then User should found name in the Search table
    And close browser