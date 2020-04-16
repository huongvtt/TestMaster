package Facebook;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChoosingBirthday {
    String HOME_PAGE = "https://www.facebook.com/";
    String WEB_DRIVER = "webdriver.chrome.driver";
    String PATH_CHROME_DRIVER = "C:\\WebDriver\\chromedriver.exe";
    WebDriver driver;

    @Before
    public void BeforeTest(){
        System.setProperty(WEB_DRIVER, PATH_CHROME_DRIVER);
        this.driver = new ChromeDriver();
        this.driver.get(HOME_PAGE);
        this.driver.manage().window().maximize();
    }

    @After
    public void AfterTest(){
        this.driver.close();
    }

    @Test
    public void SelectBirthday(){
        Birthday myBirthday = new Birthday(this.driver);
        myBirthday.withDate("31").andMonth("3").andYear("1990").perform();
        Assert.assertEquals("31", myBirthday.getSelectedDay());
        Assert.assertEquals("3", myBirthday.getSelectedMonth());
        Assert.assertEquals("1990", myBirthday.getSelectedYear());
    }
}
