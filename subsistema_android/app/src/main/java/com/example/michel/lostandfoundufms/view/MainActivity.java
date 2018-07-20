package com.example.michel.lostandfoundufms.view;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.michel.lostandfoundufms.R;
import com.example.michel.lostandfoundufms.interfaces.MainInterface;
import com.example.michel.lostandfoundufms.model.ObjectItem;
import com.example.michel.lostandfoundufms.presenter.MainPresenter;
import com.example.michel.lostandfoundufms.utils.ObjectItemAdapter;
import com.example.michel.lostandfoundufms.utils.UserSession;
import com.onesignal.OneSignal;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainInterface.View, NavigationView.OnNavigationItemSelectedListener{

    @BindView(R.id.homeToolbar)
    Toolbar toolbar;

    @BindView(R.id.mainProgressBar)
    ProgressBar mainProgressBar;

    @BindView(R.id.drawerLayoutHome)
    DrawerLayout drawerLayout;

    @BindView(R.id.sideMenu)
    NavigationView navigationView;

    @BindView(R.id.homeRecyclerView)
    RecyclerView recyclerView;

    private MainInterface.Presenter presenter;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        presenter = new MainPresenter(this);
        UserSession userSession = new UserSession(this);

        if(userSession.loggedIn()){
            OneSignal.startInit(this)
                    .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                    .unsubscribeWhenNotificationsAreDisabled(true)
                    .init();

            setSupportActionBar(toolbar);
            toolbar.setTitleTextAppearance(this, R.style.ubuntuFont);

            View headerView = navigationView.getHeaderView(0);
            TextView menuUserName = headerView.findViewById(R.id.menuUserName);
            TextView menuUserEmail = headerView.findViewById(R.id.menuUserEmail);

            navigationView.setNavigationItemSelectedListener(this);

            actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
            drawerLayout.addDrawerListener(actionBarDrawerToggle);
            actionBarDrawerToggle.syncState();

            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            ArrayList<ObjectItem> tmp = new ArrayList<>();
            adapter = new ObjectItemAdapter(tmp, this);
            recyclerView.setAdapter(adapter);

            OneSignal.sendTag("user_id", "user_" + String.valueOf(userSession.getUserSessionId()));

            menuUserName.setText(userSession.getUserSessionName());
            menuUserEmail.setText(userSession.getUserSessionEmail());

            mainProgressBar.setVisibility(View.VISIBLE);
            presenter.requestAllObjects();
        }else{
            logout();
        }
    }

    @Override
    public void loadAllObjects(List<ObjectItem> objectList) {
        adapter = new ObjectItemAdapter(objectList, this);
        recyclerView.setAdapter(adapter);
        mainProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.sideMenuHome:
                startActivity(new Intent(MainActivity.this, MainActivity.class));
                finish();
                break;
            case R.id.sideMenuMyObjects:
                startActivity(new Intent(MainActivity.this, MyObjectsActivity.class));
                break;
            case R.id.sideMenuRegisterObjects:
                startActivity(new Intent(MainActivity.this, ObjectRegisterActivity.class));
                break;
            case R.id.sideMenuCommentsReceived:
                startActivity(new Intent(MainActivity.this, ReceivedCommentsActivity.class));
                break;
            case R.id.sideMenuUser:
                startActivity(new Intent(MainActivity.this, UserActivity.class));
                break;
            case R.id.sideMenuLogout:
                logout();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public void logout() {
        presenter.logoutUser();
        startActivity(new Intent(MainActivity.this, UserLoginActivity.class));
        finish();
    }

    @Override
    public Context getContext() {
        return this;
    }
}
