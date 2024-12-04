/*
* Completely deprecated as of 09/08/2024
* New version is on src/test/java/a1qa/FinalTaskTest.java
* This file stays mainly for comparison purposes and to not mess up the version control
*/

package a1qaArt.src.test.java.a1qa;

import java.io.File;
import java.util.concurrent.TimeUnit;

//import java.beans.Transient;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import a1qaArt.src.main.java.a1qa.BaseTest;
import org.testng.Assert;
import org.openqa.selenium.WebElement;
import org.awaitility.core.ConditionTimeoutException;
import org.awaitility.Awaitility;
import org.openqa.selenium.interactions.Actions;


public class FinalTaskTest extends BaseTest{
    private final String SEARCH_OBJECT = "Albert Einstein";
    private final By INPUT = By.xpath("//*[@id='searchInput']"); 
    private final By SEARCH_SUBMIT = By.xpath("//*[@type='submit']");
    private final By NAV_HERRA= By.xpath("//*[@class='vector-page-tools-landmark']");
    //^^not unique, todo get another way to get it unique
    private final By DOWNLOAD = By.xpath("//*[@id='coll-download-as-rl']/a");
    private final By FILENAME = By.xpath("//*[@class='mw-electronpdfservice-selection-label-desc']");
    private final By DOWNLOAD_BUTTON = By.xpath("//button[@type='submit']");
    private final String FILEPATH = "C:\\Users\\Darkarael\\Downloads\\";

    @Test
    public void finalTask(){
        WebElement inputField = driver.findElement(INPUT);
        inputField.sendKeys(SEARCH_OBJECT);
        driver.findElement(SEARCH_SUBMIT).click();
        driver.findElement(NAV_HERRA).click();
        driver.findElement(DOWNLOAD).click();
        File file = new File(FILEPATH+driver.findElement(FILENAME).getText());
        driver.findElement(DOWNLOAD_BUTTON).click();
        Assert.assertTrue(fileExists(file));
        Assert.assertEquals(driver.findElement(FILENAME).getText(),file.getName(),"Name error" );
        file.delete();
    }
    public boolean fileExists(File file){
        try{
            Awaitility.await().atMost(MAX_WAIT,TimeUnit.SECONDS).until(file::exists);
        }
        catch(ConditionTimeoutException e){
            return false;
        }
        return true;
    }
}
