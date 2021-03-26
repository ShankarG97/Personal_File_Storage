package com.infovenz.sportz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PaymentActivity extends AppCompatActivity {

    EditText card_number,card_name, exp_date, card_cvv;
    Button pay_now;
    TextView address;
    String d_number, st_name,cust_name,ar_name,d_name,s_name,ph_number,pin_code;
    String total_amnt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        Intent getAddress = getIntent();

        d_number = getAddress.getStringExtra("dr_number");
        st_name = getAddress.getStringExtra("strt_name");
        cust_name = getAddress.getStringExtra("cus_name");
        ar_name = getAddress.getStringExtra("ara_name");
        d_name = getAddress.getStringExtra("dist_name");
        s_name = getAddress.getStringExtra("stt_nmae");
        ph_number = getAddress.getStringExtra("p_number");
        pin_code = getAddress.getStringExtra("pincode");
        total_amnt = getAddress.getStringExtra("total_rate");

        card_number = findViewById(R.id.card_number);
        card_name = findViewById(R.id.card_name);
        exp_date = findViewById(R.id.exp_date);
        card_cvv = findViewById(R.id.card_cvv);
        pay_now = findViewById(R.id.btn_pay);
        address = findViewById(R.id.address);

        String cust_address = cust_name +"\n"+
                d_number +","+st_name +","+ar_name+"\n"+
                d_name +","+s_name+","+pin_code+"\n"+
                ph_number;

        address.setText(cust_address);

        pay_now.setText("Pay ₹ "+total_amnt +" Now");

        pay_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(card_number.getText().toString().length()==16){
                    if(exp_date.getText().toString().length()==5){
                        if(card_cvv.getText().toString().length()==3){
                            Intent back_press= new Intent(getApplicationContext(),Payment_Success.class);
                            startActivity(back_press);
                        }
                        else
                            Toast.makeText(getApplicationContext(),"Wrong CVV",Toast.LENGTH_LONG).show();
                    }
                    else
                        Toast.makeText(getApplicationContext(),"Enter Expiry date of card",Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(getApplicationContext(),"Enter Valid Card Number",Toast.LENGTH_LONG).show();
            }
        });

    }
}