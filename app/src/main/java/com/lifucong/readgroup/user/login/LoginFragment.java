package com.lifucong.readgroup.user.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.lifucong.readgroup.HomeActivity;
import com.lifucong.readgroup.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2016/10/14.
 */

public class LoginFragment extends DialogFragment implements LoginView{

    @BindView(R.id.edit_username)
    TextInputEditText etUsername;
    @BindView(R.id.edit_password)
    TextInputEditText etPassword;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.button_confirm)
    Button btnConfirm;
    private Unbinder unbinder;
    private LoginPresenter loginPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginPresenter=new LoginPresenter();
        loginPresenter.onCreate();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        loginPresenter.attachView(this);
    }

    @OnClick(R.id.button_confirm)
    public void login(){
        String username=etUsername.getText().toString().trim();
        String password=etPassword.getText().toString().trim();
        loginPresenter.login(username,password);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        loginPresenter.datachView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        loginPresenter.onDestroy();

    }

    // start View Interface---------------------------
    @Override
    public void showLoading() {
        setCancelable(false);
        btnConfirm.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        setCancelable(true);
        btnConfirm.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToHome() {
        Intent intent=new Intent(getContext(), HomeActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
    // end View Interface---------------------------
}
