package com.example.michel.lostandfoundufms.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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
import com.example.michel.lostandfoundufms.interfaces.UserInterface;
import com.example.michel.lostandfoundufms.presenter.UserPresenter;
import com.example.michel.lostandfoundufms.utils.UserSession;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserActivity extends AppCompatActivity implements UserInterface.View{

    @BindView(R.id.userToolbar)
    Toolbar userToolbar;

    @BindView(R.id.userUsernameEditText)
    EditText userUsernameEditText;

    @BindView(R.id.userEmailEditText)
    EditText userEmailEditText;

    @BindView(R.id.userPasswordEditText)
    EditText userPasswordEditText;

    @BindView(R.id.userSaveProgressBar)
    ProgressBar userSaveProgressBar;

    private UserInterface.Presenter presenter;
    private UserSession userSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        ButterKnife.bind(this);

        presenter = new UserPresenter(this);
        userSession = new UserSession(this);

        userToolbar.setTitle("Conta de Usuário");
        setSupportActionBar(userToolbar);
        userToolbar.setTitleTextAppearance(this, R.style.ubuntuFont);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        userUsernameEditText.setText(userSession.getUserSessionName());
        userEmailEditText.setText(userSession.getUserSessionEmail());

    }

    @OnClick(R.id.userSaveBtn)
    void onClickUserSaveBtn(View view){
        userSaveProgressBar.setVisibility(View.VISIBLE);

        String userName = userUsernameEditText.getText().toString() ;
        String email = userEmailEditText.getText().toString();
        String password = userPasswordEditText.getText().toString();

        presenter.requestEditUser(userSession.getUserSessionId(), userName, email, password);
    }

    @Override
    public void userEditSuccess(String message) {
        userSaveProgressBar.setVisibility(View.GONE);
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

        startActivity(new Intent(UserActivity.this, MainActivity.class));
    }

    @Override
    public void userEditError(String message) {
        userSaveProgressBar.setVisibility(View.GONE);
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
