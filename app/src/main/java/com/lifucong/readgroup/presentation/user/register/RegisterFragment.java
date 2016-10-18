package com.lifucong.readgroup.presentation.user.register;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.lifucong.readgroup.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2016/10/15.
 */

public class RegisterFragment extends DialogFragment implements RegisterView {

    @BindView(R.id.edit_username)
    TextInputEditText etUsername;
    @BindView(R.id.edit_password)
    TextInputEditText etPassword;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.button_confirm)
    Button btnConfirm;
    private Unbinder unbinder;
    private RegisterPresenter registerPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerPresenter=new RegisterPresenter();
        registerPresenter.onCreate();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        registerPresenter.attachView(this);
    }

    @OnClick(R.id.button_confirm)
    public void register(){
        String username=etUsername.getText().toString().trim();
        String password=etPassword.getText().toString().trim();
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            String info = getString(R.string.user_error_not_null);
            Toast.makeText(getContext(), info, Toast.LENGTH_SHORT).show();
            return;
        }
        registerPresenter.register(username,password);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        registerPresenter.datachView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        registerPresenter.onDestroy();
    }

    // start view interface----------------------------------------
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
    public void showRegisterSuccess() {
        Toast.makeText(getContext(), R.string.user_register_success, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showRegisterFail(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
        dismiss();
    }

    @Override public void close() {
        dismiss();
    }
    // end view interface----------------------------------------

}
