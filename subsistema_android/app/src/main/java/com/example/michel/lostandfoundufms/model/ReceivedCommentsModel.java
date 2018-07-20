package com.example.michel.lostandfoundufms.model;

import android.util.Log;

import com.example.michel.lostandfoundufms.interfaces.ReceivedCommentsInterface;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class ReceivedCommentsModel implements ReceivedCommentsInterface.Model{

    private ReceivedCommentsInterface.Presenter presenter;
    private AsyncHttpClient client;

    public ReceivedCommentsModel(ReceivedCommentsInterface.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void requestReceivedCommentsApi(int userId) {
        String url = "http://android.redesociais.com.br/comments/list-all-comments-by-object-user-id";

        if(client == null){
            client = new AsyncHttpClient();
        }

        RequestParams params = new RequestParams();
        params.put("object_user_id", userId);

        client.post(url, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.i("Received Comments Model", response.toString());
                presenter.requestReceivedCommentsApiResponse(response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                Log.i("Received Comments Model", res);
            }
        });
    }
}
