package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class EbayStorePage extends BasePage{

    @FindBy(xpath = "//*[@class='lvtitle']//a")
    private List<WebElement> itemsList;

    @FindBy(xpath = "//li[@class='lvprice prc']")
    private List<WebElement> pricesList;

    @FindBy(xpath = "//td[@class='pagn-next']//a[@aria-disabled='true']")
    private WebElement disabledNext;

    @FindBy(xpath = "//td[@class='pagn-next']//a")
    private WebElement nextButton;

    @FindBy(id = "FooterLegalNoticeContainer")
    private WebElement updatedInformation;

    /**
     * LoginPage constructor
     *
     * @param driver WebDriver instance
     */
    public EbayStorePage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageLoaded() {
        return waitUntilElementDisplayed(updatedInformation).isDisplayed();
    }

    public List<String> scrapeStore() {

        List<String> titles = new ArrayList<>();

        do {
            for (WebElement item : itemsList) {
                titles.add(item.getText());
            }
            if (isElementDisplayed(disabledNext, 1))
                break;
            nextButton.click();
            isPageLoaded();


        } while (true);

        return titles;
    }
}
