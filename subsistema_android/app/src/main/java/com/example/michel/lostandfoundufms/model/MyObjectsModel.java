package com.example.michel.lostandfoundufms.model;

import android.util.Log;

import com.example.michel.lostandfoundufms.interfaces.MyObjectsInterface;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MyObjectsModel implements MyObjectsInterface.Model{

    private MyObjectsInterface.Presenter presenter;
    private AsyncHttpClient client;

    public MyObjectsModel(MyObjectsInterface.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void requestMyObjectsApi(int userId) {
        String url = "http://android.redesociais.com.br/objects/list-all-my-objects";

        if(client == null){
            client = new AsyncHttpClient();
        }

        RequestParams params = new RequestParams();
        params.put("user_id", userId);

        client.post(url, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.i("My Objects Model", response.toString());
                presenter.requestMyObjectsApiResponse(response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                Log.i("My Objects Model", res);
            }
        });
    }
}
