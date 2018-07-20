package com.example.michel.lostandfoundufms.view;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.michel.lostandfoundufms.R;
import com.example.michel.lostandfoundufms.interfaces.ObjectCommentsInterface;
import com.example.michel.lostandfoundufms.model.CommentItem;
import com.example.michel.lostandfoundufms.presenter.ObjectCommentsPresenter;
import com.example.michel.lostandfoundufms.utils.CommentItemAdapter;
import com.example.michel.lostandfoundufms.utils.UserSession;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ObjectCommentsActivity extends AppCompatActivity implements ObjectCommentsInterface.View{

    @BindView(R.id.objectCommentsToolbar)
    Toolbar objectCommentsToolbar;

    @BindView(R.id.objectCommentsId)
    EditText objectCommentsId;

    @BindView(R.id.objectCommentsCommentSend)
    EditText objectCommentsCommentSend;

    @BindView(R.id.objectCommentsUserId)
    EditText objectCommentsUserId;

    @BindView(R.id.commentsProgressBar)
    ProgressBar commentsProgressBar;

    @BindView(R.id.objectCommentsRecyclerView)
    RecyclerView recyclerView;

    private ObjectCommentsInterface.Presenter presenter;
    private RecyclerView.Adapter adapter;
    private String objectId;
    private UserSession userSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_comments);
        ButterKnife.bind(this);

        presenter = new ObjectCommentsPresenter(this);
        userSession = new UserSession(this);

        objectCommentsToolbar.setTitle("Comentários do Objeto");
        setSupportActionBar(objectCommentsToolbar);
        objectCommentsToolbar.setTitleTextAppearance(this, R.style.ubuntuFont);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<CommentItem> tmp = new ArrayList<>();
        adapter = new CommentItemAdapter(tmp, this);
        recyclerView.setAdapter(adapter);
        recyclerView.smoothScrollToPosition(tmp.size());

        objectId = getIntent().getStringExtra("objectId");
        String objectUserId = getIntent().getStringExtra("objectUserId");

        objectCommentsId.setText(objectId);
        objectCommentsUserId.setText(objectUserId);

        commentsProgressBar.setVisibility(View.VISIBLE);
        presenter.requestObjectComments(objectId);
    }

    @Override
    public void loadObjectComments(List<CommentItem> commentItemList) {
        adapter = new CommentItemAdapter(commentItemList, this);
        recyclerView.setAdapter(adapter);
        recyclerView.smoothScrollToPosition(commentItemList.size());
        commentsProgressBar.setVisibility(View.GONE);
    }

    @OnClick(R.id.objectCommentsSendBtn)
    void onClickObjectCommentsSendBtn(View view){
        commentsProgressBar.setVisibility(View.VISIBLE);

        String objectId = objectCommentsId.getText().toString();
        String objectUserId = objectCommentsUserId.getText().toString();
        String userId = String.valueOf(userSession.getUserSessionId());
        String comment = objectCommentsCommentSend.getText().toString();
        if(!comment.isEmpty()){
            presenter.requestSaveComment(objectId, objectUserId, userId, comment);
        }
    }

    @Override
    public void commentSaveSuccess(String message) {
        objectCommentsCommentSend.setText("");
        hideKeyboard(this);

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

        commentsProgressBar.setVisibility(View.GONE);
        presenter.requestObjectComments(objectId);
    }

    public static void hideKeyboard(Activity activity) {
        View view = activity.findViewById(android.R.id.content);
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void commentSaveError(String message) {
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

        commentsProgressBar.setVisibility(View.GONE);
    }
}
