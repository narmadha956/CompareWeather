package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MapPage {

    public MapPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id="searchBox")
    public WebElement citySearch;

    @FindBy(id="temperatureContainer")
    public WebElement countryIcon;

}
