package com.example.bestbuy.view.ui.products.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bestbuy.R;
import com.example.bestbuy.adapter.ProductAdapter;
import com.example.bestbuy.view.CustomSearchView;
import com.example.bestbuy.view.ui.products.Viewmodel.ProductsViewModel;
import com.example.bestbuy.view.ui.products.model.ProductModel;

import java.util.List;

public class ProductsFragment extends Fragment {

    private RecyclerView listRecyclerView,resultRecyclerView;
    private ProductAdapter adapter;
    private ProductsViewModel viewModel;
    private CustomSearchView customSearchView;
    private LinearLayout productListLayout;
    private LinearLayout searchResultsLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_products, container, false);
        listRecyclerView = rootView.findViewById(R.id.recyclerView);
        resultRecyclerView=rootView.findViewById(R.id.search_results_recyclerView);
        customSearchView = rootView.findViewById(R.id.custom_search_view);
        productListLayout = rootView.findViewById(R.id.product_list_layout);
        searchResultsLayout = rootView.findViewById(R.id.search_results_layout);
        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(getContext(), 2);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        listRecyclerView.setLayoutManager(gridLayoutManager);
        resultRecyclerView.setLayoutManager(gridLayoutManager1);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        observeViewModel();

        customSearchView.setSearchListener(query -> viewModel.searchProducts(query));

    }

    private void observeViewModel() {
        viewModel = new ViewModelProvider(this).get(ProductsViewModel.class);
        viewModel.getAllProductsLiveData().observe(getViewLifecycleOwner(), this::updateProductList);
        viewModel.getSearchedProductsLiveData().observe(getViewLifecycleOwner(), searchResults->{
            Log.d("searchResults",""+searchResults);
            showSearchResults(searchResults);
        });

    }

    private void updateProductList(List<ProductModel> productList) {
        if (adapter == null) {
            adapter = new ProductAdapter(getContext(), productList);
            listRecyclerView.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
        productListLayout.setVisibility(View.VISIBLE);
        searchResultsLayout.setVisibility(View.GONE);
    }

    private void showSearchResults(List<ProductModel> searchResults) {
        if (adapter == null) {
            adapter = new ProductAdapter(getContext(), searchResults);
            resultRecyclerView.setAdapter(adapter);
        } else {
            adapter = new ProductAdapter(getContext(), searchResults);
            resultRecyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        productListLayout.setVisibility(View.GONE);
        searchResultsLayout.setVisibility(View.VISIBLE);
    }
}
