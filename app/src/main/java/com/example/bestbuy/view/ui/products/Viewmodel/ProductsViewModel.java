package com.example.bestbuy.view.ui.products.Viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bestbuy.view.ui.products.model.ProductModel;
import com.example.bestbuy.repository.ProductRepository;

import java.util.List;

public class ProductViewModel extends ViewModel {
    private MutableLiveData<List<ProductModel>> productListLiveData;
    private ProductRepository productRepository;

    public ProductViewModel() {
        productListLiveData = new MutableLiveData<>();
        productRepository = new ProductRepository(); // Assuming you have a ProductRepository class
    }

    public LiveData<List<ProductModel>> getProductListLiveData() {
        return productListLiveData;
    }

    public void fetchProducts() {
        productRepository.FetchProducts(new ProductRepository.ProductFetchCallback() {
            @Override
            public void onSuccess(List<ProductModel> productList) {
                productListLiveData.setValue(productList);
            }

            @Override
            public void onError(Exception e) {
                // Handle error
            }
        });
    }

    public void searchProducts(String query) {
        productRepository.searchProducts(query, new ProductRepository.ProductSearchCallback() {
            @Override
            public void onSearchResults(List<ProductModel> searchResults) {
                productListLiveData.setValue(searchResults);
            }
        });
    }
}
