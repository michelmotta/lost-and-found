package com.example.michel.lostandfoundufms.interfaces;

import com.example.michel.lostandfoundufms.model.CommentItem;

import org.json.JSONObject;

import java.util.List;

public interface ReceivedCommentsInterface {
    interface View{
        void loadReceivedComments(List<CommentItem> commentItemList);
    }
    interface Presenter{
        void requestReceivedComments(int userId);
        void requestReceivedCommentsApiResponse(JSONObject response);
    }
    interface Model{
        void requestReceivedCommentsApi(int userId);
    }
}
