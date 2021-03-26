package com.infovenz.sportz.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.infovenz.sportz.AddAddress;
import com.infovenz.sportz.ProductDetails;
import com.infovenz.sportz.R;
import com.infovenz.sportz.model.CartProduct;
import com.infovenz.sportz.model.Products;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    Context context;
    List<CartProduct> cartProducts;

    public CartAdapter(Context context, List<CartProduct> cartProducts) {
        this.context = context;
        this.cartProducts = cartProducts;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_row_list, parent,false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        final CartProduct temp = cartProducts.get(position);
        holder.img_view.setImageResource(cartProducts.get(position).getImageUrl());
        holder.name_prd.setText(cartProducts.get(position).getProductName());
        holder.rate_prd.setText(cartProducts.get(position).getProductPrice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent actvty= new Intent(context, AddAddress.class);
                actvty.putExtra("product_rate",temp.getProductPrice());
                actvty.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(actvty);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartProducts.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder{

        ImageView img_view;
        TextView name_prd;
        TextView rate_prd;
        TextView count_prd;
        public CartViewHolder(@NonNull View itemView) {
            super(itemView);

            img_view = itemView.findViewById(R.id.cart_prd_image);
            name_prd = itemView.findViewById(R.id.cart_prd_name);
            rate_prd = itemView.findViewById(R.id.cart_prd_rate);
            count_prd = itemView.findViewById(R.id.prd_count);
        }
    }
}
