package Actions;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;

import javax.swing.text.ViewFactory;

public class HomeAction {

    private HomePage homePageObjects;
    WebDriver driver;

    public HomeAction(WebDriver driver) {
        homePageObjects = new HomePage(driver);
        this.driver=driver;
    }

    public void navigateToWeatherPage()
    {
        int count = 5;
        while(count>0)
        {
        homePageObjects.subMenu.click();
        try{
            if(homePageObjects.weatherOption.isDisplayed())
            {
                homePageObjects.weatherOption.click();
                return;
            }
        }catch(Exception ns)
        {
            ns.printStackTrace();
            WebDriverWait wait = new WebDriverWait(driver, 30);
            WebElement submenu = wait.until(
                    ExpectedConditions.elementToBeClickable(homePageObjects.subMenu));
            count--;
        }
        }
    }

}
