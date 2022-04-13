package qa.automation;

import base.TestUtil;
import com.opencsv.exceptions.CsvException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;
import utils.CsvHelper;

import java.io.IOException;

public class  LoginTest extends TestUtil {

    //dataprovider1
    @DataProvider(name = "wrongUsersList")
    public Object [][] getWrongUsers(){
        return new Object[][]{
                {"standardUser7", "secret_sauce"},
                {"standard_user", "wrong password"},
                {"blah", "blah"},
                {" ","secret_sauce"},
        };
    }

    @DataProvider(name = "csvUserList")
    public static Object[][] readUsersFromCsvFile() throws IOException, CsvException {
        return CsvHelper.readCsvFile("src/test/resources/users.csv");
    }

    @Test (dataProvider = "wrongUsersList") //this is using dataprovider1
    public void UnsuccessfulLogin(String userName, String password){
        // driver.get("https://www.saucedemo.com/");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.tryToLogin("wrong", "wrong");

        WebElement errorLoginLabel = driver.findElement(By.xpath("//*[text()='Epic sadface: Username and password do not match any user in this service']"));

        Assert.assertTrue(errorLoginLabel.isDisplayed());
    }

    @Test(dataProvider = "csvUserList")
    public void SuccessfulLogin(String userName, String password){
        // driver.get("https://www.saucedemo.com/");

        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.login(userName, password);
    }
}




