package com.example.bestbuy.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.bestbuy.R;

public class CustomSearchView extends LinearLayout {
    private EditText editTextSearch;
    private ImageView imageViewSearch;
    private SearchListener searchListener;

    public CustomSearchView(Context context) {
        super(context);
        init(context);
    }

    public CustomSearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomSearchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.search_view, this);
        editTextSearch = findViewById(R.id.editText_search);
        imageViewSearch = findViewById(R.id.imageView_logo);

        imageViewSearch.setOnClickListener(view -> {
            String query = editTextSearch.getText().toString();
            if (searchListener != null) {
                searchListener.onSearch(query);
            }
        });

        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                // Optional: You can perform search operation here on text change
            }
        });
    }

    public void setSearchListener(SearchListener listener) {
        this.searchListener = listener;
    }

    public interface SearchListener {
        void onSearch(String query);
    }
}
