package habr;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class ProfilePage extends BasePage{

    @FindBy(xpath = "//div[@class='layout__row layout__row_body']//a[2]")
    private WebElement bookmarks;

    @FindBy(xpath = "//div[@class='layout__row layout__row_body']//a[1]")
    private WebElement whois;

    @FindBy(xpath = "//ul[@class='content-list shortcuts_items']")
    private WebElement bookmarksList;


    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    public ProfilePage openBookmarks() {
        bookmarks.click();
        return this;
    }

    public ProfilePage openWhois() {
        whois.click();
        return this;
    }

    public List<String> getTitlesBookmarksList() {
        List<WebElement> elements = bookmarksList.findElements(By.className("post__title"));
        List<String> titles = new ArrayList<String>();

        for (WebElement element : elements) {
            titles.add(element.getText());
        }

        return titles;
    }
}
