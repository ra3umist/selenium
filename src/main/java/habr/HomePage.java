package habr;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import java.util.List;
import java.util.concurrent.TimeUnit;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void openPage() {
        driver.get("https://habr.com/ru/");
    }

    //Открыть пост по позиции на странице
    public PostPage openPostByPosition(int position) {
        if(position == 2 || position == 6 || position == 13 || position == 17 || position == 21) return null; // блоки с вакансиями и т.п.
        String xpath = String.format("//div[@class='posts_list']//li[%d]//a[@class='btn btn_x-large btn_outline_blue post__habracut-btn']", position);
        WebElement element = driver.findElement(By.xpath(xpath));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
        return new PostPage(driver);
    }
}
