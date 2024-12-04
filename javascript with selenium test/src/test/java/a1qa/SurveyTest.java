package a1qaArt.src.main.java.a1qa;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;

public class SurveyTest extends BaseTest{
    private final By firstButton = By.xpath(String.format(PRECISE_TEXT_XPATH, "Alerts, Frame & Windows"));
    private final By secondButton = By.xpath(String.format(PRECISE_TEXT_XPATH, "Browser Windows"));
    //check if it was opened
    private final By tabButton = By.xpath("//*[@id=\"tabButton\"]");
    //check if it was opened, text that says when displayed "This is a sample page"
    private final By tabOpen = By.xpath("//*[@id=\"sampleHeading\"]");
    @Test
    public void surveyTest(){
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        WebElement buttonScroll = driver.findElement(firstButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", buttonScroll);
        driver.findElement(firstButton).click();
        driver.findElement(secondButton).click();
        Assert.assertTrue(driver.findElement(tabButton).isDisplayed());
        driver.findElement(tabButton).click();
        Object[] windowHandles=driver.getWindowHandles().toArray();
        driver.switchTo().window((String) windowHandles[1]);
        Assert.assertEquals(driver.findElement(tabOpen).getText(),"This is a sample page");
    }
}