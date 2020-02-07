package habr;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class RegistrationPage {
    private WebDriver driver;

    @FindBy(xpath = "//input[@id='email_field']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@id='nickname_field']")
    private WebElement nicknameField;

    @FindBy(xpath = "//input[@id='password_field']")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@id='password_repeat']")
    private WebElement repeatPasswordField;

    @FindBy(xpath = "//label[@class='checkbox']")
    private WebElement userAgreementCheckBox;

    @FindBy(xpath = "//button[@id='registration_button']")
    private WebElement registrationButton;

    @FindBy(xpath = "//a[@class='form-additional-message__link']")
    private WebElement logInLink;

    @FindBy(xpath = "//div[@class='shadow-box__title']")
    private WebElement title;


    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public RegistrationPage openPage() {
        driver.get("https://account.habr.com/register/");
        return this;
    }

    public RegistrationPage registrationNewAccount(String email, String nickname, String password, String repeatPassword) {
        emailField.sendKeys(email);
        nicknameField.sendKeys(nickname);
        passwordField.sendKeys(password);
        repeatPasswordField.sendKeys(repeatPassword);
        userAgreementCheckBox.click();
        //TODO капча
        registrationButton.click();
        return this;
    }

    public RegistrationPage typeEmail(String email) {
        emailField.sendKeys(email);
        return this;
    }

    public RegistrationPage typeNickname(String nickname) {
        nicknameField.sendKeys(nickname);
        return this;
    }

    public RegistrationPage typePassword(String password) {
        passwordField.sendKeys(password);
        return this;
    }

    public RegistrationPage repeatPassword(String repeatPassword) {
        repeatPasswordField.sendKeys(repeatPassword);
        return this;
    }

    public RegistrationPage clickUserAgreement() {
        if(!userAgreementCheckBox.isSelected()) {
            userAgreementCheckBox.click();
        }
        return this;
    }

    public void clickRegistrationButton(){
        if (registrationButton.isEnabled()) {
            registrationButton.click();
        } else {
            System.out.println("oops");
        }
    }

    public LogInPage switchToLigOnPage() {
        logInLink.click();
        return new LogInPage(driver);
    }

    public String getTitleText() {
        return title.getText();
    }

}
