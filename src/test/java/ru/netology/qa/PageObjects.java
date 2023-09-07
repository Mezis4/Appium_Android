package ru.netology.qa;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class PageObjects {
    private AppiumDriver driver;
    @AndroidFindBy(id = "ru.netology.testing.uiautomator:id/textToBeChanged")
    public WebElement textOnMain;

    @AndroidFindBy(id = "ru.netology.testing.uiautomator:id/userInput")
    public WebElement textInput;

    @AndroidFindBy(id = "ru.netology.testing.uiautomator:id/buttonChange")
    public WebElement changeTextButton;

    @AndroidFindBy(id = "ru.netology.testing.uiautomator:id/buttonActivity")
    public WebElement newActivityButton;

    @AndroidFindBy(id = "ru.netology.testing.uiautomator:id/text")
    public WebElement activityText;

    PageObjects(AppiumDriver driver){
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(15)), this);
    }

}
