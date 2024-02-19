package com.example.bestbuy.view.ui.products.model;

public class ProductModel {
        private String imageUrl;
        private String price;
        private String name;
        private String description;
        private String rating;

        public ProductModel() {
            // Default constructor required for Firebase
        }

        public ProductModel(String imageUrl, String price, String name, String description, String rating) {
            this.imageUrl = imageUrl;
            this.price = price;
            this.name = name;
            this.description = description;
            this.rating = rating;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getRating() {
            return rating;
        }

        public void setRating(String rating) {
            this.rating = rating;
        }
    }


