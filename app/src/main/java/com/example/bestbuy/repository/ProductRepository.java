package com.example.bestbuy.repository;

import com.example.bestbuy.view.ui.home.Model.HomeModel;
import com.example.bestbuy.view.ui.products.model.ProductModel;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ProductRepository {
    public void FetchProducts(Consumer<List<ProductModel>> onSuccess, Consumer<Exception> onError) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Products")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        List<ProductModel> Productlist = new ArrayList<>();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            ProductModel productModel = document.toObject(ProductModel.class);
                            Productlist.add(productModel);
                        }
                        onSuccess.accept(Productlist);
                    } else {
                        onError.accept(task.getException());
                    }
                });


    }

//    public void searchProducts(String query, Consumer<List<ProductModel>> onSuccess, Consumer<Exception> onError) {
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        db.collection("Products")
//                .whereEqualTo("name", query) // Change "productName" to the actual field name you want to search against
//                .get()
//                .addOnCompleteListener(task -> {
//                    if (task.isSuccessful()) {
//                        List<ProductModel> productList = new ArrayList<>();
//                        for (QueryDocumentSnapshot document : task.getResult()) {
//                            ProductModel productModel = document.toObject(ProductModel.class);
//                            productList.add(productModel);
//                        }
//                        onSuccess.accept(productList);
//                    } else {
//                        onError.accept(task.getException());
//                    }
//                });
//    }
}
//    public void searchProducts(String query, Consumer<List<AllProducts>> onSuccess, Consumer<Exception> onError) {
//        String lowerQuery = query.toLowerCase();
//        Log.d("QueyinRep0", lowerQuery);
//        List<AllProducts> searchResults = new ArrayList<>();
//
//
//        for (AllProducts product : productList) {
//
//            String lowerTitle = product.getTitle().toLowerCase();
//            String lowerType = product.getType().toLowerCase();
//
//            if (lowerTitle.contains(lowerQuery) || lowerType.contains(lowerQuery)) {
//                Log.d("SearchProducts1", "Condition is true for: " + lowerTitle + " | " + lowerType + " | " + lowerQuery);
//                searchResults.add(product);
//            } else {
//                Log.d("SearchProducts1", "Condition is false for: " + lowerTitle + " | " + lowerType + " | " + lowerQuery);
//                // Add any additional logic or logging for the else condition if needed
//            }
//        }
//
//        Log.d("SearchProducts", "Product List Size: " + productList.size());
//        Log.d("SearchProducts", "Search Results Size: " + searchResults.size());
//
//        if (!searchResults.isEmpty()) {
//            onSuccess.accept(searchResults);
//        } else if (onError != null) {
//            onError.accept(new Exception("No matching products found."));
//        }
//    }
//
//}
