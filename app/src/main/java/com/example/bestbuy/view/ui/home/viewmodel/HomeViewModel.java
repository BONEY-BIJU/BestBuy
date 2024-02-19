package com.example.bestbuy.view.ui.home.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bestbuy.view.ui.home.Model.HomeModel;
import com.example.bestbuy.repository.HomeRepository;
import java.util.List;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<List<HomeModel.sliderimage>> imageUrls = new MutableLiveData<>();
    private MutableLiveData<List<HomeModel.Product>> newArrivals = new MutableLiveData<>();
    private final MutableLiveData<List<HomeModel.Category>> categoriesLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<HomeModel.trending>> trendingProducts =new MutableLiveData<>();
    private final HomeRepository repository;

    public HomeViewModel() {
        repository = new HomeRepository();
        fetchCategories();
        fetchNewArrivals();
        fetchSliderImages();
    }

    public LiveData<List<HomeModel.sliderimage>> getImageUrls() {
        fetchSliderImages();
        return imageUrls;
    }

    public LiveData<List<HomeModel.Category>> getCategories() {
        fetchCategories();
        return categoriesLiveData;

    }


    public LiveData<List<HomeModel.Product>> getNewArrivals() {
        fetchNewArrivals();
        return newArrivals;
    }

    public LiveData<List<HomeModel.trending>>getTrendingProducts(){
        fetchTrendingProducts();
        return trendingProducts;
    }
    private void fetchSliderImages() {
        repository.fetchSliderImages((imageUrls::postValue),
                e->{


                });
    }


    private void fetchCategories() {
        repository.fetchCategoriesFromFirestore(categoriesLiveData::postValue,
                e->{


        });
    }
    private void fetchNewArrivals () {
        repository.fetchNewArrivals((newArrivals::postValue),
                e->{


                });
    }
private void fetchTrendingProducts(){
        repository.fetchTrendingProducts((trendingProducts::postValue),
                e -> {

                });
}

}
