package com.example.mvptask.contract;

import com.example.mvptask.Model.Product;

import java.util.List;
public interface ProductContract {
    interface View {
        void displayProducts(List<Product> products);
        void displayError(String message);
    }

    interface Presenter {
        void getProducts();
    }
}

