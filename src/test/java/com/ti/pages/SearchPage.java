package com.ti.pages;

import org.omg.PortableServer.THREAD_POLICY_ID;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SearchPage extends MainPage{



//    @FindBy(xpath = "(//*[contains(text(), 'Cerveza Minerva Stout 355 ml')])[3]")
//    private WebElement titleCerveza;

    @FindBy(linkText = "Generoso")
    private WebElement lnkGeneroso;

    @FindBy(linkText = "Ordenar por:")
    private WebElement drpdSortBy;

    @FindBy(xpath = "(//button[contains(.,'Mayor precio')])[2]")
    private WebElement btnMayorPrecio;

    @FindBy(className = "card-masonry")
    private List<WebElement> productList;

    @FindBy(className = "card-title")
    private List<WebElement> cervesaList;

    @FindBy(className = "slick-track")
    private WebElement relatedItems;

    @FindBy(id = "opc_pdp_addCartButton")
    private WebElement btnAddToCart;


    /////////////////////////////////////////////////// CERVEZA ///////////////////////////////////////////////////////////
    public SearchPage clickCervezaMinerva(String item){
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        try{
            new WebDriverWait(driver, Duration.ofSeconds(15))
                    .until(ExpectedConditions.visibilityOfAllElements(cervesaList));
        }catch (TimeoutException te) {
            preLoading(cervesaList.get(0));
            new WebDriverWait(driver, Duration.ofSeconds(15))
                    .until(ExpectedConditions.visibilityOfAllElements(cervesaList));
        }
        for (WebElement cerveza:cervesaList) {
            if(cerveza.getText().contains(item)){
                cerveza.click();
                break;
            }
        }
        return this;
    }

    public SearchPage scrollToDetails(){
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        new WebDriverWait(driver, Duration.ofSeconds(8)).until(ExpectedConditions.visibilityOf(btnAddToCart));

        js =(JavascriptExecutor)driver;
        js.executeScript("var element = document.getElementById('o-product__productSpecsDetails');\n" +
                "element.scrollIntoView()");
        return this;
    }

    public SearchPage scrollToRelated() throws InterruptedException {

        js.executeScript("window.scrollBy(0,1750)");

        new WebDriverWait(driver, Duration.ofSeconds(8)).until(ExpectedConditions.visibilityOf(relatedItems));


        JavascriptExecutor js =(JavascriptExecutor)driver;
        js.executeScript("window.scrollTo(0,0);");

        new WebDriverWait(driver, Duration.ofSeconds(8)).until(ExpectedConditions.visibilityOf(btnAddToCart));

        Thread.sleep(2000);
        return this;
    }

    /////////////////////////////////////////////////// VINO ///////////////////////////////////////////////////

    public SearchPage typeGenerous(){
        try {
            new WebDriverWait(driver, Duration.ofSeconds(8)).until(ExpectedConditions.elementToBeClickable(lnkGeneroso));
        }catch (TimeoutException te){
            preLoading(lnkGeneroso);
            new WebDriverWait(driver, Duration.ofSeconds(8)).until(ExpectedConditions.elementToBeClickable(lnkGeneroso));
        }
        lnkGeneroso.click();
        return this;
    }

    public SearchPage selectTypeSort(){
        new WebDriverWait(driver, Duration.ofSeconds(8)).until(ExpectedConditions.elementToBeClickable(drpdSortBy));
        drpdSortBy.click();
        return this;
    }

    public SearchPage byHihgPrice(){
//        new WebDriverWait(driver, Duration.ofSeconds(8)).until(ExpectedConditions.elementToBeClickable(btnMayorPrecio));
        btnMayorPrecio.click();
        return this;
    }

    public SearchPage selectItemWithHighestPrice(){
        try {
            new WebDriverWait(driver, Duration.ofSeconds(8))
                    .until(ExpectedConditions.visibilityOfAllElements(productList));
        }catch (TimeoutException te){
            preLoading(productList.get(0));

            new WebDriverWait(driver, Duration.ofSeconds(8))
                    .until(ExpectedConditions.visibilityOfAllElements(productList));
        }
        WebElement firstProduct = productList.get(0);
        firstProduct.click();
        return this;
    }



}
