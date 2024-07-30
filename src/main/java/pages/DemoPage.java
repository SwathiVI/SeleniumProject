package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class DemoPage {
    WebDriver driver;

    public DemoPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Elements
    @FindBy(id = "myTextInput")
    WebElement textInput;

    @FindBy(id = "mySelect")
    WebElement selectDropdown;

    @FindBy(id = "meterBar")
    WebElement meter; // Updated ID to "meterBar"

    @FindBy(id = "svgRect")
    WebElement svgRect;

    @FindBy(css = "iframe[name='frameName3']")
    WebElement checkboxIframe;

    @FindBy(id = "checkBox6")
    WebElement iframeCheckbox;

    @FindBy(tagName = "a")
    List<WebElement> links;

    // Actions
    public void fillTextInput(String text) {
        textInput.sendKeys(text);
    }

    public String getTextInputValue() {
        return textInput.getAttribute("value");
    }

    public String getSvgRectColor() {
        return svgRect.getAttribute("fill");
    }

    public void selectDropdownOption(String option) {
        Select dropdown = new Select(selectDropdown);
        dropdown.selectByVisibleText(option);
    }

    public String getMeterValue() {
        return meter.getAttribute("value");
    }

    public List<WebElement> getLinks() {
        return links;
    }

    // Methods for handling the checkbox inside the iframe
    public void switchToCheckboxIframe() {
        driver.switchTo().frame(checkboxIframe);
    }

    public void toggleIframeCheckbox() {
        iframeCheckbox.click();
    }

    public boolean isIframeCheckboxSelected() {
        return iframeCheckbox.isSelected();
    }

    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }
}
