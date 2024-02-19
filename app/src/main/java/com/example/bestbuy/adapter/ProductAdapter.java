package com.example.bestbuy.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bestbuy.R;
import com.example.bestbuy.view.ui.products.model.ProductModel;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> implements Filterable {

    private List<ProductModel> productList;
    private List<ProductModel> productListFiltered;

    public ProductAdapter(List<ProductModel> productList) {
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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productNameTextView = itemView.findViewById(R.id.);
        }

        public void bind(ProductModel product) {
            productNameTextView.setText(product.getName());
            // Bind other product data as needed
        }
    }
}
