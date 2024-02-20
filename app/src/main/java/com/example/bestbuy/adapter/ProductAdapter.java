package com.example.bestbuy.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bestbuy.R;
import com.example.bestbuy.view.ui.products.model.ProductModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> implements Filterable {

    private List<ProductModel> productList;
    private List<ProductModel> productListFiltered;
    private Context context;

    public ProductAdapter(Context context,List<ProductModel> productList) {
        this.context=context;
        this.productList = productList;
        this.productListFiltered = new ArrayList<>(productList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.products_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProductModel product = productListFiltered.get(position);
        holder.bind(product);
    }

    @Override
    public int getItemCount() {
        return productListFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString().toLowerCase().trim();
                if (charString.isEmpty()) {
                    productListFiltered = productList;
                } else {
                    List<ProductModel> filteredList = new ArrayList<>();
                    for (ProductModel product : productList) {
                        if (product.getName().toLowerCase().contains(charString)) {
                            filteredList.add(product);
                        }
                    }
                    productListFiltered = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = productListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                productListFiltered = (List<ProductModel>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView productNameTextView;
        private TextView productPriceTextview;
        private ImageView productImageview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productNameTextView = itemView.findViewById(R.id.All_product_name);
            productPriceTextview=itemView.findViewById(R.id.AllProductprice);
            productImageview=itemView.findViewById(R.id.productimg);
        }

        @SuppressLint("SetTextI18n")
        public void bind(ProductModel product) {
            productNameTextView.setText(product.getName());
            String currencySymbol = "₹";
             productPriceTextview.setText(currencySymbol +" " + product.getPrice());
            Picasso.get().load(product.getImageUrl()).into(productImageview);
        }
    }
}
