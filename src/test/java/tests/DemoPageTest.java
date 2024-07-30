package tests;

import pages.DemoPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class DemoPageTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private DemoPage demoPage;

    @Before
    public void setUp() {
        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--start-maximized");

        driver = new EdgeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://seleniumbase.io/demo_page");
        demoPage = new DemoPage(driver);
    }

    @Test
    public void testFillTextInput() {
        demoPage.fillTextInput("MAPS is boring");
        assertEquals("MAPS is boring", demoPage.getTextInputValue());
    }

    @Test
    public void testGetSvgRectColor() {
        String colorHex = demoPage.getSvgRectColor();
        String colorRgb = hexToRgb(colorHex);
        System.out.println("SVG Rect color (RGB): " + colorRgb);
        assertEquals("rgb(76, 160, 160)", colorRgb);
    }

    private String hexToRgb(String hex) {
        int r = Integer.parseInt(hex.substring(1, 3), 16);
        int g = Integer.parseInt(hex.substring(3, 5), 16);
        int b = Integer.parseInt(hex.substring(5, 7), 16);
        return "rgb(" + r + ", " + g + ", " + b + ")";
    }

    @Test
    public void testToggleCheckBoxInIframe() {
        demoPage.switchToCheckboxIframe();
        demoPage.toggleIframeCheckbox();
        assertTrue(demoPage.isIframeCheckboxSelected());
        demoPage.switchToDefaultContent();
    }

    @Test
    public void testSelectDropdownAndMeter() {
        demoPage.selectDropdownOption("Set to 50%");
        assertEquals("0.5", demoPage.getMeterValue());
    }

    @Test
    public void testPrintAllHyperlinkTexts() {
        List<WebElement> links = demoPage.getLinks();
        for (WebElement link : links) {
            System.out.println(link.getText());
        }
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}