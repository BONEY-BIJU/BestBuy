package com.example.bestbuy.view.ui.products.Viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bestbuy.repository.ProductRepository;
import com.example.bestbuy.view.ui.products.model.ProductModel;

import java.util.List;

public class ProductsViewModel extends ViewModel {

    String query;

    private List<ProductModel> productList;
    private static ProductRepository productRepository;
    private MutableLiveData<List<ProductModel>> allProductsLiveData;
    private static MutableLiveData<List<ProductModel>> searchedProductsLiveData;
    private static MutableLiveData<Exception> errorLiveData;

    public ProductsViewModel() {
        productRepository = new ProductRepository();
        allProductsLiveData = new MutableLiveData<>();
        searchedProductsLiveData = new MutableLiveData<>();
        errorLiveData = new MutableLiveData<>();
    }

    public LiveData<List<ProductModel>> getAllProductsLiveData() {
        fetchAllProducts();
        return allProductsLiveData;
    }

    public LiveData<List<ProductModel>> getSearchedProductsLiveData() {
        return searchedProductsLiveData;
    }

    public LiveData<Exception> getErrorLiveData() {
        return errorLiveData;
    }

    public void fetchAllProducts() {
        productRepository.FetchProducts(productList->{
                    allProductsLiveData.postValue(productList);
                },
                e -> {

                });

    }

    public void searchProducts(String query) {
        productRepository.searchProducts(query,
                searchedProductsLiveData::postValue,
                error -> {  }
        );
    }
}
