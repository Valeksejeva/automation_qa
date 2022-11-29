import lv.acodemy.constants.Generic;
import lv.acodemy.page_object.InventoryPage;
import lv.acodemy.page_object.LoginPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestSauceDemo {

    ChromeDriver driver;
    LoginPage loginPage;

    InventoryPage inventoryPage;

    @BeforeMethod(description = "Preconditions")
    public void initialize() {
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
    }


    @Test(description = "Happy path: Test authorization with standard user")
    public void authorizeTest() {
        driver.get(Generic.SAUCE_URL);
        loginPage.authorize("standard_user","secret_sauce");
        Assert.assertEquals(inventoryPage.itemElementCount(), 6);
    }
    @Test(description = "Failure: Test authorization error message with incorrect credentials")
    public void invalidCredentialTest() {
        driver.get(Generic.SAUCE_URL);
        loginPage.authorize("standard_user" , "incorrect_pw");
        Assert.assertEquals(loginPage.getErrorMessage() ,"Epic sadface: Username and password do not match any user in this service");
    }

    @AfterMethod
    public void tearDown(){
        driver.close();
        driver.quit();
    }

    //1.Define way how to find element
    //2.Create method that finds this element and return next
    // public String getErrorMessage() { return driver.findElement().getText();
    //3.Create new test(name it,open browser , then authorize , call it with incorrect credentials)
    //4.do check for message, assert.Equals(loginPage.getErrorMessage(), "error message text";
}
