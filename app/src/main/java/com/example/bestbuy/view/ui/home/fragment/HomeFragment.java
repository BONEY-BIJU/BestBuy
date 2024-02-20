package com.example.bestbuy.view.ui.home.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import java.util.List;

import com.example.bestbuy.R;
import com.example.bestbuy.adapter.CategoryAdapter;
import com.example.bestbuy.adapter.ImageSliderAdapter;
import com.example.bestbuy.adapter.NewArrivalsAdapter;
import com.example.bestbuy.adapter.TrendingAdapter;
import com.example.bestbuy.view.ui.home.Model.HomeModel;
import com.example.bestbuy.view.ui.home.viewmodel.HomeViewModel;

public class HomeFragment extends Fragment {
    private GridView gridView;
    private ViewPager2 viewPager;
    private RecyclerView categoryView,trendingView;
    private CategoryAdapter categoryAdapter;
    private ImageSliderAdapter Slideradapter;
    private NewArrivalsAdapter newArrivalsAdapter;
    private TrendingAdapter trendingAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        viewPager = root.findViewById(R.id.image_slider);
        categoryView = root.findViewById(R.id.rec_category);
        gridView=root.findViewById(R.id.new_arrivals);
        categoryView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
       trendingView=root.findViewById(R.id.trendingView);
       GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
       trendingView.setLayoutManager(gridLayoutManager);
       // trendingView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        //trendingView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        observeViewModel();

    }


    private void observeViewModel() {
        HomeViewModel viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        viewModel.getImageUrls().observe(getViewLifecycleOwner(), this::updateImageSlider);
        viewModel.getCategories().observe(getViewLifecycleOwner(), this::updateRecycleView);
        viewModel.getNewArrivals().observe(getViewLifecycleOwner(),this::updateGridView);
        viewModel.getTrendingProducts().observe(getViewLifecycleOwner(),this::updateTrendingView);
    }

    private void updateTrendingView(List<HomeModel.trending> trendingProduts) {
        trendingAdapter=new TrendingAdapter(getContext(),trendingProduts);
        trendingView.setAdapter(trendingAdapter);
    }

    private void updateImageSlider(List<HomeModel.sliderimage> imageUrls) {
        Slideradapter = new ImageSliderAdapter(imageUrls);
        viewPager.setAdapter(Slideradapter);
    }

   private void updateRecycleView(List<HomeModel.Category> categories) {
      categoryAdapter =new CategoryAdapter(requireContext(),categories);
      categoryView.setAdapter(categoryAdapter);
   }
   private void updateGridView(List<HomeModel.Product>products){
        newArrivalsAdapter=new NewArrivalsAdapter(requireContext(),products);
        gridView.setAdapter(newArrivalsAdapter);
   }
}
