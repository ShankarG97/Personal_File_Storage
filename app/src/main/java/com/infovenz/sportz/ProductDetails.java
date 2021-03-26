package com.infovenz.sportz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.infovenz.sportz.DBFiles.DBHelper;
import com.infovenz.sportz.model.CartProduct;

import java.util.ArrayList;
import java.util.List;

public class ProductDetails extends AppCompatActivity {

    ImageView plus_btn, minus_btn, product_image;
    TextView prd_count, product_name, product_rate;
    Button add_to_cart;
    String prd_name, prd_rate;
    Integer prd_imag_id, product_id;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        Intent prd_intent= getIntent();
        dbHelper= new DBHelper(this);

        prd_name =prd_intent.getStringExtra("product_name");
        prd_rate =prd_intent.getStringExtra("product_rate");
        prd_imag_id= prd_intent.getIntExtra("prd_img", R.drawable.logo_img);
        product_id= prd_intent.getIntExtra("product_id", 0);

        plus_btn = findViewById(R.id.btn_plus);
        minus_btn = findViewById(R.id.btn_minus);
        product_image = findViewById(R.id.prd_img);
        prd_count = findViewById(R.id.prd_count);
        product_name = findViewById(R.id.prd_name_detail);
        product_rate = findViewById(R.id.prd_rate_detail);
        add_to_cart = findViewById(R.id.btn_addtoCart);

        product_name.setText(prd_name);
        product_rate.setText(prd_rate);
        product_image.setImageResource(prd_imag_id);

        plus_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(prd_count.getText().toString()) >= 1){
                    Integer count = Integer.parseInt(prd_count.getText().toString());
                    count++;

                    prd_count.setText(count.toString());
                }
            }
        });

        minus_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(prd_count.getText().toString()) > 1){
                    minus_btn.setEnabled(true);
                    Integer count = Integer.parseInt(prd_count.getText().toString());
                    count--;

                    prd_count.setText(count.toString());
                }
                else
                    prd_count.setText("1");
            }
        });

        add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Integer cnt = Integer.parseInt(prd_count.getText().toString());
                Boolean checkInsert = dbHelper.insertProductDetails(product_id,prd_name,product_rate.getText().toString(),cnt, prd_imag_id );
                if(checkInsert==true){
                    Toast.makeText(getApplicationContext(),"Product Added to cart",Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(getApplicationContext(),"Product already in cart!",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent back_press= new Intent(getApplicationContext(),MainActivity.class);
        startActivity(back_press);
    }
}