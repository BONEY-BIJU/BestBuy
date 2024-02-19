package com.example.bestbuy.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bestbuy.R;
import com.example.bestbuy.view.ui.home.Model.HomeModel;
import com.squareup.picasso.Picasso;
import java.util.List;

public class ImageSliderAdapter extends RecyclerView.Adapter<ImageSliderAdapter.ViewHolder> {

    private List<HomeModel.sliderimage> imageUrls;



    public ImageSliderAdapter(List<HomeModel.sliderimage> imageUrls) {
        this.imageUrls = imageUrls;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image_slider, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HomeModel.sliderimage imageUrl = imageUrls.get(position);
        Picasso.get().load(imageUrl.getImageurl()).into(holder.imageView);
    }


    @Override
    public int getItemCount()
    {
        return imageUrls != null ? imageUrls.size() : 0;
    }



    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
        }
    }
}

