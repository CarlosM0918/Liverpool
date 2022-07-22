package com.ti.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {
	private String username = "cemartin";
	private String accessKey = "WlLfItY1xJAAzmAy9D9loy6lAjmBmQBssGcfmVmPv5lkj7jwQl";

	private String getOS = System.getProperty("os.name").toLowerCase();
    private String driverProperty = "webdriver.chrome.driver";
    private String driverPath = System.getProperty("user.dir") + "/src/main/resources/";
    private String driverName = (getOS.contains("mac"))?"chromedriver" : "chromedriver.exe";
    private String braveLocation = "C:/Program Files/BraveSoftware/Brave-Browser/Application/brave.exe";
	private String path = (getOS.contains("win"))?driverPath:"/usr/local/bin/chromedriver";

    private static DriverFactory instance = new DriverFactory();

    public static DriverFactory getInstance(){
        return instance;
    }

    ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public WebDriver getDriver(){
        return driver.get();
    }

    public void setDriver(BrowserType browserType, boolean remoteDriver) throws MalformedURLException {
        // System.out.println(getOS);
        if(getOS.contains("mac") && browserType.equals(BrowserType.SAFARI)){
            driver.set(new SafariDriver());
        }else if(browserType.equals(BrowserType.BRAVE)){
            System.setProperty(driverProperty, driverPath+driverName);
            driver.set(new ChromeDriver(new ChromeOptions().setBinary(braveLocation)));
        }
		else if(browserType.equals(BrowserType.LAMBDA)){
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("browserName", "Chrome");
			capabilities.setCapability("version", "103.0");
			capabilities.setCapability("platform", "Windows 10");
			capabilities.setCapability("resolution","1024x768");
			capabilities.setCapability("build", "First Test");
			capabilities.setCapability("name", "Online test");
			capabilities.setCapability("selenium_version", "4.1.2");// To set selenium version
			capabilities.setCapability("network", true); // To enable network logs
			capabilities.setCapability("visual", true); // To enable step by step screenshot
			capabilities.setCapability("video", true); // To enable video recording
			capabilities.setCapability("console", true); // To capture console logs

			try {
				driver.set(new RemoteWebDriver(new URL("https://" + username + ":" + accessKey + "@hub.lambdatest.com/wd/hub"), capabilities));
			} catch (MalformedURLException e) {
				System.out.println("Invalid grid URL");
			}
		}else{
            WebDriverManager.getInstance(DriverManagerType.valueOf(browserType.toString())).setup();
            switch (browserType){
                case CHROME:
					if(remoteDriver){
						ChromeOptions chromeOptions = new ChromeOptions();
						try {
							driver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), chromeOptions));
						}catch (SessionNotCreatedException snce){
							System.out.println("The local server has not been started: " + snce.getMessage());
						}
					}else {
						driver.set(new ChromeDriver());
					}
                    break;
                case EDGE:
					EdgeOptions edgeOptions = new EdgeOptions();
					if(remoteDriver){
						try {
							driver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), edgeOptions));
						}catch (SessionNotCreatedException snce){
							System.out.println("The local server has not been started: " + snce.getMessage());
						};
					}else {
						driver.set(new EdgeDriver());
					}
                    break;
                case FIREFOX:
					FirefoxOptions firefoxOptions = new FirefoxOptions();
					if(remoteDriver){
						try {
							driver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), firefoxOptions));
						}catch (SessionNotCreatedException snce){
							System.out.println("The local server has not been started: " + snce.getMessage());
						}
					}else {
						driver.set(new FirefoxDriver());
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
