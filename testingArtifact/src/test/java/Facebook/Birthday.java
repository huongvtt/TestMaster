package Facebook;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Birthday {
    static WebDriver driver;
    By byDay = new By.ByCssSelector("select#day");
    By byMonth = new By.ByCssSelector("select#month");
    By byYear = new By.ByCssSelector("select#year");

    public Birthday(WebDriver driver) {
        this.driver = driver;
    }

    public BirthdaySelector withDate(String day){
        return new BirthdaySelector(day);
    }

    public class BirthdaySelector{
        String _day;
        String _month;
        String _year;

        public BirthdaySelector(String day) {
            this._day = day;
        }

        public BirthdaySelector andMonth(String month) {
            this._month = month;
            return this;
        }

        public BirthdaySelector andYear(String year) {
            this._year = year;
            return this;
        }

        public void perform(){
            Select selectDay = new Select(Birthday.driver.findElement(byDay));
            Select selectMonth = new Select(Birthday.driver.findElement(byMonth));
            Select selectYear = new Select(Birthday.driver.findElement(byYear));

            selectDay.selectByValue(this._day);
            selectMonth.selectByValue(this._month);
            selectYear.selectByValue(this._year);
        }
    }

    public String getSelectedDay(){
        WebElement myDay = Birthday.driver.findElement(byDay);
        return myDay.getAttribute("value");
    }

    public String getSelectedMonth(){
        WebElement myMonth = Birthday.driver.findElement(byMonth);
        return myMonth.getAttribute("value");
    }

    public String getSelectedYear(){
        WebElement myYear = Birthday.driver.findElement(byYear);
        return myYear.getAttribute("value");
    }
}
