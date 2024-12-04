package a1qa;

import org.openqa.selenium.By;
import org.openqa.selenium.WindowType;
import org.testng.Assert;
import org.testng.annotations.Test;
import a1qaArt.src.main.java.a1qa.BaseTest;

public class QualificationTest extends BaseTest{
    //navigate to herokuapp done
    //click on multiple windows done
    //expected: page is open done
    //click on "click here" done
    //expected: new page is open done
    protected final By multipleWindowsButton = By.xpath("//a[contains(@href,'windows')]");
    protected final By clickHereButton = By.xpath("//a[contains(@href,'windows/new')]");
    protected final By newWindowText = By.xpath("//h3[text()[contains(.,'New Window')]]");
    //checks if a page was opened on another tab when you click a button
    @Test
    public void qualificationTest(){
        driver.findElement(multipleWindowsButton).click();
        Assert.assertTrue(driver.findElement(clickHereButton).isDisplayed(),"page did not opened properly");
        driver.findElement(clickHereButton).click();
        Object[] browserTabs = driver.getWindowHandles().toArray();
        driver.switchTo().window((String) browserTabs[1]);
        Assert.assertTrue(driver.findElement(newWindowText).isDisplayed(),"Something happened with the new tab");
    }
}
