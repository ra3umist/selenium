package habr;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class LogInPage {
    WebDriver driver;

    @FindBy(xpath = "//input[@id='email_field']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@id='password_field']")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@name='go']")
    private WebElement logInButton;

    @FindBy(xpath = "//a[@class='form__remind-password-link']")
    private WebElement remindPassLink;

    @FindBy(xpath = "//a[@class='form-additional-message__link']")
    private WebElement registrationLink;

    @FindBy(xpath = "//div[@class='notice__text']")
    private WebElement errorMessage;

    @FindBy(xpath = "//div[@class='shadow-box__title']")
    private WebElement title;

    @FindBy(xpath = "//div[@class='layout__footer']//a[1]")
    private WebElement languageSwitcher;

    public LogInPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public LogInPage openPage() {
        driver.get("https://account.habr.com/login/");
        return this;
    }

    public HomePage login(String email, String password) {
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        logInButton.click();
        return new HomePage(driver);
    }

    public LogInPage incorrectLogin(String email, String password) {
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        logInButton.click();
        return this;
    }

    public RegistrationPage switchToRegistrationForm() {
        registrationLink.click();
        return new RegistrationPage(driver);
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }


    public LogInPage typeEmail(String email) {
        emailField.sendKeys(email);
        return this;
    }

    public LogInPage typePassword(String password) {
        passwordField.sendKeys(password);
        return this;
    }

    public void switchLanguege() {
        languageSwitcher.click();
    }

    public String getTitleText() {
        return title.getText();
    }

    public void clickLogInButton() {
        logInButton.click();
    }
}
