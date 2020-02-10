package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddCustomerPage {

   public WebDriver ldriver;

    public AddCustomerPage(WebDriver rdriver)
    {
        ldriver = rdriver;
        PageFactory.initElements(ldriver,this);
    }

    By lnkCustomers_menu =By.xpath("//a[@href='#']//span[contains(text(),'Customers')]");
   By lnkCustomers_menuitem = By.xpath("//a[@ href='/Admin/Customer/List']//span[@class = 'menu-item-title'][contains(text(),'Customers')]");
   By btnAddnew = By.xpath("//a[@class='btn bg-blue' ]");
  By txtEmail= By.xpath("//input[@id='Email']");
By txtPassword = By.xpath("//input[@id='Password']");
By txtFirstname = By.xpath("//input[@id='FirstName']");
By txtLastname = By.xpath("//input[@id='LastName']");
By rdMaleGender = By.id("Gender_Male");
By rdFemaleGender = By.id("Gender_Female");
By txtDob = By.xpath("//input[@id='DateOfBirth']");
By txtCompany = By.xpath("//input[@id='Company']");
By txtCustomerRoles  = By.xpath("//div[@class='k-multiselect-wrap k-floatwrap']");
By lstItemAdministrator = By.xpath("//li[contains(text(),'Administrators')]") ;
By lstItemModerator = By.xpath("//li[contains(text(),'Forum Moderators')]") ;
By lstItemGuests = By.xpath("//li[contains(text(),'Guests')]") ;
By lstItemRegistered = By.xpath("//li[contains(text(),'Registered')]") ;
By lstItemVendors = By.xpath("//li[contains(text(),'Vendors')]") ;
By drpMgrofVendor = By.xpath("//*[@id='VendorId']");
By txtAdminContent = By.xpath("//textarea[@id='AdminComment']");
By btnSave = By.xpath("//button[@name='save']");

public String getPageTitle()
{
    return ldriver.getTitle();
}

public void clickOnCustomerMenu(){
    ldriver.findElement(lnkCustomers_menu).click();
}
public void clickOnCustomerMenuItem(){
ldriver.findElement(lnkCustomers_menuitem).click();

}
public void clickonAddnew(){
ldriver.findElement(btnAddnew).click();
}
public void setEmail(String email){
    ldriver.findElement(txtEmail).sendKeys(email);
    
}

public void setPassword(String password){
    ldriver.findElement(txtPassword).sendKeys(password);

}
public void setFirstName(String fname){
   ldriver.findElement(txtFirstname).sendKeys(fname);

}

public void setLastName(String lname){
    ldriver.findElement(txtLastname).sendKeys(lname);
}
public void setGender(String gender)
{
    if(gender.equals("Male"))
    {
        ldriver.findElement(rdMaleGender).click();
    }
    else if(gender.equals("Female"))
    {

        ldriver.findElement(rdFemaleGender).click();
    }
    else
    {
        ldriver.findElement(rdMaleGender).click();

    }


}

public void setDob(String dob){

    ldriver.findElement(txtDob).sendKeys(dob);

}

public void setCompany(String compname)
{
    ldriver.findElement(txtCompany).sendKeys(compname);

}
public void setManagerofVendor(String value)
{
    Select drp = new Select(ldriver.findElement(drpMgrofVendor));
    drp.selectByVisibleText(value);

}

public void setCustomerRoles(String role) throws InterruptedException {
    if (!role.equals("Vendors")) {
       ldriver.findElement(By.xpath("//*[@id='SelectedCustomerRoleIds_taglist']/li/span[@class='k-icon k-delete']")).click();

    }
    ldriver.findElement(txtCustomerRoles).click();

    WebElement listitem;
    Thread.sleep(3000);

    if (role.equals("Administrators")) {
        listitem = ldriver.findElement(lstItemAdministrator);
    } else if (role.equals("Guests")) {
        listitem = ldriver.findElement(lstItemGuests);
    } else if (role.equals("Registered")) {

        listitem = ldriver.findElement(lstItemRegistered);
    } else if (role.equals("Vendors")) {

        listitem = ldriver.findElement(lstItemVendors);
    } else {
        listitem = ldriver.findElement(lstItemGuests);
    }

    // listitem.click();

    JavascriptExecutor js = (JavascriptExecutor) ldriver;
    js.executeScript("arguments[0].click();", listitem);

}


    public void setAdminContent(String content)
    {
           ldriver.findElement(txtAdminContent).sendKeys(content);


    }

    public void  clickOnSave()
    {

        ldriver.findElement(btnSave).click();
    }



    
}











