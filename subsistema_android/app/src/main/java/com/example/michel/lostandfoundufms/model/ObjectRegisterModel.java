package com.example.michel.lostandfoundufms.model;

import android.util.Log;

import com.example.michel.lostandfoundufms.interfaces.ObjectRegisterInterface;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class ObjectRegisterModel implements ObjectRegisterInterface.Model{

    private ObjectRegisterInterface.Presenter presenter;
    private AsyncHttpClient client;

    public ObjectRegisterModel(ObjectRegisterInterface.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void requestSaveObjectApi(int userSessionId, String title, String date, String situation, String classification, String image, String description) {
        String url = "http://android.redesociais.com.br/objects/add-object";

        if(client == null){
            client = new AsyncHttpClient();
        }

        RequestParams params = new RequestParams();
        params.put("user_id", userSessionId);
        params.put("title", title);
        params.put("solved", situation);
        params.put("type", classification);
        params.put("date", date);
        if(!image.isEmpty()){
            params.put("image", image);
        }
        params.put("description", description);

        client.post(url, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.i("Object Register Model", response.toString());
                presenter.requestSaveObjectApiResponse(response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                Log.i("Object Register Model", res);
            }
        });
    }

    @Override
    public void requestEditObjectApi(int userSessionId, String id, String title, String date, String situation, String classification, String image, String description) {
        String url = "http://android.redesociais.com.br/objects/edit-object";

        if(client == null){
            client = new AsyncHttpClient();
        }

        RequestParams params = new RequestParams();
        params.put("id", id);
        params.put("user_id", userSessionId);
        params.put("title", title);
        params.put("solved", situation);
        params.put("type", classification);
        params.put("date", date);
        if(!image.isEmpty()){
            params.put("image", image);
        }
        params.put("description", description);

        client.post(url, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.i("Object Register Model", response.toString());
                presenter.requestSaveObjectApiResponse(response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                Log.i("Object Register Model", res);
            }
        });
    }
}
