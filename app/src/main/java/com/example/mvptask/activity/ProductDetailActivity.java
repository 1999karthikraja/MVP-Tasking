package com.example.mvptask.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvptask.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class ProductDetailActivity extends AppCompatActivity {
    TextView title,des;
    ImageView image,back;
    ProgressBar progressbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        title=findViewById(R.id.title);
        des=findViewById(R.id.des);
        image=findViewById(R.id.image);
        back=findViewById(R.id.back);
        progressbar=findViewById(R.id.progressbar);

        product();

    }
    private void product() {
        Bundle bundle=getIntent().getExtras();

        String imag=bundle.getString("image");
        String tit=bundle.getString("product");
        String desc=bundle.getString("des");

        title.setText(tit);
        des.setText(desc);
        if (imag!=null){
            Picasso.get().load(imag).error(R.drawable.baseline_broken_image_24).into(image, new Callback() {
                @Override
                public void onSuccess() {
                    progressbar.setVisibility(View.GONE);
                }

                @Override
                public void onError(Exception e) {
                    Log.d("Error : ", e.getMessage());
                }
            });
        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

}