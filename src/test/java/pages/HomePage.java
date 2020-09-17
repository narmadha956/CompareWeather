package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    public HomePage(WebDriver driver) {

        PageFactory.initElements(driver, this);
    }

    @FindBy(id="h_sub_menu")
    public WebElement subMenu;

    @FindBy(xpath="//*[@class='topnav_cont']/a[contains(text(),'WEATHER')]")
    public WebElement weatherOption;

}
