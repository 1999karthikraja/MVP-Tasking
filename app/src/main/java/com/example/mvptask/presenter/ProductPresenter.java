package com.example.mvptask.presenter;

import android.util.Log;

import com.example.mvptask.Model.Product;

import com.example.mvptask.contract.ProductContract;
import com.example.mvptask.enitity.RetrofitInstance;
import com.example.mvptask.network.ProductService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductPresenter implements ProductContract.Presenter {

    private ProductContract.View view;
    public ProductPresenter(ProductContract.View view) {
        this.view = view;
    }

    @Override
    public void getProducts() {
        ProductService service = RetrofitInstance.getRetrofitInstance().create(ProductService.class);
        Call<List<Product>> call = service.getProducts();
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    List<Product> products = response.body();
                    view.displayProducts(products);
                } else {

                    Log.e("API Error", "Failed to fetch data: " + response.code() + " " + response.message());

                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                view.displayError("Failed to fetch data");
                Log.e("API Error", "Failed to fetch data", t);
            }
        });
    }
}
