package stepDefinitions;

import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

import java.util.Properties;
import java.util.logging.Logger;

public class BaseClass {

    public WebDriver driver;
    public LoginPage lp;
    public AddCustomerPage addCust;
    public SearchCustomerPage searchCust;
    public static Logger logger;
    public  Properties configProp;



    public static String randomstring(){

      String generatedString1 =  RandomStringUtils.randomAlphabetic(5);
      return (generatedString1);


    }

}
