package com.example.bestbuy.view.ui.home.Model;

public class HomeModel {

//    private String imageurl;
//
//    public HomeModel(String imageurl) {
//        this.imageurl = imageurl;
//    }
//
//   public String getImageUrl() {
//        return imageurl;
//    }


  //  public void setImageUrl(String image) {
    //    this.image = image;
    //}

    public static class Category {
        private String name;
        private String image;
        private String categoryId;

        public Category() {
            // Required empty constructor for Firestore
        }



        public String getName() {
            return name;
        }

        public String getImage() {
            return image;
        }

        public String getCategoryId(){return categoryId;}

        public void setCategoryId(String categoryId) {
            this.categoryId=categoryId;
        }
    }

    public  static class Product {
        private String name;
        private String image;
        private String price;

        public Product() {
            // Empty constructor required for Firestore
        }

        public Product(String name, String image, String price) {
            this.name = name;
            this.image = image;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public String getImage() {
            return image;
        }

        public String getPrice() {
            return price;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public void setPrice(String price) {
            this.price = price;
        }
    }

    public static class sliderimage {
        private String imageurl;

        public sliderimage() {
            // Empty constructor required for Firestore
        }

        public sliderimage(String imageurl) {
            this.imageurl = imageurl;
        }

        public String getImageurl() {
            return imageurl;
        }

        public void setImageurl(String imageurl) {
            this.imageurl = imageurl;
        }
    }

    public static class trending{

        private String image;
        private String name;
        private String price;


        public trending(){

        }

        public trending(String image,String name, String price){
            this.image=image;
            this.name=name;
            this.price=price;

        }

        public String getImage(){
            return image;
        }

        public String getName(){
            return name;
        }

        public String getPrice(){
            return price ;
        }



        public void setImage(String image){
            this.image=image;
        }

        public void setName(String name){
            this.name=name;
        }

        public  void setPrice(String price){
            this.price=price;
        }

}
}

