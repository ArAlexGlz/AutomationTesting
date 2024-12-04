package a1qaArt.src.test.java.a1qa;

import java.io.File;
import java.util.concurrent.TimeUnit;

//import java.beans.Transient;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import a1qaArt.src.main.java.a1qa.BaseTest;
import org.testng.Assert;
import org.openqa.selenium.WebElement;
import org.awaitility.core.ConditionTimeoutException;
import org.awaitility.Awaitility;
import org.openqa.selenium.interactions.Actions;
/*
 * TO DO
 * subir proyecto entero
 * meter el delete file en un metodo para un @aftermethod
 * cambiar el absolute path a un relative path, DONE
 * o cambiar la configuracion del driver para descargas y manejarlo asi, DONE
 * is there a way to check the filepath of the download on the configurations?
 */

public class FinalTaskTest extends BaseTest{
    private final String SEARCH_OBJECT = "Albert Einstein";
    private final By INPUT = By.xpath("//*[@id='searchInput']"); 
    private final By SEARCH_SUBMIT = By.xpath("//*[@type='submit']");
    private final By NAV_HERRA= By.xpath("//*[contains(@class,'vector-page-tools-landmark')]");
    //^^not unique, todo get another way to get it unique
    private final By DOWNLOAD = By.xpath("//*[@id='coll-download-as-rl']/*[@href]");
    private final By FILENAME = By.xpath("//*[contains(@class,'mw-electronpdfservice-selection-label-desc')]");
    private final By DOWNLOAD_BUTTON = By.xpath("//button[@type='submit']");
    private final By PAGE_ID = By.xpath("//*[contains(@class, \"central-textlogo__image sprite svg-Wikipedia_wordmark\")]");
    private final String FILEPATH = System.getProperty("java.io.tmpdir");
    private final String PAGENAME = "Wikipedia";
    private File file;

    @Test
    public void finalTask(){
        //Assert.assertEquals(driver.getCurrentUrl(),URL);
        //^^quite unsure if it applies as unique, but it certainly avoids redirects done after accessing main page
        Assert.assertEquals(driver.findElement(PAGE_ID).getText(),PAGENAME);
        WebElement inputField = driver.findElement(INPUT);
        inputField.sendKeys(SEARCH_OBJECT);
        driver.findElement(SEARCH_SUBMIT).click();
        driver.findElement(NAV_HERRA).click();
        driver.findElement(DOWNLOAD).click();
        driver.findElement(DOWNLOAD_BUTTON).click();
        file = new File(FILEPATH+driver.findElement(FILENAME).getText());
        Assert.assertTrue(fileExists(file));
        Assert.assertEquals(driver.findElement(FILENAME).getText(),file.getName(),"Name error" );
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
    @AfterMethod
    public void fileDelete(){
        file.delete();
    }
}
