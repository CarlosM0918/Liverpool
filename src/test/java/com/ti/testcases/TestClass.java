package com.ti.testcases;

import org.testng.annotations.Test;

public class TestClass extends BaseTest {
    @Test(priority = 1, enabled = true)
    void searchBeer() throws InterruptedException {
        search.searchItem(productToSearch.get("product1"))
                .search();

        search.clickCervezaMinerva(productToSearch.get("selectedBeer"))
                .scrollToDetails()
                .scrollToRelated();

        search.addToCart().andVerifyItemAddedAlert();
    }

    @Test(priority = 2)
    void selectWine() throws InterruptedException {
        search.searchItem(productToSearch.get("product2"))
                .search();

        search.typeGenerous()
                .selectTypeSort()
                .byHihgPrice();

        search.selectItemWithHighestPrice();

        search.availavityInStore().selectState().close();

        search.addToCart().andVerifyItemAddedAlert();

        search.goToBag().buyItems();

        loginPage.loginAs(credentials.get("email"))
                .withPassword(credentials.get("password"))
                .login();
//        Thread.sleep(5000);
    }

}
