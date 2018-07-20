package com.example.michel.lostandfoundufms.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.example.michel.lostandfoundufms.R;
import com.example.michel.lostandfoundufms.interfaces.ReceivedCommentsInterface;
import com.example.michel.lostandfoundufms.model.CommentItem;
import com.example.michel.lostandfoundufms.presenter.ReceivedCommentsPresenter;
import com.example.michel.lostandfoundufms.utils.CommentItemAdapter;
import com.example.michel.lostandfoundufms.utils.UserSession;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReceivedCommentsActivity extends AppCompatActivity implements ReceivedCommentsInterface.View{

    @BindView(R.id.receivedCommentsToolbar)
    Toolbar receivedCommentsToolbar;

    @BindView(R.id.receivedCommentsProgressBar)
    ProgressBar receivedCommentsProgressBar;

    @BindView(R.id.receivedCommentsRecyclerView)
    RecyclerView recyclerView;

    private ReceivedCommentsInterface.Presenter presenter;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_received_comments);
        ButterKnife.bind(this);

        presenter = new ReceivedCommentsPresenter(this);
        UserSession userSession = new UserSession(this);

        receivedCommentsToolbar.setTitle("Comentários Recebidos");
        setSupportActionBar(receivedCommentsToolbar);
        receivedCommentsToolbar.setTitleTextAppearance(this, R.style.ubuntuFont);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<CommentItem> tmp = new ArrayList<>();
        adapter = new CommentItemAdapter(tmp, this);
        recyclerView.setAdapter(adapter);
        recyclerView.smoothScrollToPosition(tmp.size());

        receivedCommentsProgressBar.setVisibility(View.VISIBLE);
        presenter.requestReceivedComments(userSession.getUserSessionId());
    }

    @Override
    public void loadReceivedComments(List<CommentItem> commentItemList) {
        adapter = new CommentItemAdapter(commentItemList, this);
        recyclerView.setAdapter(adapter);
        recyclerView.smoothScrollToPosition(commentItemList.size());
        receivedCommentsProgressBar.setVisibility(View.GONE);
    }
}
