package com.infovenz.sportz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.media.Image;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class IntroActivity extends AppCompatActivity {

    ImageView logo;
    TextView logo_name;
    LottieAnimationView anim_view;

    private static final int NUM_PAGES = 3;
    private ViewPager viewPager;
    private ScreenSlidePagerAdapter pagerAdapter;

    Animation anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        logo = findViewById(R.id.logo_img);
        logo_name = findViewById(R.id.app_name);
        anim_view = findViewById(R.id.lottie_anim);

        viewPager = findViewById(R.id.pager);
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        anim = AnimationUtils.loadAnimation(this,R.anim.on_boarding_anim);
        viewPager.setAnimation(anim);

        logo.animate().translationY(-1600).setDuration(1000).setStartDelay(3000).start();
        logo_name.animate().translationY(2200).setDuration(1000).setStartDelay(3000).start();
        anim_view.animate().translationY(2200).setDuration(1000).setStartDelay(3000).start();
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter{

        public ScreenSlidePagerAdapter(@NonNull FragmentManager fm){
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    OnBoarding1 tab1 = new OnBoarding1();
                    return tab1;
                case 1:
                    OnBoarding2 tab2 = new OnBoarding2();
                    return tab2;
                case 2:
                    OnBoarding3 tab3 = new OnBoarding3();
                    return tab3;
            }
            return null;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}