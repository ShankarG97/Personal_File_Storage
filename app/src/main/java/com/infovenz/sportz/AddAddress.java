package com.infovenz.sportz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.infovenz.sportz.DBFiles.DBHelper;

public class AddAddress extends AppCompatActivity {

    EditText door_number,street_name,cus_name,area_name,dist_name,state,cont_number,pincode;
    Button add_address;
    String d_number, st_name,cust_name,ar_name,d_name,s_name,ph_number,pin_code,purchase_rate;
    String p_rate, p_count;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
        dbHelper= new DBHelper(this);
        Cursor res = dbHelper.getCartData();
        Intent getRate = getIntent();

        p_rate = getRate.getStringExtra("product_rate");
        p_rate = p_rate.replace("₹","");

        if(res.getCount()==0){
            p_count="1";
        }
        else {
            if(res.moveToFirst() && res.getCount() >= 1){
                p_count = res.getString(3);
            }
        }

        purchase_rate = String.valueOf((Integer.parseInt(p_rate)*Integer.parseInt(p_count)));

        door_number = findViewById(R.id.door_number);
        street_name = findViewById(R.id.street_name);
        cus_name = findViewById(R.id.cus_name);
        area_name = findViewById(R.id.area_name);
        dist_name = findViewById(R.id.dist_name);
        state = findViewById(R.id.state_name);
        cont_number = findViewById(R.id.contact_number);
        pincode = findViewById(R.id.pincode);

        add_address = findViewById(R.id.btn_addAddress);



        add_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d_number = door_number.getText().toString();
                st_name = street_name.getText().toString();
                cust_name = cus_name.getText().toString();
                ar_name = area_name.getText().toString();
                d_name = dist_name.getText().toString();
                s_name = state.getText().toString();
                ph_number = cont_number.getText().toString();
                pin_code = pincode.getText().toString();

                Intent nextPage= new Intent(getApplicationContext(),PaymentActivity.class);
                nextPage.putExtra("dr_number",d_number);
                nextPage.putExtra("strt_name",st_name);
                nextPage.putExtra("cus_name",cust_name);
                nextPage.putExtra("ara_name",ar_name);
                nextPage.putExtra("dist_name",d_name);
                nextPage.putExtra("stt_nmae",s_name);
                nextPage.putExtra("p_number",ph_number);
                nextPage.putExtra("pincode",pin_code);
                nextPage.putExtra("total_rate",purchase_rate);
                startActivity(nextPage);
            }
        });
    }
}