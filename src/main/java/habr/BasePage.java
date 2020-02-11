package habr;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class BasePage {
    private WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@class='btn btn_medium btn_navbar_registration']")
    private WebElement registrationButton;

    @FindBy(xpath = "//a[@id='login']")
    private WebElement loginButton;

    @FindBy(xpath = "//span[@class='default-image default-image_navbar default-image_pink']")
    private WebElement iconProfile;

    @FindBy(xpath = "//a[@class='dropdown__user-info user-info']")
    private WebElement dropdownProfileLink;

    @FindBy(xpath = "//input[@id='search-form-field']")
    private WebElement searchField;

    @FindBy(xpath = "//button[@id='search-form-clear']")
    private WebElement searchFieldCloseButton;

    @FindBy(xpath = "//button[@id='search-form-btn']")
    private WebElement searchFieldOpenButton;

    @FindBy(xpath = "//li[@class='n-dropdown-menu__item']//a[@class='n-dropdown-menu__item-link n-dropdown-menu__item-link_user-menu']")
    private WebElement logoutButton;

    @FindBy(xpath = "//a[@class='logo']")
    private WebElement logo;

    @FindBy(xpath = "//div[@class='jGrowl-notification highlight ui-corner-all default']")
    private WebElement jGrowl; //Маленькие всплывающие уведомления

    @FindBy(xpath = "//div[@class='jGrowl-close']")
    private WebElement closeJGrowl;


    /*@FindBy(xpath = "//a[text()='Разработка']")
    private WebElement developmentlink;

    @FindBy(xpath = "//a[text()='Администрирование']")
    private WebElement administrationLink;

    @FindBy(xpath = "//a[text()='Дизайн']")
    private WebElement designLink;

    @FindBy(xpath = "//a[text()='Менеджмент']")
    private WebElement managementLink;

    @FindBy(xpath = "//a[text()='Маркетинг']")
    private WebElement marketingLink;

    @FindBy(xpath = "//a[text()='Гиктаймс']")
    private WebElement gikLink;

    @FindBy(xpath = "//a[text()='Разное']")
    private WebElement differentLink;*/



    public RegistrationPage switchToRegistrationPage() {
        registrationButton.click();
        return new RegistrationPage(this.driver);
    }

    public LogInPage switchToLogInPage() {
        loginButton.click();
        return new LogInPage(this.driver);
    }

    public ProfilePage switchToProfilePage() {
        iconProfile.click();
        dropdownProfileLink.click();
        return new ProfilePage(this.driver);
    }

    public HomePage switchToHomePage() {
        logo.click();
        return new HomePage(this.driver);
    }

    public HomePage logout() {
        iconProfile.click();
        logoutButton.click();
        return new HomePage(this.driver);
    }

    public boolean isAuthorized() {
        List<WebElement> elements = driver.findElements(By.xpath("//span[@class='default-image default-image_navbar default-image_pink']"));
        if (elements.size() != 0) {
            return true;
        } else {
            return false;
        }
    }

    public void search(String request) {
        searchFieldOpenButton.click();
        searchField.sendKeys(request, Keys.ENTER);
    }

    public String getJGrowlText() {
        return jGrowl.getText().substring(1).trim();
    }

    public void closeJGrowl() {
        closeJGrowl.click();
    }
}
