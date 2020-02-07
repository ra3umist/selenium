package habr.test;

import habr.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.List;
import java.util.concurrent.TimeUnit;

public class PostPageTest {
    PostPage postPage;
    HomePage homePage;
    WebDriver driver;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\Downloads\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        //postPage = new PostPage(driver);
        homePage = new HomePage(driver);
    }

    @Test
    public void jGrowlMessageTest() {
        homePage.openPage();
        LogInPage logInPage = homePage.switchToLogInPage();
        homePage = logInPage.login("", "");
        PostPage postPage = homePage.openPostByPosition(1);
        if(postPage.addPostToBookmarks()) {
            String textJGrowl = postPage.getJGrowlText();
            Assert.assertEquals("Публикация добавлена в закладки", textJGrowl);
        } else {
            postPage.removePostFromBookmarks();
            String textJGrowl = postPage.getJGrowlText();
            Assert.assertEquals("Публикация удалена из закладок", textJGrowl);
        }
    }
    //Публикация удалена из закладок
    //Публикация добавлена в закладки

    @Test
    public void addPostToBookmarksTest() {
        homePage.openPage();
        LogInPage logInPage = homePage.switchToLogInPage();
        homePage = logInPage.login("", "");
        PostPage postPage = homePage.openPostByPosition(1);
        String postTitle = postPage.getPostTitle();
        if(postPage.addPostToBookmarks()) {
            postPage.closeJGrowl();
        }
        ProfilePage profilePage = homePage.switchToProfilePage();
        profilePage.openBookmarks();
        List<String> titles = profilePage.getTitlesBookmarksList();
        Assert.assertTrue(titles.contains(postTitle));
    }

    @After
    public void tearDown() {
        //driver.quit();
    }
}
