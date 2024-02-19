package com.example.bestbuy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bestbuy.R;
import com.example.bestbuy.view.ui.home.Model.HomeModel;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private List<HomeModel.Category> categories;
    private Context context;

    public CategoryAdapter(Context context,List<HomeModel.Category> categories) {
        this.context = context;
        this.categories = categories;

    }
    public void setCategories(List<HomeModel.Category> categories) {
        this.categories = categories;
        notifyDataSetChanged(); // Notify the adapter that the data set has changed
    }


    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_category, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        HomeModel.Category category = categories.get(position);
        holder.bind(category);
    }

    @Override
    public int getItemCount() {
        return categories != null ? categories.size() : 0;
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        private ImageView categoryImage;
        private TextView categoryName;


        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryImage = itemView.findViewById(R.id.cat_img);
            categoryName = itemView.findViewById(R.id.cat_name);

        }

        public void bind(HomeModel.Category category) {
            categoryName.setText(category.getName());
            Glide.with(context).load(category.getImage()).into(categoryImage);
        }
    }
}
