package com.ti.pages;

import com.ti.base.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class MainPage {
    WebDriver driver = DriverFactory.getInstance().getDriver();
    public JavascriptExecutor js;

    @FindBy(className = "mdc-snackbar--open")
    private WebElement alertAdded;

    @FindBy(linkText = "cerveza")
    private WebElement lnkBuscado;

    @FindBy(id = "opc_pdp_addCartButton")
    private WebElement btnAddToCart;

    @FindBy(xpath = "(//button[@class='a-header__bag'])[1]")
    private  WebElement btnGoToBag;

    @FindBy(xpath = "(//button[normalize-space()='Comprar'])[1]")
    private WebElement btnBuy;

    @FindBy(className = "t-myBag__productList")
    private WebElement dvBag;

    @FindBy(className = "input-group-text")
    private WebElement btnSearch;

    @FindBy(id = "mainSearchbar")
    private WebElement txtSearchBar;

    @FindBy(xpath = "(//button[@aria-label='Close'])[1]")
    private WebElement btnClose;

    @FindBy(className = "btnGeoStore")
    private WebElement btnGeoStore;

    @FindBy(linkText = "AGUASCALIENTES")
    private WebElement lknState;

    public MainPage(){
		PageFactory.initElements(driver, this);
    }

    public MainPage preLoading(WebElement opt){
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOf(opt));
        }catch (TimeoutException te){
            driver.navigate().refresh();
			preLoading(opt);
        }
        return this;
    }

	public MainPage goBack() throws InterruptedException {
//		driver.navigate().wait(3000);
		driver.navigate().back();
		return this;
	}

    public MainPage scrollWindow(String scroll){
        js = (JavascriptExecutor)driver;
        try {
            switch (scroll){
                case "up":
                    js.executeScript("window.scrollBy(0,-250)");
                    break;
                case "down":
                    js.executeScript("window.scrollBy(0,250)");
                    break;
                default:
                    System.err.println("Bad option");
                    break;
            }
        }catch (JavascriptException je){
            System.err.println(String.format("Class: MainPAge | Method: scroll | Exception sec: "+ je.getMessage()));
        }
        return this;
    }

    public MainPage buyItems(){
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        preLoading(dvBag);
        btnBuy.click();
        return this;
    }

    public MainPage goToBag(){
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(btnGoToBag));
        btnGoToBag.click();
        return this;
    }

    public MainPage addToCart(){
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOf(btnAddToCart));
        btnAddToCart.click();
        return this;
    }

/////////////////////////////////////////////////////////////////////
    public MainPage search(){
        btnSearch.click();
        return this;
    }

    public MainPage searchItem(String obj){
        System.out.println("Objeto recibido: "+obj);
        txtSearchBar.clear();
        txtSearchBar.sendKeys(obj);
        return this;
    }

    public MainPage close(){
        btnClose.click();
        return this;
    }


    public MainPage availavityInStore(){
        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);

		try {
			new WebDriverWait(driver, Duration.ofSeconds(8)).until(ExpectedConditions.elementToBeClickable(btnGeoStore));
		}catch (TimeoutException te){
			preLoading(btnGeoStore);
			new WebDriverWait(driver, Duration.ofSeconds(8)).until(ExpectedConditions.elementToBeClickable(btnGeoStore));
		}
        btnGeoStore.click();
        return this;
    }

    public MainPage selectState(){
        new WebDriverWait(driver, Duration.ofSeconds(8)).until(ExpectedConditions.elementToBeClickable(lknState));
        lknState.click();
        return this;
    }
    public MainPage andVerifyItemAddedAlert(){
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(alertAdded));
        Assert.assertTrue(alertAdded.getText().contains("Agregaste un producto a tu bolsa"));
        return this;
    }

}
