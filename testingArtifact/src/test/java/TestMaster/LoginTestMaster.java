package TestMaster;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTestMaster {
    String HOME_PAGE = "http://testmaster.vn/admin";
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
    public void LoginNullEmail(){
        Login myLogin = new Login(this.driver);
        myLogin.withUserName("").andPassword("1234").perform();
        Assert.assertEquals(myLogin.isLoginInvalidEmail(), true);
    }

    @Test
    public void LoginInvalidEmail(){
        Login myLogin = new Login(this.driver);
        myLogin.withUserName("demo").andPassword("1234").perform();
        Assert.assertEquals(myLogin.isLoginInvalidEmail(), true);
    }

    @Test
    public void LoginNullPassword(){
        Login myLogin = new Login(this.driver);
        myLogin.withUserName("demo@test.com").andPassword("").perform();
        Assert.assertEquals(myLogin.isLoginInvalidPassword(), true);
    }

    @Test
    public void LoginWrongAccount(){
        Login myLogin = new Login(this.driver);
        myLogin.withUserName("demo@test.com").andPassword("1234").perform();
        Assert.assertEquals(myLogin.isLoginFail(),true);
    }
}
