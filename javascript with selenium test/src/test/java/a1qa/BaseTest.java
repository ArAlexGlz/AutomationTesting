package a1qaArt.src.main.java.a1qa;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseTest{
    protected static final String URL = "https://www.wikipedia.org/";
    protected static final int MAX_WAIT = 10;
    protected static final String PRECISE_TEXT_XPATH = "//*[text()='%s']";
    protected static final String PARTICULAR_TEXT_XPATH = "//*[contains(text(),'%s')]";
    protected static final String RELATIVE_RESOURCE_PATH = "src/test/resources/";
    protected static final String downloadFilepath = System.getProperty("java.io.tmpdir");
    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeMethod
    public void setup(){
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("download.default_directory", downloadFilepath);    
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);//changing download directory to a generic tmp in java
        driver = new ChromeDriver(options);
        driver.get(URL);
        driver.manage().window().maximize();
    }
    @AfterMethod
    public void teardown(){ driver.quit();}
}
