import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;


public class MainTestPage {
    public WebDriver driver;
    private String PATH="https://www.google.com/";


    @FindBy(xpath="//input[@type='text']")
    private WebElement searchInput;

    @FindBy(xpath="//div[@class='FPdoLc lJ9FBc']//input[@class='gNO89b']")
    private WebElement searchButton;

    @FindBy(xpath="//a[contains(@href,'search')][contains(text(),'Картинки')]")
    private WebElement searchPicture;

    @FindBy(xpath = "//div[@class='mJxzWe']//img[contains(@src,'data')]")
    private List<WebElement> pictures;

    public void inputSearchString(String string)
    {
        searchInput.sendKeys(string);
    }

    @BeforeTest (alwaysRun = true)
    public void testSetup()  {
            System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");
            driver = new ChromeDriver();
            driver.get(PATH);
            PageFactory.initElements(driver, this);
        }

    @Test
    public void isImagePresent()
    {
        inputSearchString("ball");
        searchButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10000));
        wait.until(ExpectedConditions.visibilityOf(searchPicture));
        searchPicture.click();
        try
        { wait.until(ExpectedConditions.visibilityOf(pictures.get(0)));}
        catch (Exception E) {Assert.fail();}
        Assert.assertTrue(pictures.size()>0);
    }

    @AfterTest (alwaysRun = true)
    public void testFinish()
    {
       driver.quit();
    }

}
