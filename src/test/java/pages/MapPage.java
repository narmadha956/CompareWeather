package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MapPage {

    public MapPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        new WebDriverWait(driver, 180).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

    }

    @FindBy(id="searchBox")
    public WebElement citySearch;

    @FindBy(id="temperatureContainer")
    public WebElement countryIcon;

    @FindBy(className = "cityText")
    public List<WebElement> citiesDisplayed;

    @FindBy(xpath = "//b[contains(text(),'Temp in Degrees')]")
    public WebElement icon;

}
