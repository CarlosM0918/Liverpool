package com.ti.pages;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class Login extends MainPage{
    @FindBy(id = "username")
    private WebElement txtUser;

    @FindBy(id = "password")
    private WebElement txtPassword;

    @FindBy(xpath = "//*[contains(@value, 'default')]")
    private WebElement btnLogin;

    @FindBy(id = "error-element-password")
    private WebElement lblError;

    public Login loginAs(String userName){
        try {
            txtUser.clear();
        }catch (TimeoutException te){
            preLoading(txtUser);
            txtUser.clear();
        }
        txtUser.sendKeys(userName);
        return this;
    }

    public Login withPassword (String password){
        txtPassword.clear();
        txtPassword.sendKeys(password);
        return this;
    }

    public Login login (){
        btnLogin.click();
//        preLoading();
        return this;
    }

    public Login validateCredentials(){
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(lblError));
        Assert.assertTrue(lblError.getText().contains("Correo electrónico o contraseña incorrecta"));
        return this;
    }
}
