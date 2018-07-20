package com.example.michel.lostandfoundufms.presenter;

import android.util.Log;
import android.widget.Toast;

import com.example.michel.lostandfoundufms.interfaces.ObjectCommentsInterface;
import com.example.michel.lostandfoundufms.model.CommentItem;
import com.example.michel.lostandfoundufms.model.ObjectCommentsModel;
import com.example.michel.lostandfoundufms.model.ObjectItem;
import com.example.michel.lostandfoundufms.model.UserItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ObjectCommentsPresenter implements ObjectCommentsInterface.Presenter{

    private ObjectCommentsInterface.View view;
    private ObjectCommentsInterface.Model model;

    public ObjectCommentsPresenter(ObjectCommentsInterface.View view) {
        this.view = view;
        model = new ObjectCommentsModel(this);
    }

    public ObjectCommentsPresenter() {}

    @Override
    public void requestObjectComments(String objectId) {
        model.requestObjectCommentsApi(objectId);
    }

    @Override
    public void requestObjectCommentsApiRestponse(JSONObject response) {
        List<CommentItem> commentItemList = new ArrayList<>();
        try {
            JSONArray CommentsArray = response.getJSONArray("data");
            for (int i = 0; i < CommentsArray.length(); i++){
                JSONObject jsonComment = CommentsArray.getJSONObject(i);

                JSONObject jsonUser = jsonComment.getJSONObject("user");
                UserItem userItem = new UserItem(
                        jsonUser.getInt("id"),
                        jsonUser.getString("username"),
                        jsonUser.getString("email"),
                        jsonUser.getString("created"),
                        jsonUser.getString("modified")
                );

                JSONObject jsonObject = jsonComment.getJSONObject("object");
                ObjectItem objectItem = new ObjectItem(
                        jsonObject.getInt("id"),
                        jsonObject.getInt("user_id"),
                        jsonObject.getString("title"),
                        jsonObject.getString("solved"),
                        jsonObject.getString("type"),
                        jsonObject.getString("date"),
                        jsonObject.getString("image"),
                        jsonObject.getString("description"),
                        jsonObject.getString("created"),
                        jsonObject.getString("modified")
                );

                CommentItem commentItem = new CommentItem(
                        jsonComment.getInt("id"),
                        jsonComment.getInt("object_id"),
                        jsonComment.getInt("user_id"),
                        jsonComment.getString("comment"),
                        jsonComment.getString("created"),
                        jsonComment.getString("modified"),
                        userItem,
                        objectItem
                );

                commentItemList.add(commentItem);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        view.loadObjectComments(commentItemList);
    }

    @Override
    public void requestSaveComment(String objectId, String objectUserId, String userId ,String comment) {
        model.requestSaveCommentApi(objectId, objectUserId, userId, comment);
    }

    @Override
    public void requestSaveCommentApiResponse(JSONObject response) {
        try {
            String status = response.getString("status");
            String message = response.getString("message");

            if(status.equals("success")){
                view.commentSaveSuccess(message);
            }else{
                view.commentSaveError(message);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
