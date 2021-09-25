package page.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.*;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;


public class MicrositeStep {
    RemoteWebDriver driver;

    @Given("^User access the website")
    public void accessTheMicrosite() throws Throwable {
        try {
            //call chrome driver, should be latest stable version
            System.setProperty("webdriver.chrome.driver", "/Users/amandafloren/Downloads/chromedriver");
            driver = new ChromeDriver();
            driver.get("https://www.online-calculator.com/full-screen-calculator/");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @When("^I divide (.*) with (.*)")
    public void multiplication(String firstOperand, String secondOperand){
        WebElement calc = driver.findElement(By.xpath("//*[@id = 'fullframe' and @src = '/html5/online-calculator/index.php?v=10']"));
        calc.sendKeys(firstOperand);
        calc.sendKeys("/");
        calc.sendKeys(secondOperand);
    }

    @When("^I subtract (.*) with (.*)")
    public void subtract(String firstOperand, String secondOperand){
        WebElement calc = driver.findElement(By.xpath("//*[@id = 'fullframe' and @src = '/html5/online-calculator/index.php?v=10']"));
        calc.sendKeys(firstOperand);
        calc.sendKeys("-");
        calc.sendKeys(secondOperand);
    }

    @When("^I switch (.*) to (.*) for (.*) with (.*)")
    public void switchDivToSubs(String firstOperator, String secondOperator, String firstOperand, String secondOperand) throws InterruptedException{
        WebElement calc = driver.findElement(By.xpath("//*[@id = 'fullframe' and @src = '/html5/online-calculator/index.php?v=10']"));
        calc.sendKeys(firstOperand);
        calc.sendKeys(firstOperator);
        Thread.sleep(1000);
        calc.sendKeys(secondOperator);
        Thread.sleep(1000);
        calc.sendKeys(secondOperand);
    }

    @Then("^I get the result from (.*) (.*) (.*)")
    public void correct_answer(String firstOperand, String operator,String secondOperand) throws IOException {
        WebElement calc = driver.findElement(By.xpath("//*[@id = 'fullframe' and @src = '/html5/online-calculator/index.php?v=10']"));
        calc.sendKeys(Keys.ENTER);
        try{
            Thread.sleep(2000);
        }
        catch(InterruptedException ie){
        }
        //Take the screenshot
        String divisionOperator = "/";
        String folderLoc = "/users/amandafloren/documents/calculator-auto/screenshots/screenshot_";
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        if(operator.equals(divisionOperator)) {
            FileUtils.copyFile(screenshot, new File(folderLoc + timestamp() + "-of-" + firstOperand + ":" + secondOperand + ".png"));
        }else{
            FileUtils.copyFile(screenshot, new File(folderLoc + timestamp() + "-of-" + firstOperand + operator + secondOperand + ".png"));
        }
        driver.quit();
    }

    public static String timestamp() {
        return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
    }

}
