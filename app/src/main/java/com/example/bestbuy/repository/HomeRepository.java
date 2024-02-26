package com.example.bestbuy.repository;

import com.example.bestbuy.view.ui.home.Model.HomeModel;
import com.example.bestbuy.adapter.CategoryAdapter;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class HomeRepository {

    private FirebaseFirestore db;
    public HomeRepository() {
        db = FirebaseFirestore.getInstance();
    }


    public void fetchCategoriesFromFirestore(Consumer <List<HomeModel.Category>> onSuccess, Consumer<Exception> onError) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Categories")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        List<HomeModel.Category> categories = new ArrayList<>();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            String categoryId = document.getId();
                            HomeModel.Category category = document.toObject(HomeModel.Category.class);
                            category.setCategoryId(categoryId);
                            categories.add(category);
                        }
                      onSuccess.accept(categories);
                    } else {
                      onError.accept(task.getException());
                    }
                });

    }

    public void fetchNewArrivals(Consumer <List<HomeModel.Product>> onSuccess, Consumer<Exception> onError) {
        db.collection("New Arrivals")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        List<HomeModel.Product> products = new ArrayList<>();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            HomeModel.Product product = document.toObject(HomeModel.Product.class);
                            products.add(product);
                        }
                        onSuccess.accept(products);
                    } else {
                        onError.accept(task.getException());
                    }
                });


    }

    public void fetchSliderImages(Consumer<List<HomeModel.sliderimage>> onSuccess, Consumer<Exception> onError) {
        db.collection("SliderImages").document("sS7iuUnIT5ziW54LDLgk") // Replace "your_document_id" with the actual document ID
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            List<HomeModel.sliderimage> sliderImages = new ArrayList<>();
                            // Assuming your image URLs are stored in an array field called "imageUrls"
                            List<String> imageUrls = (List<String>) document.get("imageUrls");
                            // Iterate through the imageUrls list and create sliderimage objects
                            for (String imageUrl : imageUrls) {
                                HomeModel.sliderimage sliderImage = new HomeModel.sliderimage(imageUrl);
                                sliderImages.add(sliderImage);
                            }
                            onSuccess.accept(sliderImages);
                        } else {
                            // Document doesn't exist
                            onError.accept(new Exception("Document not found"));
                        }
                    } else {
                        // Error getting document
                        onError.accept(task.getException());
                    }
                });
    }

    public void fetchTrendingProducts(Consumer <List<HomeModel.trending>> onSucess, Consumer<Exception> onError)
    {
        db.collection("Trending")
                .get()
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        List<HomeModel.trending> trendingProducts=new ArrayList<>();
                        for(QueryDocumentSnapshot document :task.getResult()) {
                            HomeModel.trending trending = document.toObject(HomeModel.trending.class);
                            trendingProducts.add(trending);
                        }
                        onSucess.accept(trendingProducts);
                        }else {
                        onError.accept(task.getException());
                    }

    });
    }

}
