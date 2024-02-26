package com.example.bestbuy.view.ui.products.Viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bestbuy.repository.ProductRepository;
import com.example.bestbuy.view.ui.home.Model.HomeModel;
import com.example.bestbuy.view.ui.products.model.ProductModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.List;

public class ProductsViewModel extends ViewModel {

    String query;

    private List<ProductModel> productList;
    private static ProductRepository productRepository;
    private MutableLiveData<List<ProductModel>> allProductsLiveData, products;
    private static MutableLiveData<List<ProductModel>> searchedProductsLiveData;
    private static MutableLiveData<Exception> errorLiveData;

    public ProductsViewModel() {
        productRepository = new ProductRepository();
        allProductsLiveData = new MutableLiveData<>();
        searchedProductsLiveData = new MutableLiveData<>();
        errorLiveData = new MutableLiveData<>();
        products=new MutableLiveData<>();
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
        productRepository.FetchProducts(productList -> {
                    allProductsLiveData.postValue(productList);
                },
                e -> {

                });

    }

    public void searchProducts(String query) {
        productRepository.searchProducts(query,
                searchedProductsLiveData::postValue,
                error -> {
                }
        );
    }

    public LiveData<List<ProductModel>> getProductsLiveData() {
        return  products;
    }

    public void loadProductsByCategory(String categoryId) {
        ProductRepository.getProductsByCategory(categoryId,
                products::postValue,
                error -> {
                }
        );
    }
}
