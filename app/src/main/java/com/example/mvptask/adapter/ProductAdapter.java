package com.example.mvptask.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvptask.Model.Product;
import com.example.mvptask.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private List<Product> products = new ArrayList<>();
    private OnProductItemClickListener listener;

    public ProductAdapter(OnProductItemClickListener listener) {
        this.listener = listener;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = products.get(position);
        holder.bind(product);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder{
        private TextView product_title,product_price;
        private ImageView product_image;
        CardView card_view;
        ProgressBar progressbar;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            product_image = itemView.findViewById(R.id.product_image);
            product_title = itemView.findViewById(R.id.product_title);
            product_price = itemView.findViewById(R.id.product_price);
            progressbar = itemView.findViewById(R.id.progressbar);
            card_view = itemView.findViewById(R.id.card_view);
        }
        public void bind(Product product) {
            product_title.setText(product.getTitle());
            product_price.setText( product.getPrice());
            Picasso.get().load(product.getImage()).into(product_image, new Callback() {
                @Override
                public void onSuccess() {
                    progressbar.setVisibility(View.GONE);
                }

                @Override
                public void onError(Exception e) {
                    Log.d("Error : ", e.getMessage());
                }
            });
            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onProductItemClick(product);
                }
            });
        }
    }
    public interface OnProductItemClickListener {
        void onProductItemClick(Product product);
    }
}
