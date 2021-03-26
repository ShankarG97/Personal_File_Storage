package com.infovenz.sportz;

import android.content.Intent;
import android.database.Cursor;
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

public class LoginTabFragment extends Fragment {

    EditText email, passwrd;
    Button click_login;
    float alpha=0;
    DBHelper dbHelper;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root=(ViewGroup)inflater.inflate(R.layout.login_tab_fragment, container, false);

        email = root.findViewById(R.id.email);
        passwrd = root.findViewById(R.id.password);
        click_login = root.findViewById(R.id.btn_login);

        dbHelper= new DBHelper(getContext());

        email.setTranslationY(300);
        passwrd.setTranslationY(300);
        click_login.setTranslationY(300);

        email.setAlpha(alpha);
        passwrd.setAlpha(alpha);
        click_login.setAlpha(alpha);

        email.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        passwrd.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(600).start();
        click_login.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(800).start();

        click_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!email.getText().toString().isEmpty()){
                    if(!passwrd.getText().toString().isEmpty()){

                        String emailTXT = email.getText().toString();
                        String psswrdTXT = passwrd.getText().toString();

                        Cursor res = dbHelper.getUserData(emailTXT);
                        if(res.getCount() == 0){
                            Toast.makeText(getContext(),"Not a registered User. Please Sign Up",Toast.LENGTH_LONG).show();
                        }
                        else {
                            String DBEmail="",DBpsswrd="",DBName="";
                            if(res.moveToFirst() && res.getCount() >= 1){
                                DBName= res.getString(0);
                                DBEmail= res.getString(1);
                                DBpsswrd= res.getString(2);
                            }

                           if(DBEmail.toString().equals(emailTXT) && DBpsswrd.toString().equals(psswrdTXT)){
                               Intent login= new Intent(getActivity(),MainActivity.class);
                               login.putExtra("name",DBName);
                               startActivity(login);
                           }
                           else {
                               Toast.makeText(getContext(),"Not a Valid User. Please Sign up!",Toast.LENGTH_LONG).show();
                           }
                        }
                    }
                    else
                        Toast.makeText(getContext(),"Enter password!",Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(getContext(),"Enter email!",Toast.LENGTH_LONG).show();
//                if(email.getText().toString().equals("admin@sportz.com")){
//
//                    if(passwrd.getText().toString().equals("123456")){
//                        Intent login= new Intent(getActivity(),MainActivity.class);
//                        startActivity(login);
//                    }
//                    else
//                        Toast.makeText(getContext(),"Password Wrong!",Toast.LENGTH_LONG).show();
//                }
//                else
//                    Toast.makeText(getContext(),"Not valid email!",Toast.LENGTH_LONG).show();
            }
        });

        return  root;
    }
}
