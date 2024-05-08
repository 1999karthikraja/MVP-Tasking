package com.example.mvptask.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.mvptask.contract.ProductContract;
import com.example.mvptask.Model.Product;
import com.example.mvptask.R;
import com.example.mvptask.adapter.ProductAdapter;
import com.example.mvptask.presenter.ProductPresenter;

import java.util.List;

public class ProductActivity extends AppCompatActivity implements ProductContract.View, ProductAdapter.OnProductItemClickListener {

    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private ProductPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ProductAdapter(this);
        recyclerView.setAdapter(adapter);

        presenter = new ProductPresenter(this);
        presenter.getProducts();
    }

    @Override
    public void displayProducts(List<Product> products) {
        adapter.setProducts(products);
    }

    @Override
    public void displayError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProductItemClick(Product product) {
        Intent intent = new Intent(this, ProductDetailActivity.class);
        intent.putExtra("product", (CharSequence) product.getTitle());
        intent.putExtra("des", (CharSequence) product.getDescription());
        intent.putExtra("image", (CharSequence) product.getImage());
        startActivity(intent);
    }
}