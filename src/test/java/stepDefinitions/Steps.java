package stepDefinitions;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public class Steps extends BaseClass {

     @Before
    public void setup() throws IOException {
         logger= Logger.getLogger("nopCommerce");
         PropertyConfigurator.configure("log4j.properties");

         //Reading Properties
        configProp = new Properties();
         FileInputStream configPropFile = new FileInputStream("config.properties");
        configProp.load(configPropFile);

        String br = configProp.getProperty("browser");

        if(br.equals("chrome")) {
               System.setProperty("webdriver.chrome.driver", configProp.getProperty("chromepath") );
           driver = new ChromeDriver();
       }
        else if(br.equals("firefox")) {
             System.setProperty("webdriver.gecko.driver", configProp.getProperty("firefoxpath") );
             driver = new FirefoxDriver();
         }
        else if(br.equals("ie")) {
               System.setProperty("webdriver.ie.driver", configProp.getProperty("iepath") );
               driver = new InternetExplorerDriver();
           }
         logger.info("********Launching browser********");

    }


    @Given("User Launch Chrome browser")
    public void user_Launch_Chrome_browser() {

        lp=new LoginPage(driver);

    }

    @When("User opens URL {string}")
    public void user_opens_URL(String url) {
       logger.info("********Opening URL********");
        driver.get(url);
        driver.manage().window().maximize();

    }

    @When("User enters Email as {string} and Password as {string}")
    public void user_enters_Email_as_and_Password_as(String email, String password) {
        logger.info("********Providing Login Details********");
        lp.setUserName(email);
      lp.setPassword(password);
    }

    @When("Click on Login button")
    public void click_on_Login_button() throws InterruptedException {
        logger.info("********Started Login********");
        lp.clickLogin();
        Thread.sleep(3000);

    }

    @Then("Page Title should be {string}")
    public void page_Title_should_be(String title) throws InterruptedException {

        if(driver.getPageSource().contains("Login was unsuccessful.")){
            driver.close();
            logger.info("********Login Passed********");
            Assert.assertTrue(false);
        }
        else {
            logger.info("********Login failed********");
            Assert.assertEquals(title, driver.getTitle());
        }Thread.sleep(3000);

    }

    @When("User click on Log out link")
    public void user_click_on_Log_out_link() throws InterruptedException {
        logger.info("********Click on Logout Link********");
      lp.clickLogout();
      Thread.sleep(3000);
    }

    @Then("close browser")
    public void close_browser() {
        logger.info("********Closing the browser********");
      driver.quit();
    }

    @Then("User can view Dashboard")
    public void user_can_view_Dashboard() {

        addCust = new AddCustomerPage(driver);
        Assert.assertEquals("Dashboard / nopCommerce administration",addCust.getPageTitle());

    }

    @When("User click on customers Menu")
    public void user_click_on_customers_Menu() throws InterruptedException {
        Thread.sleep(3000);
        addCust.clickOnCustomerMenu();

    }

    @When("click on customers Menu Item")
    public void click_on_customers_Menu_Item() throws InterruptedException {

        Thread.sleep(3000);

        addCust.clickOnCustomerMenuItem();

    }

    @When("click on Add new button")
    public void click_on_Add_new_button() throws InterruptedException {
        addCust.clickonAddnew();
        Thread.sleep(2000);

    }

    @Then("User can view Add new customer page")
    public void user_can_view_Add_new_customer_page() {

        Assert.assertEquals("Add a new customer / nopCommerce administration",addCust.getPageTitle());

    }

    @When("User enter customer info")
    public void user_enter_customer_info() throws InterruptedException {

        logger.info("********Adding new customer********");
        logger.info("********Providing customer details********");
       String email = randomstring()+"@gmail.com";

       addCust.setEmail(email);
       addCust.setPassword("test123");
       addCust.setCustomerRoles("Guests");
       Thread.sleep(3000);
       addCust.setManagerofVendor("Vendor 2");
       addCust.setGender("Male");
       addCust.setFirstName("Pawan");
       addCust.setLastName("Kalyan");
       addCust.setDob("1/02/2020");
       addCust.setCompany("BusyQA");
       addCust.setAdminContent("This is for testing.......");

    }

    @When("click on save button")
    public void click_on_save_button() throws InterruptedException {
        logger.info("********Saving Customer details********");
        addCust.clickOnSave();
        Thread.sleep(3000);

    }

    @Then("User can view confirmation message {string}")
    public void user_can_view_confirmation_message(String string) {
     Assert.assertTrue(driver.findElement(By.tagName("body")).getText()
             .contains("The new customer has been added successfully."));


    }

    //steps for searching a customer using Email ID......

    @When("Enter customer Email")
    public void enter_customer_Email() {

        logger.info("********Searching a customer by email ID********");
        searchCust = new SearchCustomerPage(driver);
        searchCust.setEmail("victoria_victoria@nopCommerce.com");

    }

    @When("click on search button")
    public void click_on_search_button() throws InterruptedException {
        searchCust.clickSearch();
        Thread.sleep(3000);

    }

    @Then("user should found Email in the Search Table")
    public void user_should_found_Email_in_the_Search_Table() {
      boolean status = searchCust.searchCustomerByEmail("victoria_victoria@nopCommerce.com");
      Assert.assertEquals(true,status);

    }

    //Steps for searching customer by First Name and Last Name

    @When("Enter customer FirstName")
    public void enter_customer_FirstName() {
        logger.info("********Searching Customer by Name********");
        searchCust = new SearchCustomerPage(driver);
        searchCust.setFirstName("Victoria");

    }

    @When("Enter customer LastName")
    public void enter_customer_LastName() {
        searchCust.setLastName("Terces");

    }

    @Then("User should found name in the Search table")
    public void user_should_found_name_in_the_Search_table() {
        boolean status =searchCust.searchCustomerByName("Victoria Terces");
        Assert.assertEquals(true,status);

    }




}
