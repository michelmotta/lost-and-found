package com.example.michel.lostandfoundufms.view;

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
import com.example.michel.lostandfoundufms.interfaces.UserRegisterInterface;
import com.example.michel.lostandfoundufms.presenter.UserRegisterPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserRegisterActivity extends AppCompatActivity implements UserRegisterInterface.View{

    @BindView(R.id.registerUsernameEditText)
    EditText registerUsernameEditText;

    @BindView(R.id.registerEmailEditText)
    EditText registerEmailEditText;

    @BindView(R.id.registerPasswordEditText)
    EditText registerPasswordEditText;

    @BindView(R.id.userRegisterProgressBar)
    ProgressBar userRegisterProgressBar;

    private UserRegisterInterface.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        ButterKnife.bind(this);

        presenter = new UserRegisterPresenter(this);
    }

    @OnClick(R.id.registerBackLoginBtn)
    void onClickLoginRegisterBtn(View view){
        startActivity(new Intent(UserRegisterActivity.this, UserLoginActivity.class));
        finish();
    }

    @OnClick(R.id.registerBtn)
    void onClickRegisterBtn(View view){
        userRegisterProgressBar.setVisibility(View.VISIBLE);

        String username = registerUsernameEditText.getText().toString();
        String email = registerEmailEditText.getText().toString();
        String password = registerPasswordEditText.getText().toString();

        presenter.registerUser(username, email, password);
    }

    @Override
    public void registerSuccess(String message) {
        userRegisterProgressBar.setVisibility(View.GONE);
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

        startActivity(new Intent(UserRegisterActivity.this, UserLoginActivity.class));
    }

    @Override
    public void registerError(String message) {
        userRegisterProgressBar.setVisibility(View.GONE);
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
}
