package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    protected WebDriver driver;

    @FindBy(id = "user-name")
    private WebElement userNameInput;

    @FindBy(css = "[placeholder=Password]")
    private WebElement passwordInput;

    @FindBy(name = "login-button")
    private WebElement loginBtn;

    @FindBy(xpath = "//*[text()='Epic sadface: Username and password do not match any user in this service']")
    private WebElement genericErrorMessage;

    @FindBy(xpath = "the other locator")
    private WebElement usernameEmptyErrorMessage;

    @FindBy(xpath = "the other locator")
    private WebElement passwordEmptyErrorMessage;

    @FindBy(xpath = "//button[@class='error-button']")
    private WebElement xButtonOfLoginErrorMessages;


    public LoginPage (WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public ProductsPage login (String username, String password) {
        userNameInput.clear();
        userNameInput.sendKeys(username);

        passwordInput.clear();
        passwordInput.sendKeys(password);

        loginBtn.click();

        return new ProductsPage(driver);
    }

    public void tryToLogin (String username, String password) {
        userNameInput.clear();
        userNameInput.sendKeys(username);

        passwordInput.clear();
        passwordInput.sendKeys(password);

        loginBtn.click();

    }
}

