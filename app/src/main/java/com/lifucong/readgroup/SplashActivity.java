package com.lifucong.readgroup;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lifucong.readgroup.user.login.LoginFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SplashActivity extends AppCompatActivity {

    private LoginFragment loginFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button_login)
    public void showLoginDialog() {
        if (loginFragment==null) {
            loginFragment=new LoginFragment();
        }
        loginFragment.show(getSupportFragmentManager(),null);
    }
}
