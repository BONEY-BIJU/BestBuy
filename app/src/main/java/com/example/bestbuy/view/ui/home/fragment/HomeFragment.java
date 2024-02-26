package com.example.bestbuy.view.ui.home.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import java.util.List;
import java.util.Objects;

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
    private TextView newarrivalSeeAll,trendingSeeAll;
    private Handler sliderHandler = new Handler();
    private int currentPage = 0;
    private final long SLIDE_DELAY = 3000;
    private LinearLayout sliderIndicator;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        viewPager = root.findViewById(R.id.image_slider);
        sliderIndicator = root.findViewById(R.id.slider_indicator);
        newarrivalSeeAll=root.findViewById(R.id.popular_see_all);
        trendingSeeAll=root.findViewById(R.id.newProducts_see_all);
        categoryView = root.findViewById(R.id.rec_category);
        gridView=root.findViewById(R.id.new_arrivals);
        categoryView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
       trendingView=root.findViewById(R.id.trendingView);
       GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
       trendingView.setLayoutManager(gridLayoutManager);
       newarrivalSeeAll.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               NavController navController = Navigation.findNavController((Activity) requireContext(), R.id.nav_host_fragment_activity_main);
               navController.navigate(R.id.action_navigation_home_to_navigation_products);
           }
       });
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
        startAutoSlider();
        addPageChangeListener();
        setupIndicator(imageUrls.size());
    }

   private void updateRecycleView(List<HomeModel.Category> categories) {
      categoryAdapter =new CategoryAdapter(requireContext(),categories);
      categoryView.setAdapter(categoryAdapter);
   }
   private void updateGridView(List<HomeModel.Product>products){
        newArrivalsAdapter=new NewArrivalsAdapter(requireContext(),products);
        gridView.setAdapter(newArrivalsAdapter);
   }

    private void startAutoSlider() {
        sliderHandler.postDelayed(sliderRunnable, SLIDE_DELAY);
    }

    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            if (currentPage == Slideradapter.getItemCount()) {
                currentPage = 0;
            }
            viewPager.setCurrentItem(currentPage++, true);
            sliderHandler.postDelayed(this, SLIDE_DELAY);
        }
    };
    private void addPageChangeListener() {
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                updateIndicator(position); // Update indicator when page changes
            }
        });
    }

    private void setupIndicator(int count) {
        for (int i = 0; i < count; i++) {
            ImageView indicator = new ImageView(requireContext());
            indicator.setImageResource(R.drawable.indicator_inactive); // Inactive indicator drawable
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            layoutParams.setMargins(8, 0, 8, 0); // Set margins between indicators
            indicator.setLayoutParams(layoutParams);
            sliderIndicator.addView(indicator);
        }
    }

    private void updateIndicator(int position) {
        int childCount = sliderIndicator.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView indicator = (ImageView) sliderIndicator.getChildAt(i);
            if (i == position) {
                indicator.setImageResource(R.drawable.indicator_active); // Active indicator drawable
            } else {
                indicator.setImageResource(R.drawable.indicator_inactive); // Inactive indicator drawable
            }
        }
    }
}
