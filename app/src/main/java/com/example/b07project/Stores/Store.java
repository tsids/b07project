package com.example.b07project.Stores;

import com.example.b07project.Product;

import java.util.List;
import java.io.Serializable;

public class Store implements Serializable{
        private String category;
        private String password;
        private List<Product> products;
        private String store;
        private int storeID;
        private String username;

        public Store() {
        }

        public Store(String category, String password, List<Product> products, String store, int StoreID, String username) {
                this.category = category;
                this.password = password;
                this.products = products;
                this.store = store;
                this.storeID = storeID;
                this.username = username;
        }

        public String getCategory() {
                return category;
        }

        public void setCategory(String category) {
                this.category = category;
        }

        public String getPassword() {
                return password;
        }

        public void setPassword(String password) {
                this.password = password;
        }

        public List<Product> getProducts() {
                return products;
        }

        public void setProducts(List<Product> products) {
                this.products = products;
        }

        public String getStore() {
                return store;
        }

        public void setStore(String store) {
                this.store = store;
        }

        public int getStoreID() {
                return storeID;
        }

        public void setStoreID(int storeID) {
                this.storeID = storeID;
        }

        public String getUsername() {
                return username;
        }

        public void setUsername(String username) {
                this.username = username;
        }

        // Add getters and setters as needed
        // You can also add a constructor to create Store objects with the required fields
}
