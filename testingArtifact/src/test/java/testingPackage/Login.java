package testingPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login {
    static WebDriver driver;
    By byPopover = new By.ByCssSelector("div.popover-dialog");
    By byNotice = new By.ByCssSelector("div#popover div.body-message>b");
    By byUser = new By.ByCssSelector("input[type=email]");
    By byPassword = new By.ByCssSelector("input[type=password]");
    By byLogin = new By.ByCssSelector("button.btn-login");
    By byTooltip = new By.ByCssSelector("div.tooltip-inner");

    public Login(WebDriver driver){
        this.driver = driver;
    }

    public LoginBuilder withUserName(String userName){
        return new LoginBuilder(userName);
    }

    public class LoginBuilder {
        String _username;
        String _password;

        public LoginBuilder(String username){
            this._username = username;
        }

        public LoginBuilder andPassword(String password){
            this._password = password;
            return this;
        }

        public void perform(){
            WebElement txtUser = Login.driver.findElement(byUser);
            WebElement txtPwd = Login.driver.findElement(byPassword);
            WebElement btnLogin = Login.driver.findElement(byLogin);

            txtUser.clear();
            txtUser.sendKeys(this._username);
            txtPwd.clear();
            txtPwd.sendKeys(this._password);
            btnLogin.click();
        }
    }

    public boolean isLoginInvalidEmail(){
        WebDriverWait waiter = new WebDriverWait(this.driver, 30);
        WebElement txtTooltip = waiter.until(ExpectedConditions.presenceOfElementLocated(byTooltip));
        WebElement txtUser = Login.driver.findElement(byUser);
        boolean isTooltipDisplayed = txtTooltip.getText().equals("Hãy nhập E-mail ở định dạng: user@email.com");
        boolean isInputEmailHighlighted  = txtUser.getAttribute("class").contains("error");
        return  (isInputEmailHighlighted && isTooltipDisplayed);
    }

    public boolean isLoginInvalidPassword(){
        WebDriverWait waiter = new WebDriverWait(this.driver, 30);
        WebElement txtTooltip = waiter.until(ExpectedConditions.presenceOfElementLocated(byTooltip));
        WebElement txtPwd = Login.driver.findElement(byPassword);
        boolean isTooltipDisplayed = txtTooltip.getText().equals("Mật khẩu chỉ nhận giá trị chữ và số");
        boolean isInputPasswordHighlighted  = txtPwd.getAttribute("class").contains("error");
        return  (isInputPasswordHighlighted && isTooltipDisplayed);
    }

    public boolean isLoginFail(){
        WebDriverWait waiter = new WebDriverWait(this.driver, 30);
        boolean isPopoverDisplayed = waiter.until(ExpectedConditions.attributeContains(byPopover, "aria-hidden", "false"));
        boolean isMessageMatched = driver.findElement(byNotice).getText().equals("Tên đăng nhập hoặc Mật khẩu không đúng.");
        return (isPopoverDisplayed && isMessageMatched);
    }
}
