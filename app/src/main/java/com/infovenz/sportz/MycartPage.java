package com.infovenz.sportz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.infovenz.sportz.DBFiles.DBHelper;
import com.infovenz.sportz.adapter.CartAdapter;
import com.infovenz.sportz.model.CartProduct;

import java.util.ArrayList;
import java.util.List;

public class MycartPage extends AppCompatActivity {

    RecyclerView cartView;
    CartAdapter cartPageAdapter;
    DBHelper dbHelper;
    ConstraintLayout mainLayout, childLayout;
    List<CartProduct> cartProduct;
    Button home_click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycart_page);
        dbHelper= new DBHelper(this);

        mainLayout = findViewById(R.id.main_layout);
        childLayout = findViewById(R.id.child_Layout);
        home_click = findViewById(R.id.btn_home);

        home_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back_press= new Intent(getApplicationContext(),MainActivity.class);
                startActivity(back_press);
            }
        });


        Cursor res = dbHelper.getCartData();
        if(res.getCount()==0){
            childLayout.setVisibility(View.VISIBLE);
        }
        else {
            cartView = findViewById(R.id.cart_View);
            String prd_id="";
            String prd_name="";
            String prd_rate="";
            String prd_count="";
            String prd_img="";
            cartProduct = new ArrayList<>();
            if(res.moveToFirst() && res.getCount() >= 1){
                for (int i=0;i<res.getCount();i++){
                    prd_id = res.getString(0);
                    prd_name = res.getString(1);
                    prd_rate = res.getString(2);
                    prd_count = res.getString(3);
                    prd_img = res.getString(4);

                    cartProduct.add(new CartProduct(Integer.parseInt(prd_id), prd_name, prd_rate, Integer.parseInt(prd_count),Integer.parseInt(prd_img)));
                    prd_id="";
                    prd_name="";
                    prd_rate="";
                    prd_count="";
                    prd_img="";
                    res.moveToNext();
                }
            }
            cartView.setVisibility(View.VISIBLE);
            RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
            cartView.setLayoutManager(layoutManager);
            cartPageAdapter = new CartAdapter(this,cartProduct);
            cartView.setAdapter(cartPageAdapter);
        }
    }
}