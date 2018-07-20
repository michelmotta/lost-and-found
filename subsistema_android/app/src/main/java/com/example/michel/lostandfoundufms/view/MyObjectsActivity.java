package com.example.michel.lostandfoundufms.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.example.michel.lostandfoundufms.R;
import com.example.michel.lostandfoundufms.interfaces.MyObjectsInterface;
import com.example.michel.lostandfoundufms.model.ObjectItem;
import com.example.michel.lostandfoundufms.presenter.MyObjectsPresenter;
import com.example.michel.lostandfoundufms.utils.ObjectItemAdapter;
import com.example.michel.lostandfoundufms.utils.UserSession;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyObjectsActivity extends AppCompatActivity implements MyObjectsInterface.View{

    @BindView(R.id.myObjectsToolbar)
    Toolbar myObjectsToolbar;

    @BindView(R.id.myObjectsProgressBar)
    ProgressBar myObjectsProgressBar;

    @BindView(R.id.myObjectsRecyclerView)
    RecyclerView recyclerView;

    private MyObjectsInterface.Presenter presenter;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_objects);
        ButterKnife.bind(this);

        presenter = new MyObjectsPresenter(this);
        UserSession userSession = new UserSession(this);

        myObjectsToolbar.setTitle("Meus Objetos");
        setSupportActionBar(myObjectsToolbar);
        myObjectsToolbar.setTitleTextAppearance(this, R.style.ubuntuFont);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<ObjectItem> tmp = new ArrayList<>();
        adapter = new ObjectItemAdapter(tmp, this);
        recyclerView.setAdapter(adapter);

        myObjectsProgressBar.setVisibility(View.VISIBLE);

        int userId = userSession.getUserSessionId();
        presenter.requestMyObjects(userId);
    }

    @Override
    public void loadMyObjects(List<ObjectItem> objectList) {
        adapter = new ObjectItemAdapter(objectList, this);
        recyclerView.setAdapter(adapter);
        myObjectsProgressBar.setVisibility(View.GONE);
    }
}
