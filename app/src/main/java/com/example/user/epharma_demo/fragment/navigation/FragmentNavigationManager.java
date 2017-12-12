package com.example.user.epharma_demo.fragment.navigation;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.user.epharma_demo.BuildConfig;
import com.example.user.epharma_demo.MainActivity;
import com.example.user.epharma_demo.R;
import com.example.user.epharma_demo.fragment.FragmentBabyCare;
import com.example.user.epharma_demo.fragment.FragmentBeautyCare;
import com.example.user.epharma_demo.fragment.FragmentBodyCare;
import com.example.user.epharma_demo.fragment.FragmentDIabeticCare;
import com.example.user.epharma_demo.fragment.FragmentLogin;
import com.example.user.epharma_demo.fragment.FragmentRegistration;
import com.example.user.epharma_demo.fragment.Fragment_content;


public class FragmentNavigationManager implements NavigationManager {

    private static FragmentNavigationManager sInstance;

    private FragmentManager mFragmentManager;
    private MainActivity mActivity;

    public static FragmentNavigationManager obtain(MainActivity activity) {
        if (sInstance == null) {
            sInstance = new FragmentNavigationManager();
        }
        sInstance.configure(activity);
        return sInstance;
    }

    private void configure(MainActivity activity) {
        mActivity = activity;
        mFragmentManager = mActivity.getSupportFragmentManager();
    }

    @Override
    public void showFragmentBaby(String title) {
        //showFragment(FragmentACtion.newInstance(title), false);
        showFragment(new FragmentBabyCare(title), false);

    }

    @Override
    public void showFragmentBeauty(String title) {
        //showFragment(FragmentComedy.newInstance(title), false);
        showFragment(new FragmentBeautyCare(title), false);
    }

    @Override
    public void showFragmentBody(String title) {
        showFragment(new FragmentBodyCare(title), false);
    }

    @Override
    public void showFragmentDiabetic(String title) {
        showFragment(new FragmentDIabeticCare(title), false);
    }

    @Override
    public void showFragmentPersonal(String title) {
        showFragment(new FragmentPersonalCare(title), false);
    }

    @Override
    public void showFragmentEye(String title) {

    }

    @Override
    public void showFragmentMultivita(String title) {

    }

    @Override
    public void showFragmentHealthcare(String title) {

    }

    @Override
    public void showFragmentPharmacyLocator(String title) {

    }

    @Override
    public void showFragmentProfile(String title) {

    }

    @Override
    public void showFragmentPrivacyPolicy(String title) {

    }

    @Override
    public void showFragmentHealthTips(String title) {

    }

    @Override
    public void showFragmentShareApp(String title) {

    }

    @Override
    public void showFragmentFeedback(String title) {

    }

    @Override
    public void showFragmentContactUs(String title) {

    }

    @Override
    public void showFragmentLogin() {
        showFragment(new FragmentLogin(), false);

    }

    @Override
    public void showFragmentRegistration() {
        showFragment(new FragmentRegistration(), false);
    }

    @Override
    public void showFragmentContent() {
        showFragment(new Fragment_content(), false);
    }

    private void showFragment(Fragment fragment, boolean allowStateLoss) {
        FragmentManager fm = mFragmentManager;

        @SuppressLint("CommitTransaction")
        FragmentTransaction ft = fm.beginTransaction()
                .replace(R.id.container, fragment);

        ft.addToBackStack(null);

        if (allowStateLoss || !BuildConfig.DEBUG) {
            ft.commitAllowingStateLoss();
        } else {
            ft.commit();
        }

        fm.executePendingTransactions();
    }
}
