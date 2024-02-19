package com.example.bestbuy.repository;

import com.example.bestbuy.view.ui.home.Model.HomeModel;
import com.example.bestbuy.view.ui.products.model.ProductModel;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ProductRepository {
    public void FetchProducts(Consumer<List<ProductModel>> onSuccess, Consumer<Exception> onError){
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
}
