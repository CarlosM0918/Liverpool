package com.ti.testcases;

import com.ti.base.BrowserType;
import com.ti.base.DriverFactory;
import com.ti.pages.Login;
import com.ti.pages.SearchPage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

public class BaseTest {
    String baseURL = "https://www.liverpool.com.mx/tienda/home";
    Map<String, String> productToSearch = new HashMap<>();
    Map<String, String> credentials = new HashMap<>();

    SearchPage search;
    Login loginPage;

    @BeforeTest
    @Parameters({"browser", "remoteDriver"})
     void setup(String browser, boolean remoteDriver) throws MalformedURLException {
			DriverFactory.getInstance().setDriver(BrowserType.valueOf(browser.toUpperCase()), remoteDriver);
			DriverFactory.getInstance().getDriver().navigate().to(baseURL);

        productToSearch.put("product1", "cerveza");
		productToSearch.put("product2", "vino");
		productToSearch.put("product3", "jamon");
		productToSearch.put("selectedBeer", "Blue Moon");
		productToSearch.put("selectedHam", "Serrano Cumbres");
		productToSearch.put("selectedKnife", "Cuchillo para Jamón Wüsthof");

        credentials.put("email", "test@email.com");
        credentials.put("password", "test123");
        search = new SearchPage();
        loginPage = new Login();
    }

    @AfterTest
	@Parameters("remoteDriver")
    void turnDown(boolean remoteDriver){
		DriverFactory.getInstance().removeDriver();
    }
}
