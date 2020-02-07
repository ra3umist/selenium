package habr.test;

import habr.HomePage;
import habr.LogInPage;
import habr.RegistrationPage;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class LogInPageTest {
    LogInPage logInPage;
    WebDriver driver;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\Desktop\\chromedriver.exe");
        driver = new ChromeDriver();
        logInPage = new LogInPage(driver);
    }

    @Test
    public void open() {
        logInPage.openPage();
    }

    @Test
    public void errorMessageTest() {
        logInPage.openPage();
        logInPage.incorrectLogin("123@mail.ru","123");
        Assert.assertEquals("LOL","Пользователь с такой электронной почтой или паролем не найден", logInPage.getErrorMessage());
    }

    @Test
    public void invalidMailErrorMessageTest() {
        logInPage.openPage();
        logInPage.incorrectLogin("123", "123");
        Assert.assertEquals("Введите корректный e-mail", driver.findElement(By.xpath("//*[@id=\"login_form\"]/fieldset/div[1]/div")).getText());
    }

    @Test
    public void switchToRegistrationPageTest() {
        logInPage.openPage();
        RegistrationPage registrationPage = logInPage.switchToRegistrationForm();
        Assert.assertEquals("Регистрация", registrationPage.getTitleText());
    }

    @Test
    public void switchToEnglishLanguageTest() {
        logInPage.openPage();
        logInPage.switchLanguege();
        Assert.assertEquals("Log in", logInPage.getTitleText());
        RegistrationPage registrationPage = logInPage.switchToRegistrationForm();
        Assert.assertEquals("Registration", registrationPage.getTitleText());
    }

    @Test
    public void correctLogin() {
        logInPage.openPage();
        HomePage homePage = logInPage.login("123", "123");
        Assert.assertTrue(homePage.isAuthorized());
    }

    @After
    public void tearDown() {

    }

    @AfterClass
    public static void quitDriver() {

    }


}
