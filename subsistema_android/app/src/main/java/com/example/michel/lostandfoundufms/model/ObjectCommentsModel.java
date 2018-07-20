package com.example.michel.lostandfoundufms.model;

import android.util.Log;

import com.example.michel.lostandfoundufms.interfaces.ObjectCommentsInterface;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class ObjectCommentsModel implements ObjectCommentsInterface.Model{

    private ObjectCommentsInterface.Presenter presenter;
    private AsyncHttpClient client;

    public ObjectCommentsModel(ObjectCommentsInterface.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void requestObjectCommentsApi(String objectId) {
        String url = "http://android.redesociais.com.br/comments/list-all-comments-by-object-id";

        if(client == null){
            client = new AsyncHttpClient();
        }

        RequestParams params = new RequestParams();
        params.put("object_id", objectId);

        client.post(url, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.i("Response API ObjecComme", response.toString());
                presenter.requestObjectCommentsApiRestponse(response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                Log.i("Response API ObjecComme", res);
            }
        });
    }

    @Override
    public void requestSaveCommentApi(String objectId, String objectUserId, String userId,String comment) {
        String url = "http://android.redesociais.com.br/comments/add-comment";

        if(client == null){
            client = new AsyncHttpClient();
        }

        RequestParams params = new RequestParams();
        params.put("object_id", objectId);
        params.put("object_user_id", objectUserId);
        params.put("user_id", userId);
        params.put("comment", comment);

        client.post(url, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.i("Objects Comments Model", response.toString());
                presenter.requestSaveCommentApiResponse(response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                Log.i("Objects Comments Model", res);
            }
        });
    }
}
