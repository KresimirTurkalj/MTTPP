import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations. AfterMethod ;
import org.testng.annotations. BeforeMethod ;
import org.testng.annotations.Test;

public class MTTPPTest {

    public WebDriver driver;
    public String siteStartURL = "http://www.google.com";
    public String loginStartURL = "https://www.google.com/intl/hr/gmail/about/#";

    @BeforeMethod
    public void setupTest() {
        System. setProperty ( "webdriver.chrome.driver" , "C:/drivers/chromedriver.exe" );
        driver = new ChromeDriver();
    }
    @Test
    public void gDisconnect() throws InterruptedException{
        driver.navigate().to(loginStartURL);
        WebElement login = driver.findElement(By.xpath("/html/body/nav/div/a[2]"));
        login.click();
        Thread. sleep ( 2000 );
        WebElement loginText = driver.findElement(By.xpath("//*[@id=\"headingText\"]/content"));
        WebElement input = driver.findElement(By.xpath("//*[@id=\"identifierId\"]"));
        input.sendKeys("mttpptest");
        WebElement button = driver.findElement(By.xpath("//*[@id=\"identifierNext\"]/content/span"));
        button.click();
        Thread.sleep(2000);
        input = driver.findElement(By.xpath("//*[@id=\"password\"]/div[1]/div/div[1]/input"));
        input.sendKeys("TESTaccount");
        button = driver.findElement(By.xpath("//*[@id=\"passwordNext\"]/content/span"));
        button.click();
        Thread.sleep(5000);
        button = driver.findElement(By.xpath("//*[@id=\"gb\"]/div[2]/div[3]/div/div[2]/div/a"));
        button.click();
        Thread.sleep(500);
        button = driver.findElement(By.xpath("//*[@id=\"gb_71\"]"));
        button.click();
        Thread.sleep(5000);
        WebElement responseText = driver.findElement(By.xpath("//*[@id=\"headingText\"]/content"));
        Assert.assertEquals(responseText.getText(), "Test Account");

    }
    @Test
    public void gCorrectLogin() throws InterruptedException{
        driver.navigate().to(loginStartURL);
        WebElement login = driver.findElement(By.xpath("/html/body/nav/div/a[2]"));
        login.click();
        Thread. sleep ( 2000 );
        WebElement loginText = driver.findElement(By.xpath("//*[@id=\"headingText\"]/content"));
        WebElement input = driver.findElement(By.xpath("//*[@id=\"identifierId\"]"));
        input.sendKeys("mttpptest");
        WebElement button = driver.findElement(By.xpath("//*[@id=\"identifierNext\"]/content/span"));
        button.click();
        Thread.sleep(2000);
        input = driver.findElement(By.xpath("//*[@id=\"password\"]/div[1]/div/div[1]/input"));
        input.sendKeys("TESTaccount");
        button = driver.findElement(By.xpath("//*[@id=\"passwordNext\"]/content/span"));
        button.click();
        Thread.sleep(5000);
        loginText = driver.findElement(By.xpath("//*[@id=\":48\"]/div/div[2]/span/a"));
        Assert.assertEquals(loginText.getText(),"Pristigla pošta");

    }
    @Test
    public void gIncorrectPassLogin() throws InterruptedException{
        driver.navigate().to(loginStartURL);
        WebElement login = driver.findElement(By.xpath("/html/body/nav/div/a[2]"));
        login.click();
        Thread. sleep ( 2000 );
        WebElement loginText = driver.findElement(By.xpath("//*[@id=\"headingText\"]/content"));
        Assert.assertEquals(loginText.getText(),"Prijava");
        WebElement input = driver.findElement(By.xpath("//*[@id=\"identifierId\"]"));
        input.sendKeys("mttpptest");
        WebElement button = driver.findElement(By.xpath("//*[@id=\"identifierNext\"]/content/span"));
        button.click();
        Thread.sleep(2000);
        input = driver.findElement(By.xpath("//*[@id=\"password\"]/div[1]/div/div[1]/input"));
        input.sendKeys("WrongPassword");
        button = driver.findElement(By.xpath("//*[@id=\"passwordNext\"]/content/span"));
        button.click();
        Thread.sleep(2000);
        WebElement responseText = driver.findElement(By.xpath("//*[@id=\"password\"]/div[2]/div[2]/div"));
        Assert.assertEquals(responseText.getText(),"Zaporka je pogrešna. Pokušajte ponovo ili kliknite stavku Zaboravljena zaporka da biste je poništili.");
    }
    @Test
    public void gIncorrectMailLogin() throws InterruptedException{
        //2
        driver.navigate().to(loginStartURL);
        WebElement login = driver.findElement(By.xpath("/html/body/nav/div/a[2]"));
        login.click();
        Thread. sleep ( 2000 );
        WebElement loginText = driver.findElement(By.xpath("//*[@id=\"headingText\"]/content"));
        Assert.assertEquals(loginText.getText(),"Prijava");
        WebElement input = driver.findElement(By.xpath("//*[@id=\"identifierId\"]"));
        input.sendKeys("IncorrectMailForm");
        WebElement button = driver.findElement(By.xpath("//*[@id=\"identifierNext\"]/content/span"));
        button.click();
        Thread. sleep ( 2000 );
        WebElement responseText = driver.findElement(By.xpath("//*[@id=\"view_container\"]/div/div/div[2]/div/div[1]/div/form/content/section/div/content/div[1]/div/div[2]/div[2]/div"));
        Assert.assertEquals(responseText.getText(),"Nismo uspjeli pronaći vaš Google račun");
    }
    @Test
    public void gSite() throws InterruptedException {
        //1
        driver.navigate().to(siteStartURL);
        WebElement linkText = driver.findElement(By.xpath ( "//*[@id=\"gbw\"]/div/div/div[1]/div[1]/a" ));
        linkText.click();
        Thread. sleep ( 2000 );
        String testLink = driver.getCurrentUrl();
        boolean check = testLink.contains("gmail/about/#");
        Assert.assertTrue(check, "URL doesn't contain 'gmail/about/#'");
        System.out.print("Link "+testLink+" was opened.");
    }
    //-----------------------------------MTTPPTest TearDown-----------------------------------
    @AfterMethod
    public void teardownTest() {
        driver .quit();
    }
}
