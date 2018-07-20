package com.example.michel.lostandfoundufms.presenter;

import com.example.michel.lostandfoundufms.interfaces.ReceivedCommentsInterface;
import com.example.michel.lostandfoundufms.model.CommentItem;
import com.example.michel.lostandfoundufms.model.ObjectItem;
import com.example.michel.lostandfoundufms.model.ReceivedCommentsModel;
import com.example.michel.lostandfoundufms.model.UserItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ReceivedCommentsPresenter implements ReceivedCommentsInterface.Presenter{

    private ReceivedCommentsInterface.View view;
    private ReceivedCommentsInterface.Model model;

    public ReceivedCommentsPresenter(ReceivedCommentsInterface.View view) {
        this.view = view;
        model = new ReceivedCommentsModel(this);
    }

    @Override
    public void requestReceivedComments(int userId) {
        model.requestReceivedCommentsApi(userId);
    }

    @Override
    public void requestReceivedCommentsApiResponse(JSONObject response) {
        List<CommentItem> commentItemList = new ArrayList<>();
        try {
            JSONArray objectsArray = response.getJSONArray("data");
            for (int i = 0; i < objectsArray.length(); i++){
                JSONObject jsonComment = objectsArray.getJSONObject(i);

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
        view.loadReceivedComments(commentItemList);
    }
}
