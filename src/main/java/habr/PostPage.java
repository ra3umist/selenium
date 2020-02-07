package habr;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PostPage extends BasePage {

    @FindBy(xpath = "//span[@class='post__title-text']")
    private WebElement postTitle;

    @FindBy(xpath = "//ul[@id='infopanel_post_487290']//span[@class='btn_inner']")
    private WebElement bookmark;

    @FindBy(xpath = "//a[@class='post-stats__comments-link']")
    private WebElement comments;

    @FindBy(xpath = "//textarea[@id='comment_text']")
    private WebElement commentField;

    @FindBy(xpath = "//form[@id='comments_form']//button[@name='send'][contains(text(),'Send')]")
    private WebElement sendButton;

    @FindBy(xpath = "//button[@name='preview']")
    private WebElement previewButton;

    public PostPage(WebDriver driver) {
        super(driver);
    }

    //Открыть свежий пост
    public void openFirstPost() {
        HomePage homePage = new HomePage(driver);
        homePage.openPage();
        homePage.openPostByPosition(1);
    }

    public String getPostTitle() {
        return postTitle.getText();
    }

    public boolean addPostToBookmarks() {
        if (getBookmarksButtonTitle().equals("Добавить в закладки")) {
            bookmark.click();
            return true;
        }
        return false;
    }

    public boolean removePostFromBookmarks() {
        if (getBookmarksButtonTitle().equals("Удалить из закладок")) {
            bookmark.click();
            return true;
        }
        return false;
    }

    public String getBookmarksButtonTitle() {
        WebElement element = driver.findElement(By.xpath("//button[@data-post-type='publish_ugc_ru,h_20738,h_21894,h_21896,f_management,f_geektimes']"));
        return element.getAttribute("title");
    }

    public PostPage typeComment(String comment) {
        commentField.sendKeys(comment);
        return this;
    }

    public PostPage sendComment(String comment) {
        commentField.sendKeys(comment);
        if(sendButton.isEnabled()){
            sendButton.click();
        } else {
            //...
        }
        return this;
    }
}
