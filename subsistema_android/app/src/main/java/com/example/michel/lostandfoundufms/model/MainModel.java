package com.example.michel.lostandfoundufms.model;

import android.util.Log;

import com.example.michel.lostandfoundufms.interfaces.MainInterface;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MainModel implements MainInterface.Model{

    private MainInterface.Presenter presenter;
    private AsyncHttpClient client;

    public MainModel(MainInterface.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void requestAllObjectsApi() {
        String url = "http://android.redesociais.com.br/objects/list-all-objects";

        if(client == null){
            client = new AsyncHttpClient();
        }

        client.get(url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.i("Main Model", response.toString());
                presenter.requestAllObjectsApiResponse(response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                Log.i("Main Model", res);
            }
        });

    }
}
