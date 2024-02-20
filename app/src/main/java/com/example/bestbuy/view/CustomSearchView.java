package com.example.bestbuy.view;
    import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;
    import android.widget.ImageView;
    import android.widget.LinearLayout;

    import com.example.bestbuy.R;

public class CustomSearchView extends LinearLayout {
        private EditText editTextSearch;
        private ImageView ImageSearch;

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
            ImageSearch = findViewById(R.id.imageView_logo);

            ImageSearch.setOnClickListener(view -> {
                String query = editTextSearch.getText().toString();
                // Perform search operation with the query
                // You can define a listener to handle search actions
                // For example:
                // if (searchListener != null) {
                //     searchListener.onSearch(query);
                // }
            });

            // Optional: Add text change listener to perform search on text change
            editTextSearch.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {}

                @Override
                public void afterTextChanged(Editable s) {
                    // You can perform search operation here on text change
                }
            });
        }
    }


