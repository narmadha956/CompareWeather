package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MapPage {

    public MapPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
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
