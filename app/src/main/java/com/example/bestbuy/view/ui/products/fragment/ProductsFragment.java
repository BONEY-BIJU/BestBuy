package com.example.bestbuy.view.ui.products.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bestbuy.R;
import com.example.bestbuy.adapter.ProductAdapter;
import com.example.bestbuy.databinding.FragmentProductsBinding;
import com.example.bestbuy.view.ui.home.viewmodel.HomeViewModel;
import com.example.bestbuy.view.ui.products.Viewmodel.ProductsViewModel;
import com.example.bestbuy.view.ui.products.model.ProductModel;

import java.util.List;


public class ProductsFragment extends Fragment {


        private RecyclerView recyclerView;
        private ProductAdapter adapter;
        private List<ProductModel> productList;
        private SearchView searchView;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_products, container, false);
            recyclerView = rootView.findViewById(R.id.recyclerView);
            //searchView = rootView.findViewById(R.id.searchView);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
            recyclerView.setLayoutManager(gridLayoutManager);



            // Set up search listener
//            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//                @Override
//                public boolean onQueryTextSubmit(String query) {
//                    // Handle search query submission (optional)
//                  //  ProductsViewModel.searchProducts(query);
//                    return true;
//                }
//
//                @Override
//                public boolean onQueryTextChange(String newText) {
//
//                    adapter.getFilter().filter(newText);
//                    return true;
//                }
//            });

            return rootView;
        }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        observeViewModel();

    }


    private void observeViewModel() {
        ProductsViewModel viewModel = new ViewModelProvider(this).get(ProductsViewModel.class);
        viewModel.getAllProductsLiveData().observe(getViewLifecycleOwner(), this::updateRecycleView);
         //viewModel.getSearchedProductsLiveData().observe(getViewLifecycleOwner(),this:: searchUpdates);
    }

//    private void searchUpdates(List<ProductModel> productModels) {
//            adapter=new ProductAdapter(getContext(),productList);
//            recyclerView.setAdapter(adapter);
//    }

    private void updateRecycleView(List<ProductModel> productList) {
            adapter= new ProductAdapter(getContext(),productList);
            recyclerView.setAdapter(adapter);
    }
}