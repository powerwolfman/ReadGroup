package com.lifucong.readgroup.presentation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lifucong.readgroup.R;
import com.lifucong.readgroup.presentation.user.login.LoginFragment;
import com.lifucong.readgroup.presentation.user.register.RegisterFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SplashActivity extends AppCompatActivity {

    private LoginFragment loginFragment;
    private RegisterFragment registerFragment;
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
    @OnClick(R.id.button_register)
    public void showRegisterDialog() {
        if (registerFragment==null) {
            registerFragment=new RegisterFragment();
        }
        registerFragment.show(getSupportFragmentManager(),null);
    }
}
