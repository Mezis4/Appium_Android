package ru.netology.qa;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.junit.jupiter.api.*;
import org.openqa.selenium.remote.DesiredCapabilities;


import java.net.MalformedURLException;
import java.net.URL;

public class AppiumTest {
    // Заготовка под кросс-платформенность, но без двух вариантов теста, чтобы запомнить предварительные настройки
    private AppiumDriver driver;
    enum Platform {Android, Ios}

    private Platform platform = Platform.Android;

    private String emptyField = "   ";
    private String textToSet = "Netology";

    PageObjects page;

    @BeforeEach
    public void createDriver() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        URL remoteUrl = new URL("http://127.0.0.1:4723");
        if (platform == Platform.Android) {
            desiredCapabilities.setCapability("platformName", "android");
            desiredCapabilities.setCapability("automationName", "uiautomator2");
            desiredCapabilities.setCapability("appium:deviceName", "some name");
            desiredCapabilities.setCapability("appium:appPackage", "ru.netology.testing.uiautomator");
            desiredCapabilities.setCapability("appium:appActivity", "ru.netology.testing.uiautomator.MainActivity");
            driver = new AndroidDriver(remoteUrl, desiredCapabilities);
        } else if (platform == Platform.Ios) {
            desiredCapabilities.setCapability("platformName", "iOS");
            desiredCapabilities.setCapability("appium:deviceName", "iPhone 11");
            desiredCapabilities.setCapability("appium:bundleID", "ru.netology.testing.uiautomator");
            desiredCapabilities.setCapability("appium:automationName", "XCUITest");
            driver = new IOSDriver(remoteUrl, desiredCapabilities);
        }
        page = new PageObjects(driver);
    }

    // Тесты только под Android, т.к. нет личного iOS
    @Test
    public void shouldNotChangeTextIfEmptyField() {
        page.textOnMain.isDisplayed();
        String textBeforeChange = page.textOnMain.getText();

        page.textInput.isDisplayed();
        page.textInput.click();
        page.textInput.sendKeys(emptyField);
        page.changeTextButton.isDisplayed();
        page.changeTextButton.click();
        page.textOnMain.isDisplayed();

        Assertions.assertEquals(textBeforeChange, page.textOnMain.getText());
    }

    @Test
    public void shouldOpenActivityWithText() {
        page.textOnMain.isDisplayed();
        page.textInput.isDisplayed();
        page.textInput.click();
        page.textInput.sendKeys(textToSet);
        String textBeforeChange = page.textInput.getText();
        page.newActivityButton.isDisplayed();
        page.newActivityButton.click();
        page.activityText.isDisplayed();

        Assertions.assertEquals(textBeforeChange, page.activityText.getText());
    }

    @AfterEach
    public void tearDown() {
       driver.quit();
    }

}
