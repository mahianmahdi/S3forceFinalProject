package PageObjects;

import Utils.BaseMethods;
import org.openqa.selenium.By;

public class FinalProjectPOM extends BaseMethods {
    public By signin = new By.ByCssSelector(".login");
    public By emailReg = new By.ByCssSelector("#email_create");
    public By createAccount = new By.ByCssSelector("#SubmitCreate");
    public By firstNameReg = new By.ByCssSelector("#customer_firstname");
    public By lastNameReg = new By.ByCssSelector("#customer_lastname");
    public By password = new By.ByCssSelector("#passwd");
    public By dobDays = new By.ByCssSelector("select#days option");
    public By dobMonths = new By.ByCssSelector("select#months option");
    public By dobYears = new By.ByCssSelector("select#years option");
    public By address = new By.ByCssSelector("#address1");
    public By city = new By.ByCssSelector("#city");
    public By state = new By.ByCssSelector("#id_state");
    public By zipCode = new By.ByCssSelector("input#postcode");
    public By mobile = new By.ByCssSelector("#phone_mobile");
    public By register = new By.ByCssSelector("#submitAccount");
    public By myAccount = new By.ByCssSelector(".info-account");
    public By logout = new By.ByCssSelector(".logout");
    public By username = new By.ByCssSelector("#email");
    public By userPassword = new By.ByCssSelector("#passwd");
    public By signInBtn = new By.ByCssSelector("#SubmitLogin");
    public By womenTab = new By.ByXPath("//*[text()='Women']");
    public By sortSmall = new By.ByCssSelector("#layered_id_attribute_group_1");
    public By products = new By.ByXPath("//span[text()='Add to cart']");
    public By continueShopping = new By.ByCssSelector(".continue");
    public By sortTops = new By.ByCssSelector("#layered_category_4");
    public By cart = new By.ByXPath("//b[text()='Cart']");
    public By proceed = new By.ByXPath("(//*[contains(text(),'Proceed')])[2]");
    public By termsCB = new By.ByCssSelector("#cgv");
    public By totalProduct = new By.ByCssSelector("#total_product");
    public By totalShipping = new By.ByCssSelector("#total_shipping");
    public By totalPrice = new By.ByCssSelector("#total_price");
    public By bankPayment = new By.ByCssSelector(".bankwire");
    public By confirmOrder = new By.ByCssSelector("button span i");

    public String pageUrl = "http://automationpractice.com/";
}
