package com.infovenz.sportz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.infovenz.sportz.DBFiles.DBHelper;

public class Payment_Success extends AppCompatActivity {

    Button home_click;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper= new DBHelper(this);
        setContentView(R.layout.activity_payment__success);

        home_click = findViewById(R.id.btn_home);

        home_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean isTableDeleted = dbHelper.DeleteCartData();
                Intent back_press= new Intent(getApplicationContext(),MainActivity.class);
                startActivity(back_press);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent back_press= new Intent(getApplicationContext(),PaymentActivity.class);
        startActivity(back_press);
    }
}