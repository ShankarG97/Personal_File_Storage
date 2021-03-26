package com.infovenz.sportz.adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.infovenz.sportz.ProductDetails;
import com.infovenz.sportz.R;
import com.infovenz.sportz.model.Products;

import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductListViewHolder> {

    Context context;
    List<Products> productsList;

    public ProductListAdapter(Context context, List<Products> productsList) {
        this.context = context;
        this.productsList = productsList;
    }

    @NonNull
    @Override
    public ProductListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_list_row_item, parent,false);
        return new ProductListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductListViewHolder holder, int position) {

        final Products temp = productsList.get(position);
        holder.prod_image.setImageResource(productsList.get(position).getImageUrl());
        holder.prod_name.setText(productsList.get(position).getProductName());
        holder.prod_rate.setText(productsList.get(position).getProductPrice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent actvty= new Intent(context, ProductDetails.class);
                actvty.putExtra("product_id",temp.getProductId());
                actvty.putExtra("product_name",temp.getProductName());
                actvty.putExtra("product_rate",temp.getProductPrice());
                actvty.putExtra("prd_img",temp.getImageUrl());

                Pair[] pair = new Pair[1];
                pair[0] = new Pair<View, String>(holder.prod_image,"image");
                ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation((Activity) context, pair);
                actvty.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(actvty, activityOptions.toBundle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    public static final class ProductListViewHolder extends RecyclerView.ViewHolder{

        ImageView prod_image;
        TextView prod_name;
        TextView prod_rate;

        public ProductListViewHolder(@NonNull View itemView) {
            super(itemView);

            prod_image = itemView.findViewById(R.id.product_img);
            prod_name = itemView.findViewById(R.id.prd_name);
            prod_rate = itemView.findViewById(R.id.prd_rate);
        }

    }

}
