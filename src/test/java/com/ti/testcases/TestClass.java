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

    @Test(priority = 2, enabled = true)
    void selectWine() throws InterruptedException {
        search.searchItem(productToSearch.get("product2"))
                .search();

        search.typeGenerous()
                .selectTypeSort()
                .byHihgPrice();

        search.selectItemWithHighestPrice();

        search.availavityInStore().selectState().close();

        search.addToCart().andVerifyItemAddedAlert();

//        Thread.sleep(5000);
    }

	@Test(priority = 3, enabled = true)
	void selectHam() throws InterruptedException {
		search.searchItem(productToSearch.get("product3"))
			.search();

		search.selectHam(productToSearch.get("selectedHam"));
		search.addToCart().andVerifyItemAddedAlert();
	}

	 @Test(priority = 4, enabled = true)
	 void selectKnife() throws InterruptedException {
	 	search.searchItem(productToSearch.get("product4"))
			 .search();

	 	search.selectKnife(productToSearch.get("selectedKnife"));
	 	search.addToCart().andVerifyItemAddedAlert();
	 }

	@Test(priority = 7, enabled = true)
	void goBag() {
		search.goToBag().buyItems();
	}

	@Test(priority = 8, enabled = true)
	void checkLogin(){
		loginPage.loginAs(credentials.get("email"))
			.withPassword(credentials.get("password"))
			.login();
	}
}
