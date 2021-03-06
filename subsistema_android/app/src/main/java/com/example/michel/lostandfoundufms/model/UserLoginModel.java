package com.example.michel.lostandfoundufms.model;

import android.util.Log;

import com.example.michel.lostandfoundufms.interfaces.UserLoginInterface;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class UserLoginModel implements UserLoginInterface.Model{

    private UserLoginInterface.Presenter presenter;
    private AsyncHttpClient client;

    public UserLoginModel(UserLoginInterface.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void loginRequestApi(String email, String password) {
        String url = "http://android.redesociais.com.br/users/api-auth-user";

        if(client == null){
            client = new AsyncHttpClient();
        }

        RequestParams params = new RequestParams();
        params.put("email", email);
        params.put("password", password);

        client.post(url, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.i("User Login Model", response.toString());
                presenter.loginRequestApiResponse(response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                Log.i("User Login Model", res);
            }
        });

    }
}
