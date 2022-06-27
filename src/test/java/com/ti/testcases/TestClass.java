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

//        Thread.sleep(5000);
    }

	@Test(priority = 3)
	void selectHam() throws InterruptedException {
		search.searchItem(productToSearch.get("product3"))
			.search();

		search.selectHam(productToSearch.get("selectedHam"));
		search.addToCart().andVerifyItemAddedAlert();
	}
	@Test(priority = 4)
	void selectKnife() throws InterruptedException {
		search.goBack();

		search.selectKnife(productToSearch.get("selectedKnife"));
		search.addToCart().andVerifyItemAddedAlert();
	}

	@Test(priority = 7)
	void goBag() {
		search.goToBag().buyItems();
	}

	@Test(priority = 8)
	void checkLogin(){
		loginPage.loginAs(credentials.get("email"))
			.withPassword(credentials.get("password"))
			.login();
	}
}
