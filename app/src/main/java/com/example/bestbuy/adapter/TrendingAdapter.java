package com.example.bestbuy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.bestbuy.R;
import com.example.bestbuy.view.ui.home.Model.HomeModel;
import com.squareup.picasso.Picasso;
import java.util.List;

public class TrendingAdapter extends RecyclerView.Adapter<TrendingAdapter.ViewHolder> {

    private Context context;
    private List<HomeModel.trending> trendingProducts;

    public TrendingAdapter(Context context,List<HomeModel.trending> trendingProducts) {
        this.context = context;
        this.trendingProducts = trendingProducts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.trending_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HomeModel.trending product = trendingProducts.get(position);
        holder.bind(product);
    }

    @Override
    public int getItemCount() {
        return trendingProducts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView trendingImage;
        private TextView productName;
        private TextView productPrice;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            trendingImage = itemView.findViewById(R.id.trendingimg);
            productName = itemView.findViewById(R.id.trending_product_name);
            productPrice = itemView.findViewById(R.id.trendingprice);

        }

        public void bind(HomeModel.trending product) {
            Picasso.get().load(product.getImage()).into(trendingImage);
            productName.setText(product.getName());
            productPrice.setText(product.getPrice());

        }
    }
}
