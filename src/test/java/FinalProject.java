import PageObjects.FinalProjectPOM;
import Utils.BaseMethods;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.*;
import Utils.ExcelUtils;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class FinalProject extends BaseMethods {
    WebDriver driver;
    FinalProjectPOM page;
    Faker fake = new Faker(new Locale("en-US"));

    static final String EXCEL_FILE_PATH = System.getProperty("user.dir") + "/src/test/resources/test_data/practiceData.xlsx";

    @BeforeClass
    void setUp() throws InterruptedException {
        driver = getWebDriver();
        page = new FinalProjectPOM();
        driver.manage().window().maximize();
        Thread.sleep(3000);
    }

    @DataProvider(name = "loadFormData")
    public static Object[][] dataLoad() throws Exception {
        return ExcelUtils.getTableArray(EXCEL_FILE_PATH);
    }

    @Test(dataProvider = "loadFormData", priority = 1)
    void registerTest(String fName, String lName, String password, String phone) {
        driver.get(page.pageUrl);
        driver.findElement(page.signin).click();
        driver.findElement(page.emailReg).sendKeys(fake.name().username() + "123@gmail.com");
        driver.findElement(page.createAccount).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(page.firstNameReg));
        driver.findElement(page.firstNameReg).sendKeys(fName);
        driver.findElement(page.lastNameReg).sendKeys(lName);
        driver.findElement(page.password).sendKeys(password);
        List<WebElement> days = driver.findElements(page.dobDays);
        Random rand = new Random();
        int i = rand.nextInt(days.size() - 1);
        days.get(i).click();
        List<WebElement> months = driver.findElements(page.dobMonths);
        int x = rand.nextInt(months.size() - 1);
        months.get(x).click();
        List<WebElement> years = driver.findElements(page.dobYears);
        int y = rand.nextInt(years.size() - 1);
        years.get(y).click();
        driver.findElement(page.address).sendKeys(fake.address().streetAddress());
        driver.findElement(page.city).sendKeys(fake.address().city());
        driver.findElement(page.state).sendKeys(fake.address().state(), Keys.ENTER);
        driver.findElement(page.zipCode).sendKeys(fake.address().zipCodeByState("NY"));
        driver.findElement(page.mobile).sendKeys(phone);
        driver.findElement(page.register).click();
        Assert.assertTrue(driver.findElement(page.myAccount).isDisplayed());
        wait.until(ExpectedConditions.visibilityOfElementLocated(page.logout));
        driver.findElement(page.logout).click();
    }

    @Test(priority = 2)
    void testLogin() {
        driver.get(page.pageUrl);
        driver.findElement(page.signin).click();
        String username = initializeProperties().getProperty("username");
        String password = initializeProperties().getProperty("password");
        driver.findElement(page.username).sendKeys(username);
        driver.findElement(page.userPassword).sendKeys(password);
        driver.findElement(page.signInBtn).click();
        Assert.assertTrue(driver.findElement(page.myAccount).isDisplayed());
    }

    @Test(priority = 3)
    void testPurchase() {
        driver.get(page.pageUrl);

        driver.findElement(page.womenTab).click();
        driver.findElement(page.sortSmall).click();
        List<WebElement> product = driver.findElements(page.products);
        int lastProduct = product.size() - 1;
        clickUsingJS(product.get(lastProduct));
        clickContinueShopping();


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".shopping_cart .ajax_cart_quantity")));
        WebElement cart = driver.findElement(By.cssSelector(".shopping_cart .ajax_cart_quantity"));
        Assert.assertEquals("1", cart.getText());
        System.out.println("Cart  quantity: " + cart.getText());
        driver.findElement(page.sortTops).click();
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("#enabled_filters"), "Tops"));
        List<WebElement> products = driver.findElements(page.products);
        System.out.println(products.size());
        for(int i = 0; i < products.size(); i++){
            clickUsingJS(products.get(i));
            clickContinueShopping();
            wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector(".shopping_cart .ajax_cart_quantity"),String.valueOf(i+2)));
            Assert.assertEquals(String.valueOf(i+2), cart.getText());
        }
        driver.findElement(page.cart).click();
        waitForProceed();
        waitForProceed();
        driver.findElement(page.termsCB).click();
        waitForProceed();

        String totalProduct = driver.findElement(page.totalProduct).getText().replace("$","");
        String totalShipping = driver.findElement(page.totalShipping).getText().replace("$","");
        double expected = Double.parseDouble(totalProduct) + Double.parseDouble(totalShipping);
        String actual = driver.findElement(page.totalPrice).getText().replace("$","");
        Assert.assertEquals(Double.valueOf(actual),expected);
        driver.findElement(page.bankPayment).click();

        driver.findElement(page.confirmOrder).click();
    }

    @AfterClass
    void wrapUp() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }

    void clickContinueShopping(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(page.continueShopping));
        driver.findElement(page.continueShopping).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(page.continueShopping));
    }
    void waitForProceed(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(page.proceed));
        driver.findElement(page.proceed).click();
    }
}
