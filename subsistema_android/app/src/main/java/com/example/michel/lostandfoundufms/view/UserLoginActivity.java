package com.example.michel.lostandfoundufms.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.michel.lostandfoundufms.R;
import com.example.michel.lostandfoundufms.interfaces.UserLoginInterface;
import com.example.michel.lostandfoundufms.presenter.UserLoginPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserLoginActivity extends AppCompatActivity implements UserLoginInterface.View{

    private UserLoginInterface.Presenter presenter;

    @BindView(R.id.loginEmailEditText)
    EditText loginEmailEditText;

    @BindView(R.id.loginPasswordEditText)
    EditText loginPasswordEditText;

    @BindView(R.id.loginProgressBar)
    ProgressBar loginProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        presenter = new UserLoginPresenter(this);
    }

    @OnClick(R.id.loginBtn)
    void onClickLoginBtn(View view){
        loginProgressBar.setVisibility(View.VISIBLE);

        String email = loginEmailEditText.getText().toString();
        String password = loginPasswordEditText.getText().toString();

        presenter.loginUser(email, password);
    }

    @OnClick(R.id.loginRegisterBtn)
    void onClickLoginRegisterBtn(View view){
        startActivity(new Intent(UserLoginActivity.this, UserRegisterActivity.class));
        finish();
    }

    @Override
    public void loginSuccess(String message) {
        loginProgressBar.setVisibility(View.GONE);
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.layout_toast, (ViewGroup) findViewById(R.id.toastRoot));

        LinearLayout linearLayout = layout.findViewById(R.id.toastRoot);
        TextView toastText = layout.findViewById(R.id.toastText);
        ImageView toastIcon = layout.findViewById(R.id.toastIcon);

        linearLayout.setBackgroundResource(R.color.toastSuccess);
        toastText.setText(message);
        toastIcon.setImageResource(R.drawable.ic_success_icon);

        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();

        startActivity(new Intent(UserLoginActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void loginError(String message) {
        loginProgressBar.setVisibility(View.GONE);

        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.layout_toast, (ViewGroup) findViewById(R.id.toastRoot));

        LinearLayout linearLayout = layout.findViewById(R.id.toastRoot);
        TextView toastText = layout.findViewById(R.id.toastText);
        ImageView toastIcon = layout.findViewById(R.id.toastIcon);

        linearLayout.setBackgroundResource(R.color.toastError);
        toastText.setText(message);
        toastIcon.setImageResource(R.drawable.ic_error_icon);

        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }

    @Override
    public Context getContext() {
        return this;
    }
}
