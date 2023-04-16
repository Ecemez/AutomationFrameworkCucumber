package stepDefinitions;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.Base_PO;
import pageObjects.Login_PO;

public class Login_Page_Steps extends Base_PO {
    private Login_PO login_po;

    public Login_Page_Steps(Login_PO login_po){
        this.login_po = login_po;
    }
    @Given("I access the web driver university login page")
    public void i_access_the_web_driver_university_login_page() {
    login_po.navigateTo_WebDriverUniversity_Login_Page();
    }

    @When("I enter a username {}")
    public void i_enter_a_username(String username) {
        //driver.findElement(By.xpath("/html//input[@id='text']")).sendKeys(username);
        //sendKeys(By.xpath("/html//input[@id='text']"), username);
        login_po.setUsername(username);
    }

    @And("I enter a password {}")
    public void i_enter_a_password(String password) {
        //driver.findElement(By.xpath("/html//input[@id='password']")).sendKeys(password);
        //sendKeys(By.xpath("/html//input[@id='password']"), password);
        login_po.setPassword(password);
    }

    @And("I click on the login button")
    public void i_click_on_the_login_button() {
        //driver.findElement(By.xpath("/html//button[@id='login-button']")).click();
        login_po.clickOn_Login_Button();
    }
    @Then("I should be presented with validation successful message")
    public void i_should_be_presented_with_validation_successful_message() {
        //String txt = driver.switchTo().alert().getText();
        //Assert.assertEquals(txt,"validation succeeded");
        login_po.validate_SuccessfulLogin_Message();
        }
    @Then("I should be presented with the unsuccessful message")
    public void i_should_be_presented_with_unsuccessful_message() {
        //String txt = driver.switchTo().alert().getText();
        //Assert.assertEquals(txt,"validation failed");
        login_po.validate_UnsuccessfulLogin_Message();
    }

    @Then("I should be presented with the following login validation message {}")
    public void i_should_be_presented_with_the_following_login_validation_message(String expectedMessage) {
        //String txt = driver.switchTo().alert().getText();
        //Assert.assertEquals(txt,expectedMessage);
        waitForAlert_And_ValidateText(expectedMessage);
    }
}
