package driver;

import io.cucumber.java.Scenario;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Driverfactory {
    private static RemoteWebDriver driver;
    private static String defaultWindowHandle;
    static Scenario scenario;

    public final static WebDriver getWebDriver(){
        if (driver == null ) {
            try {
                createNewDriverInstance();
            } catch (Exception e) {
                System.out.println("Error on creating driver");
                e.printStackTrace();
            }

        }
        return driver;
    }


    public static void createNewDriverInstance() throws IOException {

        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);
        options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
        driver.manage().timeouts().pageLoadTimeout(180, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
    }


    public static void tearDown(){
        if (driver != null) {
            try {
                driver.quit();
                Thread.sleep(5000);
            }catch(Exception e)
            {
                //
            }
            finally {
                driver = null;
            }
        }
    }
}
