package com.ti.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {
    private String getOS = System.getProperty("os.name").toLowerCase();
    private String driverProperty = "webdriver.chrome.driver";
    private String driverPath = System.getProperty("user.dir") + "/src/main/resources/";
    private String driverName = (getOS.contains("mac"))?"chromedriver" : "chromedriver.exe";
    private String braveLocation = "C:/Program Files/BraveSoftware/Brave-Browser/Application/brave.exe";

    private static DriverFactory instance = new DriverFactory();

    public static DriverFactory getInstance(){
        return instance;
    }

    ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public WebDriver getDriver(){
        return driver.get();
    }

    public void setDriver(BrowserType browserType, boolean remoteDriver) throws MalformedURLException {
        System.out.println(getOS);
        if(getOS.contains("mac") && browserType.equals(BrowserType.SAFARI)){
            driver.set(new SafariDriver());
        }else if(browserType.equals(BrowserType.BRAVE)){
            System.setProperty(driverProperty, driverPath+driverName);
            driver.set(new ChromeDriver(new ChromeOptions().setBinary(braveLocation)));
        }else{
            WebDriverManager.getInstance(DriverManagerType.valueOf(browserType.toString())).setup();
            switch (browserType){
                case CHROME:
					if(remoteDriver){
						ChromeOptions chromeOptions = new ChromeOptions();
						driver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), chromeOptions));
					}else {
						driver.set(new ChromeDriver());
					}
                    break;
                case EDGE:
					if(remoteDriver){
						EdgeOptions edgeOptions = new EdgeOptions();
						driver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), edgeOptions));
					}else {
						driver.set(new ChromeDriver());
					}
                    break;
                case FIREFOX:
					if(remoteDriver){
						FirefoxOptions firefoxOptions = new FirefoxOptions();
						driver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), firefoxOptions));
					}else {
						driver.set(new ChromeDriver());
					}
                    break;
                case IEXPLORER:
                    if(getOS.contains("win")){
                        driver.set(new InternetExplorerDriver());
                    }
                    break;
            }

            driver.get().manage().window().maximize();

        }
    }

    public void removeDriver(){
        if(driver.get() != null){
            try {
                driver.get().quit();
                driver.remove();
            }catch (Exception e){
                System.err.println("Unable to quit");
            }
        }
    }
}
