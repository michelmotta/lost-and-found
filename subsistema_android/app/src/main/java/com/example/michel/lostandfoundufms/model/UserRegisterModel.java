package com.example.michel.lostandfoundufms.model;

import android.util.Log;

import com.example.michel.lostandfoundufms.interfaces.UserRegisterInterface;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class UserRegisterModel implements UserRegisterInterface.Model{

    private UserRegisterInterface.Presenter presenter;
    private AsyncHttpClient client;

    public UserRegisterModel(UserRegisterInterface.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void registerRequestApi(String username, String email, String password) {
        String url = "http://android.redesociais.com.br/users/api-add-user";

        if(client == null){
            client = new AsyncHttpClient();
        }

        RequestParams params = new RequestParams();
        params.put("username", username);
        params.put("email", email);
        params.put("password", password);

        client.post(url, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.i("User Register Model", response.toString());
                presenter.registerRequestApiResponse(response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                Log.i("User Register Model", res);
            }
        });

    }
}
