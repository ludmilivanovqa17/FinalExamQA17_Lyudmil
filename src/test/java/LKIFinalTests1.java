import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class LKIFinalTests1 {
    private WebDriver driver;

    @BeforeMethod
    public void setUp()
    {
        System.setProperty("webdriver.chrome.driver", "C:\\LKI\\WebDrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://shop.pragmatic.bg");
    }

    @Test
    public void register() {

        WebElement myAccountButton = driver.findElement(By.xpath("//div[@id='top-links']//span[@class='caret']/preceding-sibling::span"));
        myAccountButton.click();

        driver.findElement(By.xpath(".//ul[@class='dropdown-menu dropdown-menu-right']/li[1]/a")).click();

        driver.findElement(By.id("input-firstname")).sendKeys("Lyudmil");
        driver.findElement(By.id("input-lastname")).sendKeys("Ivanov");

        String randomEmail = RandomStringUtils.randomAlphanumeric(7) + "@yahoo.com";
        WebElement inputEmail = driver.findElement(By.id("input-email"));
        inputEmail.sendKeys(randomEmail);

        driver.findElement(By.id("input-telephone")).sendKeys("0882928333");
        driver.findElement(By.id("input-password")).sendKeys("parola123!");
        driver.findElement(By.id("input-confirm")).sendKeys("parola123!");

        driver.findElement(By.name("agree")).click();

        driver.findElement(By.cssSelector(".btn-primary")).click();

        driver.findElement(By.cssSelector("#content div.pull-right a")).click();

        driver.findElement(By.xpath("//a[.='Modify your address book entries']")).click();

        driver.findElement(By.cssSelector(".btn-primary")).click();

        // Here starts entering the new address
        driver.findElement(By.id("input-firstname")).sendKeys("Petar");
        driver.findElement(By.id("input-lastname")).sendKeys("Georgiev");
        driver.findElement(By.id("input-address-1")).sendKeys("ul.Zaharen Zavod 11");
        driver.findElement(By.id("input-city")).sendKeys("Sofia");
        driver.findElement(By.id("input-postcode")).sendKeys("1410");
        driver.findElement(By.id("input-postcode")).sendKeys("1410");

        Select country = new Select(driver.findElement(By.id("input-country")));
        country.selectByVisibleText("Bulgaria");

        Select zone = new Select(driver.findElement(By.name("zone_id")));
        zone.selectByVisibleText("Sofia - town");

        driver.findElement(By.xpath("//input[@type='submit']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector(".alert-success")).getText(),"Your address has been successfully added", "There was a problem with adding new address");
    }

    @AfterMethod
    public void tearDown()
    {
        driver.quit();

    }
}
