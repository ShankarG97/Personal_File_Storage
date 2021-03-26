package com.infovenz.sportz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.infovenz.sportz.DBFiles.DBHelper;
import com.infovenz.sportz.adapter.ProductAdapter;
import com.infovenz.sportz.adapter.ProductListAdapter;
import com.infovenz.sportz.model.ProductCategory;
import com.infovenz.sportz.model.Products;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ProductAdapter adapter;
    ProductListAdapter productListAdapter;
    RecyclerView productCatRecycler, productListRecycler;
    String userName="";
    TextView username;
    Button cartPage;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper= new DBHelper(this);
        Intent getName= getIntent();
        userName = getName.getStringExtra("name");

        username = findViewById(R.id.lbl_user);
        cartPage = findViewById(R.id.btn_addCart);

        username.setText("Hello "+userName);

        List<ProductCategory> productCategoryList = new ArrayList<>();
        productCategoryList.add(new ProductCategory(1, "Most Popular"));
        productCategoryList.add(new ProductCategory(2, "Most Searched"));
        productCategoryList.add(new ProductCategory(3, "Squash"));
        productCategoryList.add(new ProductCategory(4, "Badminton"));
        productCategoryList.add(new ProductCategory(5, "Tennis"));

        setProductRecycler(productCategoryList);

        List<Products> productsList = new ArrayList<>();
        productsList.add(new Products(1,"Dolphin Sqaush ball black","₹449", R.drawable.sqs_ball_1));
        productsList.add(new Products(2,"WILSON Tennis Ball","₹430", R.drawable.ten_ball_img_1));
        productsList.add(new Products(3,"COSCO Nylon Shuttlecock","₹348", R.drawable.shut_imag_1));
        productsList.add(new Products(4,"Yonex GR 303 Badminton Racket","₹599", R.drawable.bad_img_4));

        productListRecycler = findViewById(R.id.productView);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        productListRecycler.setLayoutManager(layoutManager);
        productListAdapter = new ProductListAdapter(this, productsList);
        productListRecycler.setAdapter(productListAdapter);

        cartPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login= new Intent(getApplicationContext(),MycartPage.class);
                startActivity(login);
            }
        });
    }

    public void onBackPressed() {
        Boolean isTableDeleted = dbHelper.DeleteCartData();
        if (isTableDeleted==true)
            finishAffinity();
        else
            finish();
    }

    private void setProductRecycler(List<ProductCategory> categoryList){
        productCatRecycler = findViewById(R.id.cat_recycle_view);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        productCatRecycler.setLayoutManager(layoutManager);
        adapter = new ProductAdapter(this, categoryList);
        productCatRecycler.setAdapter(adapter);
    }

}