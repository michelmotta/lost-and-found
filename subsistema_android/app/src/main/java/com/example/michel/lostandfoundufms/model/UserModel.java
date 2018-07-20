package com.example.michel.lostandfoundufms.model;

import android.util.Log;

import com.example.michel.lostandfoundufms.interfaces.UserInterface;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class UserModel implements UserInterface.Model{

    private UserInterface.Presenter presenter;
    private AsyncHttpClient client;

    public UserModel(UserInterface.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void requestEditUserApi(int id, String userName, String email, String password) {
        String url = "http://android.redesociais.com.br/users/api-edit-user";

        if(client == null){
            client = new AsyncHttpClient();
        }

        RequestParams params = new RequestParams();
        params.put("id", id);
        params.put("username", userName);
        params.put("email", email);
        if(!password.isEmpty()){
            params.put("password", password);
        }

        client.post(url, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.i("User Model", response.toString());
                presenter.requestEditUserApiResponse(response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                Log.i("User Model", res);
            }
        });

    }
}
