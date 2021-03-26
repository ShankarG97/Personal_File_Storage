package com.infovenz.sportz;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class LoginAdapter extends FragmentPagerAdapter{
    private Context context;
    int TOTAL_TABS;

    public LoginAdapter(FragmentManager fragmentManager, Context context, int total_tabs){
        super(fragmentManager);
        this.context=context;
        this.TOTAL_TABS=total_tabs;
    }


    @Override
    public int getCount() {
        return TOTAL_TABS;
    }

    public Fragment getItem(int position){

        switch(position){

            case 0:
                LoginTabFragment loginTabFragment=new LoginTabFragment();
                return  loginTabFragment;
            case 1:
                SignupTabFragment signupTabFragment=new SignupTabFragment();
                return  signupTabFragment;
            default:
                return null;
        }
    }
}
