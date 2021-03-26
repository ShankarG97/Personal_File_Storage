package com.infovenz.sportz;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class OnBoarding3 extends Fragment {

    FloatingActionButton next_icon;
    TextView skip_press;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root=(ViewGroup)inflater.inflate(R.layout.fragment_onboarding3, container, false);
        next_icon = root.findViewById(R.id.btn_next_icon);
        skip_press = root.findViewById(R.id.skip_btn);

        next_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login= new Intent(getActivity(),LoginActivity.class);
                startActivity(login);
            }
        });
        skip_press.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login= new Intent(getActivity(),LoginActivity.class);
                startActivity(login);
            }
        });
        return  root;
    }
}
