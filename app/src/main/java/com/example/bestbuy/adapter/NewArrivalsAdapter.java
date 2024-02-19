package com.example.bestbuy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.example.bestbuy.R;
import com.example.bestbuy.view.ui.home.Model.HomeModel;

import java.util.List;

public class NewArrivalsAdapter extends ArrayAdapter<HomeModel.Product> {
    private Context mContext;
    private List<HomeModel.Product> mProducts;

    public NewArrivalsAdapter(Context context, List<HomeModel.Product> products) {
        super(context, R.layout.newarrivals_layout, products);
        mContext = context;
        mProducts = products;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View gridViewItem = convertView;
        if (gridViewItem == null) {
            gridViewItem = LayoutInflater.from(mContext).inflate(R.layout.newarrivals_layout, parent, false);
        }

        HomeModel.Product product = mProducts.get(position);
        ImageView imageView = gridViewItem.findViewById(R.id.Newarrivalimage);
        Glide.with(mContext).load(product.getImage()).into(imageView);
        TextView textView = gridViewItem.findViewById(R.id.new_product_name);
        textView.setText(product.getName());
        TextView textView1=gridViewItem.findViewById(R.id.price);
        textView1.setText(product.getPrice());

        return gridViewItem;
    }
}
