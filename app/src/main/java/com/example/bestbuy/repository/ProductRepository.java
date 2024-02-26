package com.example.bestbuy.repository;

import android.util.Log;

import com.example.bestbuy.view.ui.home.Model.HomeModel;
import com.example.bestbuy.view.ui.products.model.ProductModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ProductRepository {
    private  List<ProductModel> productList;

//    public ProductRepository(List<ProductModel> productList) {
//        this.productList = productList;
//    }

    public void FetchProducts(Consumer<List<ProductModel>> onSuccess, Consumer<Exception> onError) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Products")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                       productList = new ArrayList<>();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            ProductModel productModel = document.toObject(ProductModel.class);
                           this.productList.add(productModel);
                        }
                        onSuccess.accept(productList);
                    } else {
                        onError.accept(task.getException());
                    }
                });

    }
        public void searchProducts (String query, Consumer < List<ProductModel>> onSuccess, Consumer <Exception> onError){
            String lowerQuery = query.toLowerCase();
            List<ProductModel> searchResults = new ArrayList<>();

            for (ProductModel product : productList) {
                String lowerProductName = product.getName().toLowerCase();
                Log.d("SearchDebug","Out"+lowerProductName+""+lowerQuery);
                if (lowerProductName.contains(lowerQuery)) {
                    Log.d("SearchDebug","In  "+lowerProductName+""+lowerQuery);
                    searchResults.add(product);
                }
            }

            onSuccess.accept(searchResults);
        }

    public static void getProductsByCategory(String categoryId, Consumer<List<ProductModel>> onSuccess, Consumer<Exception> onError) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference clickedDocumentReference= db.collection("Categories").document(categoryId);
        db.collection("Products")
//                .whereEqualTo("category", categoryId)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        List<ProductModel> categoryProducts = new ArrayList<>();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            DocumentReference documentReference = (DocumentReference) document.get("category");
                            if (documentReference != null && documentReference.equals(clickedDocumentReference)) {
                                ProductModel product = document.toObject(ProductModel.class);
                                categoryProducts.add(product);
                            }
                        }
                        onSuccess.accept(categoryProducts);
                    }else {
                        onError.accept(task.getException());
                    }
                });
    }
    }


//    public void searchProducts(String query, Consumer<List<ProductModel>> onSuccess, Consumer<Exception> onError) {
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        db.collection("Products")
//                .whereEqualTo("name", query) // Change "name" to the actual field name you want to search against
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
//}

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
