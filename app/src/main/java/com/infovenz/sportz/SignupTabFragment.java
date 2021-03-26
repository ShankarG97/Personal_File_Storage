package com.infovenz.sportz;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.infovenz.sportz.DBFiles.DBHelper;

public class SignupTabFragment extends Fragment {

    EditText email, name, cnf_psswrd, mobile_number;
    Button click_signup;
    float alpha=0;
    DBHelper dbHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root=(ViewGroup)inflater.inflate(R.layout.signup_tab_fragment, container, false);

        email = root.findViewById(R.id.sn_email);
        cnf_psswrd = root.findViewById(R.id.sn_password);
        name = root.findViewById(R.id.sn_name);
        mobile_number = root.findViewById(R.id.phone_number);
        click_signup = root.findViewById(R.id.btn_signup);

        dbHelper= new DBHelper(getContext());

        email.setTranslationY(300);
        cnf_psswrd.setTranslationY(300);
        name.setTranslationY(300);
        mobile_number.setTranslationY(300);
        click_signup.setTranslationY(300);

        email.setAlpha(alpha);
        cnf_psswrd.setAlpha(alpha);
        name.setAlpha(alpha);
        mobile_number.setAlpha(alpha);
        click_signup.setAlpha(alpha);

        email.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        cnf_psswrd.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(500).start();
        name.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(600).start();
        mobile_number.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(700).start();
        click_signup.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(800).start();

        click_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!email.getText().toString().isEmpty()){
                    if(!name.getText().toString().isEmpty()){
                        if(!cnf_psswrd.getText().toString().isEmpty()){
                            if(!mobile_number.getText().toString().isEmpty()){

                                String nameTXT = name.getText().toString();
                                String emailTXT = email.getText().toString();
                                String psswrdTXT = cnf_psswrd.getText().toString();
                                String mobileTEXT = mobile_number.getText().toString();

                                Boolean checkInsert = dbHelper.insertUserData(nameTXT,emailTXT,psswrdTXT,mobileTEXT);

                                if(checkInsert==true){
                                    Toast.makeText(getContext(),"Signup Successful!",Toast.LENGTH_LONG).show();
                                    Intent login= new Intent(getActivity(),LoginActivity.class);
                                    startActivity(login);
                                }
                                else
                                    Toast.makeText(getContext(),"Signup Unsuccessful. Check DB Log",Toast.LENGTH_LONG).show();
                            }
                            else
                                Toast.makeText(getContext(),"Enter mobile number!",Toast.LENGTH_LONG).show();
                        }
                        else
                            Toast.makeText(getContext(),"Enter password!",Toast.LENGTH_LONG).show();
                    }
                    else
                        Toast.makeText(getContext(),"Enter name!",Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(getContext(),"Enter email!",Toast.LENGTH_LONG).show();
            }
        });

        return  root;
    }
}
